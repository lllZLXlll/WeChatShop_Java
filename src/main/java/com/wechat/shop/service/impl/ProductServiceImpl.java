package com.wechat.shop.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.mapper.ProductMapper;
import com.wechat.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public Map<String, Object> queryProductList() {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put(ReturnCode.RESULTLIST, productMapper.queryProductList());
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

}
