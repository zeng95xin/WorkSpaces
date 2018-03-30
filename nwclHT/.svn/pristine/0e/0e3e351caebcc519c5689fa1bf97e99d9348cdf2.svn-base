package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.activity.ActivityRatingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.ActivityRating;
import com.bola.nwcl.dal.mybatis.model.ActivityRatingExample;

public interface ActivityRatingMapper extends Mapper<ActivityRating, ActivityRatingExample, Long> {
	List<ActivityRatingModel> getPageRatingModel(Map<String, Object> condition);
	ActivityRatingModel getOneRatingModel(Map<String, Object> condition);
	void insertAndGetId(ActivityRating activityRating);
}