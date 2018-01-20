package com.wechat.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wechat.shop.common.BJUI;
import com.wechat.shop.common.Constants;
import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.mapper.AdminMapper;
import com.wechat.shop.service.AdminService;
import com.wechat.shop.utils.MD5;
import com.wechat.shop.utils.Page;
import com.wechat.shop.utils.PamarParse;

//此类使用注解所有方法启用事务
@Transactional
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public String adminLogin(HttpServletRequest request, String accountNumber, String password, String code) {
		// 获得session
		HttpSession session = request.getSession(true);
		// session中的验证码
		String session_code = session.getAttribute(Constants.SESSION_CODE_IMAGE) == null ? null
				: session.getAttribute(Constants.SESSION_CODE_IMAGE).toString();

		if (session_code != null) {
			// 校验验证码
			if (!(code.toLowerCase().equals(session_code))) {
				session.setAttribute("accountNumber", accountNumber);
				session.setAttribute("message", "验证码不正确，请重新输入");
				return "redirect:/admin/loginInit";
			}

			if (accountNumber != null && accountNumber.trim().length() > 0) {
				if (accountNumber != null && accountNumber.trim().length() > 0) {
					// 密码进行md5加密
					password = MD5.getMD5OpenId(password);
					// 检验账号密码
					Map<String, Object> adminMap = adminMapper.checkAdminLogin(accountNumber, password);
					if (adminMap == null) {
						session.setAttribute("accountNumber", accountNumber);
						session.setAttribute("message", "账号或密码不正确，请重试");
						return "redirect:/admin/loginInit";
					} else {
						// 将验证码设为过期
						session.setAttribute(Constants.SESSION_CODE_IMAGE, null);
						session.setAttribute(Constants.SESSION_ADMIN, adminMap);
						return "redirect:/admin/index";
					}

				} else {
					session.setAttribute("message", "请先输入密码");
					return "redirect:/admin/loginInit";
				}
			} else {
				session.setAttribute("message", "请先输入账号");
				return "redirect:/admin/loginInit";
			}
		} else {
			session.setAttribute("accountNumber", accountNumber);
			session.setAttribute("message", "请先获取验证码");
			return "redirect:/admin/loginInit";
		}
	}

	@Override
	public String exitLogin(HttpServletRequest request) {
		// 获得session
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SESSION_ADMIN, null);
		return "redirect:/admin/loginInit";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> changePwd(HttpServletRequest request, String password, String newPassword,
			String secPassword) {
		int result = -1;
		password = PamarParse.getParseString(password);
		newPassword = PamarParse.getParseString(newPassword);
		secPassword = PamarParse.getParseString(secPassword);

		if (password == null || password.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先输入旧密码", "", "");
		if (newPassword == null || newPassword.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先输入新密码", "", "");
		if (secPassword == null || secPassword.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先输入确认密码", "", "");
		if (!secPassword.equals(newPassword))
			return BJUI.ajaxDoneInfo("300", "新密码与确认密码不一致", "", "");
		if (password.equals(newPassword))
			return BJUI.ajaxDoneInfo("300", "旧密码与新密码不能一致", "", "");

		// 获得session
		HttpSession session = request.getSession(true);
		Object admin = session.getAttribute(Constants.SESSION_ADMIN);
		if (admin == null) {
			return BJUI.ajaxDoneInfo("300", "登录超时，请重新登陆后再重试", "", "");
		}

		Map<String, Object> adminMap = (Map<String, Object>) admin;
		long adminId = PamarParse.getParseLong(adminMap.get("id"));

		// 判断旧密码
		password = MD5.getMD5OpenId(password);
		result = adminMapper.checkPassword(adminId, password);
		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "旧密码不正确", "", "");

		// 修改密码
		newPassword = MD5.getMD5OpenId(newPassword);
		result = adminMapper.changePwd(adminId, newPassword);

		// 判断是否成功
		if (result > 0)
			return BJUI.ajaxDoneInfo("200", "密码修改成功", "dialog", "");
		else
			return BJUI.ajaxDoneInfo("300", "密码修改失败", "", "");

	}

	@Override
	public void homeData(Model model, int type, Integer pageNum) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);

		List<Map<String, Object>> list = adminMapper.homeData(page.getPageBeginNum(), page.getPageSize(), type);
		Integer pageTotalCount = adminMapper.homeDataCount(type);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("page", page);
	}

}
