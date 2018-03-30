package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RoleResourceManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RoleResourceMapper;
import com.bola.nwcl.dal.mybatis.model.RoleResource;
import com.bola.nwcl.dal.mybatis.model.RoleResourceExample;

@Service
public class RoleResourceManagerImpl  extends ManagerImpl<RoleResource, RoleResourceExample, Long> implements RoleResourceManager{
	
	@SuppressWarnings("unused")
	private RoleResourceMapper roleResourceMapper;
	
	@Autowired
	public RoleResourceManagerImpl(RoleResourceMapper roleResourceMapper) {
		this.mapper = roleResourceMapper;
		this.roleResourceMapper = roleResourceMapper;
	}


}
