package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.market.OwnersMarketRatingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRating;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRatingExample;

public interface OwnersMarketRatingManager extends Manager<OwnersMarketRating, OwnersMarketRatingExample, Long>{
	void insertAndGetId(OwnersMarketRating ownersMarketRating);
	
	OwnersMarketRatingModel getOneRatingModel(long ratingId, long buserId);
	Page<OwnersMarketRatingModel> getPageRatingModel(long id, long buserId, int page, int rows);
}
