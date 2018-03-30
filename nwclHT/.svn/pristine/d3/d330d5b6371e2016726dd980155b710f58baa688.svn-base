package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.NotifySendManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.NotifySendExample;

@Service
public class NotifySendManagerImpl  extends ManagerImpl<NotifySend, NotifySendExample, Long> implements NotifySendManager{
	
	@SuppressWarnings("unused")
	private NotifySendMapper notifySendMapper;
	
	@Autowired
	public NotifySendManagerImpl(NotifySendMapper notifySendMapper) {
		this.mapper = notifySendMapper;
		this.notifySendMapper = notifySendMapper;
	}

}
