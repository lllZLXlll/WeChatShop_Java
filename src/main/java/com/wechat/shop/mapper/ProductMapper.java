package com.wechat.shop.mapper;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
	
	// 分页查询商品列表
	public List<Map<String, Object>> queryProductList();
}
