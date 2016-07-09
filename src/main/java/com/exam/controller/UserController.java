package com.exam.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.entity.User;
import com.exam.service.UserService;
import com.sun.javafx.collections.MappingChange.Map;

@Controller
public class UserController {
	@Resource
	private UserService UserService;
	
	@RequestMapping(value="regist")
	@ResponseBody
	public String regist(User user,HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<>();
		if (UserService.selectByUsername(user.getUsername())==null){
		
		}
		return user.toString();
	}
}
