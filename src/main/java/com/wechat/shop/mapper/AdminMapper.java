package com.wechat.shop.mapper;

import java.util.List;
import java.util.Map;

public interface AdminMapper {

	Map<String, Object> checkAdminLogin(String accountNumber, String password);

	int changePwd(long adminId, String newPassword);

	int checkPassword(long adminId, String password);

	List<Map<String, Object>> homeData(Integer pageBeginNum, Integer pageSize);

	Integer homeDataCount();

	int homeBannerUpdateStatus(Integer id);

	Map<String, Object> queryHomeBannerById(Integer id);

	int homeBannerEdit(Integer id, Integer productId, String image, Integer status, Integer sort);

	Integer queryHomeBannerMaxSort();

	int homeBannerAdd(Integer productId, String image, Integer status, Integer sort);

	List<Map<String, Object>> homeRecommended(Integer pageBeginNum, Integer pageSize);

	Integer homeRecommendedCount();

	Integer queryHomeRecommendedMaxSort();

	int homeRecommendedAdd(Integer productId, Integer status, Integer sort);

	int homeRecommendedUpdateStatus(Integer id);

	Map<String, Object> queryHomeRecommendedById(Integer id);

	int homeRecommendedEdit(Integer id, Integer productId, Integer status, Integer sort);

	List<Map<String, Object>> productType(Integer pageBeginNum, Integer pageSize);

	Integer productTypeCount();

	int productTypeUpdateStatus(Integer id);

	int productTypeAdd(String type, String detils, Integer status, Integer sort);

	Map<String, Object> queryProductTypeById(Integer id);

	int productTypeAddEdit(Integer id, String type, String detils, Integer status, Integer sort);

	Integer queryProductTypeMaxSort();

	List<Map<String, Object>> productInfo(Integer pageBeginNum, Integer pageSize, String name, Integer typeId, Integer downShelves);

	Integer productInfoCount(String name, Integer typeId, Integer downShelves);

	List<Map<String, Object>> productTypeList();

	int addProductInfo(Map<String, Object> productMap);

	int addProductImg(long productId, String image);

	Map<String, Object> queryProductInfoById(Integer id);

	List<Map<String, Object>> queryProductImgById(Integer id);

	long editProductInfo(Map<String, Object> productMap);

	long delProductImg(Long id);

	int productInfoUpdateStatus(Long id);

	int queryProductClassCountById(Long id);

	List<Map<String, Object>> productClass(Integer pageBeginNum, Integer pageSize, Integer id);

	Integer productClassCount(Integer id);

	long addProductClass(Map<String, Object> productMap);

}
