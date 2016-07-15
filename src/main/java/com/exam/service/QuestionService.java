package com.exam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.OptionDao;
import com.exam.dao.QuestionDao;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.util.Constant;
import com.exam.util.ResultHelper;

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
    		System.out.println(question.getId());
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
		if (tags!=null && tags.size()==0) tags = null;
		List<Question> questions = questionDao.selectQuestions(tags,diffculty,count);
		for (Question question:questions){
			question.setOptions(optionDao.selectByTidWithOutAnswer(question.getId()));
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
    
	public List<Question> selectQuestionByEid(int eid)
	{
		return questionDao.selectQuestionByEid(eid);
	}
	
	public ResultHelper selectQuestionByType(String word,Question question)
	{
		StringBuilder wordSplit = null;
		List<Question> questions = null;
		
		if(word != null && !word.equals(""))
		{
			wordSplit = new StringBuilder("%");
			
			String[] singlewords = word.trim().split("");
			for(int i = 0; i < singlewords.length;i++)
			{
				if(singlewords[i].equals(" "))
				{
					continue;
				}
				wordSplit.append(singlewords[i]).append("%");
			}
			
		    questions = questionDao.selectQuestionByType(wordSplit.toString(),question);
			
		}
		else
		{
		    questions = questionDao.selectQuestionPager(question);	
		}
		
		return new ResultHelper(questions, selectQuestionCountByType(word),Constant.SUCCESS_CODE,Constant.SUCCESS_MSG);
  	}

	public ResultHelper selectQuestionByFace(String word,Question question)
	{
		StringBuilder wordSplit = new StringBuilder("%");
		String[] singlewords = word.trim().split("");
		for(int i = 0; i < singlewords.length;i++)
		{
			if(singlewords[i].equals(" "))
			{
				continue;
			}
			wordSplit.append(singlewords[i]).append("%");
		}
		//wordSplit.append("'");
		System.out.println(wordSplit);
		List<Question> questions = questionDao.selectQuestionByFace(wordSplit.toString(),question);
		return new ResultHelper(questions, selectQuestionCountByFace(word), Constant.SUCCESS_CODE,Constant.SUCCESS_MSG);
	}
	
	public int selectQuestionCountByFace(String word)
	{
		StringBuilder wordSplit = new StringBuilder("%");
		String[] singlewords = word.trim().split("");
		for(int i = 0; i < singlewords.length;i++)
		{
			if(singlewords[i].equals(" "))
			{
				continue;
			}
			wordSplit.append(singlewords[i]).append("%");
		}
		//wordSplit.append("'");
		return  questionDao.selectQuestionCountByFace(wordSplit.toString());
	
	}
	
	public int selectQuestionCountByType(String word)
	{
		StringBuilder wordSplit = null;
		
		if(word != null && !word.equals(""))
		{
			wordSplit = new StringBuilder("%");
			
			String[] singlewords = word.trim().split("");
			for(int i = 0; i < singlewords.length;i++)
			{
				if(singlewords[i].equals(" "))
				{
					continue;
				}
				wordSplit.append(singlewords[i]).append("%");
			}
			
		    return questionDao.selectQuestionCountByType(wordSplit.toString());
			
		}
		else
		{
		    return  questionDao.selectQuestionCount();
		}
	}

}
