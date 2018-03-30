package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.OwnersMarketZanManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketZanMapper;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketZan;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketZanExample;

@Service
public class OwnersMarketZanManagerImpl  extends ManagerImpl<OwnersMarketZan, OwnersMarketZanExample, Long> implements OwnersMarketZanManager{
	
	@SuppressWarnings("unused")
	private OwnersMarketZanMapper ownersMarketZanMapper;
	
	@Autowired
	public OwnersMarketZanManagerImpl(OwnersMarketZanMapper ownersMarketZanMapper) {
		this.mapper = ownersMarketZanMapper;
		this.ownersMarketZanMapper = ownersMarketZanMapper;
	}

}
