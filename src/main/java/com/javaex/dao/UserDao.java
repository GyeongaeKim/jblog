package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//1.회원등록
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao>userInsert()");
		return sqlSession.insert("user.userInsert", userVo);
	}
}
