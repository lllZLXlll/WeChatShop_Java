package com.wechat.shop.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class BankUtil {
	public static void main(String[] args) {
		String bankCardNo = "6222005865412565805";// 卡号
		String name = getBankNameByBankCardNo(bankCardNo);
		System.out.println(name);
		String name1 = getBankNameCodeByBankCardNo(bankCardNo);
		System.out.println(name1);
	}

	@SuppressWarnings("static-access")
	/**
	 * 输入银行卡号返回银行名称
	 * 
	 * @param bankCardNo
	 *            银行卡号
	 * @return 银行名称
	 */
	public static String getBankNameByBankCardNo(String bankCardNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("exAcctNo", bankCardNo);
		String s = HttpUtil.sendGet("https://testecas.srbank.cn/ecpg/pmtcbin/orgname", params, "UTF-8");
		JSONObject json = new JSONObject();
		json = json.fromObject(s);
		String bankName = ConvertUtil.objToStrConvert(json.get("orgName"));
		return bankName;
	}

	@SuppressWarnings("static-access")
	/**
	 * 输入银行卡号返回银行简称 如建设银行：CBC
	 * 
	 * @param bankCardNo
	 *            银行卡号
	 * @return 银行名称简称
	 */
	public static String getBankNameCodeByBankCardNo(String bankCardNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("cardBinCheck", true);
		params.put("cardNo", bankCardNo);
		String s = HttpUtil.sendGet("https://ccdcapi.alipay.com/validateAndCacheCardInfo.json", params, "UTF-8");
		JSONObject json = new JSONObject();
		json = json.fromObject(s);
		String bankName = ConvertUtil.objToStrConvert(json.get("bank"));
		return bankName;
	}

}
