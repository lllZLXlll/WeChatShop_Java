package com.wechat.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

// 此类使用注解所有方法启用事务
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	private Logger logger = Logger.getLogger(ProductServiceImpl.class);

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
		Map<String, Object> orderMap = new HashMap<>();
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
		// 购物车id
		Long shoppingCartId = null;
		// 商品数组
		JSONArray productArrayJson = json.getJSONArray("orderProducts");
		// 商品总数量
		int _productCount = 0;
		// 订单快递费用
		double _expressFee = 0;
		// 循环次数
		int forCount = 0;
		// 订单总金额
		double totalAmount = 0;

		// 循环插入订单信息表中
		for (int i = 0, j = productArrayJson.length(); i < j; i++) {
			JSONObject productJson = new JSONObject(productArrayJson.get(i).toString());
			Long productId = productJson.getLong("productId");
			Long productCount = productJson.getLong("productCount");
			Long productClassId = productJson.getLong("productClassId");
			_productCount += productCount;

			if (isShoppingCart == 1)
				shoppingCartId = productJson.getLong("id");

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
			Map<String, Object> productInfo = productMapper.queryProductDetailInfoByIdClassId(productId,
					productClassId);
			// 验证用户的真实性
			User user = userMapper.checkOpenIdMd5(openidMd5);

			if (user != null) {
				orderMap = new HashMap<String, Object>();
				double expressFee = Double.parseDouble(productInfo.get("expressFee").toString());
				double price = Double.parseDouble(productInfo.get("price").toString());
				double showPrice = Double.parseDouble(productInfo.get("showPrice").toString());

				orderMap.put("orderNumber", order);
				orderMap.put("openidMd5", openidMd5);
				orderMap.put("productId", productId);
				orderMap.put("productName", productInfo.get("name").toString());
				orderMap.put("productShowPrice", showPrice);
				orderMap.put("productCount", productCount);
				orderMap.put("productClassId", productClassId);
				orderMap.put("productClassName", productInfo.get("class").toString());
				orderMap.put("productPrice", price);
				orderMap.put("productImage", productInfo.get("productImage").toString());

				// 插入订单明细表
				int result = productMapper.addOrderInfo(orderMap);
				if (!(result > 0)) {
					resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0017_MESSAGE);
					resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0017);
					return resultMap;
				}
				// 从购物车中进行结算，删除购物车中对应的商品
				if (isShoppingCart != -1) {
					result = productMapper.delShoppingCartById(shoppingCartId);
					if (!(result > 0)) {
						resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0017_MESSAGE);
						resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0017);
						return resultMap;
					}
				}

				/**
				 * 一笔订单有多个商品，如每件商品都需要快递费则取快递费最低的，但不能等于0；
				 * 如有一件商品快递费用大于0而其他几件商品运费等于0则取大于0的运费 （有一笔运费大于0的，就去运费大于0的）
				 */
				if (expressFee > 0 && forCount == 0) {
					_expressFee = expressFee;
					forCount++;
				}
				// 如果有大于0，并且更低的快递费用就设置商品快递费为更低的
				if (expressFee > 0 && expressFee < _expressFee) {
					_expressFee = expressFee;
				}
				// 计算总金额
				totalAmount = Arith.add(Arith.mul(price, productCount), totalAmount);
			} else {
				resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
				resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
				return resultMap;
			}
		}

		// 将数据插入订单表
		orderMap = new HashMap<String, Object>();
		orderMap.put("orderNumber", order);
		orderMap.put("orderCreateTime", DateUtil.getDateFormatYMDHMS());
		orderMap.put("openidMd5", openidMd5);
		orderMap.put("productCount", _productCount);
		orderMap.put("expressFee", _expressFee);
		orderMap.put("totalAmount", totalAmount + _expressFee);
		orderMap.put("addressId", addressId);
		orderMap.put("describes", describes);
		// 插入订单明细表
		int result = productMapper.addOrder(orderMap);
		if (!(result > 0)) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0017_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0017);
			return resultMap;
		}

		// 返回订单号
		resultMap.put(ReturnCode.ORDER, order);
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		return resultMap;
	}

	@Override
	public Map<String, Object> queryOrderInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		// request中的json数据
		JSONObject json = GetRequestJsonUtils.getRequestJson(request);

		// 订单号
		String order = PamarParse.getParseString(json.get("order"));
		// openid
		String openidMd5 = PamarParse.getParseString(json.get("openid"));
		// 验证用户的真实性
		User user = userMapper.checkOpenIdMd5(openidMd5);
		if (user == null) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
			return resultMap;
		}

		// 查询订单基本信息
		Map<String, Object> orderMap = productMapper.queryOrderInfo(order);

		// 订单相关商品信息
		List<Map<String, Object>> productLIst = productMapper.queryOrderProductInfoByOrder(order);

		// 收货地址信息
		ReceivingAddress addressMap = receivingAddressMapper
				.queryAddressById(PamarParse.getParseLong(orderMap.get("addressId")));

		resultMap.put("orderInfo", orderMap);
		resultMap.put("productLIst", productLIst);
		resultMap.put("address", addressMap);
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
		// openid
		String openidMd5 = PamarParse.getParseString(json.get("openid"));
		// 验证用户的真实性
		User user = userMapper.checkOpenIdMd5(openidMd5);
		if (user == null) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
			return resultMap;
		}

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

	@Override
	public Map<String, Object> queryAllOrder(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		// request中的json数据
		JSONObject json = GetRequestJsonUtils.getRequestJson(request);
		if (json == null) {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0011_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0011);
			return resultMap;
		}

		// 订单号
		String openidMd5 = "";
		// 当前页
		Integer pageNum = 1;
		// 订单状态
		Integer orderType = null;
		try {
			openidMd5 = PamarParse.getParseString(json.get("openid"));
			pageNum = PamarParse.getParseInteger(json.get("pageNum"));
			orderType = PamarParse.getParseInteger(json.get("orderType"));
		} catch (Exception e) {
		}

		// 验证用户的真实性
		User user = userMapper.checkOpenIdMd5(openidMd5);
		if (user != null) {
			// 查询所有订单
			Page page = new Page();
			if (pageNum == null || pageNum == -1)
				page.setPageNum(1);
			else
				page.setPageNum(pageNum);

			// 查询订单基本信息
			List<Map<String, Object>> orderList = productMapper.queryAllOrder(openidMd5, page.getPageBeginNum(),
					page.getPageSize(), orderType);
			// 查询总条数
			Integer pageTotalCount = productMapper.queryAllOrderCount(openidMd5, orderType);

			// 循环根据订单基本信息查询订单对应商品信息集合
			for (Map<String, Object> orderMap : orderList) {
				List<Map<String, Object>> productLIst = productMapper
						.queryOrderProductInfoByOrder(orderMap.get("orderNumber").toString());
				// 将订单号对应的商品信息组装到page中
				orderMap.put("productLIst", productLIst);
			}

			page.setPage(orderList);
			page.setPageTotalCount(pageTotalCount);
			resultMap.put(ReturnCode.PAGE, page);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_SUCCESS_CODE);
		} else {
			resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0008_MESSAGE);
			resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0008);
		}

		return resultMap;
	}

}
