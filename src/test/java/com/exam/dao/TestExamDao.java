package com.exam.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;

public class TestExamDao {
	@Test
	public void testInsert(){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamPaperDao examPaperDao = (ExamPaperDao) applicationContext.getBean("examPaperDao");
		ExamQuestionDao examQuestionDao = (ExamQuestionDao) applicationContext.getBean("examQuestionDao");
		
//		ExamPaper examPaper = new ExamPaper();
//		examPaper.setTitle("练习系统");
//		examPaper.setStartTime(System.currentTimeMillis());
//		examPaper.setEndTime(System.currentTimeMillis());
//		examPaper.setPassword("12");
//		examPaper.setPractice(1);
		int id = 129;
		int qid = 18;
		ExamQuestion examQuestion = new ExamQuestion();
		examQuestion.setEid(id);
		examQuestion.setQid(qid);
		System.out.println(examQuestion);
		examQuestionDao.insert(examQuestion);
		
//		System.out.println(examQuestionDao.getId());
	}
}
