package com.msy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pc/login")
public class LoginController {

	@RequestMapping("/toLogin.page")
	public String toLoginPage(){
		return "pc/login";
	}

	@ResponseBody
	@RequestMapping("/login.form")
	public String login(String username, String password,
						HttpServletRequest request, HttpServletResponse response){
		if ("1".equals(username) && "1".equals(password)){
			HttpSession session = request.getSession(true);
			//过期时间30分钟*60s
			session.setMaxInactiveInterval(30*60);
			session.setAttribute("user","1");
			return "登陆成功";
		}
		return "用户名或密码错误";
	}

	@ResponseBody
	@RequestMapping("/logout.form")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("user");
		return "退出成功";
	}

}
