package com.exam.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.Question;
import com.exam.service.QuestionService;

public class TestQuestionDao {
	@Test
	public void testSelectQuestion(){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionDao questionDao = (QuestionDao)applicationContext.getBean("questionDao");
		QuestionService questionService=(QuestionService)applicationContext.getBean("questionService");
		List<Integer> tags = new ArrayList<Integer>();
//		tags.add(583);
		List<Question> list = questionService.selectQuestions(tags, 2,3);
		System.out.println(list);
	}
}
