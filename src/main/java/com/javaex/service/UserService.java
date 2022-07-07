package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	
	
	//1. 중복체크
	public boolean checkId(String checkId) {
		System.out.println("UserService>checkId()");
		String id = userDao.checkId(checkId);
		
		System.out.println(id==null);
		if(id == null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//2. 회원등록
	public int userInsert(UserVo userVo) {
		System.out.println("UserService>userInsert()");
		userDao.userInsert(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName() + "의 블로그");
		blogVo.setLogoFile("/assets/images/spring-logo.jpg");
		
		System.out.println(blogVo);
		
		return blogDao.joinBlog(blogVo);
	}
	
	
	
	//3. 로그인
	public UserVo userLogin(UserVo userVo) {
		System.out.println("UserService>userLogin()");
		UserVo authUser = userDao.getUser(userVo);
		return authUser;
	}
}
