package com.wechat.shop.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {

	// 分页查询商品列表
	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort);

}
