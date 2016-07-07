package com.exam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.PersonDao;
import com.exam.entity.Person;

@Service
public class PersonService {
	@Resource
	private PersonDao personDao;
	
	public List<Person> selectAll(){
		return personDao.selectAll();
	}
	
}
