package com.exam.dao;

import org.apache.ibatis.annotations.Insert;

import com.exam.entity.Type;

public interface TypeDao {
	
	
	@Insert("insert into type(type,superType) values(#{type},#{supperType})")
    public int addType(Type type);
	
}
