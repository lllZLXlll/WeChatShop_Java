package com.wechat.shop.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.shop.service.UserService;

@Controller
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index() throws NullPointerException {
		if (true) {
			throw new NullPointerException();
		}
		return "index";
	}

	/**
	 * 用户登录获取信息保存
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String jdCode, String nickName, Integer gender, String avatarUrl, String country,
			String province, String city) throws Exception {
		return userService.login(jdCode, nickName, gender, avatarUrl, country, province, city);
	}

	/**
	 * 用户增加收货地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/addReceivingAddress")
	@ResponseBody
	public Map<String, Object> addReceivingAddress(String openid, String userName, String postalCode,
			String provinceName, String cityName, String countyName, String detailInfo, String nationalCode,
			String telNumber) throws Exception {
		return userService.addReceivingAddress(openid, userName, postalCode, provinceName, cityName, countyName,
				detailInfo, nationalCode, telNumber);
	}
	
	/**
	 * 查询用户收货地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/queryReceivingAddressListById")
	@ResponseBody
	public Map<String, Object> queryReceivingAddressListById(String openid) throws Exception {
		return userService.queryReceivingAddressListById(openid);
	}
	
	/**
	 * 修改默认收货地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/setAddressStatusById")
	@ResponseBody
	public Map<String, Object> setAddressStatusById(String openid, Long id) throws Exception {
		return userService.setAddressStatusById(openid, id);
	}
	
	/**
	 * 删除收货地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/delAddressStatusById")
	@ResponseBody
	public Map<String, Object> delAddressStatusById(String openid, Long id, Integer status) throws Exception {
		return userService.delAddressStatusById(openid, id, status);
	}
}
