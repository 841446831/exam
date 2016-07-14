package com.exam.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exam.dao.ExamPaperDao;
import com.exam.dao.ExamQuestionDao;
import com.exam.dao.UserDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;
import com.exam.entity.Question;
import com.exam.util.Constant;
import com.exam.util.ResultHelper;

@Service
public class ExamPaperService {
	@Resource
	private ExamPaperDao examPaperDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	
	@Resource
	private UserDao userDao;
	
	public int insert(ExamPaper examPaper){
		examPaperDao.insert(examPaper);
		for (Question question:examPaper.getQuestions()){
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setEid(examPaper.getId());
			examQuestion.setQid(question.getId());
			examQuestionDao.insert(examQuestion);
		}
		return 0;
	}
	
	public int insert(String qidString,ExamPaper examPaper)
	{
        
		if(qidString != null && !qidString.equals(""))
		{
			examPaperDao.insert(examPaper);
			if(qidString.indexOf(",")>=0)
			{
				String[] qids = qidString.split(",");
				for(int i = 0 ; i < qids.length;i++)
				{
					ExamQuestion examQuestion = new ExamQuestion();
					examQuestion.setEid(examPaper.getId());
					examQuestion.setQid(Integer.valueOf(qids[i]));
					examQuestionDao.insert(examQuestion);
				}
			}
			else
			{
				int qid = Integer.valueOf(qidString);
				ExamQuestion examQuestion = new ExamQuestion();
				examQuestion.setEid(examPaper.getId());
				examQuestion.setQid(Integer.valueOf(qid));
				examQuestionDao.insert(examQuestion);
			}
			
		}
      
		return 0;
	}
	
	public ResultHelper selectByPractice(ExamPaper examPaper)
	{
		List<ExamPaper> examPapers = examPaperDao.selectByPractice(examPaper);
		//List<ExamPaper> list = new ArrayList<ExamPaper>();
		List<Object> list = new ArrayList<>();
		
		for(ExamPaper e: examPapers)
		{
		    long startTime =  e.getStartTime();
		    long endTime = e.getEndTime();
		    long currentTime = System.currentTimeMillis();
		    
		    if(currentTime >= startTime && currentTime <= endTime)
		    {
		    	JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(e));
		    
		    	String username = userDao.selectUserNameByUid(e.getUid());
		    	
		    	jsonObject.put("username",username);
		    	
		    	list.add(jsonObject);
		    }
		  
		}
		return new ResultHelper(list,examPaperDao.selectAll().size(),Constant.SUCCESS_CODE,Constant.SUCCESS_MSG);
	}
	
	
}

