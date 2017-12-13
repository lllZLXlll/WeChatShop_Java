package com.wechat.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.entity.User;
import com.wechat.shop.mapper.UserMapper;
import com.wechat.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUserInfo() {
		User user = userMapper.findUserInfo();
		return user;
	}

}
