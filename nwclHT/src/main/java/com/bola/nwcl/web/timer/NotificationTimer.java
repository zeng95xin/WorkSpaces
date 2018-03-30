package com.bola.nwcl.web.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.NotifyManager;
import com.bola.nwcl.biz.NotifySendManager;
import com.bola.nwcl.biz.PropertyPayMentManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.JPushUtil;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.PropertyPayMent;
import com.bola.nwcl.dal.mybatis.model.PropertyPayMentExample;
import com.bola.nwcl.web.model.RoomAndMonth;

// 物业收费通知定时器
public class NotificationTimer {

	@Autowired PropertyPayMentManager propertyPayMentManager;
	@Autowired RoomManager roomManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired NotifyManager notifyManager;
	@Autowired NotifySendManager notifySendManager;
	// 满足条件发送通知
	public void sendNotification(){
		// 所有 房间 未缴清费用单
		PropertyPayMentExample ex1 = new PropertyPayMentExample();
		ex1.or().andReceivableStatusEqualTo(0);
		ex1.setGroupByClause("room_id");
		List<PropertyPayMent> undoneBills = propertyPayMentManager.selectByExample(ex1);
		
		// 同一房间 未完成 订单。查询 业主，未完成月份（月份不重复）
		List<RoomAndMonth> rms = new ArrayList<RoomAndMonth>();
		for(PropertyPayMent pt : undoneBills){
			PropertyPayMentExample ex2 = new PropertyPayMentExample();
			ex2.or().andReceivableStatusEqualTo(0).andRoomIdEqualTo(pt.getRoomId());
			List<PropertyPayMent> roomBills = propertyPayMentManager.selectByExample(ex2);
			RoomAndMonth rm = new RoomAndMonth();
			rm.setRoomId(pt.getRoomId());
			String mon = "";
			for(PropertyPayMent p : roomBills){
				if (p.getPropertyDate() != null) {
					String ss = DateUtils.DateToStr("MM", p.getPropertyDate());
					if (mon.equals("")) {
						mon+=ss;
					}else{
						mon+=("，"+ss);
					}
				}
			}
			rm.setMonth(mon);
			rms.add(rm);
		}
		for(RoomAndMonth ra : rms){
			BizUserRoomExample uroomExample = new BizUserRoomExample();
			uroomExample.or().andTypeEqualTo("业主").andRoomIdEqualTo(ra.getRoomId());
			List<BizUserRoom> ums = bizUserRoomManager.selectByExample(uroomExample);
			Notify notify = new Notify();
			notify.setTitle("缴费提示");
			notify.setDetail("您有"+ra.getMonth()+"月物业费未缴清，逾期将产生滞纳金，请及时缴纳。");
			notify.setType(4+":-1:-1");
			notify.setStatus(1);
			notify.setPublishPeopleId(-1l);
			notify.setRowAddTime(new Date());
			notifyManager.insertAndGetId(notify);
			// 推送器
			JPushChatModel model = new JPushChatModel();
			model.setType("4:-1:-1");
			model.setTitle("缴费提示");
			model.setContent("您有"+ra.getMonth()+"月物业费未缴清，逾期将产生滞纳金，请及时缴纳。");
			for(BizUserRoom bur : ums){
				Long userid = bur.getBizUserId();
				NotifySend send = new NotifySend();
				send.setBuserId(userid);
				send.setNotifyId(notify.getId());
				send.setStatus(0);
				send.setRowAddTime(new Date());
				notifySendManager.insert(send);
				List<String> alias = new ArrayList<String>();
				alias.add(userid + "buser");
				BizUser bizUser = bizUserManager.selectByPrimaryKey(userid);
				if("1".equals(bizUser.getPhoneType())){
					JPushUtil.jPushAndroidAlert(alias, model);
				}
				if("2".equals(bizUser.getPhoneType())){
					JPushUtil.jPushAssign(alias, model);
				}
			}
		}
		
		System.out.println("------------------------- 缴费通知已发送 -----------------------------");
		
	}
}
