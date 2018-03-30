package com.bola.nwcl.biz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.likeshare.LikeShareRatingSimpleModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.LikeShareRating;
import com.bola.nwcl.dal.mybatis.model.LikeShareRatingExample;

public interface LikeShareRatingManager extends Manager<LikeShareRating, LikeShareRatingExample, Long>{
	
	LikeShareRatingSimpleModel getOneRatingModel(long id,long buserId);
	List<LikeShareRatingSimpleModel> getLimitRatingModel(long id, Long buserId, int ratingCount);
	Page<LikeShareRatingSimpleModel> getPageRatingModel(long id, Long buserId, int page, int rows);
	
	void insertAndGetId(LikeShareRating likeShareRating);
	
}
