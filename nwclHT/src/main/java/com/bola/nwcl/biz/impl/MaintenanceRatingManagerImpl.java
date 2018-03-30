package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.MaintenanceRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceRatingMapper;
import com.bola.nwcl.dal.mybatis.model.MaintenanceRating;
import com.bola.nwcl.dal.mybatis.model.MaintenanceRatingExample;

@Service
public class MaintenanceRatingManagerImpl  extends ManagerImpl<MaintenanceRating, MaintenanceRatingExample, Long> implements MaintenanceRatingManager{
	
	@SuppressWarnings("unused")
	private MaintenanceRatingMapper maintenanceRatingMapper;
	
	@Autowired
	public MaintenanceRatingManagerImpl(MaintenanceRatingMapper maintenanceRatingMapper) {
		this.mapper = maintenanceRatingMapper;
		this.maintenanceRatingMapper = maintenanceRatingMapper;
	}
}
