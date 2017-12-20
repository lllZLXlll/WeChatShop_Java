package com.wechat.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
	
	// 分页查询商品列表
	public List<Map<String, Object>> queryProductList(Integer pageNum, Integer pageSize, String name);

	public Integer queryProductListCount(@Param("name")String name);
}
