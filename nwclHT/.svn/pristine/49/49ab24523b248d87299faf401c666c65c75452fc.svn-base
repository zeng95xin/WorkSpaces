package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.IdlethingRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingRatingMapper;
import com.bola.nwcl.dal.mybatis.model.IdlethingRating;
import com.bola.nwcl.dal.mybatis.model.IdlethingRatingExample;

@Service
public class IdlethingRatingManagerImpl  extends ManagerImpl<IdlethingRating, IdlethingRatingExample, Long> implements IdlethingRatingManager{
	
	@SuppressWarnings("unused")
	private IdlethingRatingMapper IdlethingRatingMapper;
	
	@Autowired
	public IdlethingRatingManagerImpl(IdlethingRatingMapper IdlethingRatingMapper) {
		this.mapper = IdlethingRatingMapper;
		this.IdlethingRatingMapper = IdlethingRatingMapper;
	}

}
