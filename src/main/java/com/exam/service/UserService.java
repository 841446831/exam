package com.exam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.UserDao;
import com.exam.entity.User;
import com.exam.util.Constant;
import com.exam.util.ResultHelper;

@Service
public class UserService {
	@Resource
	UserDao userDao;
	
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.selectByUsername(username);
	}

	public User selectByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.selectByEmail(email);
	}
	
	public int insertUser(User user)
	{
		return userDao.insertUser(user);
	}
	
	public ResultHelper selectUserPager(User user)
	{
	    List<User> users = userDao.selectUserPager(user);
		return new ResultHelper(users, users.size(),Constant.SUCCESS_CODE,Constant.SUCCESS_MSG);
	}

	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

}
