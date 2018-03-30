package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.MaintenanceOfferManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceOfferMapper;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOffer;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOfferExample;

@Service
public class MaintenanceOfferManagerImpl  extends ManagerImpl<MaintenanceOffer, MaintenanceOfferExample, Long> implements MaintenanceOfferManager{
	
	@SuppressWarnings("unused")
	private MaintenanceOfferMapper maintenanceOfferMapper;
	
	@Autowired
	public MaintenanceOfferManagerImpl(MaintenanceOfferMapper maintenanceOfferMapper) {
		this.mapper = maintenanceOfferMapper;
		this.maintenanceOfferMapper = maintenanceOfferMapper;
	}

}
