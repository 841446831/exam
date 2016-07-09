package com.exam.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.ExamPaperDao;
import com.exam.dao.ExamQuestionDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;
import com.exam.entity.Question;

@Service
public class ExamPaperService {
	@Resource
	private ExamPaperDao examPaperDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	
	public int insert(ExamPaper examPaper){
		examPaperDao.insert(examPaper);
		for (Question question:examPaper.getQuestions()){
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setEid(examPaper.getId());
			examQuestion.setQid(question.getId());
			examQuestionDao.insert(examQuestion);
		}
		return 0;
	}
}

