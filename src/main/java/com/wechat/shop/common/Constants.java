package com.wechat.shop.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.wechat.shop.controller.LoginController;

/**
 * 
 * @author ZLX
 *
 *         静态常量，配置常量类
 *
 */

public class Constants {

	private static Logger logger = Logger.getLogger(Constants.class);

	private static Properties properties = new Properties();

	// 使用静态代码块让程序在初始化时获取到配置文件
	static {
		try {
			FileInputStream in = new FileInputStream("src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("程序初始化读取配置文件config.properties出现异常: " + e);
		}
	}

	// 小程序appid
	public final static String APPID = properties.getProperty("wechat.appId");

	// 小程序appSecret
	public final static String APPSECRET = properties.getProperty("wechat.appSecret");

	// 小程序 获取用户oponid api
	public final static String OPONIDAPI = getOponIdApi();

	private static String getOponIdApi() {
		// 替换url中的参数，因为appSecret会更改
		String oponIdApi = properties.getProperty("wechat.oponIdApi");
		oponIdApi = oponIdApi.replace("APPID", APPID);
		oponIdApi = oponIdApi.replace("APPSECRET", APPSECRET);
		return oponIdApi;
	}

}
