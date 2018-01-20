package com.wechat.shop.common;

import java.util.HashMap;
import java.util.Map;

public class BJUI {
	
	public static Map<String, Object> ajaxDoneInfo(String statusCode, String message, String closeCurrent,
			String tabid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCode", statusCode);
		map.put("message", message);
		map.put("closeCurrent", closeCurrent);
		map.put("tabid", tabid);
		return map;
	}
}
