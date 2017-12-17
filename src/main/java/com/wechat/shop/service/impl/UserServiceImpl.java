package com.wechat.shop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.Constants;
import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.entity.User;
import com.wechat.shop.mapper.UserMapper;
import com.wechat.shop.service.UserService;
import com.wechat.shop.utils.HTTPRequestUtil;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public Map<String, Object> login(String jdCode, String nickName, Integer gender, String avatarUrl, String country,
			String province, String city) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		long updateCount = -1;

		// 调用api 获取oponid session_key
		String result = HTTPRequestUtil.sendGet(Constants.OPONIDAPI + jdCode);

		if (result != null && result.trim().length() > 0) {
			JSONObject resultJson = new JSONObject(result);
			String session_key = resultJson.getString("session_key");
			String openid = resultJson.getString("openid");

			// 判断用户是否登录过
			User user = userMapper.queryUserInfoByOpenid(openid);

			// 用户第一次登录保存用户信息
			if (user == null) {
				updateCount = userMapper.addUser(new User(openid, session_key, nickName, gender, avatarUrl, country,
						province, city, new Date(), new Date()));
			} else {
				updateCount = userMapper.updateUser(new User(openid, session_key, nickName, gender, avatarUrl, country,
						province, city, null, new Date()));
			}
		}

		if (updateCount != -1) {
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.SUCCESS_0001_MESSAGE);
		} else {
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0001);
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0001_MESSAGE);
		}

		return resultMap;
	}

}
