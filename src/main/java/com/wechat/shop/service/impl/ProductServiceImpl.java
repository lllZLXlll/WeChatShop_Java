package com.wechat.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.shop.common.ReturnCode;
import com.wechat.shop.entity.ReceivingAddress;
import com.wechat.shop.entity.User;
import com.wechat.shop.mapper.ProductMapper;
import com.wechat.shop.mapper.ReceivingAddressMapper;
import com.wechat.shop.mapper.UserMapper;
import com.wechat.shop.service.ProductService;
import com.wechat.shop.utils.Arith;
import com.wechat.shop.utils.DateUtil;
import com.wechat.shop.utils.GetRequestJsonUtils;
import com.wechat.shop.utils.Page;
import com.wechat.shop.utils.PamarParse;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ReceivingAddressMapper receivingAddressMapper;

	@Override
	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort,
			String productClass) {
		Map<String, Object> resultMap = new HashMap<>();

		Page page = new Page();
		page.setPageNum(pageNum == null ? 1 : pageNum);

		List<Map<String, Object>> list = productMapper.queryProductList(page.getPageBeginNum(), page.getPageSize(),
				name, salesVolumeSort, priceSort, productClass);
		Integer pageTotalCount = productMapper.queryProductListCount(name, productClass);

		page.setPage(list);
		page.setPageTotalCount(pageTotalCount);

		resultMap.put(ReturnCode.PAGE, page);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryProductType() {
		Map<String, Object> resultMap = new HashMap<>();

		List<Map<String, Object>> list = productMapper.queryProductType();

		resultMap.put(ReturnCode.RESULTLIST, list);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryProductDetailInfoById(Long productId, String openidMd5) {
		Map<String, Object> resultMap = new HashMap<>();

		// 商品基本信息
		Map<String, Object> productInfoMap = productMapper.queryProductDetailInfoById(productId);

		// 商品图片信息
		productInfoMap.put("productImages", productMapper.queryProductDetailImagesById(productId));

		// 商品分类信息
		productInfoMap.put("productCalsss", productMapper.queryProductDetailClasssById(productId));

		// 商品参数信息
		productInfoMap.put("productParams", productMapper.queryProductDetailParamsById(productId));

		// 商品参数信息
		productInfoMap.put("productImageText", productMapper.queryProductDetailImgTextById(productId));

		// 检验用户登录
		if (openidMd5 == null || openidMd5.trim().length() == 0 || openidMd5.equals("null")) {
			// 无用户登录 返回未收藏商品
			productInfoMap.put("collectionProduct", "0");
		} else {
			// 有用户登录 查询是否收藏商品
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("productId", productId);
			paramMap.put("openidMd5", openidMd5);
			int collectionCount = productMapper.queryCollectionByProductId(paramMap);
			if (collectionCount > 0) {
				productInfoMap.put("collectionProduct", "1");
			} else {
				productInfoMap.put("collectionProduct", "0");
			}
		}

		resultMap.put(ReturnCode.RESULTMAP, productInfoMap);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> addCollectionProduct(Long productId, String openidMd5) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		int result = -1;

		// 验证商品id的真实性
		if (productId == null || openidMd5 == null || openidMd5.trim().length() == 0 || openidMd5.equals("null")) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0011_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0011);
			return resultMap;
		}

		int count = productMapper.checkProductById(productId);
		if (!(count > 0)) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0009_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0009);
			return resultMap;
		}

		// 判断用户是否已收藏过该商品，如果收藏了再点击收藏则删除收藏的商品
		dataMap.put("productId", productId);
		dataMap.put("openidMd5", openidMd5);
		int collectionCount = productMapper.queryCollectionByProductId(dataMap);
		// 删除已收藏的商品
		if (collectionCount > 0) {
			result = productMapper.delCollectionByProductId(dataMap);
			if (!(result > 0)) {
				// 删除失败
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0012_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0012);
			} else {
				// 删除成功
				resultMap.put("type", "0");
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
			}
			return resultMap;
		}

		// 验证用户的真实性
		User user = userMapper.checkOpenIdMd5(openidMd5);
		if (user != null) {
			// 添加收藏
			result = productMapper.addCollectionProduct(dataMap);

			if (!(result > 0)) {
				// 添加失败
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0010_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0010);
			} else {
				// 添加成功
				resultMap.put("type", "1");
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
			}
		} else {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> addShoppingCart(String openidMd5, Long productId, Long productClassId,
			Long productCount) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		int result = -1;

		// 验证商品id的真实性
		if (productId == null || openidMd5 == null || openidMd5.trim().length() == 0 || openidMd5.equals("null")) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0011_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0011);
			return resultMap;
		}

		int count = productMapper.checkProductById(productId);
		if (!(count > 0)) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0009_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0009);
			return resultMap;
		}

		// 验证用户的真实性
		User user = userMapper.checkOpenIdMd5(openidMd5);
		if (user != null) {
			// 添加购物车

			dataMap.put("openidMd5", openidMd5);
			dataMap.put("productId", productId);
			dataMap.put("productClassId", productClassId);
			dataMap.put("productCount", productCount);

			result = productMapper.addShoppingCart(dataMap);

			if (!(result > 0)) {
				// 添加失败
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0014_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0014);
			} else {
				// 添加成功
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.SUCCESS_0005_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
			}
		} else {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> queryOrderSettlementInfo(String openidMd5) {
		Map<String, Object> resultMap = new HashMap<>();

		if (openidMd5 == null || openidMd5.trim().length() == 0 || openidMd5.equals("null")) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0011_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0011);
			return resultMap;
		}
		// 验证用户的真实性
		User user = userMapper.checkOpenIdMd5(openidMd5);
		if (user != null) {
			// 用户收货地址
			ReceivingAddress address = receivingAddressMapper.queryAddressByUserId(user.getId());

			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
			resultMap.put("address", address);
		} else {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> addOrder(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		// request中的json数据
		JSONObject json = GetRequestJsonUtils.getRequestJson(request);
		if (json == null) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0011_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0011);
			return resultMap;
		}

		// 订单号
		String order = DateUtil.createOrder();
		// 买家留言
		String describes = PamarParse.getParseString(json.get("describes"));
		// openid
		String openidMd5 = PamarParse.getParseString(json.get("openid"));
		// 地址id
		Long addressId = json.getLong("addressId");
		// 是否从购物车中结算
		Long isShoppingCart = json.getLong("isShoppingCart");

		// 商品数组
		JSONArray productArrayJson = json.getJSONArray("orderProducts");

		// 循环将订单插入表中
		for (int i = 0; i < productArrayJson.length(); i++) {
			JSONObject productJson = new JSONObject(productArrayJson.get(i).toString());
			Long productId = productJson.getLong("productId");
			Long productCount = productJson.getLong("productCount");
			Long productClassId = productJson.getLong("productClassId");

			// 校验数据
			// 验证商品id的真实性
			if (productId == null || productClassId == null || productCount == null || addressId == null
					|| openidMd5 == null || openidMd5.trim().length() == 0 || openidMd5.equals("null")) {
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0011_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0011);
				return resultMap;
			}

			int count = productMapper.checkProductById(productId);
			if (!(count > 0)) {
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0009_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0009);
				return resultMap;
			}

			// 商品信息
			Map<String, Object> productInfo = productMapper.queryProductDetailInfoById(productId);

			// 验证用户的真实性
			User user = userMapper.checkOpenIdMd5(openidMd5);
			if (user != null) {
				Map<String, Object> orderMap = new HashMap<String, Object>();
				double expressFee = Double.parseDouble(productInfo.get("expressFee").toString());
				double price = Double.parseDouble(productInfo.get("price").toString());
				double totalAmount = Arith.mul(price, productCount) + expressFee;

				orderMap.put("orderNumber", order);
				orderMap.put("orderCreateTime", DateUtil.getDateFormatYMDHMS());
				orderMap.put("openidMd5", openidMd5);
				orderMap.put("productId", productId);
				orderMap.put("productCount", productCount);
				orderMap.put("productClassId", productClassId);
				orderMap.put("expressFee", expressFee);
				orderMap.put("addressId", addressId);
				orderMap.put("totalAmount", totalAmount);
				orderMap.put("describes", describes);

				// 插入订单表
				int result = productMapper.addOrder(orderMap);
				if (result > 0) {
					// 返回订单号
					resultMap.put(ReturnCode.ORDER, order);
					resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
				} else {
					resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0017_MESSAGE);
					resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0017);
				}
				// 如果商品订单数量大于1件，说明是从购物车中进行结算的，删除购物车中对应的商品
				if (isShoppingCart != -1) {
					result = productMapper.delShoppingCart(productId, openidMd5);
					if (!(result > 0)) {
						resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0017_MESSAGE);
						resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0017);
					}
				}
			} else {
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
			}
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> queryOrderInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		// request中的json数据
		JSONObject json = GetRequestJsonUtils.getRequestJson(request);

		// 订单号
		String order = PamarParse.getParseString(json.get("order"));

		// 查询订单完整详情
		List<Map<String, Object>> orderList = productMapper.queryOrderInfo(order);
		Map<String, Object> orderMap = orderList.get(0);

		ReceivingAddress addressMap = receivingAddressMapper
				.queryAddressById(PamarParse.getParseLong(orderMap.get("addressId")));

		resultMap.put("orderInfo", orderList);
		resultMap.put("address", addressMap);
		resultMap.put(ReturnCode.ORDER, order);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> updateOrderStatus(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		// request中的json数据
		JSONObject json = GetRequestJsonUtils.getRequestJson(request);

		// 订单号
		String order = PamarParse.getParseString(json.get("order"));
		
		int result = productMapper.updateOrderStatus(order);
		
		if (result > 0) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.SUCCESS_0007_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		} else {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0018_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0018);
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> delOrder(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		// request中的json数据
		JSONObject json = GetRequestJsonUtils.getRequestJson(request);

		// 订单号
		String order = PamarParse.getParseString(json.get("order"));
		
		int result = productMapper.delOrder(order);
		
		if (result > 0) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.SUCCESS_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		} else {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0019_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0019);
		}
		
		return resultMap;
	}

}
