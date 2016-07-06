package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.service.PersonService;


public class Test {
	public static void main(String... args){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//		PersonService personService = (PersonService) applicationContext.getBean("personService");
//		System.out.println(personService.selectAll());
	}
}
