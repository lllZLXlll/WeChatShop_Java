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

	void homeData(Model model, int type, Integer pageCurrent);

}
