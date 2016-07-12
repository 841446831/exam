package com.exam.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
