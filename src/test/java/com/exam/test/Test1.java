package com.exam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.entity.Type;
import com.exam.service.QuestionService;
import com.exam.service.TypeService;
import com.mysql.fabric.xmlrpc.base.Array;

public class Test1 {

	@Test
	public void testInsertQuestion() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		
		Question question = new Question(); 
		List<Option> list = new ArrayList<>();
		Option option = null;
		
		question.setId(2);
		question.setFace("题面2");
		question.setAnswer("答案2");
		question.setLevel(1);
		question.setTid(569);
		
		for(int i = 1; i <= 4;i++)
		{
			option = new Option();
			option.setId(i + 4);
			option.setQid(1);
			option.setIsTrue(0);
			option.setTitle("选项内容" + i);
			option.setTutorial("题目解析" + i);
			list.add(option);
		}
		
		for(Option o : list)
		{
			System.out.println(o.toString());
		}
		
		if(questionService.insertQuestion(question, list) == 1)
		{
			System.out.println("添加成功");
		}
		
	}

	@Test
	public void testSelectQuestionByTypeAndLevel()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		
		List<Question> list = questionService.selectQuestionByTypeAndLevel(1);
		
		//System.out.println(list.get(0).getOptions().size());
		
		for(Question q : list)
		{
			System.out.println(q.toString());
		}
	}
	
}
