package com.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.entity.User;
import com.exam.service.QuestionService;

@Controller
public class QuestionController {
	@Resource
	private QuestionService questionService;
	@RequestMapping("questions")
	public String getAllQuestions(Model model){
		model.addAttribute("questions", questionService.selectAll());
		return "questions";
	}
    
	@RequestMapping(value="selectQuestionByType",produces="application/json;charset=utf-8")
	@ResponseBody
	public String selectQuestionByType(@RequestParam("word") String word,Question question)
	{
		String json = JSON.toJSONString(questionService.selectQuestionByType(word,question));		
		return json;
	}	
	
	@RequestMapping(value="selectQuestionByFace",produces="application/json;charset=utf-8")
	@ResponseBody
	public String selectQuestionByFace(@RequestParam("word") String word,Question question)
	{
		String json = JSON.toJSONString(questionService.selectQuestionByFace(word,question));
		return json;
	}
	
	@RequestMapping(value="addQuestion")
	@ResponseBody
	public String addQuestion(String questionJson,HttpServletRequest request){
		Question question = JSON.toJavaObject(JSON.parseObject(questionJson), Question.class);
		Map<String, Object> map = new HashMap<String, Object>();
		Object user = request.getSession().getAttribute("user");
		if (user!=null){
			question.setUser( (User) user);
			question.setLevel(1);
			questionService.insertQuestion(question);	
			map.put("code", 0);
		}else{
			map.put("code", 1);
			map.put("msg", "用户未登录");
		}
		return JSON.toJSONString(map);
	}
}
