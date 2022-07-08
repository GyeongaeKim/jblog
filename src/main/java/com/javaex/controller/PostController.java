package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.PostService;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class PostController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	
	
	//admin 카테고리로 이동
	@RequestMapping(value="/{id}/admin/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminWirte(Model model, @PathVariable("id") String id, HttpSession session) {
		System.out.println("PostController>adminWrite()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String checkId = authUser.getId();
		Map<String, String> blogMap = blogService.getBasic(id, checkId);
		if(blogMap == null) {
			return "error/403";
		}
		model.addAttribute("blogMap", blogMap);
		return "blog/admin/blog-admin-write";
	}
	
	
	
	@RequestMapping(value="/{id}/admin/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println("PostController>write");
		postService.write(postVo);
		return "redirect:/{id}/admin/writeForm";
	}
	
}
