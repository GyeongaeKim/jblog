package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	
	
	//1. 블로그 정보 가져오기
	public Map<String, String> getBlog(String id) {
		System.out.println("BlogService>getBlog()");
		Map<String, String> blogVo = blogDao.getBlog(id);
		return blogVo;
	}
	
	
	//2.블로그 정보 가져오기(basic수정)
	public Map<String, String> getBasic(String id, String checkId) {
		System.out.println("BlogService>getBasic()");
		if(!id.equals(checkId)) {
			return null;
		}
		Map<String, String> blogVo = blogDao.getBlog(id);
		return blogVo;
	}
	
	//블로그 정보 수정(updateBasic)
	public int updateBasic(BlogVo blogVo, MultipartFile file) {
		System.out.println("BlogService>updateBasic()");
		
		String saveDir = "c:\\javaStudy\\upload";
		
		//1-오리지날 파일명
		String orgName = file.getOriginalFilename();
		//2-확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		//3-저장파일명(+확장자명)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		//4-파일경로(디렉토리+저장파일명)
		String filePath = saveDir + "\\" + saveName;
		//5-파일사이즈
		long fileSize = file.getSize();
		
		String logoFile = "upload/" + saveName;
		
		
		//*2.파일저장-하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		blogVo.setLogoFile(logoFile);
		int count = blogDao.updateBasic(blogVo);
		
		return count;
	}
	
	
	
	
	
	
	
	//ajax활용 - 전체리스트 가져오기
	public List<CategoryVo> getCategoryList() {
		System.out.println("GuestbookService>getGuestList()");
		List<CategoryVo> getCategoryList =  blogDao.selectList();
		return getCategoryList;
	}
	

}
