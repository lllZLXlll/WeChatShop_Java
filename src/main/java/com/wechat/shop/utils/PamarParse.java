package com.wechat.shop.utils;

/**
 * 参数转化工具类
 * 
 * @author ZLX
 *
 */
public class PamarParse {

	public static String getParseString(Object strObj) {
		String parseStr = null;
		if (strObj != null) {
			parseStr = strObj.toString();
			if (parseStr.equals("") || parseStr.equals("null") || parseStr.trim().length() == 0) {
				parseStr = "";
			}
		}
		return parseStr;
	}

	public static Long getParseLong(Object object) {
		Long parseLong = -1l;
		if (object != null) {
			try {
				parseLong = Long.parseLong(object.toString());
			} catch (Exception e) {
				parseLong = -1l;
			}
		}
		return parseLong;
	}

	public static Integer getParseInteger(Object object) {
		Integer parseLong = -1;
		if (object != null) {
			try {
				parseLong = Integer.parseInt(object.toString());
			} catch (Exception e) {
				parseLong = -1;
			}
		}
		return parseLong;
	}
}
