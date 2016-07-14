package com.exam.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml") 

public class TestExamDao {
	@Resource
	private UserDao userDao;
	@Test
	public void testInsert(){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamPaperDao examPaperDao = (ExamPaperDao) applicationContext.getBean("examPaperDao");
		
		ExamPaper examPaper = new ExamPaper();
		examPaper.setTitle("练习系统");
		examPaper.setStartTime(System.currentTimeMillis());
		examPaper.setEndTime(System.currentTimeMillis());
		examPaper.setPassword("12");
		examPaper.setPractice(1);
		examPaper.setUid(1);
		examPaperDao.insert(examPaper);
	}
}
