package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.AuthorizeUserModel;
import com.bola.nwcl.api.model.BizUserCouponModel;
import com.bola.nwcl.api.model.RoomModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;

public interface BizUserMapper extends Mapper<BizUser, BizUserExample, Long> {
	List<RoomModel> getUserRooms(String phone);
	List<RoomModel> getUserActiveRooms(long id);
	List<RoomModel> getUserAllRooms(long id);
	void insertAndGetId(BizUser bizUser);
	List<AuthorizeUserModel> getAllAuthorizeUser(long id);
	
	List<BizUserCouponModel> getUserAllCoupon(Map<String, Object> condition);
	
	
}