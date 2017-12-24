package com.wechat.shop.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @author ZLX
 *
 *         静态常量，配置常量类
 *
 */

@Configuration
@ConfigurationProperties(prefix = "wechat")
@PropertySource("classpath:config.properties")
public class Config {

	public static String appId;
	public static String appSecret;
	public static String oponIdApi;

	public static String getAppId() {
		return appId;
	}

	public static void setAppId(String appId) {
		Config.appId = appId;
	}

	public static String getAppSecret() {
		return appSecret;
	}

	public static void setAppSecret(String appSecret) {
		Config.appSecret = appSecret;
	}

	public static String getOponIdApi() {
		// 替换url中的参数，因为appSecret会更改
		oponIdApi = oponIdApi.replace("APPID", getAppId());
		oponIdApi = oponIdApi.replace("APPSECRET", getAppSecret());
		return oponIdApi;
	}

	public static void setOponIdApi(String oponIdApi) {
		Config.oponIdApi = oponIdApi;
	}

}
