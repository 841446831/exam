package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.entity.ExamPaper;
import com.exam.entity.Question;
import com.exam.service.ExamPaperService;
import com.exam.service.QuestionService;

import ch.qos.logback.classic.Logger;

@Controller
public class ExamPaperController {
	 private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Resource
	QuestionService questionService;
	@Resource
	ExamPaperService exampaperService;
	
	@RequestMapping(value="makepaper",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getExamPaper(String tag,int diffculty,int count){
		logger.info("makepaper,param="+tag+","+diffculty+","+count);
		List<Integer> tags = null;
		if (tag.indexOf(',')>=0){
			tags = new ArrayList<>();
			for (String t:tag.split(",")){
				tags.add(Integer.valueOf(t));
			}
		}
		List<Question> questions = questionService.selectQuestions(tags, diffculty, count);
		ExamPaper examPaper = new ExamPaper();
		examPaper.setPractice(1);
		examPaper.setTitle("练习");
		examPaper.setQuestions(questions);
		exampaperService.insert(examPaper);
		logger.info(JSON.toJSONString(examPaper));
		return  "callback("+JSON.toJSONString(examPaper) +");";
	}
	
	
	
	@RequestMapping("practice")
	public String getExamPaper(String tag,int diffculty,int count,Model model){
		model.addAttribute("tag", tag);
		model.addAttribute("diffculty", diffculty);
		model.addAttribute("count", count);
		return "practice.jsp";
	}
	
}
