package com.exam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.OptionDao;
import com.exam.entity.Option;

@Service
public class OptionService {
    
    @Resource
    private OptionDao optionDao;
    
	public int selectTrueAnswer(int qid)
	{
		return optionDao.selectTrueAnswer(qid);
	}

	public Option selectById(int i) {
		// TODO Auto-generated method stub
		return optionDao.selectById(i);
	}
	
	public List<Option> selectByQid(int qid)
	{
		return optionDao.selectByQid(qid);
	}
	
}
