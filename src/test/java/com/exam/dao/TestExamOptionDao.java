package com.exam.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) 
@ContextConfiguration("classpath:spring/applicationContext.xml") 
public class TestExamOptionDao {
	@Resource
	private ExamOptionDao examOptionDao;
	@Test
	public void testCountByEid(){
		System.out.println(examOptionDao.selectCountByEidAndUid(358, 2));
	}
}
