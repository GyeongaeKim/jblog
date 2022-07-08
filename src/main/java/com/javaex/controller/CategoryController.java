package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Controller
public class CategoryController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	
	//admin 카테고리로 이동
	@RequestMapping(value="/{id}/admin/category", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminCategory(Model model, @PathVariable("id") String id, HttpSession session) {
		System.out.println("CategoryController>adminCategory()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String checkId = authUser.getId();
		Map<String, String> blogMap = blogService.getBasic(id, checkId);
		if(blogMap == null) {
			return "error/403";
		}
		model.addAttribute("blogMap", blogMap);
		
		return "blog/admin/blog-admin-cate";
	}
	
	
	
	//카테고리 리스트
	
	
	
	//카테고리 추가
	@ResponseBody
	@RequestMapping(value="/addCategory", method= {RequestMethod.GET, RequestMethod.POST})
	public int addCategory(@RequestBody CategoryVo categoryVo){
		System.out.println("CategoryController>addCategory");
		return categoryService.addCategory(categoryVo);
	}
	
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/deleteCategory", method= {RequestMethod.GET, RequestMethod.POST})
	public int deleteCategory(@RequestBody int cateNo) {
		System.out.println("CategoryController>deleteCategory");
		return categoryService.deleteCategory(cateNo);
	}
	
	
}
