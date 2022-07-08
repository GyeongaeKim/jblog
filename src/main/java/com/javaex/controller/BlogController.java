package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	//블로그 메인으로 이동
	@RequestMapping(value="/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String blogMain(@PathVariable("id") String id, HttpSession session){
		System.out.println("BlogController>main()");
		System.out.println(id);
		
		Map<String, String> blogMap = blogService.getBlog(id);
		session.setAttribute("blogMap", blogMap);
		System.out.println(blogMap); 
		
		return "blog/blog-main";
	}
	
	
	//블로그 admin basic으로 이동
	@RequestMapping(value="/{id}/admin/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminBasic(Model model, @PathVariable("id") String id, HttpSession session){
		System.out.println("BlogController>main()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String checkId = authUser.getId();
		Map<String, String> blogMap = blogService.getBasic(id, checkId);
		if(blogMap == null) {
			return "error/403";
		}
		model.addAttribute("blogMap", blogMap);
		
		return "blog/admin/blog-admin-basic";
	}
	
	
	//admin업데이트-basic
	@RequestMapping(value="/{id}/updateBasic", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateBasic(@RequestParam("file") MultipartFile file,
								@RequestParam("blogTitle") String blogTitle, 
								@PathVariable String id,
								Model model, HttpSession session) {
		System.out.println("BlogController>updateBasic()");
		System.out.println(file.getOriginalFilename());
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(session.getId());
		blogVo.setBlogTitle(blogTitle);
		
		System.out.println(blogVo);
		blogService.updateBasic(blogVo, file);
		
		
		return "redirect:/{id}/admin/basic";
		
	}
	
	
	
	
}
