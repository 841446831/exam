package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.exam.entity.Person;


public interface PersonDao {
	@Select("select * from person")
	List<Person> selectAll();
	
	
}
