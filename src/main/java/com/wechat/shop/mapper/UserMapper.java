package com.wechat.shop.mapper;

import com.wechat.shop.entity.User;

public interface UserMapper {
	
	// 根据openid 查询用户
	public User queryUserInfoByOpenid(String openid);

	// 用户登录时添加用户
	public Long addUser(User user);

	// 修改用户信息
	public Long updateUser(User user);

	public User checkOpenIdMd5(String openid);
}
