package com.wechat.shop.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wechat.shop.common.Constants;
import com.wechat.shop.mapper.AdminMapper;
import com.wechat.shop.service.AdminService;
import com.wechat.shop.utils.MD5;

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

}
