package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RecommendRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RecommendRatingMapper;
import com.bola.nwcl.dal.mybatis.model.RecommendRating;
import com.bola.nwcl.dal.mybatis.model.RecommendRatingExample;

@Service
public class RecommendRatingManagerImpl  extends ManagerImpl<RecommendRating, RecommendRatingExample, Long> implements RecommendRatingManager{
	
	@SuppressWarnings("unused")
	private RecommendRatingMapper recommendRatingMapper;
	
	@Autowired
	public RecommendRatingManagerImpl(RecommendRatingMapper recommendRatingMapper) {
		this.mapper = recommendRatingMapper;
		this.recommendRatingMapper = recommendRatingMapper;
	}

}
