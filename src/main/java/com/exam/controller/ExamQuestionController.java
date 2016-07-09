package com.exam.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.dao.ExamQuestionDao;
import com.exam.service.ExamPaperService;
import com.exam.service.ExamQuestionService;
import com.exam.service.OptionService;

@Controller
public class ExamQuestionController {
    
	@Resource
	private ExamPaperService examPaperService;
	
	@Resource
	private ExamQuestionService examQuestionService;
	
	@Resource
	private OptionService optionService;
	
	@RequestMapping("getQuestionsAnswer")
	@ResponseBody
	public String getQuestionsAnswer(@RequestParam("eid") int eid,HttpServletRequest request)
	{
		List<Integer> listQid = examQuestionService.selectQidByEid(eid);
		
		for(Integer qid : listQid)
		{
			int trueAnswerConut = optionService.selectTrueAnswer(qid);
			if(trueAnswerConut == 1)
			{
				String userSelect = request.getParameter(qid.toString());
			}
			
			//request.getParameter(qid)
		}
		
		return "";
	}
}
