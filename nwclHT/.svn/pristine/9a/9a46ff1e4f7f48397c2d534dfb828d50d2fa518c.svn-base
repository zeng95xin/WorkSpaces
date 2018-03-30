package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HonourEnjoyUserManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyUserMapper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUser;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUserExample;

@Service
public class HonourEnjoyUserManagerImpl  extends ManagerImpl<HonourEnjoyUser, HonourEnjoyUserExample, Long> implements HonourEnjoyUserManager{
	
	@SuppressWarnings("unused")
	private HonourEnjoyUserMapper roleMapper;
	
	@Autowired
	public HonourEnjoyUserManagerImpl(HonourEnjoyUserMapper roleMapper) {
		this.mapper = roleMapper;
		this.roleMapper = roleMapper;
	}

	@Override
	public void insertAndGetId(HonourEnjoyUser honourEnjoy) {
		roleMapper.insertAndGetId(honourEnjoy);
	}

}
