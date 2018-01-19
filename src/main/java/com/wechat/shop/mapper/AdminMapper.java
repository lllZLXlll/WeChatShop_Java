package com.wechat.shop.mapper;

import java.util.Map;

public interface AdminMapper {

	Map<String, Object> checkAdminLogin(String accountNumber, String password);

}
