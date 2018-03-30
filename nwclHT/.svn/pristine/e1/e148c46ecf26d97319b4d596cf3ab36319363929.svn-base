package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.neighbor.NeighborHelpMessageModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessage;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessageExample;

public interface NeighborHelpMessageMapper extends Mapper<NeighborHelpMessage, NeighborHelpMessageExample, Long> {
	List<NeighborHelpMessageModel> getPageMessageModel(Map<String, Object> condition);
	void insertAndGetId(NeighborHelpMessage neighborHelpMessage);
	
	NeighborHelpMessageModel getOneMessageModel(Map<String, Object> condition);
}