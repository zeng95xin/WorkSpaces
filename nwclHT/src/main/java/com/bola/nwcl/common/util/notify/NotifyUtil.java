package com.bola.nwcl.common.util.notify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.NotifyManager;
import com.bola.nwcl.biz.NotifySendManager;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifySend;

@Service
public class NotifyUtil {

	@Autowired
	private NotifyManager notifyManager;

	@Autowired
	private NotifySendManager notifySendManager;
	
	@Autowired
	private BizUserManager bizUserManager;

	/**
	 * @param notify_title 消息标题
	 * @param notify_detail 消息内容
	 * @param notify_type 消息类型
	 * @param publishPeopleId 消息推送人
	 * @param buserId 消息接收人
	 * @param notify_remark 消息备注
	 */
	public void send(String notify_title, String notify_detail, String notify_type, Long publishPeopleId, Long buserId,
			String notify_remark) {
		
		if(!notify_type.startsWith("-1:")){
			return;
		}
		
		add(notify_title, notify_detail, notify_type, publishPeopleId, buserId, notify_remark);

	}
	
	public void send(String notify_title, String notify_detail, String notify_type, Long publishPeopleId, List<BizUser> busers,
			String notify_remark) {
		
		if(!notify_type.startsWith("-1:")){
			return;
		}
		
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(publishPeopleId);
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notify.setRemark(notify_remark);
		notifyManager.insertAndGetId(notify);
		for(BizUser buser : busers){
			NotifySend notify_send = new NotifySend();
			notify_send.setBuserId(buser.getId());
			notify_send.setNotifyId(notify.getId());
			notify_send.setStatus(0);
			notifySendManager.insert(notify_send);
		}

	}
	
	public void sendToUsersById(String notify_title, String notify_detail, String notify_type, Long publishPeopleId, List<Long> user_ids,
			String notify_remark) {
		
		if(!notify_type.startsWith("-1:")){
			return;
		}
		
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(publishPeopleId);
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notify.setRemark(notify_remark);
		notifyManager.insertAndGetId(notify);
		for(Long userid : user_ids){
			NotifySend notify_send = new NotifySend();
			notify_send.setBuserId(userid);
			notify_send.setNotifyId(notify.getId());
			notify_send.setStatus(0);
			notifySendManager.insert(notify_send);
		}
		
	}

	private void add(String notify_title, String notify_detail, String notify_type, Long publishPeopleId, Long buserId,
			String notify_remark) {
		
		if(!notify_type.startsWith("-1:")){
			return;
		}
		
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(publishPeopleId);
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notify.setRemark(notify_remark);
		notifyManager.insertAndGetId(notify);

		NotifySend notify_send = new NotifySend();
		notify_send.setBuserId(buserId);
		notify_send.setNotifyId(notify.getId());
		notify_send.setStatus(0);
		notifySendManager.insert(notify_send);

	}
}
