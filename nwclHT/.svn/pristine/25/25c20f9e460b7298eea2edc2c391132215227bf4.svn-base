package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.MaintenanceImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceImgMapper;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImgExample;

@Service
public class MaintenanceImgManagerImpl  extends ManagerImpl<MaintenanceImg, MaintenanceImgExample, Long> implements MaintenanceImgManager{
	
	private MaintenanceImgMapper maintenanceImgMapper;
	
	@Autowired
	public MaintenanceImgManagerImpl(MaintenanceImgMapper maintenanceImgMapper) {
		this.mapper = maintenanceImgMapper;
		this.maintenanceImgMapper = maintenanceImgMapper;
	}

	@Override
	public void insertAndGetId(MaintenanceImg maintenanceImg) {
		maintenanceImgMapper.insertAndGetId(maintenanceImg);
	}

}
