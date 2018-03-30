package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.NotifyImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.NotifyImgMapper;
import com.bola.nwcl.dal.mybatis.model.NotifyImg;
import com.bola.nwcl.dal.mybatis.model.NotifyImgExample;

@Service
public class NotifyImgManagerImpl  extends ManagerImpl<NotifyImg, NotifyImgExample, Long> implements NotifyImgManager{
	
	@SuppressWarnings("unused")
	private NotifyImgMapper notifyImgMapper;
	
	@Autowired
	public NotifyImgManagerImpl(NotifyImgMapper notifyImgMapper) {
		this.mapper = notifyImgMapper;
		this.notifyImgMapper = notifyImgMapper;
	}

}
