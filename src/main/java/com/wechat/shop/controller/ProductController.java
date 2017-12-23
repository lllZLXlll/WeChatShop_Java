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
	 * 商品列表多条件查询
	 * 
	 * @param pageNum
	 *            当前页
	 * @param name
	 *            商品名称
	 * @param salesVolumeSort
	 *            销量排序 1：降序
	 * @param priceSort
	 *            价格排序 1：升序 2：降序
	 * @param productClass
	 *            商品类型
	 * @return 分页数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryProductList")
	@ResponseBody
	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort,
			String productClass) throws Exception {
		try {
			return productService.queryProductList(pageNum, name, salesVolumeSort, priceSort, productClass);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryProductList 请求异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 商品分类查询
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/queryProductType")
	@ResponseBody
	public Map<String, Object> queryProductType() throws Exception {
		try {
			return productService.queryProductType();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryProductType 请求异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 商品详细信息查询
	 * 
	 * @param productId
	 *            商品id
	 * @return 商品详情数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryProductDetailInfoById")
	@ResponseBody
	public Map<String, Object> queryProductDetailInfoById(Long productId) throws Exception {
		try {
			return productService.queryProductDetailInfoById(productId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryProductDetailInfoById 请求异常" + e.toString());
			throw e;
		}
	}

}
