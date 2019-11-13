package com.msy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driver")
public class DriverController {

	@RequestMapping("/toIndex.page")
	public String toIndexPage(){
		return "/driver/index";
	}

	@RequestMapping("/toWaitSend.page")
	public String toWaitSendPage(){
		return "/driver/waitSend";
	}

	@RequestMapping("/toMy.page")
	public String toMyPage(){
		return "/driver/my";
	}
}
