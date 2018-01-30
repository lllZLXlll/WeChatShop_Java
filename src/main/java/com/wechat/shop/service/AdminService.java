package com.wechat.shop.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface AdminService {

	String adminLogin(HttpServletRequest request, String accountNumber, String password, String code);

	String exitLogin(HttpServletRequest request);

	Map<String, Object> changePwd(HttpServletRequest request, String password, String newPassword, String secPassword);

	void homeData(Model model, String tabid, int type, Integer pageCurrent, Integer pageSize);

	Map<String, Object> homeBannerUpdateStatus(Integer id);

	void homeBannerEditInit(Model model, String tabid, Integer id, Integer pageCurrent, Integer pageSize);

	Map<String, Object> homeBannerEdit(String tabid, Integer id, Integer productId, String image, Integer status, Integer sort);

	void homeBannerAddInit(Model model, String tabid, Integer pageCurrent, Integer pageSize);

	Map<String, Object> homeBannerAdd(String tabid, Integer productId, String image, Integer status, Integer sort);

	void queryAdminSelectProductList(Model model, Integer pageCurrent, String _productName, Integer pageSize, Integer pageSize2);

	void homeRecommended(Model model, String tabid, int i, Integer pageCurrent, Integer pageSize);

	void homeRecommendedAddInit(Model model, String tabid, Integer pageCurrent, Integer pageSize);

	Map<String, Object> homeRecommendedAdd(String tabid, Integer productId, Integer status, Integer sort);

	Map<String, Object> homeRecommendedUpdateStatus(Integer id);

	void homeRecommendedEditInit(Model model, String tabid, Integer id, Integer pageCurrent, Integer pageSize);

	Map<String, Object> homeRecommendedEdit(String tabid, Integer id, Integer productId, Integer status,
			Integer sort);

	void productType(Model model, String tabid, int i, Integer pageCurrent, Integer pageSize);

	Map<String, Object> productTypeUpdateStatus(Integer id);

	Map<String, Object> productTypeAdd(String tabid, String type, String detils, Integer status, Integer sort);

	void productTypeEditInit(Model model, String tabid, Integer id);

	Map<String, Object> productTypeAddEdit(String tabid, Integer id, String type, String detils, Integer status, Integer sort);

	void productTypeAddInit(Model model, String tabid);

	void productInfo(Model model, String tabid, Integer pageCurrent, Integer pageSize, String name, Integer typeId, Integer downShelves);

	void productInfoAddInit(Model model, String tabid);

	Map<String, Object> productInfoAdd(String tabid, String name, String productImage, Integer typeId, Double price,
			Double showPrice, Double expressFee, Integer buyCount, String[] detilsImage);

	void productInfoEditInit(Model model, String tabid, Integer id);

	Map<String, Object> productInfoEdit(String tabid, Long id, String name, String productImage, Integer typeId, Double price,
			Double showPrice, Double expressFee, Integer buyCount, String[] detilsImage);

}
