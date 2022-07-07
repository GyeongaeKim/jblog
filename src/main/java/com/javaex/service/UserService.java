package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//전체리스트 가져오기
	public List<UserVo> getUserList(){
		System.out.println("UserService>getUserList()");
		List<UserVo> getUserList = userDao.selectList();
		return getUserList;
	}
	
	
	//1.회원등록
	public int userInsert(UserVo userVo) {
		System.out.println("UserService>userInsert()");
		int count = userDao.userInsert(userVo);
		return count;
	}
	
	
	
	//2. 로그인
	public UserVo userLogin(UserVo userVo) {
		System.out.println("UserService>userLogin()");
		UserVo authUser = userDao.getUser(userVo);
		return authUser;
	}
}
