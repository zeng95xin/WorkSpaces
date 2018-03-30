package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HousekeeperDetailManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HousekeeperDetailMapper;
import com.bola.nwcl.dal.mybatis.model.HousekeeperDetail;
import com.bola.nwcl.dal.mybatis.model.HousekeeperDetailExample;

@Service
public class HousekeeperDetailManagerImpl  extends ManagerImpl<HousekeeperDetail, HousekeeperDetailExample, Long> implements HousekeeperDetailManager{
	
	@SuppressWarnings("unused")
	private HousekeeperDetailMapper housekeeperDetailMapper;
	
	@Autowired
	public HousekeeperDetailManagerImpl(HousekeeperDetailMapper housekeeperDetailMapper) {
		this.mapper = housekeeperDetailMapper;
		this.housekeeperDetailMapper = housekeeperDetailMapper;
	}

}
