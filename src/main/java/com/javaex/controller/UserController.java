package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//회원정보 리스트
	@ResponseBody
	@RequestMapping(value="/user/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List <UserVo> list(){
		System.out.println("UserController>list()");
		List <UserVo> userList = userService.getUserList();
		System.out.println(userList);
		
		return userList;
	}
	
	
	
	//1. 회원가입폼
	@RequestMapping(value="/user/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController>joinForm()");
		return "user/joinForm";
	}
	
	//2. 회원등록
	@RequestMapping(value="/user/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController>join()");
		
		userService.userInsert(userVo);
		return "user/joinSuccess";
	}
	
	
	
	
	//3. 로그인폼
	@RequestMapping(value="/user/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController>loginForm()");
		return "user/loginForm";
	}
	
	
	//4. 로그인
	@RequestMapping(value="/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>login()");
		
		UserVo authUser = userService.userLogin(userVo);
		
		//세션에 저장!
		if(authUser != null) {	//로그인 성공
			System.out.println("로그인 성공!!");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}else {
			System.out.println("로그인 실패ㅠㅠ");
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	//5. 로그아웃
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>logout()");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	
}
