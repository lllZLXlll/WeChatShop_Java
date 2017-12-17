package com.wechat.shop.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wechat.shop.entity.User;

@Service
public interface UserService {

	public Map<String, Object> login(String jdCode, String nickName, Integer gender, String avatarUrl, String country,
			String province, String city) throws Exception;

}
