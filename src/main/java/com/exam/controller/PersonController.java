package com.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

import com.alibaba.fastjson.JSON;
import com.exam.entity.Person;
import com.exam.service.PersonService;

@Controller
public class PersonController {
	@Resource
	private PersonService personService;
	private Logger logger = (Logger) LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping("persons")
	@ResponseBody
	public String selectAll(Model model){
		
		Map<String,Object> list = new HashMap<>();
		List<Person> listPeron = new ArrayList<>();
		Person person = new Person();
		person.setFirstName("fdff");
		person.setLastName("fdfd");
		listPeron.add(person);
		person = new Person();
		person.setFirstName("12");
		person.setLastName("4564");
		listPeron.add(person);
		list.put("list", listPeron);
		return JSON.toJSONString(listPeron);
	}
}
