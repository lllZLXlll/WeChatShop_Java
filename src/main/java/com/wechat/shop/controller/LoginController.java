package com.wechat.shop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);

	private static final String APPID = "wx03ef451c68c7c55b";
	private static final String APPSECRET = "4df8a047af38ae13c0c37e90eab04af8";
	private static final String LOGINAPI = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret="
			+ APPSECRET + "&grant_type=authorization_code&js_code=";

	@RequestMapping("/login")
	@ResponseBody
	public String login(String jdCode) throws Exception {
		// 获取用户在小程序登录成功后返回的code 拼接api获取session_key ，openid
		String loginApi = LOGINAPI + jdCode;
		logger.info("用户登录code: " + jdCode);

		// 调用api
		String result = sendGet(loginApi);

		if (result != null && result.trim().length() > 0) {
			JSONObject resultJson = new JSONObject(result);
			String session_key = resultJson.getString("session_key");
			String openid = resultJson.getString("openid");
			System.out.println("session_key: " + session_key);
			System.out.println("openid: " + openid);
		}
		return null;
	}

	public static String sendGet(String loginApi) {
		StringBuffer resultBuffer = null;
		// 构建请求参数
		HttpURLConnection con = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		// 发送请求
		try {
			URL url = new URL(loginApi);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(loginApi);
			osw.flush();
			
			// 读取返回内容
			resultBuffer = new StringBuffer();
			int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
			if (contentLength > 0) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String temp;
				while ((temp = br.readLine()) != null) {
					resultBuffer.append(temp);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					osw = null;
					throw new RuntimeException(e);
				} finally {
					if (con != null) {
						con.disconnect();
						con = null;
					}
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				} finally {
					if (con != null) {
						con.disconnect();
						con = null;
					}
				}
			}
		}

		return resultBuffer.toString();
	}

}
