package com.bola.nwcl.common.util.huanxin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.dal.mybatis.model.BizUser;

public class HuanXinUserApi {

	private static final Logger log = LoggerFactory.getLogger(HuanXinUserApi.class);

	private static final String url = HuanXinUtil.api_url_base + "/users";

	public static JSON getUserInfo(BizUser buser) {
		
		String requestBody = "";
		
		String result = HuanXinHttpClientUtil.postHttps(url+"/" + buser.getId() + "buser2", requestBody, "application/json", HeaderWrapper.getMapAuthorization());
		System.out.println(result);
		JSONObject json_result = JSON.parseObject(result);
		String error = json_result.getString("error");
		if(null != error){
			return regUserInfo(buser);
		}else{
			if(!StringUtils.hasText(buser.getRegistrationId()) || buser.getRegistrationId().indexOf(":") < 0){
				buser.setRegistrationId(buser.getId() + "buser2:123456");
			}
		}
		return json_result;
	}
	
	private static JSON regUserInfo(BizUser buser){
		//{“username\":\"${用户名}\",\"password\":\"${密码}\"}
		String username = buser.getId() + "buser2";
//		String password = UUID.randomUUID().toString().substring(0, 10).replace("-", "");
		String password = "123456";
		JSONObject json_requestBody = new JSONObject();
		
		json_requestBody.put("username", username);
		json_requestBody.put("password", password);
		String requestBody = json_requestBody.toJSONString();
		
		String result = HuanXinHttpClientUtil.postHttps(url, requestBody, "application/json", HeaderWrapper.getMapAuthorization());
		System.out.println(result);
		JSONObject json_result = JSON.parseObject(result);
		String error = json_result.getString("error");
		if(null != error){
			log.warn("注册环信用户失败,:{}",json_result);
			throw new BusinessValidateException("注册环信用户失败");
		}
		buser.setRegistrationId(username + ":" + password);
		return json_result;
	}

}
