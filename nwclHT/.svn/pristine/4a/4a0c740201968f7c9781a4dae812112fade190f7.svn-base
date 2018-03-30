package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.LikeShareZanManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareZanMapper;
import com.bola.nwcl.dal.mybatis.model.LikeShareZan;
import com.bola.nwcl.dal.mybatis.model.LikeShareZanExample;

@Service
public class LikeShareZanManagerImpl  extends ManagerImpl<LikeShareZan, LikeShareZanExample, Long> implements LikeShareZanManager{
	
	@SuppressWarnings("unused")
	private LikeShareZanMapper likeShareZanMapper;
	
	@Autowired
	public LikeShareZanManagerImpl(LikeShareZanMapper likeShareZanMapper) {
		this.mapper = likeShareZanMapper;
		this.likeShareZanMapper = likeShareZanMapper;
	}

}
