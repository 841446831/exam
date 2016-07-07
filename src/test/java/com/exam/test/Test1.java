package com.exam.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.service.QuestionService;
public class Test1 {

	@Test
	public void testInsertQuestion() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		
		Question question = new Question(); 
		Option option = null;
		
//		question.setId(2);
		question.setFace("题面2");
		question.setAnswer("答案2");
		question.setLevel(1);
		question.setTid(569);
		
		for(int i = 1; i <= 4;i++)
		{
			option = new Option();
			option.setId(i + 4);
			option.setQid(1);
			option.setIsTrue(true);
			option.setTitle("选项内容" + i);
			option.setTutorial("题目解析" + i);
			question.getOptions().add(option);
		}
		
		questionService.insertQuestion(question);
		
	}

	@Test
	public void testSelectQuestionByTypeAndLevel()
	{
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		
		List<Question> list = questionService.selectQuestionByTypeAndLevel(1);
		
		//System.out.println(list.get(0).getOptions().size());
		
		for(Question q : list)
		{
			System.out.println(q.toString());
		}
	}

	@Test
	public void testSelectQuestionByFace(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		Question question = questionService.selectById(18);
		System.out.println(questionService.countQuestionByFace(question.getFace()));
	}
}
