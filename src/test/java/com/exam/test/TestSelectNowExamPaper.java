package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.ExamPaper;
import com.exam.service.ExamPaperService;
import com.exam.service.ExamQuestionService;

public class TestSelectNowExamPaper {
    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamPaperService examPaperService = (ExamPaperService) applicationContext.getBean("examPaperService");
	    
		ExamPaper examPaper = new ExamPaper();
		
		
		examPaper.setLimit(3);
		examPaper.setStart(0);
		
		System.out.println(examPaperService.selectByPractice(examPaper));
		
	}
}
