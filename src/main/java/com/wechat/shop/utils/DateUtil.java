package com.wechat.shop.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 时间工具类
 * 
 * @author zlx
 *
 */
public class DateUtil {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(createOrder());
	}

	// 日期格式
	private static final SimpleDateFormat DATEFORMAT_TIME = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	// 日期格式
	private static final SimpleDateFormat DATEFORMAT_Y_M_D_H_M_S = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

	// 日历实例
	private static final Calendar CALENDAR = Calendar.getInstance();

	/**
	 * 根据当前时间戳加上5位随机数字生成订单号
	 * 
	 * @return 唯一订单号
	 */
	public static String createOrder() {
		// 获得当前时间戳
		Date date = CALENDAR.getTime();
		String dateTime = DATEFORMAT_TIME.format(date);

		// 获得随机5位数字 确保订单号唯一性
		int max = 99999;
		int min = 10000;
		Random random = new Random();
		int randomInt = random.nextInt(max - min) + min;

		return dateTime + randomInt;
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static Date getDateTime() {
		return CALENDAR.getTime();
	}

}
