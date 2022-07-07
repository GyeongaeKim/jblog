package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	//블로그 메인으로 이동
	@RequestMapping(value="/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String blogMain(@PathVariable("id") String id){
		System.out.println("BlogController>main()");
		return "blog/blog-main";
	}
	
	
	//블로그 메인으로 이동
	@RequestMapping(value="/{id}/admin/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminBasic(@PathVariable("id") String id){
		System.out.println("BlogController>main()");
		
		return "blog/admin/blog-admin-basic";
	}
	
	
	//admin업데이트-basic
	@RequestMapping(value="/updateBasic", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateBasic(@RequestParam("file") MultipartFile file,
								@RequestParam("blogTitle") String blogTitle, Model model, HttpSession session) {
		System.out.println("BlogController>updateBasic()");
		System.out.println(file.getOriginalFilename());
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(session.getId());
		blogVo.setBlogTitle(blogTitle);
		blogVo.setLogoFile(blogTitle);
		
		
		System.out.println(blogVo);
		blogService.updateBasic(blogVo);
		
		
		return "redirect:/{id}/admin/basic";
		
	}
	
	
	//admin업데이트-category
	@RequestMapping(value="/{id}/admin/category", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminCategory(@PathVariable("id") String id){
		System.out.println("BlogController>updateCategory()");
		
		return "blog/admin/blog-admin-cate";
	}
	
	//categoryList
	@ResponseBody
	@RequestMapping(value="/blog/categoryList", method= {RequestMethod.GET, RequestMethod.POST})
	public List <CategoryVo> categoryList() {
		System.out.println("blog/admin/categoryList()");
		List <CategoryVo> categoryList = blogService.getCategoryList();
		System.out.println(categoryList);
		
		return categoryList;
	}
	
}
