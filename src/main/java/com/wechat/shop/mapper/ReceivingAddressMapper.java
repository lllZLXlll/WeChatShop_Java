package com.wechat.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wechat.shop.entity.ReceivingAddress;

public interface ReceivingAddressMapper {

	// 根据userId 查询常用地址
	public Long queryUserInfoByOpenid(Long userId);

	// 添加收货地址
	public Long addReceivingAddress(ReceivingAddress receivingAddress);

	public List<ReceivingAddress> queryReceivingAddressListById(Long userId);

	public Long setAddressStatusById(@Param("userId") Long userId, @Param("id") Long id);

	public long setAddressStatusByStatus(@Param("userId") Long userId);

	public long delAddressStatusById(@Param("userId") Long userId, @Param("id") Long id);

	public long setAddressStatusDefaultById(Long id);

	public ReceivingAddress queryAddressByUserId(Long userId);
}
