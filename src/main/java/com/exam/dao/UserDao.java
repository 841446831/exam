package com.exam.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.entity.User;

public interface UserDao {
	@Select("select * from user where id = #{id}")
	User selectById(int id);

	@Select("select * from user where username=#{username}")
	User selectByUsername(String username);

	@Select("select * from user where email=#{email}")
	User selectByEmail(String email);
	
	@Insert("INSERT into user(username,password,email) values(#{username},#{password},#{email})")
	int insertUser(User user);
}
