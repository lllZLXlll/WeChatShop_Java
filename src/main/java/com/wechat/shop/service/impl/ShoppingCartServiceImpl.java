package com.wechat.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.mapper.ProductMapper;
import com.wechat.shop.service.ShoppingCartService;
import com.wechat.shop.utils.Page;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public Map<String, Object> queryShoppingCartList(Integer pageNum, String openidMd5) {
		Map<String, Object> resultMap = new HashMap<>();

		// 用户是否登录
		if (openidMd5 == null || openidMd5.equals("null") || openidMd5.trim().length() == 0) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0015_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0015);
			return resultMap;
		}
		
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);

		List<Map<String, Object>> list = productMapper.queryShoppingCartList(page.getPageBeginNum(), page.getPageSize(),
				openidMd5);
		Integer pageTotalCount = productMapper.queryShoppingCartListCount(openidMd5);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		resultMap.put(ReturnCode.PAGE, page);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

}
