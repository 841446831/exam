package com.exam.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exam.dao.OptionDao;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.service.ExamQuestionService;
import com.exam.service.QuestionService;

public class testGetQuestionsAnswer {
    public static void main(String[] args) {
    	
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionDao questionDao= (QuestionDao) applicationContext.getBean("questionDao");
		ExamQuestionService examQuestionService =  (ExamQuestionService) applicationContext.getBean("examQuestionService");
		OptionDao optionDao=  (OptionDao) applicationContext.getBean("optionDao");
    	
        List<Question> listQuestion = questionDao.selectQuestionByEid(134);
        List<Integer> listQid = examQuestionService.selectQidByEid(134);
       
        Map<Integer, Object> map = null;
        
        List<Map<Integer, Object>> listSelect = new ArrayList<>();
	    
        for(Integer qid : listQid)
        {
        	 map = new HashMap<>();
        	 String[] s = new String[]{"A","C"};
        	 map.put(qid, s);
        	 listSelect.add(map);
        }
        
        
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
		    	
		    	if(stringSelect == null){break;}
		    	
		    	JSONObject jsonObject  = JSONObject.parseObject(JSON.toJSONString(listQuestion.get(index).getOptions().get(i)));
		    	jsonObject.put("isSelect", 0);
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
		
	
		//mapListOption.put("options",listOption);
		System.out.println(JSON.toJSONString(questions));
		
		ExamPaper examPaper =  examQuestionService.selectExamPaperByEid(134);
		Map<String,Object> mapExamPaper = new HashMap<>();
		mapExamPaper.put("endTime",examPaper.getEndTime());
		mapExamPaper.put("examQuestions", examPaper.getExamQuestions());
		mapExamPaper.put("id",examPaper.getId());
		mapExamPaper.put("practice", examPaper.getPractice());
		mapExamPaper.put("startTime", examPaper.getStartTime());
		mapExamPaper.put("title",examPaper.getTitle());
		mapExamPaper.put("questions",questions);
		
	    //JSONObject jsonObjectExamPaper = JSONObject.parseObject(JSON.toJSONString(examPaper));
	    System.out.println(JSON.toJSONString(mapExamPaper));
	    
	
	}
}
