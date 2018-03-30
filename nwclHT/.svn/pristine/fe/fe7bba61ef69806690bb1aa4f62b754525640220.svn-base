package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.RecommendModel;
import com.bola.nwcl.api.model.indexes.OwnerFairIndexModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Recommend;
import com.bola.nwcl.dal.mybatis.model.RecommendExample;

public interface RecommendMapper extends Mapper<Recommend, RecommendExample, Long> {
	void insertAndGetId(Recommend recommend);
	List<RecommendModel> getAllRecommend(Map<String,Object> condition);
	
	List<OwnerFairIndexModel> getOwnerFairIndex(Map<String,Object> condition);
	
}