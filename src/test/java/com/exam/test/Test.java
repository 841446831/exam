package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.service.TypeService;


public class Test {
	public static void main(String... args){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		TypeService typeService = (TypeService) applicationContext.getBean("typeService");
		//		typeService.insertType(type);
		System.out.println(typeService.SelectAll());
	}
}
