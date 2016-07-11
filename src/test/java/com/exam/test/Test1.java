package com.exam.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.exam.dao.QuestionDao;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.entity.Type;
import com.exam.service.ExamQuestionService;
import com.exam.service.OptionService;
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
			option.setIsTrue(1);
			option.setTitle("选项内容" + i);
			option.setTutorial("题目解析" + i);
			question.getOptions().add(option);
		}
		
		
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
		@SuppressWarnings("resource")
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
		//QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		QuestionDao questionDao = (QuestionDao)applicationContext.getBean("questionDao");
		int count = questionDao.typeOfQuestionCount(123);
//		System.out.println(count);
		
	}
    
    @Test
    public void testGetQid()
    {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamQuestionService examQuestionService = (ExamQuestionService) applicationContext.getBean("examQuestionService");
		
		System.out.println(examQuestionService.selectQidByEid(134));
		
    }
    
    @Test
    public void testTrueAnswerCount()
    {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		OptionService optionService = (OptionService) applicationContext.getBean("optionService");
		
		System.out.println(optionService.selectTrueAnswer(79));
		
    }
    
    @Test
    public void testSelectNum()
    {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionDao questionDao = (QuestionDao) applicationContext.getBean("questionDao");
		
		OptionService optionService = (OptionService) applicationContext.getBean("optionService");
		
		List<Integer> list = questionDao.selectQuesionId();
		//System.out.println(list);
		
		int checkbox = 0;
		int radio = 0;
		for(Integer qid : list)
		{
			if(optionService.selectTrueAnswer(qid) > 1)
			{
				checkbox++;
			}
			else
			{
				radio++;
			}
		}
		System.out.println(radio + "," +checkbox);
    }
    
    @Test
    public void test11()
    {
//    	Option option = new Option();
//    	option.setId(1);
//    	option.setIsTrue(1);
//    	option.setTitle("dfhfjfj");
//    	option.setTutorial("hfdhff");
//    	JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(option));
//    	jsonObject.put("isSelect", 0);
//    	jsonObject.remove("isSelect");
//    	jsonObject.put("isSelect", 1);
//    	
//    	System.out.println(jsonObject);
    	//System.out.println(JSON.toJSONString(option));
    	
    	//String[] s1 = new String[]{"A","B","C"};
    	//String[] s2 ;
    	//s2 = s1;
    	//System.out.println(s2);
//    	s[0]="A";
//    	s[1]="B";
    	
    	//String str = "A";
    	
    	//System.out.println(s2[0].toString().equals(str));
    	
    	//Map<String, Object> map = new HashMap<>();
    	//map.put("null", null);
    	
    	//System.out.println(map.get("null"));
    	
         
    	
//    	 HttpServletRequest request = 
//    	 
//    	 String webRoot = request.getSession().getServletContext().getRealPath("/");
//    	 File file = new File(webRoot + user.getId() + ".jpg");
//    	 
    	
    	
    }
    
}
