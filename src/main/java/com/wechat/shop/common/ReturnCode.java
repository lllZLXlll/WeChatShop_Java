package com.wechat.shop.common;

/**
 * 
 * @author ZLX
 *
 *         请求结果返回码
 *
 */
public class ReturnCode {
	/**
	 * 返回状态码 key
	 */
	public static final String ERROR = "error";

	/**
	 * 返回信息 key
	 */
	public static final String MESSAGE = "message";

	/**
	 * 返回List数据 key
	 */
	public static final String RESULTLIST = "resultList";

	/**
	 * 返回Map数据 key
	 */
	public static final String RESULTMAP = "resultMap";

	/**
	 * 返回分页数据 key
	 */
	public static final String PAGE = "page";
	
	/**
	 * 返回订单号 key
	 */
	public static final String ORDER = "order";

	/**
	 * 返回状态码
	 */

	/**
	 * 所有成功状态码 code-0000
	 */
	public static final String RETURN_SUCCESS_CODE = "code-0000";

	/**
	 * 登录失败
	 */
	public static final String RETURN_FAIL_CODE_0001 = "code-0001";

	/**
	 * 请求参数为空
	 */
	public static final String RETURN_FAIL_CODE_0002 = "code-0002";

	/**
	 * 添加地址失败
	 */
	public static final String RETURN_FAIL_CODE_0003 = "code-0003";

	/**
	 * openid 校验失败
	 */
	public static final String RETURN_FAIL_CODE_0004 = "code-0004";

	/**
	 * 修改默认地址失败
	 */
	public static final String RETURN_FAIL_CODE_0005 = "code-0005";

	/**
	 * 删除地址失败
	 */
	public static final String RETURN_FAIL_CODE_0006 = "code-0006";

	/**
	 * 服务器异常
	 */
	public static final String RETURN_FAIL_CODE_0007 = "code-0007";

	/**
	 * 无此用户信息
	 */
	public static final String RETURN_FAIL_CODE_0008 = "code-0008";

	/**
	 * 无此商品信息
	 */
	public static final String RETURN_FAIL_CODE_0009 = "code-0009";

	/**
	 * 添加收藏失败
	 */
	public static final String RETURN_FAIL_CODE_0010 = "code-0010";

	/**
	 * 参数校验失败
	 */
	public static final String RETURN_FAIL_CODE_0011 = "code-0011";

	/**
	 * 取消收藏失败
	 */
	public static final String RETURN_FAIL_CODE_0012 = "code-0012";

	/**
	 * 删除收藏失败
	 */
	public static final String RETURN_FAIL_CODE_0013 = "code-0013";

	/**
	 * 添加购物车失败
	 */
	public static final String RETURN_FAIL_CODE_0014 = "code-0014";
	
	/**
	 * 用户未登录
	 */
	public static final String RETURN_FAIL_CODE_0015 = "code-0015";
	
	/**
	 * 删除购物车失败
	 */
	public static final String RETURN_FAIL_CODE_0016 = "code-0016";
	
	/**
	 * 生成订单失败
	 */
	public static final String RETURN_FAIL_CODE_0017 = "code-0017";
	
	/**
	 * 取消订单失败
	 */
	public static final String RETURN_FAIL_CODE_0018 = "code-0018";
	
	/**
	 * 删除订单失败
	 */
	public static final String RETURN_FAIL_CODE_0019 = "code-0019";
	
	/**
	 * 商品已售罄
	 */
	public static final String RETURN_FAIL_CODE_0020 = "code-0020";
	
	/**
	 * 库存不足，请重新下单
	 */
	public static final String RETURN_FAIL_CODE_0021 = "code-0021";

	// --------------------------------------------------------------------------------------------------------------

	/**
	 * 返回信息
	 */

	// 成功
	
	/**
	 * 登录成功
	 */
	public static final String SUCCESS_0001_MESSAGE = "登录成功";

	/**
	 * 添加地址成功
	 */
	public static final String SUCCESS_0003_MESSAGE = "添加地址成功";

	/**
	 * 删除收藏成功
	 */
	public static final String SUCCESS_0004_MESSAGE = "删除收藏成功";
	
	/**
	 * 添加购物车成功
	 */
	public static final String SUCCESS_0005_MESSAGE = "添加购物车成功";
	
	/**
	 * 删除购物车成功
	 */
	public static final String SUCCESS_0006_MESSAGE = "删除购物车成功";
	
	/**
	 * 取消订单成功
	 */
	public static final String SUCCESS_0007_MESSAGE = "取消订单成功";
	
	/**
	 * 删除订单成功
	 */
	public static final String SUCCESS_0008_MESSAGE = "删除订单成功";

	// 失败
	
	/**
	 * 登录失败
	 */
	public static final String FAIL_0001_MESSAGE = "登录失败";

	/**
	 * 请求参数为空
	 */
	public static final String FAIL_0002_MESSAGE = "请求参数为空";

	/**
	 * 添加地址失败
	 */
	public static final String FAIL_0003_MESSAGE = "添加地址失败";

	/**
	 * openid 校验失败
	 */
	public static final String FAIL_0004_MESSAGE = "ID校验失败";

	/**
	 * 修改默认地址失败
	 */
	public static final String FAIL_0005_MESSAGE = "修改地址失败";

	/**
	 * 删除地址失败
	 */
	public static final String FAIL_0006_MESSAGE = "删除地址失败";

	/**
	 * 服务器异常
	 */
	public static final String FAIL_0007_MESSAGE = "服务器异常";

	/**
	 * 无此用户信息
	 */
	public static final String FAIL_0008_MESSAGE = "无此用户信息";

	/**
	 * 无此商品信息
	 */
	public static final String FAIL_0009_MESSAGE = "无此商品信息";

	/**
	 * 添加收藏失败
	 */
	public static final String FAIL_0010_MESSAGE = "添加收藏失败";

	/**
	 * 参数校验失败
	 */
	public static final String FAIL_0011_MESSAGE = "参数校验失败";

	/**
	 * 取消收藏失败
	 */
	public static final String FAIL_0012_MESSAGE = "取消收藏失败";

	/**
	 * 删除收藏失败
	 */
	public static final String FAIL_0013_MESSAGE = "删除收藏失败";

	/**
	 * 添加购物车失败
	 */
	public static final String FAIL_0014_MESSAGE = "添加购物车失败";
	
	/**
	 * 用户未登录
	 */
	public static final String FAIL_0015_MESSAGE = "用户未登录";
	
	/**
	 * 删除购物车失败
	 */
	public static final String FAIL_0016_MESSAGE = "删除购物车失败";
	
	/**
	 * 生成订单失败
	 */
	public static final String FAIL_0017_MESSAGE = "生成订单失败";
	
	/**
	 * 取消订单失败
	 */
	public static final String FAIL_0018_MESSAGE = "取消订单失败";
	
	/**
	 * 删除订单失败
	 */
	public static final String FAIL_0019_MESSAGE = "删除订单失败";
	
	/**
	 * 商品已售罄
	 */
	public static final String FAIL_0020_MESSAGE = "商品已售罄";
	
	/**
	 * 库存不足，请重新下单
	 */
	public static final String FAIL_0021_MESSAGE = "库存不足，请重新下单";


}
