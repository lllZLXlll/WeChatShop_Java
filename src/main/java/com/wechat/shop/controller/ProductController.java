package com.wechat.shop.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.shop.service.ProductService;

@Controller
public class ProductController {

	private Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * 用户登录获取信息保存
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/queryProductList")
	@ResponseBody
	public Map<String, Object> queryProductList() throws Exception {
		return productService.queryProductList();
	}

}
