package com.exam.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.ExamPaperDao;
import com.exam.dao.ExamQuestionDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;

@Service
public class ExamPaperService {
	@Resource
	private ExamPaperDao examPaperDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	
	public int insert(ExamPaper examPaper){
		int id = examPaperDao.insert(examPaper);
		for (ExamQuestion examQuestion:examPaper.getQuestions()){
			examQuestion.setEid(id);
			examQuestionDao.insert(examQuestion);
		}
		return 0;
	}
}

