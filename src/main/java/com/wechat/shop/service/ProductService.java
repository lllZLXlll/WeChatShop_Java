package com.wechat.shop.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {

	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort,
			String productClass);

	public Map<String, Object> queryProductType();

	public Map<String, Object> queryProductDetailInfoById(Long productId, String openid);

	public Map<String, Object> addCollectionProduct(Long productId, String openidMd5);

	public Map<String, Object> addShoppingCart(String openid, Long productId, Long productClassId, Long productCount);

	public Map<String, Object> queryOrderSettlementInfo(String openid);

	public Map<String, Object> addOrder(HttpServletRequest request) throws Exception;

	public Map<String, Object> queryOrderInfo(HttpServletRequest request) throws Exception;

	public Map<String, Object> updateOrderStatus(HttpServletRequest request) throws Exception;

	public Map<String, Object> delOrder(HttpServletRequest request) throws Exception;

	public Map<String, Object> queryAllOrder(HttpServletRequest request) throws Exception;

}
