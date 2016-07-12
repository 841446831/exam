package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.dao.QuestionDao;
import com.exam.service.ExamQuestionService;

public class selectQuestionsAnswer {
	
	
    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    	QuestionDao questionDao= (QuestionDao) applicationContext.getBean("questionDao");
    	ExamQuestionService examQuestionService =  (ExamQuestionService) applicationContext.getBean("examQuestionService");
    	
    	System.out.println(examQuestionService.selectQuestionsAnswer(147));
    	
	}

}
