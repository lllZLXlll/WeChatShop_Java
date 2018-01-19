package com.wechat.shop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	private Logger logger = Logger.getLogger(AdminController.class);


	@RequestMapping("/adminLogin")
	public String adminLogin() throws NullPointerException {
		return "login";
	}
	
	@RequestMapping("/index")
	public String main() throws NullPointerException {
		return "index";
	}


}
