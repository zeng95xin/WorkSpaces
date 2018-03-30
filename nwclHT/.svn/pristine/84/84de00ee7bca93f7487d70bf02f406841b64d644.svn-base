package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.IdlethingRatingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.IdlethingRating;
import com.bola.nwcl.dal.mybatis.model.IdlethingRatingExample;

public interface IdlethingRatingMapper extends Mapper<IdlethingRating, IdlethingRatingExample, Long> {
	List<IdlethingRatingModel> getPageRatingModel(Map<String, Object> condition);
	
	IdlethingRatingModel getOneRatingModel(Map<String, Object> condition);
	void insertAndGetId(IdlethingRating idlethingRating);
}