package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HousekeeperManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HousekeeperMapper;
import com.bola.nwcl.dal.mybatis.model.Housekeeper;
import com.bola.nwcl.dal.mybatis.model.HousekeeperExample;

@Service
public class HousekeeperManagerImpl  extends ManagerImpl<Housekeeper, HousekeeperExample, Long> implements HousekeeperManager{
	
	@SuppressWarnings("unused")
	private HousekeeperMapper housekeeperMapper;
	
	@Autowired
	public HousekeeperManagerImpl(HousekeeperMapper housekeeperMapper) {
		this.mapper = housekeeperMapper;
		this.housekeeperMapper = housekeeperMapper;
	}


}
