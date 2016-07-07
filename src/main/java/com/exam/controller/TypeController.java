package com.exam.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.service.TypeService;

@Controller
public class TypeController {

	@Resource
 	private TypeService typeService;
 	
	@RequestMapping("addType")
	public String addType()
	{
	    
		return "WEB-INF/jsp/index.jsp";
	}
	
}
