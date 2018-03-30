package com.bola.nwcl.common.util.lingling.util;

import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.common.global.Configure;

public class JsonUtil {
	public static final String REQUEST_PARAM = "requestParam";
	public static final String RESPONSE_RESULT = "responseResult";
	public static final String HEADER = "header";
	public static final String SIGNATURE = "signature";
	public static final String TOKEN = "token";
	
	/**
	 * 生成发送消息
	 * @param requestParam
	 * @return
	 */
	public static String makeMessage(JSONObject requestParam){
		
		JSONObject message = new JSONObject();
		message.put(REQUEST_PARAM, requestParam);
		// 生成消息头
		JSONObject header = new JSONObject();
		header.put(SIGNATURE, Configure.getLinglingSignature());
		header.put(TOKEN, Configure.getLinglingToken());
		message.put(HEADER, header);
		return message.toString();
	}
}
