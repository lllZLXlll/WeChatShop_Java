package com.wechat.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.entity.User;
import com.wechat.shop.mapper.ProductMapper;
import com.wechat.shop.mapper.UserMapper;
import com.wechat.shop.service.ProductService;
import com.wechat.shop.utils.Page;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private UserMapper userMapper;

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
	public Map<String, Object> queryProductDetailInfoById(Long productId, String openidMd5) {
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

		// 检验用户登录
		if (openidMd5 == null || openidMd5.trim().length() == 0 || openidMd5.equals("null")) {
			// 无用户登录 返回未收藏商品
			productInfoMap.put("collectionProduct", "0");
		} else {
			// 有用户登录 查询是否收藏商品
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("productId", productId);
			paramMap.put("openidMd5", openidMd5);
			int collectionCount = productMapper.queryCollectionByProductId(paramMap);
			if (collectionCount > 0) {
				productInfoMap.put("collectionProduct", "1");
			} else {
				productInfoMap.put("collectionProduct", "0");
			}
		}

		resultMap.put(ReturnCode.RESULTMAP, productInfoMap);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> addCollectionProduct(Long productId, String openidMd5) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		int result = -1;

		// 验证商品id的真实性
		if (productId == null || openidMd5 == null || openidMd5.trim().length() == 0 || openidMd5.equals("null")) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0011_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0011);
			return resultMap;
		}

		int count = productMapper.checkProductById(productId);
		if (!(count > 0)) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0009_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0009);
			return resultMap;
		}

		// 判断用户是否已收藏过该商品，如果收藏了再点击收藏则删除收藏的商品
		dataMap.put("productId", productId);
		dataMap.put("openidMd5", openidMd5);
		int collectionCount = productMapper.queryCollectionByProductId(dataMap);
		// 删除已收藏的商品
		if (collectionCount > 0) {
			result = productMapper.delCollectionByProductId(dataMap);
			if (!(result > 0)) {
				// 删除失败
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0012_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0012);
			} else {
				// 删除成功
				resultMap.put("type", "0");
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
			}
			return resultMap;
		}

		// 验证用户的真实性
		User user = userMapper.checkOpenIdMd5(openidMd5);
		if (user != null) {
			// 添加收藏
			result = productMapper.addCollectionProduct(dataMap);

			if (!(result > 0)) {
				// 添加失败
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0010_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0010);
			} else {
				// 添加成功
				resultMap.put("type", "1");
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
			}
		} else {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
		}

		return resultMap;
	}

}
