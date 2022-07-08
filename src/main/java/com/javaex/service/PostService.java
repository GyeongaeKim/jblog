package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	
	//포스트 추가
	public int write(PostVo postVo) {
		System.out.println("PostService>write()");
		
		postVo.setPostContent(postVo.getPostContent().replace("\n", "<br>"));
		
		int count = postDao.write(postVo);
		return count;
	}
}
