package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HonourEnjoyRelationManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyRelationMapper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRelation;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRelationExample;

@Service
public class HonourEnjoyRelationManagerImpl  extends ManagerImpl<HonourEnjoyRelation, HonourEnjoyRelationExample, Long> implements HonourEnjoyRelationManager{
	
	@SuppressWarnings("unused")
	private HonourEnjoyRelationMapper roleMapper;
	
	@Autowired
	public HonourEnjoyRelationManagerImpl(HonourEnjoyRelationMapper roleMapper) {
		this.mapper = roleMapper;
		this.roleMapper = roleMapper;
	}
}
