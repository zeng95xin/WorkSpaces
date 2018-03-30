package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.AdminCommunityManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.AdminCommunityMapper;
import com.bola.nwcl.dal.mybatis.model.AdminCommunity;
import com.bola.nwcl.dal.mybatis.model.AdminCommunityExample;

@Service
public class AdminCommunityManagerImpl extends ManagerImpl<AdminCommunity, AdminCommunityExample, Long>
		implements AdminCommunityManager {
	private AdminCommunityMapper adminCommunityMapper;
	@Autowired
	public AdminCommunityManagerImpl(AdminCommunityMapper adminCommunityMapper) {
		this.mapper = adminCommunityMapper;
		this.adminCommunityMapper = adminCommunityMapper;
	}
	
}
