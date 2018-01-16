package com.wechat.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

	public List<Map<String, Object>> queryCollectionProductList(Integer pageBeginNum, Integer pageSize, String openid);

	public Integer queryCollectionProductListCount(String openid);

	public int addShoppingCart(Map<String, Object> dataMap);

	public List<Map<String, Object>> queryShoppingCartList(Integer pageBeginNum, Integer pageSize, String openidMd5);

	public Integer queryShoppingCartListCount(String openidMd5);

	public int delShoppingCartList(@Param("array") String[] array, @Param("openidMd5") String openidMd5);
	
	public int delShoppingCart(Long productId, String openidMd5);

	public Map<String, Object> queryOrderSettlementInfo(Long productId, Long productClassId);

	public int addOrder(Map<String, Object> orderMap);

	public Map<String, Object> queryOrderInfo(String order);

	public int updateOrderStatus(String order);

	public int delOrder(String order);

	public List<Map<String, Object>> queryAllOrder(String openidMd5, Integer integer, Integer integer2);

	public Map<String, Object> queryProductDetailInfoByIdClassId(Long productId, Long productClassId);

	public int addOrderInfo(Map<String, Object> orderMap);

	public List<Map<String, Object>> queryOrderProductInfoByOrder(String order);

	public int delShoppingCartById(Long shoppingCartId);

	public Integer queryAllOrderCount(String openidMd5);

}
