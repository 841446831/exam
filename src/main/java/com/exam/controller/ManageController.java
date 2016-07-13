package com.exam.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.entity.User;
import com.exam.service.UserService;

@Controller
public class ManageController {
	@Resource
	private UserService userService;
	@RequestMapping("manage")
	public String manage(HttpServletRequest request){
		//管理员用户
		request.getSession().setAttribute("user", userService.selectByUsername("cai"));
		return "manage";
	}
}
