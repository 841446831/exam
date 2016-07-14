package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.ExamPaper;
import com.exam.service.ExamPaperService;

public class TestInsertPaper {
    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    	ExamPaperService examPaperService= (ExamPaperService) applicationContext.getBean("examPaperService");
    	
    	String qidString = "22,24";
    	
    	ExamPaper examPaper = new ExamPaper();
    	examPaper.setPractice(0);
    	examPaper.setTitle("考试");
    	examPaper.setUid(1);
    	
    	examPaperService.insert(qidString, examPaper);
	}
}
