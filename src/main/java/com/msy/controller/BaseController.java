package com.msy.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

	/**
	 * 获取本次请求cookie中的openid
	 * @param request
	 * @return
	 */
	public String getOpenID(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies){
			if (cookie.getName().equals("openid")){
				return cookie.getValue();
			}
		}
		return null;
	}

}
