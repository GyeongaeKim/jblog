package com.javaex.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	
	//카테고리 리스트 가져오기
	public List<Map<String, Object>> getCList(String id) {
		System.out.println("CategoryDao>getCList");
		
		List<Map<String, Object>> categoryList = sqlSession.selectList("category.getCList", id);
		return categoryList;
	}
	
	//포스트-카테고리 리스트 가져오기
	public List<CategoryVo> getNameList(String id) {
		System.out.println("CategoryDao>getNameList");
		List<CategoryVo> categoryList = sqlSession.selectList("category.getNameList", id);
		return categoryList;
	}
	
	
	
	//카테고리 추가
	public int addCategory(CategoryVo categoryVo) {
		System.out.println("CategoryDao>addCategory");
		return sqlSession.insert("category.addCategory", categoryVo);
	}
	
	
	//카테고리 추가 후 정보 가져오기
	public Map<String, Object> getCategory(int cateNo) {
		System.out.println("CategoryDao>getCategory()");
		Map<String, Object> categoryMap = sqlSession.selectOne("category.getCategory", cateNo);
		return categoryMap;
	}
	
	
	
	//카테고리 삭제
	public int deleteCategory(int cateNo) {
		System.out.println("CategoryDao>deleteCategory");
		return sqlSession.delete("category.deleteCategory", cateNo);
	}
	
	
}