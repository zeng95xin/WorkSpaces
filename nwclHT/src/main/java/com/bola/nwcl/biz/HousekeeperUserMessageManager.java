package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.HousekeeperUserMessageModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.HousekeeperExample;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessage;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample;

public interface HousekeeperUserMessageManager extends Manager<HousekeeperUserMessage, HousekeeperUserMessageExample, Long>{
	Page<HousekeeperUserMessageModel> getUserAllHousekeeperMsg(long buserId, int page, int rows);
	int selectCountByExample(HousekeeperUserMessageExample example); 
}
