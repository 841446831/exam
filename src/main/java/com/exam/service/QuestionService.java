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

	
	public List<Question> selectQuestions(List<Integer> tags, int diffculty, int count) {
		// TODO Auto-generated method stub
		if (tags!=null && tags.size()==0) tags = null;
		List<Question> questions = questionDao.selectQuestions(tags,diffculty,count);
		for (Question question:questions){
			question.setOptions(optionDao.selectByTid(question.getId()));
			int cnt = 0;
			for (Option option:question.getOptions()){
				if (option.getIsTrue()==1){
					cnt++;
					option.setIsTrue(0);
				}
			}
			if (cnt==1){
				question.setIsRadio(1);
			}
//			question.setOptions(optionDao.selectByTid(question.getId())); 
		}
		return questions;
	}
	
	public int typeOfQuestionCount(int tid)
	{
		return questionDao.typeOfQuestionCount(tid);
	}

	public List<Question> selectAll() {
		return questionDao.selectAll();
	}

	public int update(Question question) {
		// TODO Auto-generated method stub
		return questionDao.update(question);
	}
    
}
