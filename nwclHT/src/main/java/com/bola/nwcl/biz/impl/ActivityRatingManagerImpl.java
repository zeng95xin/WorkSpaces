package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.activity.ActivityRatingModel;
import com.bola.nwcl.biz.ActivityRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.ActivityRatingMapper;
import com.bola.nwcl.dal.mybatis.model.ActivityRating;
import com.bola.nwcl.dal.mybatis.model.ActivityRatingExample;

@Service
public class ActivityRatingManagerImpl  extends ManagerImpl<ActivityRating, ActivityRatingExample, Long> implements ActivityRatingManager{
	
	private ActivityRatingMapper activityRatingMapper;
	
	@Autowired
	public ActivityRatingManagerImpl(ActivityRatingMapper activityRatingMapper) {
		this.mapper = activityRatingMapper;
		this.activityRatingMapper = activityRatingMapper;
	}

	@Override
	public List<ActivityRatingModel> getLimitActivityRatingModel(long id, long buserId, int ratingCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", ratingCount);
		return activityRatingMapper.getPageRatingModel(map);
	}

	@Override
	public Page<ActivityRatingModel> getPageActivityRatingModel(long id, long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		ActivityRatingExample example_rating = new ActivityRatingExample();
		example_rating.or().andActivityIdEqualTo(id);
		int count = activityRatingMapper.countByExample(example_rating);
		List<ActivityRatingModel> list = activityRatingMapper.getPageRatingModel(map);
		Page<ActivityRatingModel> pageData = new PageImpl<ActivityRatingModel>(list, pager, count);
		return pageData;
	}

	@Override
	public ActivityRatingModel getOneRatingModel(long id, long buserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		return activityRatingMapper.getOneRatingModel(map);
	}

	@Override
	public void insertAndGetId(ActivityRating activityRating) {
		activityRatingMapper.insertAndGetId(activityRating);
	}

}
