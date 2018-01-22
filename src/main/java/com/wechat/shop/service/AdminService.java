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

	Map<String, Object> homeBannerAdd(String tabid, Integer productId, String image, Integer status, Integer sort, Integer type);

	void queryAdminSelectProductList(Model model, Integer pageCurrent, String _productName, Integer pageSize, Integer pageSize2);

}
