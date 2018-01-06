package com.wechat.shop.service;

import java.util.Map;

import org.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {

	Map<String, Object> queryShoppingCartList(Integer pageNum, String openid);

	Map<String, Object> delShoppingCartList(String array, String openid) throws JSONException;

}
