package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.MaintenanceProspectingImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceProspectingImgMapper;
import com.bola.nwcl.dal.mybatis.model.MaintenanceProspectingImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceProspectingImgExample;

@Service
public class MaintenanceProspectingImgManagerImpl  extends ManagerImpl<MaintenanceProspectingImg, MaintenanceProspectingImgExample, Long> implements MaintenanceProspectingImgManager{
	
	@SuppressWarnings("unused")
	private MaintenanceProspectingImgMapper maintenanceProspectingImgMapper;
	
	@Autowired
	public MaintenanceProspectingImgManagerImpl(MaintenanceProspectingImgMapper maintenanceProspectingImgMapper) {
		this.mapper = maintenanceProspectingImgMapper;
		this.maintenanceProspectingImgMapper = maintenanceProspectingImgMapper;
	}

}
