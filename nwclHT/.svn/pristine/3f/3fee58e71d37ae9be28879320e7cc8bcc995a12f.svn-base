package com.bola.nwcl.common.util.huanxin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.util.HttpClientUtil;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;

public class ServiceUserMsgApi {

	private static final Logger log = LoggerFactory.getLogger(ServiceUserMsgApi.class);

	private static final String url = HuanXinUtil.api_url_base + "/messages";

	public static void sendBuserMsg(ServiceUser suser, JPushChatModel model) {
		sendBuserMsg(suser.getId(), model);
	}

	public static void sendBuserMsg(long suserId, JPushChatModel model) {
		sendBuserMsg(suserId, model.getContent(), model.getType());
	}
	
	public static void sendBuserMsg(long suserId, String content, String type) {
		String username = suserId + "suser";

		JSONObject json = new JSONObject();
		json.put("target_type", "users");
		json.put("target", new String[] { username });
		json.put("from", "admin");

		JSONObject msg = new JSONObject();
		msg.put("type", "txt");
		msg.put("msg", content);
		json.put("msg", msg);

		if (StringUtils.isNotBlank(type)) {
			JSONObject ext = new JSONObject();
			ext.put("type", type);
			JSONObject em_apns_ext = new JSONObject();
			em_apns_ext.put("type", type);
			em_apns_ext.put("em_ignore_notification", true);
			em_apns_ext.put("em_push_title", type);
			ext.put("em_apns_ext", em_apns_ext);
			json.put("ext", ext);
		}

		String requestBody = json.toJSONString();

		String result = HttpClientUtil.postHttps(url, requestBody, "application/json",
				HeaderWrapper.getMapAuthorization());

		System.out.println(result);
		JSONObject json_result = JSON.parseObject(result);
		String error = json_result.getString("error");
		if (null != error) {
			log.warn("发送信息失败,{}", json_result);
			throw new BusinessValidateException("发送信息失败");
		}
	}

}
