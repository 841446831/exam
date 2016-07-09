package com.exam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.ExamQuestionDao;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.Option;
import com.exam.entity.Question;

@Service
public class ExamQuestionService {
    
	@Resource
	private ExamQuestionDao examQuestionDao;

	@Resource
	private QuestionDao questionDao;
	
	public ExamPaper selectExamPaperByEid(int eid)
	{
		return examQuestionDao.selectExampPaperByEid(eid);
	}
	
	public List<Integer> selectQidByEid(int eid)
	{
		return examQuestionDao.selectQidByEid(eid);
	}
	
	public String getQuestionsAnswer(int eid,String answer)
	{
		List<Question> questions = questionDao.selectQuestionByEid(eid);
		
		ExamPaper examPaper = examQuestionDao.selectExampPaperByEid(eid);
		
		
		
	    return null;
	}
	
}
