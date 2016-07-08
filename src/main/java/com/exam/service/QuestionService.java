package com.exam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.OptionDao;
import com.exam.dao.QuestionDao;
import com.exam.entity.Option;
import com.exam.entity.Question;

@Service
public class QuestionService {
    @Resource
    private QuestionDao questionDao;
    @Resource
    private OptionDao optionDao;
    
    public int insertQuestion(Question question)
    {
    	questionDao.insertQuestion(question);
    	for (Option option:question.getOptions()){
    		option.setQid(question.getId());
    		optionDao.insertOption(option);
    	}
        return 1;
    }
    
    public List<Question> selectQuestionByTypeAndLevel(int level)
    {
    	List<Question> list = questionDao.selectQuestionByTypeAndLevel(level);
    	
    	return list;
    }


	public int countQuestionByFace(String face) {
		// TODO Auto-generated method stub
		
		return questionDao.selectQuestionsByFace(face).size();
	}

	public Question selectById(int i) {
		// TODO Auto-generated method stub
		return questionDao.selectById(i);
	}

	
	public List<Question> selectQuestions(int tag_id, int diffculty, int count) {
		// TODO Auto-generated method stub
		List<Question> questions = questionDao.selectQuestions(tag_id,diffculty,count);
		for (Question question:questions){
			question.setOptions(optionDao.selectByTid(question.getId()));
		}
		return questions;
	}
    
}
