package com.exam.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.entity.User;
import com.exam.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="regist", produces="application/json;charset=utf-8")
	@ResponseBody
	public String regist(User user,HttpServletRequest request){
		HashMap<String, Object> map = new HashMap<>();
		User tempUser = userService.selectByUsername(user.getUsername());

		if (userService.selectByUsername(user.getUsername()) ==null ){
			if (userService.selectByEmail(user.getEmail())==null){
				map.put("msg", "注册成功");		
//				userService.insert(user);
//				login(user);
			}else{
				map.put("msg", "邮箱被注册");
			}
		}else{
			map.put("msg", "用户名已经被注册");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="login", produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(User user){
		HashMap<String, Object> map = new HashMap<>();
		User temp = userService.selectByEmail(user.getEmail());
		if (temp==null){
			temp = userService.selectByUsername(user.getUsername());
		}
		if (temp!=null){
			if (temp.getPassword().equals(user.getPassword())){
				map.put("msg", "登录成功");
				//持久化session
			}else{
				map.put("msg", "用户名密码不匹配");
			}
		}else{
			map.put("msg", "用户名不存在");
		}
		return JSON.toJSONString(map);
	}
}
