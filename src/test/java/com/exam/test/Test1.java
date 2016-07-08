package com.exam.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.entity.Type;
import com.exam.service.QuestionService;
public class Test1 {

	@Test
	public void testInsertQuestion() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		
		Question question = new Question(); 
		Option option = null;
		
//		question.setId(2);
		question.setFace("题面2");
		question.setAnswer("答案2");
		question.setLevel(1);
		question.setTid(569);
		
		for(int i = 1; i <= 4;i++)
		{
			option = new Option();
			option.setId(i + 4);
			option.setQid(1);
			option.setIsTrue(true);
			option.setTitle("选项内容" + i);
			option.setTutorial("题目解析" + i);
			question.getOptions().add(option);
		}
		
		questionService.insertQuestion(question);
		
	}

	@Test
	public void testSelectQuestionByTypeAndLevel()
	{
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		
		List<Question> list = questionService.selectQuestionByTypeAndLevel(1);
		
		//System.out.println(list.get(0).getOptions().size());
		
		for(Question q : list)
		{
			System.out.println(q.toString());
		}
	}

	@Test
	public void testSelectQuestionByFace(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		Question question = questionService.selectById(18);
		System.out.println(questionService.countQuestionByFace(question.getFace()));
	}
	
	@Test
	public void testList()
	{
        Map<String, Object> map = new HashMap<>();
	    
	    List<Type> listType = new ArrayList<Type>();
	    
	    Map<String,List<Type>> mapListType = new HashMap<>();
	    
	    Type type1 = new Type();
	    type1.setId(1);
	    type1.setType("C++");
	    type1.setSuperType("编程语言");
	    listType.add(type1);
	    
	    Type type2 = new Type();
	    type2.setId(2);
	    type2.setType("java");
	    type2.setSuperType("编程语言");
	    listType.add(type2);
	    
	    mapListType.put("编程语言",listType);
	    
	    System.out.println(mapListType.get("编程语言").get(0));
	    
	    Set set = mapListType.keySet();
	    
	    for(Object key : set)
	    {
	    	 map.put("title",key);
	    	 map.put("list", mapListType.get(key));
	    }
	    
	    String json = JSON.toJSONString(map);
	    System.out.println(json);
	    
	    //List< Map<String,Object> > list;
		
	}
	
    @Test
	public void testGetTypeCount()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		int count = questionService.typeOfQuestionCount(569);
		System.out.println(count);
	}
	
}
