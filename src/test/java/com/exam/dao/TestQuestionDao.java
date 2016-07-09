package com.exam.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.Question;
import com.exam.service.QuestionService;

public class TestQuestionDao {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	QuestionDao questionDao = (QuestionDao)applicationContext.getBean("questionDao");
	QuestionService questionService=(QuestionService)applicationContext.getBean("questionService");
	

	
	@Test
	public void testSelectQuestion(){
		List<Integer> tags = new ArrayList<Integer>();
//		tags.add(583);
		List<Question> list = questionService.selectQuestions(tags, 2,3);
		System.out.println(list);
	}
	
	@Test
	public void testUpdate() {
		Question question = questionDao.selectById(18);
		System.out.println(question);
//		question.setIsRadio(1);
//		questionDao.update(question);
//		Question questionAfterUpdate = questionDao.selectById(18);
//		System.out.println(questionAfterUpdate);
	}
}
