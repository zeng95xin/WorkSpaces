package com.bola.nwcl.common.util.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.common.util.huanxin.ServiceUserMsgApi;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil_Back {
	
	private static String jPushAppKey="207d0116d7339aa6af531a9c";
	private static String jPushMasterSecret="2709d4c5f9148ebeacf1902e";
	private static boolean jPushsetApnsProduction=true;
	
	static Logger logger=LoggerFactory.getLogger(JPushUtil_Back.class);
	
	public static void sendBackMsg(String title, String alert, 
            String type, String... registrationID){
		try {
			JPushClient jpushClient = new JPushClient(jPushMasterSecret, jPushAppKey, 3);
			PushPayload pushPayload = build_AndroidMSGPushPayloadWithRegistrationID(title, alert, type, registrationID);
			jpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendBackMsgByType(long suserId, String content, String type){
//		sendBackMsgByType(suserId, content, type);
		JPushChatModel model = new JPushChatModel();
		model.setContent(content);
		model.setType(type);
		ServiceUserMsgApi.sendBuserMsg(suserId, model);
	}
	
//	public static void sendBackMsgByType(String title, String alert, 
//        String type, String... registrationID){
//		sendBackMsg(title, alert, type, registrationID);
//	}
	
	public static PushPayload build_AndroidMSGPushPayloadWithRegistrationID(String title, String alert, 
	        String type,String... registrationID){
		return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registrationID))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(title)
                                //.setBadge(+1)
                                //.setSound("happy.caf")
                                .addExtra("title", title)
                                .addExtra("alert", alert)
                                .addExtra("type", type)
                                .build())
                        .build())
                 .setMessage(Message.content(title))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(jPushsetApnsProduction)
                         .build())
                 .build();
	}
	
}
