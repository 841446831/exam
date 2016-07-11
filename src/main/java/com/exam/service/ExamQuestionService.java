package com.exam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exam.dao.ExamQuestionDao;
import com.exam.dao.OptionDao;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.Option;
import com.exam.entity.Question;

@Service
public class ExamQuestionService {
    
	@Resource
	private ExamQuestionDao examQuestionDao;

	@Resource
	private QuestionDao questionDao;
	
	@Resource
	private OptionDao optionDao;
	
	public ExamPaper selectExamPaperByEid(int eid)
	{
		return examQuestionDao.selectExamPaperByEid(eid);
	}
	
	public List<Integer> selectQidByEid(int eid)
	{
		return examQuestionDao.selectQidByEid(eid);
	}
	
	public String getQuestionsAnswer(int eid,List<Map<Integer,Object>> listSelect)
	{
		
		List<Question> listQuestion = questionDao.selectQuestionByEid(eid);
	    
		Map<String,Object> mapListOption = new HashMap<>();
        //放添加isSelect属性之后的option
        List<Object> listOption = null;
        //放questions
        List<Map<String, Object>> questions = new ArrayList<>();
        //放quesion
	    Map<String,Object> question = null;
	    
        
	    int index = 0;
		for(Question q : listQuestion)
		{
			question = new HashMap<>();
			listOption = new ArrayList<>();
		    String[] stringSelect = (String[]) listSelect.get(index).get(q.getId());
		    
		    List<Option> options = optionDao.selectByQid(q.getId());
		    
		    for(int i = 0 ; i < options.size();i++)
		    {
		    	JSONObject jsonObject  = JSONObject.parseObject(JSON.toJSONString(listQuestion.get(index).getOptions().get(i)));
		    	jsonObject.put("isSelect", 0);
		    	if(stringSelect == null){
		    		listOption.add(jsonObject);
		    		continue;
		    	}
		    	//[A,C,D]
		    	for(int j = 0 ; j < stringSelect.length; j++)
		    	{
		    		if(options.get(i).getSymbol().equals(stringSelect[j].toString()))
		    		{
		    			jsonObject.remove("isSelect");
		    			jsonObject.put("isSelect", 1);
		    			    break;
		    		}
		    	}
		    	listOption.add(jsonObject);
		    }
		    question.put("face", q.getFace());
		    question.put("id", q.getId());
		    question.put("isRadio",q.getIsRadio());
		    question.put("level",q.getLevel());
		    question.put("options", listOption);
			questions.add(question);
            index++;
		}
		ExamPaper examPaper =  examQuestionDao.selectExamPaperByEid(134);
		Map<String,Object> mapExamPaper = new HashMap<>();
		mapExamPaper.put("endTime",examPaper.getEndTime());
		mapExamPaper.put("examQuestions", examPaper.getExamQuestions());
		mapExamPaper.put("id",examPaper.getId());
		mapExamPaper.put("practice", examPaper.getPractice());
		mapExamPaper.put("startTime", examPaper.getStartTime());
		mapExamPaper.put("title",examPaper.getTitle());
		mapExamPaper.put("questions",questions);
		
	    //System.out.println(JSON.toJSONString(mapExamPaper));
	    
	    return JSON.toJSONString(mapExamPaper);
	}
	
}
