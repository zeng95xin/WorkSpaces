package com.bola.nwcl.web.controller.bjwdt.city;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.util.HttpClientUtil;

public class BjwdtUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(BjwdtUtil.class);
	
	private static final String url = " http://125.97.241.19:8080/lunengs/works.action";
	
	private static final String paramName = "bizData";
	
	public static final JSONObject doPost(JSONObject bizData){
		return doPost(bizData.toJSONString());
	}
	
	public static final JSONObject doPost(String bizData){
		
		JSONObject result = null;
		
		logger.info("请求北京网电通接口,\n 数据：{}", bizData);
		Map<String, String> params = new HashMap<String, String>(1);
		params.put(paramName, bizData);
		String resultStr = HttpClientUtil.post(url, params);
		logger.info("请求北京网电通接口,\n 返回值：{}", resultStr);
		
		result = JSONObject.parseObject(resultStr);
		
		if(null == result || !"99".equals(result.getString("status"))){
			throw new BusinessDealException("请求北京网电通接口出错，信息:{}", resultStr);
		}
		
		return result;
	}
	
	
}
