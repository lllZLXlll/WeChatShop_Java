package com.wechat.shop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.Constants;
import com.wechat.shop.common.MD5;
import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.entity.ReceivingAddress;
import com.wechat.shop.entity.User;
import com.wechat.shop.mapper.ReceivingAddressMapper;
import com.wechat.shop.mapper.UserMapper;
import com.wechat.shop.service.UserService;
import com.wechat.shop.utils.HTTPRequestUtil;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ReceivingAddressMapper receivingAddressMapper;

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
			String openidMd5 = MD5.getMD5OpenId(openid);
			resultMap.put("openid", openidMd5);

			// 判断用户是否登录过
			User user = userMapper.queryUserInfoByOpenid(openid);

			// 用户第一次登录保存用户信息
			if (user == null) {
				updateCount = userMapper.addUser(new User(openid, openidMd5, session_key, nickName, gender, avatarUrl,
						country, province, city, new Date(), new Date()));
			} else {
				updateCount = userMapper.updateUser(new User(openid, openidMd5, session_key, nickName, gender,
						avatarUrl, country, province, city, null, new Date()));
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

	@Override
	public Map<String, Object> addReceivingAddress(String openid, String userName, String postalCode,
			String provinceName, String cityName, String countyName, String detailInfo, String nationalCode,
			String telNumber) {
		Map<String, Object> resultMap = new HashMap<>();
		long updateCount = -1;

		// 查询数据库是否有此openid
		User user = userMapper.checkOpenIdMd5(openid);
		// 校验openid 是否正确
		if (user != null && user.getOpenidMd5() != null && user.getOpenidMd5().trim().length() > 0
				&& user.getOpenidMd5().equals(openid)) {
			// 查询此用户是否有收货地址，如果第一次添加收货地址则设置为常用地址status为1，如不是则status为0
			long count = receivingAddressMapper.queryUserInfoByOpenid(user.getId());
			int status = count > 0 ? 0 : 1;

			// 保存收货地址
			updateCount = receivingAddressMapper
					.addReceivingAddress(new ReceivingAddress(user.getId(), userName, telNumber, postalCode,
							provinceName, cityName, countyName, detailInfo, nationalCode, status, new Date()));
			
			if (updateCount != -1) {
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.SUCCESS_0003_MESSAGE);
			} else {
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0003);
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0003_MESSAGE);
			}
		} else {
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0004);
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0004_MESSAGE);
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> queryReceivingAddressListById(String openid) {
		Map<String, Object> resultMap = new HashMap<>();
		// 查询数据库是否有此openid
		User user = userMapper.checkOpenIdMd5(openid);
		// 校验openid 是否正确
		if (user != null && user.getOpenidMd5() != null && user.getOpenidMd5().trim().length() > 0
				&& user.getOpenidMd5().equals(openid)) {
			List<ReceivingAddress> addressList = receivingAddressMapper.queryReceivingAddressListById(user.getId());
			resultMap.put(ReturnCode.RESULTLIST, addressList);
		}
		
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

}
