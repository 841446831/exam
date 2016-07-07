package com.exam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.TypeDao;
import com.exam.entity.Type;

@Service
public class TypeService {
	
	@Resource
	private TypeDao typeDao;
	
	public List<Type> SelectAll() {
		return typeDao.selectAll();
	}
	
	public int insertType(Type type)
	{
		return typeDao.insertType(type);
	}
	
}
