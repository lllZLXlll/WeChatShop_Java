package com.wechat.shop.entity;

import java.util.Date;

public class User {
	// 用户数字id标示
	private Long id;

	// 微信平台用户唯一标示
	private String openid;

	// 本次登录的会话密钥,刷新登录状态后的密钥会改变
	private String session_key;

	// 微信昵称
	private String nickName;

	// 性别 0：未知，1：男，女：2
	private Integer gender;

	// 用户头像
	private String avatarUrl;

	// 用户所在国家
	private String country;

	// 用户所在省份
	private String province;

	// 用户所在城市
	private String city;

	// 用户第一次登录时间
	private Date firstLoginTime;

	// 用户最近一次登录时间
	private Date lastLoginTime;

	public User() {
	}

	public User(String openid, String session_key, String nickName, Integer gender, String avatarUrl, String country,
			String province, String city, Date firstLoginTime, Date lastLoginTime) {
		this.openid = openid;
		this.session_key = session_key;
		this.nickName = nickName;
		this.gender = gender;
		this.avatarUrl = avatarUrl;
		this.country = country;
		this.province = province;
		this.city = city;
		this.firstLoginTime = firstLoginTime;
		this.lastLoginTime = lastLoginTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getFirstLoginTime() {
		return firstLoginTime;
	}

	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
