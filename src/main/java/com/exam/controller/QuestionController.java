package com.exam.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.entity.Question;
import com.exam.entity.User;
import com.exam.service.QuestionService;

@Controller
public class QuestionController {
    
	@Resource
	QuestionService questionService;
	
	@RequestMapping(value="selectQuestionByType",produces="application/json;charset=utf-8")
	@ResponseBody
	public String selectQuestionByType(@RequestParam("word") String word,Question question)
	{
		String json = JSON.toJSONString(questionService.selectQuestionByType(word,question));		
		return json;
	}
	
	public String selectQuestionByFace(@RequestParam("word") String word,Question question)
	{
		String json = JSON.toJSONString(questionService.selectQuestionByFace(word,question));
		return json;
	}
	
}
