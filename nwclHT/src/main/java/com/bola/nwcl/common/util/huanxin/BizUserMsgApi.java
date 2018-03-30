package com.bola.nwcl.common.util.huanxin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.util.HttpClientUtil;
import com.bola.nwcl.dal.mybatis.model.BizUser;

public class BizUserMsgApi {

	private static final Logger log = LoggerFactory.getLogger(BizUserMsgApi.class);

	private static final String url = HuanXinUtil.api_url_base + "/messages";
	
	public static void sendBuserMsg(BizUser buser, JPushChatModel model){
		if(1 != buser.getAcceptPush().intValue()){
			return;
		}
		String username = buser.getId() + "buser2";
		
		JSONObject json = new JSONObject();
		json.put("target_type", "users");
		json.put("target", new String[]{username});
		json.put("from", "admin");
		
		JSONObject msg = new JSONObject();
		msg.put("type", "txt");
		msg.put("msg", model.getContent());
		json.put("msg", msg);
		
		if(StringUtils.isNoneBlank(model.getType())){
			JSONObject ext = new JSONObject();
			ext.put("type", model.getType());
			JSONObject em_apns_ext = new JSONObject();
			em_apns_ext.put("type", model.getType());
			em_apns_ext.put("em_ignore_notification", true);
			em_apns_ext.put("em_push_title", model.getContent());
			ext.put("em_apns_ext", em_apns_ext);
			json.put("ext", ext);
		}
		
		String requestBody = json.toJSONString();
		
		String result = HttpClientUtil.postHttps(url, requestBody, "application/json", HeaderWrapper.getMapAuthorization());
		
		System.out.println(result);
		JSONObject json_result = JSON.parseObject(result);
		String error = json_result.getString("error");
		if(null != error){
			log.warn("发送信息失败,{}", json_result);
			throw new BusinessValidateException("发送信息失败");
		}

	}
	
	public static void sendBuserMsg(List<BizUser> busers, JPushChatModel model){
		
		if(busers.size() > 5){
			BizUserMsgThread thread_send = new BizUserMsgThread();
			thread_send.setBusers(busers);
			thread_send.setModel(model);
			thread_send.start();
			return;
		}
		
		int j = 0;
		int sendSize = 40;
		List<String> target = new ArrayList<String>(sendSize);
		for (int i = 0; i < busers.size(); i++) {
			BizUser buser = busers.get(i);
			if(null == buser){
				continue;
			}
			if(1 != buser.getAcceptPush().intValue()){
				continue;
			}
			if(null != buser){
				sendBuserMsg(buser, model);
				if(i%10 == 0){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				continue;
			}
			
			String username = buser.getId() + "buser2";
			target.add(username);
			
			j++;
			if(j % sendSize == 0 || i == busers.size() -1){
				JSONObject json = new JSONObject();
				json.put("target_type", "users");
				json.put("target", target);
				json.put("from", "admin");
				
				JSONObject msg = new JSONObject();
				msg.put("type", "txt");
				msg.put("msg", model.getContent());
				json.put("msg", msg);
				
				if(StringUtils.isNotBlank(model.getType())){
					JSONObject ext = new JSONObject();
					ext.put("type", model.getType());
					JSONObject em_apns_ext = new JSONObject();
					em_apns_ext.put("type", model.getType());
					em_apns_ext.put("em_ignore_notification", true);
					em_apns_ext.put("em_push_title", model.getContent());
					ext.put("em_apns_ext", em_apns_ext);
					json.put("ext", ext);
				}
				
				String requestBody = json.toJSONString();
				
				String result = HttpClientUtil.postHttps(url, requestBody, "application/json", HeaderWrapper.getMapAuthorization());
				
				System.out.println(result);
				JSONObject json_result = JSON.parseObject(result);
				String error = json_result.getString("error");
				if(null != error){
					log.warn("发送信息失败,{}", json_result);
					throw new BusinessValidateException("发送信息失败");
				}
				target = new ArrayList<String>(sendSize);
				j = 0;
			}
			
		}
		
	}
	
	public static void sendBuserMsgThread(List<BizUser> busers, JPushChatModel model){
		int j = 0;
		int sent_size = 50;
		List<String> target = new ArrayList<String>(20);
		for (int i = 0; i < busers.size(); i++) {
			BizUser buser = busers.get(i);
			if(null == buser){
				continue;
			}
			if(1 != buser.getAcceptPush().intValue()){
				continue;
			}
			
			String username = buser.getId() + "buser2";
			target.add(username);
			
			j++;
			if(j % sent_size == 0 || i == busers.size() -1){
				JSONObject json = new JSONObject();
				json.put("target_type", "users");
				json.put("target", target);
				json.put("from", "admin");
				
				JSONObject msg = new JSONObject();
				msg.put("type", "txt");
				msg.put("msg", model.getContent());
				json.put("msg", msg);
				
				if(StringUtils.isNotBlank(model.getType())){
					JSONObject ext = new JSONObject();
					ext.put("type", model.getType());
					JSONObject em_apns_ext = new JSONObject();
					em_apns_ext.put("type", model.getType());
					em_apns_ext.put("em_ignore_notification", true);
					em_apns_ext.put("em_push_title", model.getContent());
					ext.put("em_apns_ext", em_apns_ext);
					json.put("ext", ext);
				}
				
				String requestBody = json.toJSONString();
				
				String result = HttpClientUtil.postHttps(url, requestBody, "application/json", HeaderWrapper.getMapAuthorization());
				
				System.out.println(result);
				JSONObject json_result = JSON.parseObject(result);
				String error = json_result.getString("error");
				if(null != error){
					log.warn("发送信息失败,{}", json_result);
					//throw new BusinessValidateException("发送信息失败");
				}
				target = new ArrayList<String>(sent_size);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	

}
