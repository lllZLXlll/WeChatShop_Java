package com.wechat.shop.mapper;

import java.util.List;
import java.util.Map;

public interface AdminMapper {

	Map<String, Object> checkAdminLogin(String accountNumber, String password);

	int changePwd(long adminId, String newPassword);

	int checkPassword(long adminId, String password);

	List<Map<String, Object>> homeData(Integer pageBeginNum, Integer pageSize, int type);

	Integer homeDataCount(int type);

}
