package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.BizUserRoomMapper;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;

@Service
public class BizUserRoomManagerImpl  extends ManagerImpl<BizUserRoom, BizUserRoomExample, Long> implements BizUserRoomManager{
	
	@SuppressWarnings("unused")
	private BizUserRoomMapper bizUserRoomMapper;
	
	@Autowired
	public BizUserRoomManagerImpl(BizUserRoomMapper bizUserRoomMapper) {
		this.mapper = bizUserRoomMapper;
		this.bizUserRoomMapper = bizUserRoomMapper;
	}

}
