package com.javaex.service;


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
	public int addCategory(CategoryVo categoryVo) {
		System.out.println("CategoryService > addCategory");
		
		return categoryDao.addCategory(categoryVo);
	}
	
	//카테고리 삭제
	public int deleteCategory(int cateNo) {
		System.out.println("CategoryService>delteCategory");
		return categoryDao.deleteCategory(cateNo);
	}
}
