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
    
    public int insertQuestion(Question question,List<Option> list)
    {
        if(questionDao.insertQuestion(question) == 1)
        {
        	for(Option o : list)
        	{
        		int i = optionDao.insertOption(o);
        		if(0 == i)
        		{
        		    return 0;
        		}
        	}
        	return 1;
        }
        
        return 0;
        
    }
    
    public List<Question> selectQuestionByTypeAndLevel(int level)
    {
    	List<Question> list = questionDao.selectQuestionByTypeAndLevel(level);
    	
    	return list;
    }
    
    
}
