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

}
