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

		List<Map<String, Object>> list = adminMapper.homeData(page.getPageBeginNum(), page.getPageSize());
		Integer pageTotalCount = adminMapper.homeDataCount();

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
		Integer maxSort = adminMapper.queryHomeBannerMaxSort();
		if (maxSort == null)
			maxSort = 1;

		queryAdminSelectProductList(model, null, null, pageNum, pageSize);

		model.addAttribute("tabid", tabid);
		model.addAttribute("sort", maxSort);
	}

	@Override
	public Map<String, Object> homeBannerAdd(String tabid, Integer productId, String image, Integer status,
			Integer sort) {
		image = PamarParse.getParseString(image);

		if (image == null || image.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先选择图片", "", "");
		if (productId == null || status == null || sort == null)
			return BJUI.ajaxDoneInfo("300", "参数为空", "", "");

		int result = adminMapper.homeBannerAdd(productId, image, status, sort);

		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "添加失败，请稍后重试", "", "");

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public void queryAdminSelectProductList(Model model, Integer _productId, String _productName, Integer pageNum,
			Integer pageSize) {
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

	@Override
	public void homeRecommended(Model model, String tabid, int type, Integer pageNum, Integer pageSize) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		List<Map<String, Object>> list = adminMapper.homeRecommended(page.getPageBeginNum(), page.getPageSize());
		Integer pageTotalCount = adminMapper.homeRecommendedCount();

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("page", page);
		model.addAttribute("tabid", tabid);
	}

	@Override
	public void homeRecommendedAddInit(Model model, String tabid, Integer pageNum, Integer pageSize) {
		// 查询当前最大序号
		Integer maxSort = adminMapper.queryHomeRecommendedMaxSort();
		if (maxSort == null)
			maxSort = 1;

		queryAdminSelectProductList(model, null, null, pageNum, pageSize);

		model.addAttribute("tabid", tabid);
		model.addAttribute("sort", maxSort);
	}

	@Override
	public Map<String, Object> homeRecommendedAdd(String tabid, Integer productId, Integer status, Integer sort) {

		if (productId == null || status == null || sort == null)
			return BJUI.ajaxDoneInfo("300", "参数为空", "", "");

		int result = adminMapper.homeRecommendedAdd(productId, status, sort);

		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "添加失败，请稍后重试", "", "");

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public Map<String, Object> homeRecommendedUpdateStatus(Integer id) {
		// 参数校验
		if (id == null)
			return BJUI.ajaxDoneInfo("300", "参数为空，修改失败", "", "");

		// 修改状态
		int result = adminMapper.homeRecommendedUpdateStatus(id);

		if (!(result > 0)) {
			return BJUI.ajaxDoneInfo("300", "删除失败", "", "");
		}

		return BJUI.ajaxDoneInfo("200", "删除成功", "", "");
	}

	@Override
	public void homeRecommendedEditInit(Model model, String tabid, Integer id, Integer pageNum, Integer pageSize) {
		// 编辑商品信息
		Map<String, Object> bannerMap = adminMapper.queryHomeRecommendedById(id);

		queryAdminSelectProductList(model, null, null, pageNum, pageSize);

		model.addAttribute("tabid", tabid);
		model.addAttribute("item", bannerMap);
	}

	@Override
	public Map<String, Object> homeRecommendedEdit(String tabid, Integer id, Integer productId, Integer status,
			Integer sort) {

		if (id == null || productId == null || status == null || sort == null)
			return BJUI.ajaxDoneInfo("300", "参数为空", "", "");

		int result = adminMapper.homeRecommendedEdit(id, productId, status, sort);

		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "修改失败，请稍后重试", "", "");

		return BJUI.ajaxDoneInfo("200", "修改成功", "dialog", tabid);
	}

	@Override
	public void productType(Model model, String tabid, int type, Integer pageNum, Integer pageSize) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		List<Map<String, Object>> list = adminMapper.productType(page.getPageBeginNum(), page.getPageSize());
		Integer pageTotalCount = adminMapper.productTypeCount();

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("page", page);
		model.addAttribute("tabid", tabid);
	}

	@Override
	public Map<String, Object> productTypeUpdateStatus(Integer id) {
		// 参数校验
		if (id == null)
			return BJUI.ajaxDoneInfo("300", "参数为空，修改失败", "", "");

		// 修改状态
		int result = adminMapper.productTypeUpdateStatus(id);

		if (!(result > 0)) {
			return BJUI.ajaxDoneInfo("300", "删除失败", "", "");
		}

		return BJUI.ajaxDoneInfo("200", "删除成功", "", "");
	}

	@Override
	public Map<String, Object> productTypeAdd(String tabid, String type, String detils, Integer status, Integer sort) {
		type = PamarParse.getParseString(type);
		detils = PamarParse.getParseString(detils);

		if (type == null || type.equals(""))
			return BJUI.ajaxDoneInfo("300", "请填写类型", "", "");
		if (detils == null || detils.equals(""))
			return BJUI.ajaxDoneInfo("300", "请填写类型描述", "", "");
		if (status == null || sort == null)
			return BJUI.ajaxDoneInfo("300", "参数为空", "", "");

		int result = adminMapper.productTypeAdd(type, detils, status, sort);

		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "添加失败，请稍后重试", "", "");

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public void productTypeEditInit(Model model, String tabid, Integer id) {
		// 编辑商品信息
		Map<String, Object> productTypeMap = adminMapper.queryProductTypeById(id);

		model.addAttribute("tabid", tabid);
		model.addAttribute("item", productTypeMap);
	}

	@Override
	public Map<String, Object> productTypeAddEdit(String tabid, Integer id, String type, String detils, Integer status,
			Integer sort) {
		type = PamarParse.getParseString(type);
		detils = PamarParse.getParseString(detils);

		if (type == null || type.equals(""))
			return BJUI.ajaxDoneInfo("300", "请填写类型", "", "");
		if (detils == null || detils.equals(""))
			return BJUI.ajaxDoneInfo("300", "请填写类型描述", "", "");

		if (id == null || status == null || sort == null)
			return BJUI.ajaxDoneInfo("300", "参数为空", "", "");

		int result = adminMapper.productTypeAddEdit(id, type, detils, status, sort);

		if (!(result > 0))
			return BJUI.ajaxDoneInfo("300", "修改失败，请稍后重试", "", "");

		return BJUI.ajaxDoneInfo("200", "修改成功", "dialog", tabid);
	}

	@Override
	public void productTypeAddInit(Model model, String tabid) {
		Integer maxSort = adminMapper.queryProductTypeMaxSort();
		if (maxSort == null)
			maxSort = 1;

		model.addAttribute("tabid", tabid);
		model.addAttribute("sort", maxSort);
	}

	@Override
	public void productInfo(Model model, String tabid, Integer pageNum, Integer pageSize, String name, Integer typeId,
			Integer downShelves) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		List<Map<String, Object>> list = adminMapper.productInfo(page.getPageBeginNum(), page.getPageSize(), name,
				typeId, downShelves);
		Integer pageTotalCount = adminMapper.productInfoCount(name, typeId, downShelves);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		// 商品分类信息
		List<Map<String, Object>> typeList = adminMapper.productTypeList();

		model.addAttribute("name", name);
		model.addAttribute("typeId", typeId);
		model.addAttribute("downShelves", downShelves);
		model.addAttribute("typeList", typeList);
		model.addAttribute("page", page);
		model.addAttribute("tabid", tabid);
	}

	@Override
	public void productInfoAddInit(Model model, String tabid) {
		List<Map<String, Object>> typeList = adminMapper.productTypeList();

		model.addAttribute("tabid", tabid);
		model.addAttribute("typeList", typeList);
	}

	@Override
	public Map<String, Object> productInfoAdd(String tabid, String name, String productImage, Integer typeId,
			Double price, Double showPrice, Double expressFee, Integer buyCount, String[] detilsImage) {
		name = PamarParse.getParseString(name);
		productImage = PamarParse.getParseString(productImage);
		price = PamarParse.getParseDouble(price);
		showPrice = PamarParse.getParseDouble(showPrice);
		expressFee = PamarParse.getParseDouble(expressFee);

		// 参数校验
		if (detilsImage != null && detilsImage.length <= 0)
			return BJUI.ajaxDoneInfo("300", "请先上传商品详情图", "", "");
		if (name == null || name.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写商品名称", "", "");
		if (productImage == null || productImage.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先上传商品主图", "", "");
		if (typeId == null || typeId == -1)
			return BJUI.ajaxDoneInfo("300", "请先选择分类", "", "");
		if (price == null || price == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写价格", "", "");
		if (showPrice == null || showPrice == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写展示价格", "", "");
		if (expressFee == null || expressFee == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写快递费", "", "");
		if (buyCount == null)
			return BJUI.ajaxDoneInfo("300", "请先限购数量", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("id", "");
		productMap.put("name", name);
		productMap.put("productImage", productImage);
		productMap.put("typeId", typeId);
		productMap.put("price", price);
		productMap.put("showPrice", showPrice);
		productMap.put("expressFee", expressFee);
		productMap.put("buyCount", buyCount);

		// 将基本信息写入商品信息表
		long result = adminMapper.addProductInfo(productMap);
		if (result <= 0)
			throw new RuntimeException("添加商品失败");

		// 将商品详情图循环写入商品图片表
		for (String image : detilsImage) {
			image = PamarParse.getParseString(image);
			if (image == null || image.equals(""))
				continue;
			result = adminMapper.addProductImg(PamarParse.getParseLong(productMap.get("id")), image);
			if (result <= 0)
				throw new RuntimeException("添加商品失败");
		}

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public void productInfoEditInit(Model model, String tabid, Integer id) {
		// 商品基本信息
		Map<String, Object> productMap = adminMapper.queryProductInfoById(id);

		// 商品图片信息
		List<Map<String, Object>> imageList = adminMapper.queryProductImgById(id);
		Map<String, Object> imageMap = new HashMap<String, Object>();
		for (int i = 0; i < 5; i++) {
			try {
				imageMap.put("image_" + (i + 1), imageList.get(i).get("image"));
			} catch (Exception e) {
				imageMap.put("image_" + (i + 1), "");
			}
		}

		// 商品分类信息
		List<Map<String, Object>> typeList = adminMapper.productTypeList();

		model.addAttribute("tabid", tabid);
		model.addAttribute("product", productMap);
		model.addAttribute("image", imageMap);
		model.addAttribute("typeList", typeList);
	}

	@Override
	public Map<String, Object> productInfoEdit(String tabid, Long id, String name, String productImage, Integer typeId,
			Double price, Double showPrice, Double expressFee, Integer buyCount, String[] detilsImage) {
		name = PamarParse.getParseString(name);
		productImage = PamarParse.getParseString(productImage);
		price = PamarParse.getParseDouble(price);
		showPrice = PamarParse.getParseDouble(showPrice);
		expressFee = PamarParse.getParseDouble(expressFee);

		// 参数校验
		if (detilsImage != null && detilsImage.length <= 0)
			return BJUI.ajaxDoneInfo("300", "请先上传商品详情图", "", "");
		if (name == null || name.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写商品名称", "", "");
		if (productImage == null || productImage.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先上传商品主图", "", "");
		if (typeId == null || typeId == -1)
			return BJUI.ajaxDoneInfo("300", "请先选择分类", "", "");
		if (price == null || price == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写价格", "", "");
		if (showPrice == null || showPrice == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写展示价格", "", "");
		if (expressFee == null || expressFee == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写快递费", "", "");
		if (buyCount == null)
			return BJUI.ajaxDoneInfo("300", "请先限购数量", "", "");
		if (id == null)
			return BJUI.ajaxDoneInfo("300", "商品id为空", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("id", id);
		productMap.put("name", name);
		productMap.put("productImage", productImage);
		productMap.put("typeId", typeId);
		productMap.put("price", price);
		productMap.put("showPrice", showPrice);
		productMap.put("expressFee", expressFee);
		productMap.put("buyCount", buyCount);

		// 将基本信息写入商品信息表
		long result = adminMapper.editProductInfo(productMap);
		if (result <= 0)
			throw new RuntimeException("修改商品失败");

		// 将原先的图片删除，再插入原来的图片
		result = adminMapper.delProductImg(id);
		if (result <= 0)
			throw new RuntimeException("修改商品失败");

		result = -1;
		// 将商品详情图循环写入商品图片表
		for (String image : detilsImage) {
			image = PamarParse.getParseString(image);
			if (image == null || image.equals(""))
				continue;
			result = adminMapper.addProductImg(id, image);
			if (result <= 0)
				throw new RuntimeException("修改商品失败");
		}
		if (result <= 0)
			throw new RuntimeException("至少要有一张商品详情图");

		return BJUI.ajaxDoneInfo("200", "修改成功", "dialog", tabid);
	}

	@Override
	public Map<String, Object> productInfoUpdateStatus(Long id) {
		id = PamarParse.getParseLong(id);
		if (id == null || id == -1)
			return BJUI.ajaxDoneInfo("300", "id为空", "", "");

		// 查询是否此商品还有商品分类信息，如果有将不能删除
		int count = adminMapper.queryProductClassCountById(id);
		if (count > 0) {
			return BJUI.ajaxDoneInfo("300", "该商品分类中还有商品数据，不能删除！", "", "");
		}

		int result = adminMapper.productInfoUpdateStatus(id);
		if (result <= 0)
			return BJUI.ajaxDoneInfo("300", "删除失败", "", "");

		return BJUI.ajaxDoneInfo("200", "删除成功", "", "");
	}

	@Override
	public void productClass(Model model, String tabid, Integer pageNum, Integer pageSize, Integer id) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		List<Map<String, Object>> list = adminMapper.productClass(page.getPageBeginNum(), page.getPageSize(), id);
		Integer pageTotalCount = adminMapper.productClassCount(id);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("id", id);
		model.addAttribute("page", page);
		model.addAttribute("tabid", tabid);

	}

	@Override
	public Map<String, Object> productClassAdd(String tabid, Long productId, String className, String productImage,
			Double price, Integer count) {
		productId = PamarParse.getParseLong(productId);
		className = PamarParse.getParseString(className);
		productImage = PamarParse.getParseString(productImage);
		price = PamarParse.getParseDouble(price);
		count = PamarParse.getParseInteger(count);

		// 参数校验
		if (className == null || className.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写商品名称", "", "");
		if (productImage == null || productImage.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先上传商品分类图", "", "");
		if (productId == null || productId == -1)
			return BJUI.ajaxDoneInfo("300", "参数为空，请关闭此标签重试", "", "");
		if (price == null || price == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写价格", "", "");
		if (count == null || count == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写库存", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("productId", productId);
		productMap.put("class", className);
		productMap.put("productImage", productImage);
		productMap.put("price", price);
		productMap.put("count", count);

		// 将基本信息写入商品信息表
		long result = adminMapper.addProductClass(productMap);
		if (result <= 0)
			throw new RuntimeException("添加失败，请重试");

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public void productClassEditInit(Model model, String tabid, Integer id) {
		// 商品基本信息
		Map<String, Object> productMap = adminMapper.queryProductClassById(id);

		model.addAttribute("tabid", tabid);
		model.addAttribute("item", productMap);
	}

	@Override
	public Map<String, Object> productClassEdit(String tabid, Long id, String className, String productImage,
			Double price, Integer count) {
		id = PamarParse.getParseLong(id);
		className = PamarParse.getParseString(className);
		productImage = PamarParse.getParseString(productImage);
		price = PamarParse.getParseDouble(price);
		count = PamarParse.getParseInteger(count);

		// 参数校验
		if (className == null || className.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写商品名称", "", "");
		if (productImage == null || productImage.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先上传商品分类图", "", "");
		if (id == null || id == -1)
			return BJUI.ajaxDoneInfo("300", "参数为空，请关闭此标签重试", "", "");
		if (price == null || price == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写价格", "", "");
		if (count == null || count == -1)
			return BJUI.ajaxDoneInfo("300", "请先填写库存", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("id", id);
		productMap.put("class", className);
		productMap.put("productImage", productImage);
		productMap.put("price", price);
		productMap.put("count", count);

		// 将基本信息写入商品信息表
		long result = adminMapper.editProductClass(productMap);
		if (result <= 0)
			throw new RuntimeException("修改失败");

		return BJUI.ajaxDoneInfo("200", "修改成功", "dialog", tabid);
	}

	@Override
	public void productParam(Model model, String tabid, Integer pageNum, Integer pageSize, Integer id) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		List<Map<String, Object>> list = adminMapper.productParam(page.getPageBeginNum(), page.getPageSize(), id);
		Integer pageTotalCount = adminMapper.productParamCount(id);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("id", id);
		model.addAttribute("page", page);
		model.addAttribute("tabid", tabid);

	}

	@Override
	public Map<String, Object> productParamAdd(String tabid, Long productId, String key, String detail) {
		productId = PamarParse.getParseLong(productId);
		key = PamarParse.getParseString(key);
		detail = PamarParse.getParseString(detail);

		// 参数校验
		if (key == null || key.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写参数名称", "", "");
		if (detail == null || detail.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写参数描述", "", "");
		if (productId == null || productId == -1)
			return BJUI.ajaxDoneInfo("300", "参数为空，请关闭此标签重试", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("productId", productId);
		productMap.put("paramKey", key);
		productMap.put("detail", detail);

		long result = adminMapper.productParamAdd(productMap);
		if (result <= 0)
			throw new RuntimeException("添加失败");

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public void productParamEditInit(Model model, String tabid, Integer id) {
		// 商品基本信息
		Map<String, Object> productMap = adminMapper.queryProductParamById(id);

		model.addAttribute("tabid", tabid);
		model.addAttribute("item", productMap);
	}

	@Override
	public Map<String, Object> productParamEdit(String tabid, Long id, String key, String detail) {
		id = PamarParse.getParseLong(id);
		key = PamarParse.getParseString(key);
		detail = PamarParse.getParseString(detail);

		// 参数校验
		if (key == null || key.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写参数名称", "", "");
		if (detail == null || detail.equals(""))
			return BJUI.ajaxDoneInfo("300", "请先填写参数描述", "", "");
		if (id == null || id == -1)
			return BJUI.ajaxDoneInfo("300", "参数为空，请关闭此标签重试", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("id", id);
		productMap.put("paramKey", key);
		productMap.put("detail", detail);

		long result = adminMapper.productParamEdit(productMap);
		if (result <= 0)
			throw new RuntimeException("修改失败");

		return BJUI.ajaxDoneInfo("200", "修改成功", "dialog", tabid);
	}

	@Override
	public Map<String, Object> productParamDel(Long id) {
		long result = adminMapper.productParamDel(id);
		if (result <= 0)
			throw new RuntimeException("修改失败");

		return BJUI.ajaxDoneInfo("200", "修改成功", "", "");
	}

	@Override
	public void productImageText(Model model, String tabid, Integer pageNum, Integer pageSize, Integer id) {
		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);
		if (pageSize != null)
			page.setPageSize(pageSize);

		List<Map<String, Object>> list = adminMapper.productImageText(page.getPageBeginNum(), page.getPageSize(), id);
		Integer pageTotalCount = adminMapper.productImageTextCount(id);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		model.addAttribute("id", id);
		model.addAttribute("page", page);
		model.addAttribute("tabid", tabid);

	}

	@Override
	public Map<String, Object> productImageTextAdd(String tabid, Long productId, String detail, String image) {
		productId = PamarParse.getParseLong(productId);
		detail = PamarParse.getParseString(detail);
		image = PamarParse.getParseString(image);

		if ((detail == null || detail.equals("")) && (image == null || image.equals("")))
			return BJUI.ajaxDoneInfo("300", "文字描述与图片至少填写其中一项", "", "");
		if (productId == null || productId == -1)
			return BJUI.ajaxDoneInfo("300", "参数为空，请关闭此标签重试", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("productId", productId);
		productMap.put("detail", detail);
		productMap.put("image", image);

		// 将基本信息写入商品信息表
		long result = adminMapper.productImageTextAdd(productMap);
		if (result <= 0)
			throw new RuntimeException("添加失败，请重试");

		return BJUI.ajaxDoneInfo("200", "添加成功", "dialog", tabid);
	}

	@Override
	public void productImageTextEditInit(Model model, String tabid, Integer id) {
		// 商品基本信息
		Map<String, Object> productMap = adminMapper.queryProductImageTextById(id);

		model.addAttribute("tabid", tabid);
		model.addAttribute("item", productMap);
	}

	@Override
	public Map<String, Object> productImageTextEdit(String tabid, Long id, String detail, String image) {
		id = PamarParse.getParseLong(id);
		image = PamarParse.getParseString(image);
		detail = PamarParse.getParseString(detail);

		// 参数校验
		if ((detail == null || detail.equals("")) && (image == null || image.equals("")))
			return BJUI.ajaxDoneInfo("300", "文字描述与图片至少填写其中一项", "", "");
		if (id == null || id == -1)
			return BJUI.ajaxDoneInfo("300", "参数为空，请关闭此标签重试", "", "");

		Map<String, Object> productMap = new HashMap<>();
		productMap.put("id", id);
		productMap.put("image", image);
		productMap.put("detail", detail);

		long result = adminMapper.productImageTextEdit(productMap);
		if (result <= 0)
			throw new RuntimeException("修改失败");

		return BJUI.ajaxDoneInfo("200", "修改成功", "dialog", tabid);
	}

	@Override
	public Map<String, Object> productImageTextDel(Long id) {
		long result = adminMapper.productImageTextDel(id);
		if (result <= 0)
			throw new RuntimeException("修改失败");

		return BJUI.ajaxDoneInfo("200", "修改成功", "", "");
	}

}
