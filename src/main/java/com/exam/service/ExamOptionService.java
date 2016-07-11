package com.exam.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.ExamOptionDao;
import com.exam.dao.OptionDao;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExamOption;
import com.exam.entity.Option;
import com.exam.entity.Question;

@Service
public class ExamOptionService {
    
	@Resource
	ExamOptionDao examOptionDao;
	
	@Resource
	QuestionDao questionDao;
	
	@Resource
	OptionDao optionDao;
	
	public int insertExamOption(int eid,List<Map<Integer, Object>> listSelect)
	{
		List<Question> listQuestion = questionDao.selectQuestionByEid(eid);
		
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
				examOption.setEid(eid);
				examOption.setQid(q.getId());
				examOption.setOid(options.get(i).getId());
				
				if(stringSelect == null)
				{
					if(examOptionDao.insertExamOption(examOption) < 1)
					{
						return 0;
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
					return 0;
				}
		
			}
			index++;
		}
		
		return 1;
	}
	
}
