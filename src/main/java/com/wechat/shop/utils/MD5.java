package com.wechat.shop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@SuppressWarnings("restriction")
public class MD5 {

	/**
	 * MD5 加密openid
	 * 
	 * @param openid
	 * @return
	 */
	public static String getMD5OpenId(String openid) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(openid.getBytes());
			return Base64.encode(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5OpenId("123456"));
	}
}
