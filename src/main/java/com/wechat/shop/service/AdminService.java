package com.wechat.shop.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

	String adminLogin(HttpServletRequest request, String accountNumber, String password, String code);

	String exitLogin(HttpServletRequest request);

}
