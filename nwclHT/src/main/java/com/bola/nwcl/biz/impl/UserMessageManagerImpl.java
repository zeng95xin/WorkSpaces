package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.UserMessageModel;
import com.bola.nwcl.biz.UserMessageManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.UserMessageMapper;
import com.bola.nwcl.dal.mybatis.model.UserMessage;
import com.bola.nwcl.dal.mybatis.model.UserMessageExample;

@Service
public class UserMessageManagerImpl  extends ManagerImpl<UserMessage, UserMessageExample, Long> implements UserMessageManager{
	
	private UserMessageMapper userMessageMapper;
	
	@Autowired
	public UserMessageManagerImpl(UserMessageMapper userMessageMapper) {
		this.mapper = userMessageMapper;
		this.userMessageMapper = userMessageMapper;
	}

	@Override
	public Page<UserMessageModel> getUserAllMsg(long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		UserMessageExample example = new UserMessageExample();
		example.or().andBuserIdEqualTo(buserId);
		int count = userMessageMapper.countByExample(example);
		List<UserMessageModel> list = userMessageMapper.getUserAllMsg(map);
		Page<UserMessageModel> pageData = new PageImpl<UserMessageModel>(list, pager, count);
		return pageData;
	}

}
