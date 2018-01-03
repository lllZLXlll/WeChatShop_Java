package com.wechat.shop.mapper;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

	// 分页查询商品列表
	public List<Map<String, Object>> queryProductList(Integer pageNum, Integer pageSize, String name,
			String salesVolumeSort, String priceSort, String productClass);

	public Integer queryProductListCount(String name, String productClass);

	public List<Map<String, Object>> queryProductType();

	public Map<String, Object> queryProductDetailInfoById(Long productId);

	public List<Map<String, Object>> queryProductDetailImagesById(Long productId);

	public List<Map<String, Object>> queryProductDetailClasssById(Long productId);

	public List<Map<String, Object>> queryProductDetailParamsById(Long productId);

	public List<Map<String, Object>> queryProductDetailImgTextById(Long productId);

	public int checkProductById(Long productId);

	public int addCollectionProduct(Map<String, Object> dataMap);

	public int queryCollectionByProductId(Map<String, Object> dataMap);

	public int delCollectionByProductId(Map<String, Object> dataMap);

}
