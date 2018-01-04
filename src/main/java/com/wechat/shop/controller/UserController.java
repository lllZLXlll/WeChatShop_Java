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
		try {
			return userService.login(jdCode, nickName, gender, avatarUrl, country, province, city);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- login 用户登录获取信息保存 异常" + e.toString());
			throw e;
		}
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
		try {
			return userService.addReceivingAddress(openid, userName, postalCode, provinceName, cityName, countyName,
					detailInfo, nationalCode, telNumber);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- addReceivingAddress 用户增加收货地址 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 查询用户收货地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/queryReceivingAddressListById")
	@ResponseBody
	public Map<String, Object> queryReceivingAddressListById(String openid) throws Exception {
		try {
			return userService.queryReceivingAddressListById(openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryReceivingAddressListById 查询用户收货地址 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 修改默认收货地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/setAddressStatusById")
	@ResponseBody
	public Map<String, Object> setAddressStatusById(String openid, Long id) throws Exception {
		try {
			return userService.setAddressStatusById(openid, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- setAddressStatusById 修改默认收货地址 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 删除收货地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/delAddressStatusById")
	@ResponseBody
	public Map<String, Object> delAddressStatusById(String openid, Long id, Integer status) throws Exception {
		try {
			return userService.delAddressStatusById(openid, id, status);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- delAddressStatusById 删除收货地址 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 用户收藏商品列表
	 * 
	 * @param pageNum
	 *            当前页
	 *            
	 * @param openid
	 *            用户id
	 *            
	 * @return 分页数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryCollectionProductList")
	@ResponseBody
	public Map<String, Object> queryCollectionProductList(Integer pageNum, String openid) throws Exception {
		try {
			return userService.queryCollectionProductList(pageNum, openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryCollectionProductList 用户收藏商品列表查询 异常" + e.toString());
			throw e;
		}
	}
	
	/**
	 * 删除收藏
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/delCollectionById")
	@ResponseBody
	public Map<String, Object> delCollectionById(String openid, Long productId) throws Exception {
		try {
			return userService.delCollectionById(openid, productId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- delCollectionById 删除收藏 异常" + e.toString());
			throw e;
		}
	}
}
