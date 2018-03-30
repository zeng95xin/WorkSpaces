package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RoomPhoneManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RoomPhoneMapper;
import com.bola.nwcl.dal.mybatis.model.RoomPhone;
import com.bola.nwcl.dal.mybatis.model.RoomPhoneExample;

@Service
public class RoomPhoneManagerImpl  extends ManagerImpl<RoomPhone, RoomPhoneExample, Long> implements RoomPhoneManager{
	
	@SuppressWarnings("unused")
	private RoomPhoneMapper roomPhoneMapper;
	
	@Autowired
	public RoomPhoneManagerImpl(RoomPhoneMapper roomPhoneMapper) {
		this.mapper = roomPhoneMapper;
		this.roomPhoneMapper = roomPhoneMapper;
	}

}
