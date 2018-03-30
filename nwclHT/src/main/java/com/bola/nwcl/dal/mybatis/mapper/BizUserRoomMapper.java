package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.RoomBuserModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;

public interface BizUserRoomMapper extends Mapper<BizUserRoom, BizUserRoomExample, Long> {
	List<RoomBuserModel> getAllRoomUser(Map<String, Object> condition);
}