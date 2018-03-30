package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.market.OwnersMarketRatingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRating;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRatingExample;

public interface OwnersMarketRatingMapper extends Mapper<OwnersMarketRating, OwnersMarketRatingExample, Long> {
	
	void insertAndGetId(OwnersMarketRating ownersMarketRating);
	
	List<OwnersMarketRatingModel> getOwnersMarketRating(Map<String, Object> condition);
}