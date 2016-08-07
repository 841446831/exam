package com.exam.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;
import com.exam.service.ExamQuestionService;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml") 

public class TestExamDao {
	@Resource
	private UserDao userDao;
	@Test
	public void testInsert(){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamQuestionService examQuestionService = (ExamQuestionService) applicationContext.getBean("examQuestionService");
		System.out.println(examQuestionService.selectQuestionsAnswer(448));
	}
}
