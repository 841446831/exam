package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.Type;

public interface TypeDao {
	
	
	@Insert("insert into type(id,type,superType) values(#{id},#{type},#{superType})")
    public int insertType(Type type);

	@Select("select * from type")
	public List<Type> selectAll();
	
	
	
}
