package com.exam.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExamPaper;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.entity.Type;
import com.exam.service.ExamPaperService;
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
//    	
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionService questionService = (QuestionService) applicationContext.getBean("questionService");
		
		Question question = new Question();
		question.setLimit(3);
		question.setStart(0);
		
	    //System.out.println(questionService.selectQuestionByType("",question));
    	
    	System.out.println(questionService.selectQuestionByFace("",question));
     	
    }
    @Test
    public void testTime()
    {
    	long currentTime = System.currentTimeMillis();
    	System.out.println(currentTime);
    	
    	Date date = new Date();
    	System.out.println(date.toString());
    	
    	long time = date.getTime();
    	System.out.println(time);
    	
    	String sDate = "2016-7-12 18:00:00";
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date1 = null;
    	try {
			date1 = format.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(date1);
    	
    }
    
    @Test
    public void testInsert()
    {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExamPaperService  examPaperService = (ExamPaperService) applicationContext.getBean("examPaperService");
		
		String sDate1 = "2016-7-12 20:00:00";
		String sDate2 = "2016-7-12 24:30:00";
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date1 = null;
    	Date date2 = null;
    	try {
			date1 = format.parse(sDate1);
			date2 = format.parse(sDate2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExamPaper examPaper = new ExamPaper();
		
		examPaper.setPractice(0);
		examPaper.setTitle("永春java考试5");
	    examPaper.setUid(1);
		examPaper.setStartTime(date1.getTime());
		examPaper.setEndTime(date2.getTime());
		
		List<Question> questions = new ArrayList<>();
		
		int j=40;
		for(int i = 0;i < 5;i++)
		{
			
			Question question = new Question();
			question.setId(j++);
			questions.add(question);
		}
		examPaper.setQuestions(questions);
		
		//examPaperService.insert(examPaper);
		
    }

    
}
