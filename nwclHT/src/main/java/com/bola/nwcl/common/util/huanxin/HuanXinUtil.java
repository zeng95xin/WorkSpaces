package com.bola.nwcl.common.util.huanxin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.global.Configure;
import com.bola.nwcl.common.util.HttpClientUtil;
import com.bola.nwcl.dal.mybatis.model.BizUser;

public class HuanXinUtil {
	
	private static final String url_base = Configure.getUrl_base();
	private static final String org_name = Configure.getOrg_name();
	private static final String app_name = Configure.getApp_name();
	private static final String client_id = Configure.getClient_id();
	private static final String client_secret = Configure.getClient_secret();
	
	public static final String api_url_base = url_base + "/" + org_name + "/" + app_name;
	
	private static HuanXinToken token;
	
	public static synchronized HuanXinToken getToken(){
		if(null == token || token.getExpireDate().getTime() < (System.currentTimeMillis() - 1000*60*60)){
			refreshToken();
		}
		return token;
	}
	
	private static Map<String, String> headerMap_json(Map<String, String> map){
		if(null == map){
			map = new HashMap<String, String>();
		}
		map.put("Content-Type", "application/json");
		return map;
	}
	
	private static Map<String, String> headerMapAuthorization_json(){
		Map<String, String> map = headerMap_json(null);
		//Authorization: Bearer YWMtSozP9jHNEeSQegV9EK5eAQAAAUlmBR2bTGr-GP2xNh8GhUCdKViBFgtox3M
		map.put("Authorization", "Bearer " + getToken().getToken());
		return map;
	}
	
	//{"access_token":"YWMtXdEMNDBpEeaQ2qeTBrxDhgAAAVZ4WfyJzpjZSM2FrNPrtR1TAZ9_8AisTVA","expires_in":5183999,"application":"a768fc00-3049-11e6-a8ea-ebee9a933c27"}
	private static void refreshToken(){
		String url = api_url_base + "/token";
		if(StringUtils.isNotBlank(Configure.getHuanXinTokenUrl())){
			url = Configure.getHuanXinTokenUrl();
		}
		String requestBody = "{\"grant_type\":\"client_credentials\",\"client_id\":\""+client_id+"\",\"client_secret\":\""+client_secret+"\"}";
		String result = HttpClientUtil.postHttps(url, requestBody, "application/json", headerMap_json(null));
		System.out.println(result);
		JSONObject json = JSON.parseObject(result);
		String access_token = json.getString("access_token");
		if(null == access_token){
			throw new BusinessValidateException("刷新access_token失败");
		}
		if(null == token){
			token = new HuanXinToken();
		}
		token.setToken(access_token);
		Long expires_in = json.getLongValue("expires_in");
		HeaderWrapper.setToken(access_token);
		token.setExpireDate(new Date(System.currentTimeMillis() + expires_in));
	}
	
	public static void sendBuserMsg(BizUser buser, JPushChatModel model){
		if(1 != buser.getAcceptPush()){
			return;
		}
		//{“username\":\"${用户名}\",\"password\":\"${密码}\"}
		String username = buser.getId() + "buser";
		sendBuserMsg(username, model);
	}
	
	public static void sendBuserMsg(String username, JPushChatModel model){
		
		String url = api_url_base + "/messages";
		//{“username\":\"${用户名}\",\"password\":\"${密码}\"}
		
		JSONObject json = new JSONObject();
		json.put("target_type", "users");
		json.put("target", new String[]{username});
		json.put("from", "admin");
		
		JSONObject msg = new JSONObject();
		msg.put("type", "txt");
		msg.put("msg", model.getContent());
		json.put("msg", msg);
		
		JSONObject ext = new JSONObject();
		ext.put("type", model.getType());
		JSONObject em_apns_ext = new JSONObject();
		em_apns_ext.put("type", model.getType());
		em_apns_ext.put("em_ignore_notification", true);
		em_apns_ext.put("em_push_title", model.getContent());
		ext.put("em_apns_ext", em_apns_ext);
		json.put("ext", ext);
		
		String requestBody = json.toJSONString();
		
		String result = HttpClientUtil.postHttps(url, requestBody, "application/json", headerMapAuthorization_json());
		
		System.out.println(result);
		JSONObject json_result = JSON.parseObject(result);
		String error = json_result.getString("error");
		if(null != error){
			System.out.println(error);
			throw new BusinessValidateException("发送信息失败");
		}
	}
	
	public static void main(String[] args) {
//		refreshToken();
//		buser.setId(2l);
//		getUserInfo(buser);
		
		BizUser buser = new BizUser();
		buser.setId(2l);
		buser.setAcceptPush(1);
		JPushChatModel model = new JPushChatModel();
		model.setContent("content");
		model.setType("type");
		sendBuserMsg(buser, model);
		
	}
	
	
	
}
