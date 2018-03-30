package com.bola.nwcl.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;
import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.api.model.jpush.JPushDevice;
import com.bola.nwcl.common.global.Configure;
public class JPushUtil {
	private static String jPushAppKey=Configure.getJPushAppKey();
	private static String jPushMasterSecret=Configure.getJPushMasterSecret();
	private static String jPushApiUrl = Configure.getJPushApiUrl();
	
	static Logger logger=LoggerFactory.getLogger(JPushUtil.class);
	/**
	 * 平台用户和极光别名绑定
	 * 参数说明：String user 需要绑定的用户可以是自己平台的userId
	 * String registrationId 设备id由app传参过来
	 */
	public static void bindingJPush(String user,String registrationId){
		try{
			String str = jPushAppKey+":"+jPushMasterSecret;
			String token = new BASE64Encoder().encode(str.getBytes());
			String url = jPushApiUrl+"/v3/devices/" + registrationId;
			JPushDevice jpdever = new JPushDevice();
			jpdever.setAlias(user);
			String requestBody = JSON.toJSONString(jpdever);
			Map<String,String> headerMap = new HashMap<String,String>();
			headerMap.put("Authorization", "Basic " + token);
			headerMap.put("Accept", "application/json");
			String abc = HttpClientUtil.postHttps(url, requestBody, "application/json; charset=utf-8", headerMap);
			logger.info("极光绑定设备日志输出：" + abc);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 指定设备别名发 通知 （IOS）
	 */
	public static void jPushIosMassgae(List<String> alias, JPushChatModel jpModel) {
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey, 3);
			PushPayload payload = ios_massgae(alias, jpModel);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("推送日志：" + result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 指定设备别名发 消息 （Android）
	 */
	public static void jPushAndroidAlert(List<String> alias, JPushChatModel jpModel) {
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey, 3);
			PushPayload payload = android_alert(alias, jpModel);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("推送日志：" + result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 指定设备发送-（安卓和ios 通知）
	 */
	public static void jPushAssign(List<String> alias, JPushChatModel jpModel) {
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey, 3);
			PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(alias, jpModel);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("推送日志：" + result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 推送给所有设备 内容 （消息）
	 */
	public static void jPushAll(String alert) {
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey, 3);
			PushPayload payload =  buildPushObject_all_all_alert(alert);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("推送日志：" + result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 推送给所有安卓设备 内容 (通知)  
	 */
	public static void jPushAndroid(JPushChatModel jpModel) {
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey, 3);
			PushPayload payload =  buildPushObject_android_alertWithTitle(jpModel);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("推送日志：" + result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 推送给所有苹果设备 内容  （通知）
	 */
	public static void jPushIos(JPushChatModel jpModel) {
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey, 3);
			PushPayload payload =  buildPushObject_ios_alertWithTitle(jpModel);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("推送日志：" + result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(List<String> alias, JPushChatModel model) { 
		return PushPayload.newBuilder()
		.setPlatform(Platform.all())
		.setAudience(Audience.alias(alias))
		.setNotification(Notification.newBuilder()
		.addPlatformNotification(IosNotification.newBuilder()
		.setAlert(model.getContent())
		.setBadge(5)
		.addExtra("type", model.getType())
		.addExtra("content", model.getContent())
		.addExtra("title", model.getTitle())
		.addExtra("sendUserId", model.getSendUserId())
		.addExtra("url", model.getUrl())
		.build())
		.addPlatformNotification(AndroidNotification.newBuilder()
		.setAlert(model.getContent())
		.setTitle(model.getTitle())
		.addExtra("type", model.getType())
		.addExtra("content", model.getContent())
		.addExtra("title", model.getTitle())
		.addExtra("sendUserId", model.getSendUserId())
		.addExtra("url", model.getUrl())
		.build())
		.build())
		.setOptions(Options.newBuilder()
		.setApnsProduction(false)
		.build())
		.build();
	}
	public static PushPayload buildPushObject_all_all_alert(String alert) {
		return PushPayload.alertAll(alert);
	}
	
	public static PushPayload buildPushObject_android_alertWithTitle(JPushChatModel model) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setNotification(Notification.android(model.getContent(), model.getTitle(), null)).build();
	}
	public static PushPayload buildPushObject_ios_alertWithTitle(JPushChatModel model) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setNotification(Notification.android(model.getContent(), model.getTitle(), null)).build();
	}
	
	/**
	 * ios推送通知
	 * @param alias
	 * @param model
	 * @return
	 */
	public static PushPayload ios_massgae(List<String> alias, JPushChatModel model) {
		return PushPayload.newBuilder()
               .setPlatform(Platform.ios())
               .setAudience(Audience.alias(alias))
               .setNotification(Notification.newBuilder()
               .addPlatformNotification(IosNotification.newBuilder()
               .setAlert(model.getContent())
               .setBadge(5)
               .addExtra("type", model.getType())
               .addExtra("content", model.getContent())
               .addExtra("title", model.getTitle())
               .addExtra("sendUserId", model.getSendUserId())
               .addExtra("url", model.getUrl())
               .build())
               .build())
               .setOptions(Options.newBuilder()
            	.setApnsProduction(true)
            	.build())
            	.build();
	}
	
	/**
	 * 安卓推送消息
	 * @param alias
	 * @param model
	 * @return
	 */
	public static PushPayload android_alert(List<String> alias, JPushChatModel model) {
		return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                .addAudienceTarget(AudienceTarget.alias(alias))
                .build())
                .setMessage(Message.newBuilder()
                .setMsgContent(model.getContent())
                .addExtra("type", model.getType())
				.addExtra("content", model.getContent())
				.addExtra("title", model.getTitle())
				//.addExtra("sendUserId", model.getSendUserId())
				//.addExtra("url", model.getUrl())
                        .build())
                .build();
    }
}
