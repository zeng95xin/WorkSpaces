package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.likeshare.LikeShareRatingSimpleModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.LikeShareRating;
import com.bola.nwcl.dal.mybatis.model.LikeShareRatingExample;

public interface LikeShareRatingMapper extends Mapper<LikeShareRating, LikeShareRatingExample, Long> {
	
	List<LikeShareRatingSimpleModel> getRatingModel(Map<String, Object> condition);
	
	void insertAndGetId(LikeShareRating likeShareRating);
	
	LikeShareRatingSimpleModel getOneRatingModel(Map<String, Object> condition);
}