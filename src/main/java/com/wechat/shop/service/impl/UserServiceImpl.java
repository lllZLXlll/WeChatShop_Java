package com.wechat.shop.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.Constants;
import com.wechat.shop.controller.UserController;
import com.wechat.shop.entity.User;
import com.wechat.shop.mapper.UserMapper;
import com.wechat.shop.service.UserService;
import com.wechat.shop.utils.HTTPRequestUtil;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	public User getUserInfo() {
		User user = userMapper.findUserInfo();
		return user;
	}

	@Override
	public String login(String jdCode) throws Exception {
		// 获取用户在小程序登录成功后返回的code 拼接api获取session_key ，openid
		String loginApi = Constants.OPONIDAPI + jdCode;
		logger.info("用户登录code: " + jdCode);

		// 调用api
		String result = HTTPRequestUtil.sendGet(loginApi);

		if (result != null && result.trim().length() > 0) {
			JSONObject resultJson = new JSONObject(result);
			String session_key = resultJson.getString("session_key");
			String openid = resultJson.getString("openid");
			System.out.println("session_key: " + session_key);
			System.out.println("openid: " + openid);

			// 用户数据进行保存

		}
		return null;
	}

}
