package com.wechat.shop.mapper;

import java.util.List;
import java.util.Map;

import com.wechat.shop.entity.ReceivingAddress;

public interface ReceivingAddressMapper {

	// 根据userId 查询常用地址
	public Long queryUserInfoByOpenid(Long userId);

	// 添加收货地址
	public Long addReceivingAddress(ReceivingAddress receivingAddress);

	public List<ReceivingAddress> queryReceivingAddressListById(Long userId);
}
