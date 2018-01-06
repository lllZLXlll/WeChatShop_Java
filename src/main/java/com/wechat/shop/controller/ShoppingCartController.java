package com.wechat.shop.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.shop.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

	private Logger logger = Logger.getLogger(ShoppingCartController.class);

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 购物车列表
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

	@RequestMapping("/queryShoppingCartList")
	@ResponseBody
	public Map<String, Object> queryShoppingCartList(Integer pageNum, String openid) throws Exception {
		try {
			return shoppingCartService.queryShoppingCartList(pageNum, openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryShoppingCartList 购物车列表 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 购物车列表
	 * 
	 * @param id
	 *            购物车表id
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/delShoppingCartList")
	@ResponseBody
	public Map<String, Object> delShoppingCartList(String array, String openid) throws Exception {
		try {
			return shoppingCartService.delShoppingCartList(array, openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- delShoppingCartList 删除购物车商品 异常" + e.toString());
			throw e;
		}
	}

}
