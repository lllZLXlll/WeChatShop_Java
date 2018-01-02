package com.wechat.shop.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {

	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort, String productClass);

	public Map<String, Object> queryProductType();

	public Map<String, Object> queryProductDetailInfoById(Long productId);

	public Map<String, Object> addCollectionProduct(Long productId, String openidMd5);

}
