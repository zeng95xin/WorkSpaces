package com.bola.nwcl.biz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.activity.ActivityRatingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.ActivityRating;
import com.bola.nwcl.dal.mybatis.model.ActivityRatingExample;

public interface ActivityRatingManager extends Manager<ActivityRating, ActivityRatingExample, Long>{
	List<ActivityRatingModel> getLimitActivityRatingModel(long id, long buserId, int ratingCount);
	Page<ActivityRatingModel> getPageActivityRatingModel(long id, long buserId, int page, int rows);
	ActivityRatingModel getOneRatingModel(long id, long buserId);
	void insertAndGetId(ActivityRating activityRating);
}
