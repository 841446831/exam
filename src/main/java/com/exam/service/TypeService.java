package com.exam.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.TypeDao;
import com.exam.entity.Type;

@Service
public class TypeService {
	
	@Resource
	private TypeDao typeDao;
	
	public int addType(Type type)
	{
		return typeDao.addType(type);
	}
	
}
