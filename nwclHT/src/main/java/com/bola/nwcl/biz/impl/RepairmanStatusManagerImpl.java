package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RepairmanStatusManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RepairmanStatusMapper;
import com.bola.nwcl.dal.mybatis.model.RepairmanStatus;
import com.bola.nwcl.dal.mybatis.model.RepairmanStatusExample;


@Service
public class RepairmanStatusManagerImpl  extends ManagerImpl<RepairmanStatus, RepairmanStatusExample, Long> implements RepairmanStatusManager{
	
	private RepairmanStatusMapper articleReleaseMapper;
	
	@Autowired
	public RepairmanStatusManagerImpl(RepairmanStatusMapper articleReleaseMapper) {
		this.mapper = articleReleaseMapper;
		this.articleReleaseMapper = articleReleaseMapper;
	}

	
}
