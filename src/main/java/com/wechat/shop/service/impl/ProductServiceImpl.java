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
	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort,
			String productClass) {
		Map<String, Object> resultMap = new HashMap<>();

		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);

		List<Map<String, Object>> list = productMapper.queryProductList(page.getPageBeginNum(), page.getPageSize(),
				name, salesVolumeSort, priceSort, productClass);
		Integer pageTotalCount = productMapper.queryProductListCount(name, productClass);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		resultMap.put(ReturnCode.PAGE, page);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryProductType() {
		Map<String, Object> resultMap = new HashMap<>();

		List<Map<String, Object>> list = productMapper.queryProductType();

		resultMap.put(ReturnCode.RESULTLIST, list);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryProductDetailInfoById(Long productId) {
		Map<String, Object> resultMap = new HashMap<>();

		// 商品基本信息
		Map<String, Object> productInfoMap = productMapper.queryProductDetailInfoById(productId);

		// 商品图片信息
		productInfoMap.put("productImages", productMapper.queryProductDetailImagesById(productId));
		
		// 商品分类信息
		productInfoMap.put("productCalsss", productMapper.queryProductDetailClasssById(productId));
		
		// 商品参数信息
		productInfoMap.put("productParams", productMapper.queryProductDetailParamsById(productId));
		
		// 商品参数信息
		productInfoMap.put("productImageText", productMapper.queryProductDetailImgTextById(productId));

		resultMap.put(ReturnCode.RESULTMAP, productInfoMap);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

}
