package com.wechat.shop.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

import org.json.JSONObject;

//实名认证
public class IDcard {
	private final static String MALL_ID = "110543";
	private final static String APPKEY = "bd477703eb7a8ecb380ac25f05ce8ed4";
	private final static String URL = "http://121.41.42.121:8080/v2/id-server?";

	public int idcard_verify(String realname, String idcard) throws Exception {

		idcard = idcard.toLowerCase();
		long tm = System.currentTimeMillis();
		String md5_param = MALL_ID + realname + idcard + tm + APPKEY;
		String sign = md5(md5_param);
		String param = new StringBuffer().append("mall_id=" + MALL_ID).append("&realname=" + realname)
				.append("&idcard=" + idcard)

				.append("&tm=" + tm).append("&sign=" + sign).toString();
		String url_v = URL + param;
		url_v = url_v.replace(realname, URLEncoder.encode(realname, "UTF-8"));
		String json = url2string(url_v);

		String jsonString = new String(json.getBytes(), "UTF-8");
		JSONObject result = new JSONObject(jsonString);
		System.out.println(result);

		int status = Integer.parseInt(result.getString("status"));
		int code = Integer.parseInt(result.getJSONObject("data").getString("code"));

		/* 客户可以根据自己的业务需求进行处理 */
		if (status == 2001) {

		}
		// 正常情况下不需要处理，商家也可以根据自己的业务进行处理
		else if (status == 2002) {
			// 2002=第三方服务器异常

		} else if (status == 2003) {
			// 2003=服务器维护

		} else if (status == 2004) {
			// 2004=账号余额不足

		} else if (status == 2005) {
			// 2005=参数异常

		}
		// 1000=一致
		// 1001=不一致
		// 1002=库中无此号
		// 1101=商家ID不合法
		// 1102=身份证姓名不合法
		// 1103=身份证号码不合法
		// 1104=签名不合法
		// 1105=第三方服务器异常
		// 1106=账户余额不足
		// 1107=tm不合法
		// 1108=其他异常
		// 1109=账号被暂停

		return code;
	}

	private String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String url2string(String url) {
		StringBuffer sb = new StringBuffer();
		try {
			InputStream is = new URL(url).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		IDcard demo = new IDcard();
		demo.idcard_verify("宋华", "362101197009032824");
	}

}
