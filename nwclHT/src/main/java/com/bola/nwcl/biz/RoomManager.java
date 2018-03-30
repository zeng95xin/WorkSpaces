package com.bola.nwcl.biz;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.RoomDetailModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

public interface RoomManager extends Manager<Room, RoomExample, Long>{
	/**
	 * 获取指定房间详情,如果id为null,则获取所有房间详情
	 * @param id
	 * @return
	 */
	List<RoomDetailModel> getRoomDetail(Map<String,Object> map);
}
