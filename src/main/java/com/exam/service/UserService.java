package com.exam.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.dao.UserDao;
import com.exam.entity.User;

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

}
