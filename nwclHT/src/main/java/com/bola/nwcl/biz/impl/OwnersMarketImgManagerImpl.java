package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.OwnersMarketImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketImgMapper;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketImg;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketImgExample;

@Service
public class OwnersMarketImgManagerImpl  extends ManagerImpl<OwnersMarketImg, OwnersMarketImgExample, Long> implements OwnersMarketImgManager{
	
	@SuppressWarnings("unused")
	private OwnersMarketImgMapper ownersMarketImgMapper;
	
	@Autowired
	public OwnersMarketImgManagerImpl(OwnersMarketImgMapper ownersMarketImgMapper) {
		this.mapper = ownersMarketImgMapper;
		this.ownersMarketImgMapper = ownersMarketImgMapper;
	}

}
