package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.keeper.KeeperUserModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;

public interface ServiceUserMapper extends Mapper<ServiceUser, ServiceUserExample, Long> {
	
	void insertAndGetId(ServiceUser serviceUser);
	
	List<KeeperUserModel> getKeeperModel(Map<String,Object> condition);
}