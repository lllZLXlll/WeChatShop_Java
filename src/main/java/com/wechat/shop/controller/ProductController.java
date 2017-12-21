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
	 * pageNum			当前页
	 * name				商品名称
	 * salesVolumeSort	销量排序 	1：升序	2：降序
	 * priceSort		价格排序	1：升序	2：降序
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/queryProductList")
	@ResponseBody
	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort) throws Exception {
		return productService.queryProductList(pageNum, name, salesVolumeSort, priceSort);
	}

}
