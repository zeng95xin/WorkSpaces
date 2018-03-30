package com.bola.nwcl.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.RoomDetailModel;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

@Service
public class RoomManagerImpl  extends ManagerImpl<Room, RoomExample, Long> implements RoomManager{
	
	private RoomMapper roomMapper;
	
	@Autowired
	public RoomManagerImpl(RoomMapper roomMapper) {
		this.mapper = roomMapper;
		this.roomMapper = roomMapper;
	}

	@Override
	public List<RoomDetailModel> getRoomDetail(Map<String,Object> map) {
		return roomMapper.getRoomDetail(map);
	}
	
}
