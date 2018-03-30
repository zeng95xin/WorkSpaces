package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.NeighborHelpImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpImgMapper;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImg;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImgExample;

@Service
public class NeighborHelpImgManagerImpl  extends ManagerImpl<NeighborHelpImg, NeighborHelpImgExample, Long> implements NeighborHelpImgManager{
	
	private NeighborHelpImgMapper neighborHelpImgMapper;
	
	@Autowired
	public NeighborHelpImgManagerImpl(NeighborHelpImgMapper neighborHelpImgMapper) {
		this.mapper = neighborHelpImgMapper;
		this.neighborHelpImgMapper = neighborHelpImgMapper;
	}

	@Override
	public void insertAndGetId(NeighborHelpImg neighborHelpImg) {
		neighborHelpImgMapper.insertAndGetId(neighborHelpImg);
	}

}
