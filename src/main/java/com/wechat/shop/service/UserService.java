package com.wechat.shop.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wechat.shop.entity.User;

@Service
public interface UserService {

	public Map<String, Object> login(String jdCode, String nickName, Integer gender, String avatarUrl, String country,
			String province, String city) throws Exception;

	public Map<String, Object> addReceivingAddress(String openid, String userName, String postalCode,
			String provinceName, String cityName, String countyName, String detailInfo, String nationalCode,
			String telNumber);

	public Map<String, Object> queryReceivingAddressListById(String openid);

	public Map<String, Object> setAddressStatusById(String openid, Long id);

	public Map<String, Object> delAddressStatusById(String openid, Long id, Integer status);

}
