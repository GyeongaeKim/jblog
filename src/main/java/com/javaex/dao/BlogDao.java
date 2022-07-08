package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Repository
public class BlogDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//블로그 정보 가져오기
	public Map<String, String> getBlog(String id) {
		System.out.println("BlogDao>getBlog()");
		Map<String, String> blogMap = sqlSession.selectOne("blog.getBlog", id);
		System.out.println(blogMap);
		return blogMap;
	}
	
	
	//회원가입 시 블로그 만들기
	public int joinBlog(BlogVo blogVo) {
		System.out.println("BlogDao>joinBlog()");
		return sqlSession.insert("blog.joinBlog", blogVo);
	}
	
	
	
	//admin업데이트-basic수정
	public int updateBasic(BlogVo blogVo) {
		System.out.println("BlodDao>updateBasic");
		System.out.println(blogVo);
		return sqlSession.update("blog.updateBasic", blogVo);
	}
	
	
	
	//ajax활용 - 전체리스트 가져오기
	public List<CategoryVo> selectList() {
		System.out.println("BlogDao>selectList()");
		return sqlSession.selectList("category.selectList");
	}
}
