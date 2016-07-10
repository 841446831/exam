package com.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@RequestMapping(value = "getQuestionsAnswer",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getQuestionsAnswer(@RequestParam("eid") int eid,HttpServletRequest request)
	{
		
		List<Integer> listQid = examQuestionService.selectQidByEid(eid);
		List<Map<Integer,Object>> listSelect = new ArrayList<>();
		Map<Integer, Object> map = null;
	 
		for(Integer qid : listQid)
		{
			map = new HashMap<Integer,Object>();
			String[] userSelect = request.getParameterValues(qid.toString());
			map.put(qid, userSelect);
		    listSelect.add(map);
		}
		
		String json = examQuestionService.getQuestionsAnswer(eid, listSelect);
		
		return json;
	}
}
