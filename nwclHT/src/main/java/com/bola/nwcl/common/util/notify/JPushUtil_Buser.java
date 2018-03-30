package com.bola.nwcl.common.util.notify;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;
import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.common.global.Configure;
import com.bola.nwcl.common.util.huanxin.BizUserMsgApi;
import com.bola.nwcl.dal.mybatis.model.BizUser;

public class JPushUtil_Buser {
	
	private static String jPushAppKey=Configure.getJPushAppKey();
	private static String jPushMasterSecret=Configure.getJPushMasterSecret();
	private static boolean jPushsetApnsProduction=Configure.getJPushsetApnsProduction();
	
	static Logger logger=LoggerFactory.getLogger(JPushUtil_Buser.class);
	
	public static void sendToBuser(JPushChatModel model, BizUser buser){
		BizUserMsgApi.sendBuserMsg(buser, model);
		if(!model.getType().startsWith("-") || model.getType().startsWith("-1")){
			sendBuserMsgWithModel(model, buser.getPhoneType(), buser.getRegistrationId());
		}
	}
	public static void sendToBuser(JPushChatModel model, List<BizUser> busers){
		List<String> androidRegistrationIds = new ArrayList<String>(busers.size());
		List<String> iosRegistrationIds = new ArrayList<String>(busers.size());
		for(BizUser buser : busers){
			if("1".equals(buser.getPhoneType())){
				if(StringUtils.hasText(buser.getRegistrationId()) && !buser.getRegistrationId().contains(":")){
					androidRegistrationIds.add(buser.getRegistrationId());
				}
			}else{
				if(StringUtils.hasText(buser.getRegistrationId()) && !buser.getRegistrationId().contains(":")){
					iosRegistrationIds.add(buser.getRegistrationId());
				}
			}
		}
		BizUserMsgApi.sendBuserMsg(busers, model);
		
		if(!model.getType().startsWith("-") || model.getType().startsWith("-1:")){
			sendBuserMsgWithModel(model, "1", androidRegistrationIds.toArray(new String[0]));
			sendBuserMsgWithModel(model, "2", iosRegistrationIds.toArray(new String[0]));
		}
	}
	public static void sendBuserMsgWithModel(JPushChatModel model, String type, String... registrationID){
		PushResult pushResult;
		if("2".equals(type)){
			pushResult = sendBuserMsg_IOSNotification(model, registrationID);
		}else{
			pushResult = sendBuserMsg_AndroidMSG(model, registrationID);
		}
		logger.info("极光推送返回值:{}", JSON.toJSONString(pushResult));
	}
	
	public static void main(String[] args) {
		
		JPushChatModel model = new JPushChatModel();
		model.setContent("content");
		model.setTitle("title");
		model.setType("type");
		sendBuserMsg_IOSNotification(model, "121c83f7602cd258d12");
	}
	
	public static PushResult sendBuserMsg_AndroidMSG(JPushChatModel model, String... registrationID) {
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey);
			PushPayload payload = build_AndroidMSGPushPayloadWithRegistrationID(model, registrationID);
			PushResult pushResult = jpushClient.sendPush(payload);
			return pushResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PushResult sendBuserMsg_IOSNotification(JPushChatModel model,String... registrationID){
		try {
			PushPayload pushPayload = build_IOSPushPayloadWithRegistrationID(model, registrationID);
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey);
			PushResult pushResult = jpushClient.sendPush(pushPayload);
			return pushResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PushPayload build_AndroidMSGPushPayloadWithRegistrationID(JPushChatModel model,String... registrationID){
		return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registrationID))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(model.getTitle())
                                //.setBadge(+1)
                                //.setSound("happy.caf")
                                .addExtra("title", model.getTitle())
                                .addExtra("content", model.getContent())
                                .addExtra("type", model.getType())
                                .build())
                        .build())
                 .setMessage(Message.content(model.getTitle()))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(jPushsetApnsProduction)
                         .build())
                 .build();
	}
	
	public static PushPayload build_IOSPushPayloadWithRegistrationID(JPushChatModel model,String... registrationID){
		return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.registrationId(registrationID))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(model.getTitle())
                                .autoBadge()
                                //.setSound("happy.caf")
                                .addExtra("title", model.getTitle())
                                .addExtra("content", model.getContent())
                                .addExtra("type", model.getType())
                                .build())
                        .build())
                 .setMessage(Message.content(model.getTitle()))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(jPushsetApnsProduction)
                         .build())
                 .build();
	}
	
}
