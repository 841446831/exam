package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.entity.ExamPaper;
import com.exam.entity.Question;
import com.exam.entity.User;
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
	public String getExamPaper(String tag,@RequestParam(defaultValue="1")int diffculty,@RequestParam(defaultValue="5")int count){
		List<Integer> tags = new ArrayList<>();
		if (tag!=null && !tag.equals(""))
			if (tag.indexOf(',')>=0){
				tags = new ArrayList<>();
				for (String t:tag.split(",")){
					tags.add(Integer.valueOf(t));
				}
			}else{
				tags.add(Integer.valueOf(tag));
			}
		List<Question> questions = questionService.selectQuestions(tags, diffculty, count);
		ExamPaper examPaper = new ExamPaper();
		examPaper.setPractice(1);
		examPaper.setTitle("练习");
		examPaper.setQuestions(questions);
		exampaperService.insert(examPaper);
		return  "callback("+JSON.toJSONString(examPaper) +");";
	}
	
	@RequestMapping("practice")
	public String getExamPaperRedirect(String tag,int diffculty,int count,Model model){
		model.addAttribute("tag", tag);
		model.addAttribute("diffculty", diffculty);
		model.addAttribute("count", count);
		return "practice";
	}
	
	@RequestMapping("insertPaper")
	public String insertPaper(@RequestParam("qidString") String qidString,@RequestParam("mark") double mark,ExamPaper examPaper,HttpSession session)
	{
		examPaper.setUid(((User)session.getAttribute("user")).getId());
		
	    exampaperService.insert(qidString,mark,examPaper);
		
		return null;
	}
	
	@RequestMapping("selectNowExam")
	public String selectNowExam(){
		
		return JSON.toJSONString(exampaperService.selectByPractice(0));
		
	}
	
}
