package com.javaex.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	//카테고리 리스트 가져오기
	
	
	//카테고리 추가
	public Map<String, Object> addCategory(CategoryVo categoryVo) {
		System.out.println("CategoryService > addCategory");
		
		categoryDao.addCategory(categoryVo);
		
		Map<String, Object> categoryMap = null;
		int cateNo = categoryVo.getCateNo();
		categoryMap = categoryDao.getCategory(cateNo);
		
		return categoryMap;
	}
	
	//카테고리 삭제
	public int deleteCategory(int cateNo) {
		System.out.println("CategoryService>delteCategory");
		return categoryDao.deleteCategory(cateNo);
	}
}
