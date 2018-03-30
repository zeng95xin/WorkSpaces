package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HousekeeperSchedulingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HousekeeperSchedulingMapper;
import com.bola.nwcl.dal.mybatis.model.HousekeeperScheduling;
import com.bola.nwcl.dal.mybatis.model.HousekeeperSchedulingExample;

@Service
public class HousekeeperSchedulingManagerImpl  extends ManagerImpl<HousekeeperScheduling, HousekeeperSchedulingExample, Long> implements HousekeeperSchedulingManager{
	
	@SuppressWarnings("unused")
	private HousekeeperSchedulingMapper housekeeperSchedulingMapper;
	
	@Autowired
	public HousekeeperSchedulingManagerImpl(HousekeeperSchedulingMapper housekeeperSchedulingMapper) {
		this.mapper = housekeeperSchedulingMapper;
		this.housekeeperSchedulingMapper = housekeeperSchedulingMapper;
	}

}
