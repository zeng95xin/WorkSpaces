package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.AnnouncementSendManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementSendMapper;
import com.bola.nwcl.dal.mybatis.model.AnnouncementSend;
import com.bola.nwcl.dal.mybatis.model.AnnouncementSendExample;

@Service
public class AnnouncementSendManagerImpl  extends ManagerImpl<AnnouncementSend, AnnouncementSendExample, Long> implements AnnouncementSendManager{
	
	@SuppressWarnings("unused")
	private AnnouncementSendMapper announcementSendMapper;
	
	@Autowired
	public AnnouncementSendManagerImpl(AnnouncementSendMapper announcementSendMapper) {
		this.mapper = announcementSendMapper;
		this.announcementSendMapper = announcementSendMapper;
	}

}
