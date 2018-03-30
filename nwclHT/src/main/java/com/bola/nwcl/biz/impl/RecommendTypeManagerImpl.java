package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RecommendTypeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RecommendTypeMapper;
import com.bola.nwcl.dal.mybatis.model.RecommendType;
import com.bola.nwcl.dal.mybatis.model.RecommendTypeExample;

@Service
public class RecommendTypeManagerImpl  extends ManagerImpl<RecommendType, RecommendTypeExample, Long> implements RecommendTypeManager{
	
	@SuppressWarnings("unused")
	private RecommendTypeMapper recommendTypeMapper;
	
	@Autowired
	public RecommendTypeManagerImpl(RecommendTypeMapper recommendTypeMapper) {
		this.mapper = recommendTypeMapper;
		this.recommendTypeMapper = recommendTypeMapper;
	}

}
