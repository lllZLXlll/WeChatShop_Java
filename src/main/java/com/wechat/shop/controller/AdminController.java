package com.wechat.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wechat.shop.common.Constants;
import com.wechat.shop.service.AdminService;
import com.wechat.shop.utils.VerifyCodeUtils;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

	private Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	/**
	 * 获取图片验证码
	 */
	@RequestMapping("/getAuthImage")
	public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SESSION_CODE_IMAGE, verifyCode.toLowerCase());
		logger.info("---!!!---后台管理系统登录，session验证码为：" + verifyCode);
		// 生成图片
		int w = 200, h = 80;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);

	}

	/**
	 * 登录初始化
	 */
	@RequestMapping("/loginInit")
	public String loginInit() {
		return "login/login";
	}

	/**
	 * 跳转后台管理首页
	 */
	@RequestMapping("/index")
	public String index() {
		return "index/index";
	}

	/**
	 * 管理员登录
	 */
	@RequestMapping("/adminLogin")
	public String adminLogin(HttpServletRequest request, String accountNumber, String password, String code) {
		return adminService.adminLogin(request, accountNumber, password, code);
	}

	/**
	 * 管理员退出登录
	 */
	@RequestMapping("/exitLogin")
	public String exitLogin(HttpServletRequest request) {
		return adminService.exitLogin(request);
	}

}
