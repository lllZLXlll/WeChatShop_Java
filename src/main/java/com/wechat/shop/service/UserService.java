package com.wechat.shop.service;

import org.springframework.stereotype.Service;

import com.wechat.shop.entity.User;

@Service
public interface UserService {

	public User getUserInfo();
	
	public String login(String jdCode) throws Exception;

}
