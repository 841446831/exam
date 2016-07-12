package com.exam.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.ExamPaperDao;
import com.exam.dao.ExamQuestionDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.ExamQuestion;
import com.exam.entity.Question;

@Service
public class ExamPaperService {
	@Resource
	private ExamPaperDao examPaperDao;
	@Resource
	private ExamQuestionDao examQuestionDao;
	
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
	
	public int insert(String qidString,double mark,ExamPaper examPaper)
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
					examQuestion.setMark(mark);
					examQuestionDao.insert(examQuestion);
				}
			}
			else
			{
				int qid = Integer.valueOf(qidString);
				ExamQuestion examQuestion = new ExamQuestion();
				examQuestion.setEid(examPaper.getId());
				examQuestion.setQid(Integer.valueOf(qid));
				examQuestion.setMark(mark);
				examQuestionDao.insert(examQuestion);
			}
			
		}
      
		return 0;
	}
	
	public List<ExamPaper> selectByPractice(int practice)
	{
		List<ExamPaper> examPapers = examPaperDao.selectByPractice(practice);
		List<ExamPaper> list = new ArrayList<ExamPaper>();
		
		for(ExamPaper examPaper: examPapers)
		{
		    long startTime =  examPaper.getStartTime();
		    long endTime = examPaper.getEndTime();
		    long currentTime = System.currentTimeMillis();
		    
		    if(currentTime >= startTime && currentTime <= endTime)
		    {
		    	list.add(examPaper);
		    }
		  
		}
		
		return list;
	}
	
}

