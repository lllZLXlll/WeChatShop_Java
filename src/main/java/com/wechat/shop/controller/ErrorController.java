package com.wechat.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.shop.common.ReturnCode;

/**
 * 
 * @author zlx
 *
 *         异常处理
 */
@ControllerAdvice
public class ErrorController {

	private static Logger logger = Logger.getLogger(ErrorController.class);

	// 异常时返回json信息
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map<String, Object> resultError(Exception e) {
		logger.error("---!!!---出现异常处理ErrorController，异常信息为：" + e.toString() + "---!!!---");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ReturnCode.ERROR, ReturnCode.RETURN_FAIL_CODE_0007);
		resultMap.put(ReturnCode.MESSAGE, ReturnCode.FAIL_0007_MESSAGE);
		return resultMap;
	}

}
