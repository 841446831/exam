package com.exam.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.entity.Question;
import com.exam.service.QuestionService;

@Controller
public class ExamPaperController {
	@Resource
	QuestionService questionService;
	
	@RequestMapping("/api/exampaper")
	@ResponseBody
	public String getExamPaper(List<Integer> tags,@RequestParam(defaultValue="3")int diffculty,@RequestParam(defaultValue="3") int count){
		List<Question> questions = questionService.selectQuestions(tags,diffculty,count);
		return JSON.toJSONString(questions);
	}
}
