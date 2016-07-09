package com.exam.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.exam.entity.Question;
import com.exam.service.ExamQuestionService;
import com.exam.service.QuestionService;

public class testSelectQuestionByEid {
    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
	    
		//System.out.println(questionService.selectQuestionByEid(134));
		
		List<Question> questions = questionService.selectQuestionByEid(134);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("questions",questions);
		
		String json = JSON.toJSONString(map);
		
		System.out.println(json);
		
	}
}
