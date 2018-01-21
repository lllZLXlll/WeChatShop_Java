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

	private static final SimpleDateFormat DATEFORMAT_TIME = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	private static final SimpleDateFormat DATEFORMAT_Y_M_D_H_M_S = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

	private static final SimpleDateFormat DATEFORMAT_Y_M_D = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 根据当前时间戳加上5位随机数字生成订单号
	 * 
	 * @return 唯一订单号
	 */
	public static String createOrder() {
		// 获得当前时间戳
		String dateTime = DATEFORMAT_TIME.format(Calendar.getInstance().getTime());

		// 获得随机5位数字 确保订单号唯一性
		int max = 99999;
		int min = 10000;
		Random random = new Random();
		int randomInt = random.nextInt(max - min) + min;

		return dateTime + randomInt;
	}

	/**
	 * 获得当前时间戳
	 * 
	 * @return
	 */
	public static String getDateTimeStr() {
		return DATEFORMAT_TIME.format(Calendar.getInstance().getTime()).toString();
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static Date getDateTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获得当前格式化时间 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDateFormatYMD() {
		return DATEFORMAT_Y_M_D.format(Calendar.getInstance().getTime());
	}

	/**
	 * 获得当前格式化时间 yyyy-MM-dd HH-mm-ss
	 * 
	 * @return
	 */
	public static String getDateFormatYMDHMS() {
		return DATEFORMAT_Y_M_D_H_M_S.format(Calendar.getInstance().getTime());
	}

}
