package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	
	
	//admin업데이트-basic
	public int updateBasic(BlogVo blogVo) {
		System.out.println("BlogService>updateBasic");
		return blogDao.updateBasic(blogVo);
	}
	
	
	
	//ajax활용 - 전체리스트 가져오기
	public List<CategoryVo> getCategoryList() {
		System.out.println("GuestbookService>getGuestList()");
		List<CategoryVo> getCategoryList =  blogDao.selectList();
		return getCategoryList;
	}
	

}
