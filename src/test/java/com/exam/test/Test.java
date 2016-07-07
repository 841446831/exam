package com.exam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.entity.Type;
import com.exam.service.PersonService;
import com.exam.service.TypeService;


public class Test {
	public static void main(String... args){
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		TypeService typeService = (TypeService) applicationContext.getBean("typeService");
		Type type = new Type(){{
			setSuperType("编程语言");
			setType("C++");
		}};
		typeService.insertType(type);
		System.out.println(typeService.SelectAll());
	}
}
