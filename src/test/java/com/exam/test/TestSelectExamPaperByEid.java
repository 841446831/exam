package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.service.ExamQuestionService;

public class TestSelectExamPaperByEid {
    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamQuestionService examQuestionService = (ExamQuestionService) applicationContext.getBean("examQuestionService");
	    
		System.out.println(examQuestionService.selectExamPaperByEid(154));
    }
}
