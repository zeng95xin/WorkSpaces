package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.HousekeeperUserMessageModel;
import com.bola.nwcl.api.model.keeper.KeeperUserMessageIndexModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.HousekeeperExample;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessage;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample;

public interface HousekeeperUserMessageMapper extends Mapper<HousekeeperUserMessage, HousekeeperUserMessageExample, Long> {
	
	List<KeeperUserMessageIndexModel> getIndexMessage(Map<String, Object> condition);
	int getIndexMessageCount(Map<String, Object> condition);
	
	
	List<HousekeeperUserMessageModel> getUserAllHousekeeperMsg(Map<String, Object> condition);
	
	void updateMarkHasNotRead(Map<String, Object> condition);
	
	int selectCountByExample(HousekeeperUserMessageExample example); 
	
}