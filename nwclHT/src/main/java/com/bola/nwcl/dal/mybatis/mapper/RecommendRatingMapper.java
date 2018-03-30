package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.RecommendRatingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.RecommendRating;
import com.bola.nwcl.dal.mybatis.model.RecommendRatingExample;

public interface RecommendRatingMapper extends Mapper<RecommendRating, RecommendRatingExample, Long> {
	List<RecommendRatingModel> getPageRatingModel(Map<String, Object> condition);
	RecommendRatingModel getOneRatingModel(Map<String, Object> condition);
	void insertAndGetId(RecommendRating recommendRating);
}