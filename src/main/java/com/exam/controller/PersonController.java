package com.exam.controller;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;

import com.exam.service.PersonService;

@Controller
public class PersonController {
	@Resource
	private PersonService personService;
	private Logger logger = (Logger) LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping("persons")
	public String selectAll(Model model){
		logger.info("获取全部用户列表");
		model.addAttribute("persons", personService.selectAll());
		logger.info(personService.selectAll().toString());
		return "WEB-INF/persons.jsp";
	}
}
