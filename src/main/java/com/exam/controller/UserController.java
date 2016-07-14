package com.exam.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.entity.User;
import com.exam.service.UserService;
import com.exam.util.FileUpload;
import com.exam.util.Identicon;

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
				userService.insertUser(user);
			    Identicon identicon = new Identicon();
			    String webRoot = request.getSession().getServletContext().getRealPath("/");
			    String filePath = webRoot + user.getId() + ".jpg";
			    System.out.println(webRoot);
			    File file = new File(filePath);
			    try {
					ImageIO.write( identicon.genarate(user.getId()),"jpg",file);
					FileUpload fileUpload = new FileUpload(filePath,user.getId()+".jpg");
					fileUpload.upload();
					if(file.exists())
					{
						file.delete();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				login(user,request.getSession());
				map.put("code", 0);
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
	public String login(User user,HttpSession session){

		HashMap<String, Object> map = new HashMap<>();
		if (session.getAttribute("user") !=null){
			User u = (User) session.getAttribute("user");
			map.put("msg", "登录成功");
			map.put("code",0);
			map.put("user", u);
			return JSON.toJSONString(map);
		}
		
		User temp = userService.selectByEmail(user.getEmail());
		if (temp==null){
			temp = userService.selectByUsername(user.getUsername());
		}
		if (temp!=null){
			if (temp.getPassword().equals(user.getPassword())){
				map.put("msg", "登录成功");
				map.put("code",0);
				map.put("user", temp);
				//持久化session
				session.setAttribute("user", temp);
				System.out.println("当前session为"+temp);
			}else{
				map.put("msg", "用户名密码不匹配");
			}
		}else{
			map.put("msg", "用户名不存在");
		}
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("users")
	public String getAllUsers(Model model){
		model.addAttribute("users",userService.selectAll());
		return "users";
	}
	
	@RequestMapping("user")
	public String getUser(Model model){
		return "user";
	}
	
	@RequestMapping(value="selctUserPager", produces="application/json;charset=utf-8")
	@ResponseBody
	public String selectUserPager(User user)
	{
		return JSON.toJSONString(userService.selectUserPager(user));
	}
	
	
	
}
