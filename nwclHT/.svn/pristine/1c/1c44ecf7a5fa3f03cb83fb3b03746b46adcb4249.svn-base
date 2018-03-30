package com.bola.nwcl.common.util.property;

import java.util.HashMap;
import java.util.Map;

import com.bola.nwcl.common.global.Configure;
import com.bola.nwcl.common.util.HttpClientUtil;

public class PropertyValidate {
	private static final String propertyValidateUrl = Configure.getPropertyValidateUrl();
	private static final String propertyGetUnitPaymentListUrl = Configure.getPropertyGetUnitPaymentList();
	public static String validate(String phone){
		Map<String, String> paramMap = new HashMap<String, String>(1);
		paramMap.put("p_CustomerPhone", phone);
		String res = HttpClientUtil.get(propertyValidateUrl, paramMap);
		System.out.println(res);
		return res;
	}
	
	public static String getUnitPayment(String unitNo, String start, String end){
		Map<String, String> paramMap = new HashMap<String, String>(1);
		paramMap.put("p_UnitNO", unitNo);
		paramMap.put("p_PayBeginMonth", start);
		paramMap.put("p_PayEndMonth", end);
		String res = HttpClientUtil.get(propertyGetUnitPaymentListUrl, paramMap);
		System.out.println(res);
		return res;
	}
	
}
