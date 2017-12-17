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
}
