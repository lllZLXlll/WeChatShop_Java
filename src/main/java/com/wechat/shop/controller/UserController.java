package com.wechat.shop.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.shop.entity.User;
import com.wechat.shop.service.UserService;

@Controller
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String jdCode, String nickName, Integer gender, String avatarUrl, String country,
			String province, String city) throws Exception {
		return userService.login(jdCode, nickName, gender, avatarUrl, country, province, city);
	}
}
