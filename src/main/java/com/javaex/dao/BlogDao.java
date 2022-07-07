package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Repository
public class BlogDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//admin업데이트-basic
	public int updateBasic(BlogVo blogVo) {
		System.out.println("BlodDao>updateBasic");
		return sqlSession.update("blog.updateBasic", blogVo);
	}
	
	
	
	//ajax활용 - 전체리스트 가져오기
	public List<CategoryVo> selectList() {
		System.out.println("BlogDao>selectList()");
		return sqlSession.selectList("category.selectList");
	}
}
