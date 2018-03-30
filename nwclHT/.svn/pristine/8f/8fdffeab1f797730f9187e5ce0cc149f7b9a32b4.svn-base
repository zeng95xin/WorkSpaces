package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.api.model.keeper.KeeperUserMessageIndexModel;
import com.bola.nwcl.api.model.keeper.KeeperUserMessageModel;
import com.bola.nwcl.biz.KeeperConsultationAndCommunicationManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.util.JPushUtil;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.HousekeeperUserMessageMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessage;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;

@Service
public class KeeperConsultationAndCommunicationManagerImpl  implements KeeperConsultationAndCommunicationManager{
	
	@Autowired
	private HousekeeperUserMessageMapper msgMapper;
	
	@Autowired
	private BizUserMapper buserMapper;
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	@Autowired
	private NotifySendMapper notifySendMapper;
	
	@Override
	public Page<KeeperUserMessageIndexModel> getIndexMessage(int page, int rows, Long id) {
		
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<KeeperUserMessageIndexModel> list = null;
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", pager.getOffset());
		condition.put("limit", pager.getPageSize());
		condition.put("id", id);
		list = msgMapper.getIndexMessage(condition);
		
		count = msgMapper.getIndexMessageCount(condition);
		
		Page<KeeperUserMessageIndexModel> pageData = new PageImpl<KeeperUserMessageIndexModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public Page<KeeperUserMessageModel> updateGetBizUserMessage(Long id, int page, int rows, ServiceUser suser) {
		BizUser buser = buserMapper.selectByPrimaryKey(id);
		if(buser == null){
			throw new BusinessValidateException("该用户不存在");
		}
		
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		HousekeeperUserMessageExample example_msg = new HousekeeperUserMessageExample();
		example_msg.or().andBuserIdEqualTo(id).andHousekeeperIdEqualTo(suser.getId());
		
		count = msgMapper.countByExample(example_msg);
		
		example_msg.setLimit(pager.getPageSize());
		example_msg.setOffset(pager.getOffset());
		example_msg.setOrderByClause(" row_add_time desc ");
		
		List<HousekeeperUserMessage> list = msgMapper.selectByExample(example_msg);
		List<KeeperUserMessageModel> data = new ArrayList<KeeperUserMessageModel>(list.size());
		for(HousekeeperUserMessage msg : list){
			KeeperUserMessageModel model = new KeeperUserMessageModel();
			BeanUtils.copyProperties(msg, model);
			model.setBuser_headPortrait(buser.getHeadPortrait());
			model.setBuser_nickname(buser.getNickname());
			model.setKeeper_headPortrait(suser.getHeadPortrait());
			model.setKeeper_nickname(suser.getNickname());
			data.add(model);
		}
		
		Page<KeeperUserMessageModel> pageData = new PageImpl<KeeperUserMessageModel>(data, pager, count);
		
		
		HousekeeperUserMessage msg = new HousekeeperUserMessage();
		msg.setStatus(1);
		msgMapper.updateByExampleSelective(msg, example_msg);
		
		return pageData;
	}

	@Override
	public void updateMarkHasRead(long id, long suerId) {
		HousekeeperUserMessageExample example_msg = new HousekeeperUserMessageExample();
		example_msg.or().andBuserIdEqualTo(id).andHousekeeperIdEqualTo(suerId);
		HousekeeperUserMessage msg = new HousekeeperUserMessage();
		msg.setStatus(1);
		msgMapper.updateByExampleSelective(msg, example_msg);
	}
	@Override
	public void updateMarkHasNotRead(long id, long suerId) {
		Map<String, Object> condition = new HashMap<String, Object>(2);
		condition.put("id", id);
		condition.put("suerId", suerId);
		msgMapper.updateMarkHasNotRead(condition);
	}

	@Override
	public void deleteMsg(long id, long suserId) {
		HousekeeperUserMessageExample example_msg = new HousekeeperUserMessageExample();
		example_msg.or().andBuserIdEqualTo(id).andHousekeeperIdEqualTo(suserId);
		msgMapper.deleteByExample(example_msg);
	}

	@Override
	public void addMsg(long id, long suserId, String content, ServiceUser suer) {
		if(buserMapper.selectByPrimaryKey(id) == null){
			throw new BusinessValidateException("用户不存在");
		}
		HousekeeperUserMessage msg = new HousekeeperUserMessage();
		msg.setBuserId(id);
		msg.setContent(content);
		msg.setHousekeeperId(suserId);
		msg.setSendType(0);
		msg.setMessageType(0);
		msg.setStatus(0);
		msgMapper.insert(msg);
		
		String notify_title = "楼宇管家,"+suer.getName() + ",发给您一条信息";
		String notify_detail = content;
		String notify_type = "1:"+suer.getId()+":-1";
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suer.getId());
		notify.setStatus(1);
		notify.setSendCount(1);
		notify.setType(notify_type);
		notifyMapper.insertAndGetId(notify);
		NotifySend notify_send = new NotifySend();
		notify_send.setBuserId(id);
		notify_send.setNotifyId(notify.getId());
		notify_send.setStatus(0);
		notifySendMapper.insert(notify_send);
		List<String> alias=new ArrayList<String>(1);
		alias.add(id+"buser");
		JPushChatModel jpModel=new JPushChatModel();
		jpModel.setType(notify_type);
		jpModel.setTitle(notify_title);
		jpModel.setContent(notify_detail);
		JPushUtil.jPushAssign(alias, jpModel);
	}
	
}
