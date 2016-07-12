package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.service.ExamPaperService;
import com.exam.service.ExamQuestionService;

public class TestSelectNowExamPaper {
    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamPaperService examPaperService = (ExamPaperService) applicationContext.getBean("examPaperService");
	    
		System.out.println(examPaperService.selectByPractice(0));
		
	}
}
