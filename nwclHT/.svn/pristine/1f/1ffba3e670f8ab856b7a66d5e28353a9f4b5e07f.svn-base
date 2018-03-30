package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HonourEnjoyImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyImgMapper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyImg;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyImgExample;

@Service
public class HonourEnjoyImgManagerImpl  extends ManagerImpl<HonourEnjoyImg, HonourEnjoyImgExample, Long> implements HonourEnjoyImgManager{
	
	@SuppressWarnings("unused")
	private HonourEnjoyImgMapper roleMapper;
	
	@Autowired
	public HonourEnjoyImgManagerImpl(HonourEnjoyImgMapper roleMapper) {
		this.mapper = roleMapper;
		this.roleMapper = roleMapper;
	}
}
