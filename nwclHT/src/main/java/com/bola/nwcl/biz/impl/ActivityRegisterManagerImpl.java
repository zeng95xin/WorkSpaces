package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ActivityRegisterManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.ActivityRegisterMapper;
import com.bola.nwcl.dal.mybatis.model.ActivityRegister;
import com.bola.nwcl.dal.mybatis.model.ActivityRegisterExample;

@Service
public class ActivityRegisterManagerImpl  extends ManagerImpl<ActivityRegister, ActivityRegisterExample, Long> implements ActivityRegisterManager{
	
	@SuppressWarnings("unused")
	private ActivityRegisterMapper activityRegisterMapper;
	
	@Autowired
	public ActivityRegisterManagerImpl(ActivityRegisterMapper activityRegisterMapper) {
		this.mapper = activityRegisterMapper;
		this.activityRegisterMapper = activityRegisterMapper;
	}

}
