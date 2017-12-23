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
	 * 返回状态码
	 */

	/**
	 * 所有成功状态码
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

	// --------------------------------------------------------------------------------------------------------------

	/**
	 * 返回信息
	 */

	/**
	 * 登录成功
	 */
	public static final String SUCCESS_0001_MESSAGE = "登录成功";

	/**
	 * 登录失败
	 */
	public static final String FAIL_0001_MESSAGE = "登录失败";

	/**
	 * 请求参数为空
	 */
	public static final String FAIL_0002_MESSAGE = "请求参数为空";

	/**
	 * 添加地址成功
	 */
	public static final String SUCCESS_0003_MESSAGE = "添加地址成功";

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
}
