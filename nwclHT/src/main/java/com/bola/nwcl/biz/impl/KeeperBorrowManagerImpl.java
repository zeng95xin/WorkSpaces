package com.bola.nwcl.biz.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bola.nwcl.api.model.BorrowThingModel;
import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.api.model.keeper.KeeperBorrowModel;
import com.bola.nwcl.biz.KeeperBorrowManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.util.JPushUtil;
import com.bola.nwcl.dal.mybatis.mapper.BorrowMapper;
import com.bola.nwcl.dal.mybatis.mapper.BorrowThingMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.mapper.ThingMapper;
import com.bola.nwcl.dal.mybatis.model.Borrow;
import com.bola.nwcl.dal.mybatis.model.BorrowExample;
import com.bola.nwcl.dal.mybatis.model.BorrowThing;
import com.bola.nwcl.dal.mybatis.model.BorrowThingExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.Thing;

@Service
public class KeeperBorrowManagerImpl implements KeeperBorrowManager {

	@Autowired
	private BorrowMapper borrowMapper;
	
	@Autowired
	private BorrowThingMapper borrowThingMapper;
	
	@Autowired
	private ThingMapper thingMapper;
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	@Autowired
	private NotifySendMapper notifySendMapper;
	
	@Override
	public Page<KeeperBorrowModel> getAllBorrow(int orderType, int type, int page, int rows) {
		
		PageRequest pager = new PageRequest(page - 1, rows);
		
		Map<String, Object> condition = new HashMap<String, Object>(4);
		condition.put("offset", pager.getOffset());
		condition.put("limit", pager.getPageSize());
		String orderByClause = " expect_return_time desc";
		if(orderType == 2){
			orderByClause = " row_add_time desc";
		}
		condition.put("orderByClause", orderByClause);
		condition.put("type", type);
		List<KeeperBorrowModel> list = borrowMapper.getAllBorrowByKeeper(condition);
		
		for(KeeperBorrowModel t : list){
			List<BorrowThingModel> list_thing = borrowThingMapper.getBorrowThingDetail(t.getId());
			t.setBorrowThings(list_thing);
		}
		BorrowExample example_b = new BorrowExample();
		if(type == 1){
			example_b.or().andStatusEqualTo(0);
		}else if(type == 2){
			example_b.or().andStatusNotEqualTo(0);
		}
		int count = borrowMapper.countByExample(example_b);
		
		Page<KeeperBorrowModel> pageData = new PageImpl<KeeperBorrowModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public KeeperBorrowModel getBorrowDetail(long id) {
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		
		List<KeeperBorrowModel> list = borrowMapper.getAllBorrowByKeeper(condition);
		
		if(list == null || list.size() < 1){
			return null;
		}
		
		List<BorrowThingModel> list_thing = borrowThingMapper.getBorrowThingDetail(id);
		KeeperBorrowModel kbm = list.get(0);
		kbm.setBorrowThings(list_thing);
		
		return kbm;
	}
	
	@Override
	public void updateAllowBorrow(long borrowId, ServiceUser suser) {
		Borrow b = borrowMapper.selectByPrimaryKey(borrowId);
		if(b == null){
			throw new BusinessValidateException("该借用单不存在");
		}
		
		if(b.getStatus().intValue() <= -1){
			throw new BusinessValidateException("同意失败,该借用单已经取消");
		}
		if(b.getStatus().intValue() >= 1){
			throw new BusinessValidateException("同意失败,该借用单已经同意过了");
		}
		
		b.setStatus(1);
		borrowMapper.updateByPrimaryKeySelective(b);
		BorrowThingExample example_bt = new BorrowThingExample();
		example_bt.or().andBorrowIdEqualTo(borrowId);
		BorrowThing bt = new BorrowThing();
		bt.setStatus(1);
		borrowThingMapper.updateByExampleSelective(bt, example_bt);
		
		
		String notify_title = "您的借用单已经被同意借用了";
		String notify_detail = "尊敬的业主，您的物品借用申请已受理，订单号:"+b.getSerial()+"，请凭此单号到物业中心领取借用物品，感谢您的支持，祝您生活愉快。";
		String notify_type = "2:"+b.getId()+":"+b.getStatus();
		long notify_buserId = b.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suser.getId());
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notifyMapper.insertAndGetId(notify);
		NotifySend notify_send = new NotifySend();
		notify_send.setBuserId(notify_buserId);
		notify_send.setNotifyId(notify.getId());
		notify_send.setStatus(0);
		notifySendMapper.insert(notify_send);
		List<String> alias=new ArrayList<String>(1);
		alias.add(notify_buserId+"buser");
		JPushChatModel jpModel=new JPushChatModel();
		jpModel.setType(notify_type);
		jpModel.setTitle(notify_title);
		jpModel.setContent(notify_detail);
		JPushUtil.jPushAssign(alias, jpModel);
		
	}
	

	@Override
	public void updateTakeBorrow(long borrowId, String thing_id, ServiceUser suser) {
		Borrow b = borrowMapper.selectByPrimaryKey(borrowId);
		if(b == null){
			throw new BusinessValidateException("该借用单不存在");
		}
		if(b.getStatus().intValue() == -1){
			throw new BusinessValidateException("领取失败,该借用单已经取消");
		}
		if(b.getStatus().intValue() == 2 ){
			BorrowThingExample example_bt2 = new BorrowThingExample();
			example_bt2.or().andBorrowIdEqualTo(borrowId).andStatusEqualTo(1);
			int count2 = borrowThingMapper.countByExample(example_bt2);
			if(count2 < 1){
				throw new BusinessValidateException("领取失败,该借用单已经领取");
			}
		}
		if(b.getStatus().intValue() == 3){
			throw new BusinessValidateException("领取失败,该借用单已经归还");
		}
		if(b.getStatus().intValue() == 0){
			throw new BusinessValidateException("领取失败,该借用单还未被同意");
		}
		String title = "";
		title = "成功领取借用单物品";
		String content = "";
		b.setStatus(2);
		if(!StringUtils.hasText(thing_id)){
			content = "你已经成功领取所有借用单物品";
			b.setExpectReturnTime(new Date(System.currentTimeMillis()+1*24*60*60*1000));
		}else{
			
			BorrowThingExample example_bt_x = new BorrowThingExample();
			example_bt_x.or().andBorrowIdEqualTo(borrowId).andStatusEqualTo(2);
			int count_x = borrowThingMapper.countByExample(example_bt_x);
			if(count_x <= 0){
				b.setExpectReturnTime(new Date(System.currentTimeMillis()+1*24*60*60*1000));
			}
			
			String[] ids = thing_id.split(",");
			long[] ids_long = new long[ids.length];
			for(int i=0;i<ids.length;i++){
				String id = ids[i];
				long id_long = Long.valueOf(id);
				ids_long[i]=id_long;
			}
			content = "你已经成功领取借用单物品:";
			for(long id : ids_long){
				BorrowThing bt = borrowThingMapper.selectByPrimaryKey(id);
				if(bt == null){
					throw new BusinessValidateException("该借用单物品不存在");
				}
				if(bt.getBorrowId().longValue() != borrowId){
					throw new BusinessValidateException("领取失败,该借用单物品不属于该借用单");
				}
				if(bt.getStatus().intValue() == -1){
					throw new BusinessValidateException("领取失败,该借用单物品已经取消");
				}
				if(bt.getStatus().intValue() >= 2){
					throw new BusinessValidateException("领取失败,该借用单物品已经领取");
				}
				if(bt.getStatus().intValue() != 1){
					throw new BusinessValidateException("领取失败,该借用单物品还未被同意");
				}
				bt.setStatus(2);
				borrowThingMapper.updateByPrimaryKeySelective(bt);
				Thing ting = thingMapper.selectByPrimaryKey(bt.getThingId());
				content += ting.getName();
			}
			BorrowThingExample example_bt = new BorrowThingExample();
			example_bt.or().andBorrowIdEqualTo(borrowId).andStatusEqualTo(1);
			int count = borrowThingMapper.countByExample(example_bt);
			if(count <= 0){
				content = "你已经成功领取所有借用单物品";
			}
		}
		borrowMapper.updateByPrimaryKeySelective(b);
		
		
		String notify_title = title;
		String notify_detail = content;
		String notify_type = "2:"+b.getId()+":"+b.getStatus();
		long notify_buserId = b.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suser.getId());
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notifyMapper.insertAndGetId(notify);
		NotifySend notify_send = new NotifySend();
		notify_send.setBuserId(notify_buserId);
		notify_send.setNotifyId(notify.getId());
		notify_send.setStatus(0);
		notifySendMapper.insert(notify_send);
		List<String> alias=new ArrayList<String>(1);
		alias.add(notify_buserId+"buser");
		JPushChatModel jpModel=new JPushChatModel();
		jpModel.setType(notify_type);
		jpModel.setTitle(notify_title);
		jpModel.setContent(notify_detail);
		JPushUtil.jPushAssign(alias, jpModel);
		
	}

	@Override
	public void updateCancelBorrow(long borrowId, String cancelReason, ServiceUser suser) {
		Borrow b = borrowMapper.selectByPrimaryKey(borrowId);
		if(b == null){
			throw new BusinessValidateException("该借用单不存在");
		}
		if(b.getStatus().intValue() == -1){
			throw new BusinessValidateException("该借用单已经取消");
		}
		if(b.getStatus().intValue() >= 2){
			throw new BusinessValidateException("取消借用单失败,该借用单已经领取");
		}
		
		b.setStatus(-1);
		b.setCancelReason(cancelReason);
		borrowMapper.updateByPrimaryKeySelective(b);
		
		BorrowThingExample example_bt = new BorrowThingExample();
		example_bt.or().andBorrowIdEqualTo(borrowId);
		BorrowThing bt = new BorrowThing();
		bt.setStatus(-1);
		borrowThingMapper.updateByExampleSelective(bt, example_bt);
		
		String notify_title = "您的借用单被取消";
		String notify_detail = "取消原因:"+cancelReason;
		long notify_buserId = b.getBuserId();
		String notify_type = "2:"+b.getId()+":"+b.getStatus();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suser.getId());
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notifyMapper.insertAndGetId(notify);
		NotifySend notify_send = new NotifySend();
		notify_send.setBuserId(notify_buserId);
		notify_send.setNotifyId(notify.getId());
		notify_send.setStatus(0);
		notifySendMapper.insert(notify_send);
		List<String> alias=new ArrayList<String>(1);
		alias.add(notify_buserId+"buser");
		JPushChatModel jpModel=new JPushChatModel();
		jpModel.setType(notify_type);
		jpModel.setTitle(notify_title);
		jpModel.setContent(notify_detail);
		JPushUtil.jPushAssign(alias, jpModel);
		
	}

	@Override
	public void updateReturnBorrow(long borrowId, String thing_id, ServiceUser suser) {
		Borrow b = borrowMapper.selectByPrimaryKey(borrowId);
		if(b == null){
			throw new BusinessValidateException("该借用单不存在");
		}
		if(b.getStatus().intValue() == -1){
			throw new BusinessValidateException("该借用单已经取消");
		}
		if(b.getStatus().intValue() < 2 && b.getStatus().intValue() != -1){
			throw new BusinessValidateException("归还失败,该借用单还未领取");
		}
		if(b.getStatus().intValue() == 3){
			throw new BusinessValidateException("归还失败,该借用单已经归还");
		}
		String title = "归还借用单";
		String content = "";
		if(!StringUtils.hasText(thing_id)){
			b.setStatus(3);
			content = "您已经归还所有借用单物品";
		}else{
			String[] ids = thing_id.split(",");
			long[] ids_long = new long[ids.length];
			for(int i=0;i<ids.length;i++){
				String id = ids[i];
				long id_long = Long.valueOf(id);
				ids_long[i]=id_long;
			}
			content = "你已经成功归还借用单物品:";
			for(long id : ids_long){
				BorrowThing bt = borrowThingMapper.selectByPrimaryKey(id);
				if(bt == null){
					throw new BusinessValidateException("该借用单物品不存在");
				}
				if(bt.getBorrowId().longValue() != borrowId){
					throw new BusinessValidateException("归还失败,该借用单物品不属于该借用单");
				}
				if(bt.getStatus().intValue() == -1){
					throw new BusinessValidateException("该借用单物品已经取消");
				}
				if(bt.getStatus().intValue() < 2 && b.getStatus().intValue() != -1){
					throw new BusinessValidateException("归还失败,该借用单物品还未领取");
				}
				if(bt.getStatus().intValue() == 3){
					throw new BusinessValidateException("归还失败,该借用单物品已经归还");
				}
				bt.setStatus(3);
				borrowThingMapper.updateByPrimaryKeySelective(bt);
				Thing ting = thingMapper.selectByPrimaryKey(bt.getThingId());
				content += ting.getName();
			}
			BorrowThingExample example_bt = new BorrowThingExample();
			example_bt.or().andBorrowIdEqualTo(borrowId).andStatusEqualTo(2);
			int count = borrowThingMapper.countByExample(example_bt);
			if(count <= 0){
				content = "你已经成功归还所有领取过的借用单物品";
				b.setStatus(3);
			}
		}
		
		borrowMapper.updateByPrimaryKeySelective(b);
		
		String notify_title = title;
		String notify_detail = content;
		String notify_type = "2:"+b.getId()+":"+b.getStatus();
		long notify_buserId = b.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suser.getId());
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notifyMapper.insertAndGetId(notify);
		NotifySend notify_send = new NotifySend();
		notify_send.setBuserId(notify_buserId);
		notify_send.setNotifyId(notify.getId());
		notify_send.setStatus(0);
		notifySendMapper.insert(notify_send);
		List<String> alias=new ArrayList<String>(1);
		alias.add(notify_buserId+"buser");
		JPushChatModel jpModel=new JPushChatModel();
		jpModel.setType(notify_type);
		jpModel.setTitle(notify_title);
		jpModel.setContent(notify_detail);
		JPushUtil.jPushAssign(alias, jpModel);
	}

}
