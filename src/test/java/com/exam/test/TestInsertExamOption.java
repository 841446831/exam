package com.exam.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.dao.ExamOptionDao;
import com.exam.dao.OptionDao;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExamOption;
import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.service.ExamQuestionService;

public class TestInsertExamOption {
    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		QuestionDao questionDao= (QuestionDao) applicationContext.getBean("questionDao");
		ExamQuestionService examQuestionService =  (ExamQuestionService) applicationContext.getBean("examQuestionService");
		OptionDao optionDao=  (OptionDao) applicationContext.getBean("optionDao");
		ExamOptionDao examOptionDao = (ExamOptionDao) applicationContext.getBean("examOptionDao");
		

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
		
		ExamOption examOption = null;

		int index = 0;
		for(Question q : listQuestion)
		{
			String[] stringSelect = (String[]) listSelect.get(index).get(q.getId());
			List<Option> options = optionDao.selectByQid(q.getId());
			for(int i = 0;i < options.size();i++)
			{
				examOption = new ExamOption();
				examOption.setIsSelect(0);
				examOption.setEid(134);
				examOption.setQid(q.getId());
				examOption.setOid(options.get(i).getId());
				
				if(stringSelect == null)
				{
					if(examOptionDao.insertExamOption(examOption) < 1)
					{
						System.out.println("插入失败");
					}
					continue;
				}
				
				for(int j = 0; j < stringSelect.length;j++)
				{
					if(options.get(i).getSymbol().equals(stringSelect[j].toString()))
					{
						examOption.setIsSelect(1);
						break;
					}
				}
				if(examOptionDao.insertExamOption(examOption) < 1)
				{
					System.out.println("插入失败");
				}
		
			}
			index++;
		}
        System.out.println("插入成功");
	}
}
