package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.honour.HonourEnjoyRatingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRating;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRatingExample;

public interface HonourEnjoyRatingMapper extends Mapper<HonourEnjoyRating, HonourEnjoyRatingExample, Long> {
	List<HonourEnjoyRatingModel> getPageRatingModel(Map<String, Object> condition);
	HonourEnjoyRatingModel getOneRatingModel(Map<String, Object> condition);
	void insertAndGetId(HonourEnjoyRating honourEnjoyRating);
}