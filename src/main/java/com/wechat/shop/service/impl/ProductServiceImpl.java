package com.wechat.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.mapper.ProductMapper;
import com.wechat.shop.service.ProductService;
import com.wechat.shop.utils.Page;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public Map<String, Object> queryProductList(Integer pageNum, String name) {
		Map<String, Object> resultMap = new HashMap<>();

		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);

		List<Map<String, Object>> list = productMapper.queryProductList(page.getPageBeginNum(), page.getPageSize(), name);
		Integer pageTotalCount = productMapper.queryProductListCount();

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		resultMap.put(ReturnCode.PAGE, page);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

}
