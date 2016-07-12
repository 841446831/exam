package com.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
	@Options(useGeneratedKeys=true, keyProperty="id")//添加该行，product中的id将被自动添加
	int insertUser(User user);

	@Select("select * from user")
	List<User> selectAll();
}
