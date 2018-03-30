package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.UserWebmasterMessageModel;
import com.bola.nwcl.biz.UserWebmasterMessageManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.UserWebmasterMessageMapper;
import com.bola.nwcl.dal.mybatis.model.UserWebmasterMessage;
import com.bola.nwcl.dal.mybatis.model.UserWebmasterMessageExample;

@Service
public class UserWebmasterMessageManagerImpl  extends ManagerImpl<UserWebmasterMessage, UserWebmasterMessageExample, Long> implements UserWebmasterMessageManager{
	
	private UserWebmasterMessageMapper userWebmasterMessageMapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	public UserWebmasterMessageManagerImpl(UserWebmasterMessageMapper userWebmasterMessageMapper) {
		this.mapper = userWebmasterMessageMapper;
		this.userWebmasterMessageMapper = userWebmasterMessageMapper;
	}

	@Override
	public void updateSendMessage(String saveDir, long buserSendId,
			long buserReceiveId, HttpServletRequest request, String type,
			String content, MultipartFile[] files) throws Exception {
		if(files == null){
			UserWebmasterMessage msg = new UserWebmasterMessage();
			msg.setBuserReceiveId(buserReceiveId);
			msg.setBuserSendId(buserSendId);
			msg.setContent(content);
			msg.setMessageType(0);
			msg.setStatus(0);
			userWebmasterMessageMapper.insert(msg);
		}else{
			String fileName = sdf.format(new Date()) + UUID.randomUUID().toString().substring(0,10);
			for(MultipartFile file : files){
				if(!file.isEmpty()){
					UserWebmasterMessage msg = new UserWebmasterMessage();
					msg.setBuserReceiveId(buserReceiveId);
					msg.setBuserSendId(buserSendId);
					msg.setMessageType(Integer.valueOf(type));
					String savePath = FileUploadDeleteUtil.upload(file, request, saveDir, fileName);
					msg.setContent(savePath);
					msg.setStatus(0);
					userWebmasterMessageMapper.insert(msg);
				}
			}
		}
		
	}

	@Override
	public Page<UserWebmasterMessageModel> updateGetUserAllMsgWithNeitherUser(
			long buserId, long neitherId, int page, int rows) {
		PageRequest pager=new PageRequest(page - 1, rows);
		UserWebmasterMessageExample example_msg=new UserWebmasterMessageExample();
		example_msg.or().andBuserReceiveIdEqualTo(buserId).andBuserSendIdEqualTo(neitherId);
		example_msg.or().andBuserReceiveIdEqualTo(neitherId).andBuserSendIdEqualTo(buserId);
		int count = userWebmasterMessageMapper.countByExample(example_msg);
		Map<String, Object> condition=new HashMap<String, Object>(4);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("buserId", buserId);
		condition.put("neitherId", neitherId);
		List<UserWebmasterMessageModel> list=userWebmasterMessageMapper.getUserAllMsgWithNeitherUser(condition);
		Page<UserWebmasterMessageModel> pageData=new PageImpl<UserWebmasterMessageModel>(list,pager,count);
		List<Long> list_long = new ArrayList<Long>(list.size());
		for(UserWebmasterMessageModel model : list){
			list_long.add(model.getId());
		}
		UserWebmasterMessageExample example_update=new UserWebmasterMessageExample();
		example_update.or().andIdIn(list_long).andBuserReceiveIdEqualTo(buserId);
		UserWebmasterMessage msg=new UserWebmasterMessage();
		msg.setStatus(1);
		userWebmasterMessageMapper.updateByExampleSelective(msg, example_update);
		return pageData;
	}
	
	

}
