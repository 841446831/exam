package com.exam.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exam.dao.ExamPaperDao;
import com.exam.dao.ExamQuestionDao;
import com.exam.dao.OptionDao;
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
	QuestionService questionService;
	@Resource
	private ExamQuestionDao examQuestionDao;
	@Resource
	private OptionDao optionDao;
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
	
	public ResultHelper selectByCurrentTime(ExamPaper examPaper)
	{
		long currentTime = System.currentTimeMillis();
		List<ExamPaper> examPapers = examPaperDao.selectByCurrentTime(currentTime, examPaper);
		List<Object> list = new ArrayList<>();
		for(ExamPaper e: examPapers)
		{
		    
	        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(e));
		    
		    String username = userDao.selectUserNameByUid(e.getUid());
		    	
		    jsonObject.put("username",username);
		    	
		    list.add(jsonObject); 
		   
		}
		return new ResultHelper(list,examPaperDao.selectCountByCurrentTime(currentTime),Constant.SUCCESS_CODE,Constant.SUCCESS_MSG);
	}

	public ExamPaper selectById(int id) {
		ExamPaper examPaper = examPaperDao.selectById(id);
		List<ExamQuestion> examQuestions = examQuestionDao.selectByEid(id);
		List<Question> questions = new ArrayList<>();
		for (ExamQuestion examQuestion:examQuestions){
			Question question = questionService.selectById(examQuestion.getQid());
			question.setOptions(optionDao.selectByTidWithOutAnswer(question.getId()));
			questions.add(question);
		}
		examPaper.setQuestions(questions);
		return examPaper;
	}
	
}

