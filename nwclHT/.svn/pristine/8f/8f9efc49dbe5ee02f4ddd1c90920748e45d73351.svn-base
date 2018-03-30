package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.RoomDetailModel;
import com.bola.nwcl.api.model.keeper.FloorModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

public interface RoomMapper extends Mapper<Room, RoomExample, Long> {
	void insertAndGetId(Room room);
	List<RoomDetailModel> getRoomDetail(Map<String,Object> map);
	List<FloorModel> getAllFloor(Long id);
	
	//keeper
	List<RoomDetailModel> searchRoom(Map<String,Object> map);
	
}