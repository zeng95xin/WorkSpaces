package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.NeighborHelpZanManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpZanMapper;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpZan;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpZanExample;

@Service
public class NeighborHelpZanManagerImpl  extends ManagerImpl<NeighborHelpZan, NeighborHelpZanExample, Long> implements NeighborHelpZanManager{
	
	@SuppressWarnings("unused")
	private NeighborHelpZanMapper neighborHelpZanMapper;
	
	@Autowired
	public NeighborHelpZanManagerImpl(NeighborHelpZanMapper neighborHelpZanMapper) {
		this.mapper = neighborHelpZanMapper;
		this.neighborHelpZanMapper = neighborHelpZanMapper;
	}

}
