package com.bola.nwcl.biz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.neighbor.NeighborHelpMessageModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessage;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessageExample;

public interface NeighborHelpMessageManager extends Manager<NeighborHelpMessage, NeighborHelpMessageExample, Long>{
	List<NeighborHelpMessageModel> getLimitMessageModel(long id, Long buserId, int messageCount);
	Page<NeighborHelpMessageModel> getPageMessageModel(long id, Long buserId, int page, int rows);
	
	void insertAndGetId(NeighborHelpMessage neighborHelpMessage);
	
	NeighborHelpMessageModel getOneMessageModel(long id,long buserId);
}
