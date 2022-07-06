package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//1.회원등록
	public int userInsert(UserVo userVo) {
		System.out.println("UserService>userInsert()");
		return userDao.userInsert(userVo);
	}
}
