package com.exam.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.dao.OptionDao;
import com.exam.dao.UserDao;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.entity.User;
import com.exam.service.QuestionService;
import com.exam.service.TypeService;


public class Test {
	public static void main(String... args){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		TypeService typeService = (TypeService) applicationContext.getBean("typeService");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		OptionDao optionDao = (OptionDao)applicationContext.getBean("optionDao");
		UserDao userDao = (UserDao)applicationContext.getBean("userDao");
		User admin = userDao.selectById(1);
		List<Question> questions = questionService.selectAll();
		int radioCount = 0;
		for (Question question:questions){
			List<com.exam.entity.Option> options = optionDao.selectByQid(question.getId());
			int cnt = 0;
			for (Option option:options){
				if (option.getIsTrue()==1){
					cnt++;
				}
			}
			if (cnt == 1){
				radioCount++;
				question.setIsRadio(1);
			}else{
				question.setIsRadio(0);
			}
			
			question.setIsPublic(1);
			question.setUser(admin);
//			更新
//			questionService.update(question);
		}

		System.out.println(radioCount);
	}
}
