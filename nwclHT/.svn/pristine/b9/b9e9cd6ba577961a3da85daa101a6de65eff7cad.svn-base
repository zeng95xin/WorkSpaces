package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RecommendZanManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RecommendZanMapper;
import com.bola.nwcl.dal.mybatis.model.RecommendZan;
import com.bola.nwcl.dal.mybatis.model.RecommendZanExample;

@Service
public class RecommendZanManagerImpl  extends ManagerImpl<RecommendZan, RecommendZanExample, Long> implements RecommendZanManager{
	
	@SuppressWarnings("unused")
	private RecommendZanMapper recommendZanMapper;
	
	@Autowired
	public RecommendZanManagerImpl(RecommendZanMapper recommendZanMapper) {
		this.mapper = recommendZanMapper;
		this.recommendZanMapper = recommendZanMapper;
	}

}
