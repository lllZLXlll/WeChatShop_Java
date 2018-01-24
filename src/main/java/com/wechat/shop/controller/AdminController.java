package com.wechat.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	/**
	 * 管理员退出登录
	 */
	@RequestMapping("/changePwdInit")
	public String changePwdInit() {
		return "/index/changePwd";
	}

	/**
	 * 管理员修改密码
	 */
	@RequestMapping("/changePwd")
	@ResponseBody
	public Map<String, Object> changePwd(HttpServletRequest request, String password, String newPassword,
			String secPassword) {
		try {
			return adminService.changePwd(request, password, newPassword, secPassword);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- changePwd 管理员修改登陆密码 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 查询首页banner
	 */
	@RequestMapping("/homeBanner")
	public String homeBanner(Model model, String tabid, Integer pageCurrent, Integer pageSize) {
		try {
			adminService.homeData(model, tabid, 1, pageCurrent, pageSize);
			return "/home/home-banner";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeBanner 查询首页banner 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 管理员修改banner状态
	 */
	@RequestMapping("/homeBannerUpdateStatus")
	@ResponseBody
	public Map<String, Object> homeBannerUpdateStatus(Integer id) {
		try {
			return adminService.homeBannerUpdateStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeBannerUpdateStatus 管理员修改banner状态 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 编辑首页banner，页面跳转
	 */
	@RequestMapping("/homeBannerEditInit")
	public String homeBannerEditInit(Model model, String tabid, Integer id, Integer pageCurrent, Integer pageSize) {
		adminService.homeBannerEditInit(model, tabid, id, pageCurrent, pageSize);
		return "/home/home-banner-edit";
	}

	/**
	 * 编辑首页banner
	 */
	@RequestMapping("/homeBannerEdit")
	@ResponseBody
	public Map<String, Object> homeBannerEdit(String tabid, Integer id, Integer productId, String image, Integer status,
			Integer sort) {
		try {
			return adminService.homeBannerEdit(tabid, id, productId, image, status, sort);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeBannerEdit 编辑首页banner 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 添加首页banner，页面跳转
	 */
	@RequestMapping("/homeBannerAddInit")
	public String homeBannerAddInit(Model model, String tabid, Integer pageCurrent, Integer pageSize) {
		adminService.homeBannerAddInit(model, tabid, pageCurrent, pageSize);
		return "/home/home-banner-add";
	}

	/**
	 * 查询商品信息让页面选择
	 */
	@RequestMapping("/queryAdminSelectProductList")
	public String queryAdminSelectProductList(Model model, Integer _productId, String _productName, Integer pageCurrent,
			Integer pageSize) {
		adminService.queryAdminSelectProductList(model, _productId, _productName, pageCurrent, pageSize);
		return "/home/home-banner-product";
	}

	/**
	 * 添加首页banner
	 */
	@RequestMapping("/homeBannerAdd")
	@ResponseBody
	public Map<String, Object> homeBannerAdd(String tabid, Integer id, Integer productId, String image, Integer status,
			Integer sort) {
		try {
			return adminService.homeBannerAdd(tabid, productId, image, status, sort);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeBannerAdd 添加首页banner 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 查询首页推荐商品
	 */
	@RequestMapping("/homeRecommended")
	public String homeRecommended(Model model, String tabid, Integer pageCurrent, Integer pageSize) {
		try {
			adminService.homeRecommended(model, tabid, 1, pageCurrent, pageSize);
			return "/home/home-recommended";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeRecommended 查询首页推荐商品 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 添加首页推荐商品，页面跳转
	 */
	@RequestMapping("/homeRecommendedAddInit")
	public String homeRecommendedAddInit(Model model, String tabid, Integer pageCurrent, Integer pageSize) {
		adminService.homeRecommendedAddInit(model, tabid, pageCurrent, pageSize);
		return "/home/home-recommended-add";
	}

	/**
	 * 添加首页推荐商品
	 */
	@RequestMapping("/homeRecommendedAdd")
	@ResponseBody
	public Map<String, Object> homeRecommendedAdd(String tabid, Integer id, Integer productId, Integer status,
			Integer sort) {
		try {
			return adminService.homeRecommendedAdd(tabid, productId, status, sort);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeRecommendedAdd 添加首页推荐商品 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 管理员修改推荐商品状态
	 */
	@RequestMapping("/homeRecommendedUpdateStatus")
	@ResponseBody
	public Map<String, Object> homeRecommendedUpdateStatus(Integer id) {
		try {
			return adminService.homeRecommendedUpdateStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeRecommendedUpdateStatus 管理员修改推荐商品状态 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 编辑首页推荐商品，页面跳转
	 */
	@RequestMapping("/homeRecommendedEditInit")
	public String homeRecommendedEditInit(Model model, String tabid, Integer id, Integer pageCurrent,
			Integer pageSize) {
		adminService.homeRecommendedEditInit(model, tabid, id, pageCurrent, pageSize);
		return "/home/home-recommended-edit";
	}

	/**
	 * 编辑首页推荐商品
	 */
	@RequestMapping("/homeRecommendedEdit")
	@ResponseBody
	public Map<String, Object> homeRecommendedEdit(String tabid, Integer id, Integer productId, Integer status,
			Integer sort) {
		try {
			return adminService.homeRecommendedEdit(tabid, id, productId, status, sort);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeRecommendedEdit 编辑首页推荐商品 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 查询商品类型
	 */
	@RequestMapping("/productType")
	public String productType(Model model, String tabid, Integer pageCurrent, Integer pageSize) {
		try {
			adminService.productType(model, tabid, 1, pageCurrent, pageSize);
			return "/product/product-type";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- productType 查询首页推荐商品 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 管理员修改商品类型状态
	 */
	@RequestMapping("/productTypeUpdateStatus")
	@ResponseBody
	public Map<String, Object> productTypeUpdateStatus(Integer id) {
		try {
			return adminService.productTypeUpdateStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- productTypeUpdateStatus 管理员修改类型状态 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 添加商品类型，页面跳转
	 */
	@RequestMapping("/productTypeAddInit")
	public String productTypeAddInit(Model model, String tabid) {
		adminService.productTypeAddInit(model, tabid);
		return "/product/product-type-add";
	}

	/**
	 * 添加商品类型
	 */
	@RequestMapping("/productTypeAdd")
	@ResponseBody
	public Map<String, Object> productTypeAdd(String tabid, String type, String detils, Integer status, Integer sort) {
		try {
			return adminService.productTypeAdd(tabid, type, detils, status, sort);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- homeRecommendedAdd 添加首页推荐商品 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 编辑商品类型，页面跳转
	 */
	@RequestMapping("/productTypeEditInit")
	public String productTypeEditInit(Model model, String tabid, Integer id) {
		adminService.productTypeEditInit(model, tabid, id);
		return "/home/home-recommended-edit";
	}

	/**
	 * 编辑商品类型
	 */
	@RequestMapping("/productTypeAddEdit")
	@ResponseBody
	public Map<String, Object> productTypeAddEdit(String tabid, Integer id, String type, String detils, Integer status,
			Integer sort) {
		try {
			return adminService.productTypeAddEdit(tabid, id, type, detils, status, sort);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- productTypeAddEdit 编辑商品类型 异常" + e.toString());
			throw e;
		}
	}

}
