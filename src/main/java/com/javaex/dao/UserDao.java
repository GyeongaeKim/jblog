package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	//아이디 중복확인
	public String checkId(String checkId) {
		System.out.println("UserDao>checkId()");
		String id = sqlSession.selectOne("user.checkId", checkId);
		return id;
	}
	
	
	
	
	
	
	
	//전체리스트 가져오기
	public List<UserVo> selectList(){
		System.out.println("UserDao>selectList()");
		return sqlSession.selectList("user.selectList");
	}
	
	
	
	//1.회원등록
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao>userInsert()");
		return sqlSession.insert("user.userInsert", userVo);
	}
	
	//2. 로그인
	public UserVo getUser (UserVo userVo) {
		System.out.println("UserDao>getUser()");
		UserVo authVo = sqlSession.selectOne("user.getUser", userVo);
		System.out.println("Dao " + authVo);
		return authVo;
	}
}
