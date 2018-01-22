package com.wechat.shop.service.impl;

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
import com.wechat.shop.mapper.AdminMapper;
import com.wechat.shop.mapper.ProductMapper;
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

	@Autowired
	private ProductMapper productMapper;

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
	public void homeData(Model model, String tabid, int type, Integer pageNum, Integer pageSize) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		List<Map<String, Object>> list = adminMapper.homeData(page.getPageBeginNum(), page.getPageSize(), type);
		Integer pageTotalCount = adminMapper.homeDataCount(type);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("page", page);
		model.addAttribute("tabid", tabid);
	}

	@Override
	public Map<String, Object> homeBannerUpdateStatus(Integer id) {
		// 参数校验
		if (id == null)
			return BJUI.ajaxDoneInfo("300", "参数为空，修改失败", "", "");

		// 修改状态
		int result = adminMapper.homeBannerUpdateStatus(id);

		if (!(result > 0)) {
			return BJUI.ajaxDoneInfo("300", "删除失败", "", "");
		}

		return BJUI.ajaxDoneInfo("200", "删除成功", "", "");
	}

	@Override
	public void homeBannerEditInit(Model model, String tabid, Integer id, Integer pageNum, Integer pageSize) {
		// 编辑商品信息
		Map<String, Object> bannerMap = adminMapper.queryHomeBannerById(id);

		queryAdminSelectProductList(model, null, null, pageNum, pageSize);

		model.addAttribute("tabid", tabid);
		model.addAttribute("item", bannerMap);
	}

	@Override
	public Map<String, Object> homeBannerEdit(String tabid, Integer id, Integer productId, String image, Integer status,
			Integer sort) {
		image = PamarParse.getParseString(image);

		if (image == null || image.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先选择图片", "", "");
		if (id == null || productId == null || status == null || sort == null)
			return BJUI.ajaxDoneInfo("300", "参数为空", "", "");

		int result = adminMapper.homeBannerEdit(id, productId, image, status, sort);

		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "修改失败，请稍后重试", "", "");

		return BJUI.ajaxDoneInfo("200", "修改成功", "dialog", tabid);
	}

	@Override
	public void homeBannerAddInit(Model model, String tabid, Integer pageNum, Integer pageSize) {
		// 查询当前最大序号
		int maxSort = adminMapper.queryHomeBannerMaxSort();

		queryAdminSelectProductList(model, null, null, pageNum, pageSize);

		model.addAttribute("tabid", tabid);
		model.addAttribute("sort", maxSort);
	}

	@Override
	public Map<String, Object> homeBannerAdd(String tabid, Integer productId, String image, Integer status,
			Integer sort, Integer type) {
		image = PamarParse.getParseString(image);

		if (image == null || image.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先选择图片", "", "");
		if (type == null || productId == null || status == null || sort == null)
			return BJUI.ajaxDoneInfo("300", "参数为空", "", "");

		int result = adminMapper.homeBannerAdd(productId, image, status, sort, type);

		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "添加失败，请稍后重试", "", "");

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public void queryAdminSelectProductList(Model model, Integer _productId, String _productName, Integer pageNum, Integer pageSize) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		// 查询商品信息，以供页面上选择
		List<Map<String, Object>> productLIst = productMapper.queryAdminSelectProductList(page.getPageBeginNum(),
				page.getPageSize(), _productName);
		int pageTotalCount = productMapper.queryAdminSelectProductListCount(_productName);

		page.setPage(productLIst);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("page", page);
		// 从编辑页面传入的商品id，传回给home-banner-product.jsp页面，如果商品列表中的id与此id相等，让单选框选中
		model.addAttribute("_productId", _productId);
		model.addAttribute("_productName", _productName);
	}

}
