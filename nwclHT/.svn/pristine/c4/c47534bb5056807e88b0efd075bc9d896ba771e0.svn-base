package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.LikeShareImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareImgMapper;
import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.bola.nwcl.dal.mybatis.model.LikeShareImgExample;

@Service
public class LikeShareImgManagerImpl  extends ManagerImpl<LikeShareImg, LikeShareImgExample, Long> implements LikeShareImgManager{
	
	private LikeShareImgMapper likeShareImgMapper;
	
	@Autowired
	public LikeShareImgManagerImpl(LikeShareImgMapper likeShareImgMapper) {
		this.mapper = likeShareImgMapper;
		this.likeShareImgMapper = likeShareImgMapper;
	}

	@Override
	public void insertAndGetId(LikeShareImg likeShareImg) {
		likeShareImgMapper.insertAndGetId(likeShareImg);
	}

}
