package com.exam.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.OptionDao;

@Service
public class OptionService {
    
    @Resource
    private OptionDao optionDao;
    
	public int selectTrueAnswer(int qid)
	{
		return optionDao.selectTrueAnswer(qid);
	}
	
	
}
