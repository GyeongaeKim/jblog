package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private BlogDao blogDao;
	
	//카테고리 영역 가져오기
	public Map<String, Object> writeForm(String id, String checkId) {
		System.out.println("PostService>WriteForm()");
		
		if(!id.equals(checkId)) {
			return null;
		}
		
		Map<String, Object> postMap = new HashMap<>();
		Map<String, String> blogMap = blogDao.getHeader(id);
		postMap.put("blogMap", blogMap);
		List<CategoryVo> categoryList = categoryDao.getNameList(id);
		postMap.put("categoryList", categoryList);
		
		return postMap;
	}
	
	
	//포스트 추가
	public int write(PostVo postVo) {
		System.out.println("PostService>write()");
		postVo.setPostContent(postVo.getPostContent().replace("\n", "<br>"));
		int count = postDao.write(postVo);
		return count;
	}
}
