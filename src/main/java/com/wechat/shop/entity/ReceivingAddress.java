package com.wechat.shop.entity;

import java.util.Date;

/**
 * 
 * @author ZLX
 *
 *         收货地址记录表
 */
public class ReceivingAddress {

	// 收货地址id
	private Long id;
	// 用户id 对应user表中id
	private Long userId;
	// 收货人姓名
	private String userName;
	// 收货人手机号码
	private String telNumber;
	// 邮编
	private String postalCode;
	// 国标收货地址第一级地址
	private String provinceName;
	// 国标收货地址第二级地址
	private String cityName;
	// 国标收货地址第三级地址
	private String countyName;
	// 详细收货地址信息
	private String detailInfo;
	// 收货地址国家码
	private String nationalCode;
	// 常用收货地址 0：否，1：是
	private Integer status;
	// 添加地址时间
	private Date addAddressTime;

	public ReceivingAddress() {
	}

	public ReceivingAddress(Long userId, String userName, String telNumber, String postalCode, String provinceName,
			String cityName, String countyName, String detailInfo, String nationalCode, Integer status,
			Date addAddressTime) {
		this.userId = userId;
		this.userName = userName;
		this.telNumber = telNumber;
		this.postalCode = postalCode;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.countyName = countyName;
		this.detailInfo = detailInfo;
		this.nationalCode = nationalCode;
		this.status = status;
		this.addAddressTime = addAddressTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddAddressTime() {
		return addAddressTime;
	}

	public void setAddAddressTime(Date addAddressTime) {
		this.addAddressTime = addAddressTime;
	}

}
