package com.wechat.shop.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.shop.service.ProductService;

/**
 * 小程序首页
 * 
 * @author zlx
 *
 */

@Controller
@RequestMapping
public class HomeController {

	private Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private ProductService productService;

	/**
	 * 查询首页数据
	 */

	@RequestMapping("/queryHomeData")
	@ResponseBody
	public Map<String, Object> queryHomeData() throws Exception {
		try {
			return productService.queryHomeData();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryHomeData 查询首页数据 异常" + e.toString());
			throw e;
		}
	}
	
}
