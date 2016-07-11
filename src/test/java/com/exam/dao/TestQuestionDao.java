package com.exam.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.service.QuestionService;

@RunWith(SpringRunner.class) 
@ContextConfiguration("classpath:spring/applicationContext.xml") 

public class TestQuestionDao {
	@Resource
	QuestionDao questionDao;
	@Resource
	QuestionService questionService;
	
	@Test
	public void testSelectQuestion(){
		List<Integer> tags = new ArrayList<Integer>();
		tags.add(570);
		List<Question> list = questionService.selectQuestions(tags, 3,5);
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
	@Test
	public void testInsert(){
		Question question = new Question();
		question.setFace("你是不是傻");
		List<Option> options = new ArrayList<Option>();
		Option option = new Option();
		option.setSymbol("A");
		option.setTitle("是");
		options.add(option);
		question.setOptions(options);
		question.setLevel(1);
		question.setTid(570);
//		questionService.insertQuestion(question);
//		System.out.println(question);
	}
}
