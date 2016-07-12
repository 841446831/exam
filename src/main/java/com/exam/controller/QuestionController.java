package com.exam.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.service.QuestionService;

@Controller
public class QuestionController {
    
	@Resource
	QuestionService questionService;
	
	@RequestMapping(value="selectQuestionByType",produces="application/json;charset=utf-8")
	@ResponseBody
	public String selectQuestionByType(@RequestParam("word") String word)
	{
		String json = JSON.toJSONString(questionService.selectQuestionByType(word));		
		return json;
	}
	
}
