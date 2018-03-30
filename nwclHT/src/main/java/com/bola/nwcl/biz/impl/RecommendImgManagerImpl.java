package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RecommendImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RecommendImgMapper;
import com.bola.nwcl.dal.mybatis.model.RecommendImg;
import com.bola.nwcl.dal.mybatis.model.RecommendImgExample;

@Service
public class RecommendImgManagerImpl  extends ManagerImpl<RecommendImg, RecommendImgExample, Long> implements RecommendImgManager{
	
	@SuppressWarnings("unused")
	private RecommendImgMapper recommendImgMapper;
	
	@Autowired
	public RecommendImgManagerImpl(RecommendImgMapper recommendImgMapper) {
		this.mapper = recommendImgMapper;
		this.recommendImgMapper = recommendImgMapper;
	}

}
