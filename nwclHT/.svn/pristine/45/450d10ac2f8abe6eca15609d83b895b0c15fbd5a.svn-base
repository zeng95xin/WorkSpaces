package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.lecture.LectureHallRatingModel;
import com.bola.nwcl.biz.LectureHallRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LectureHallRatingMapper;
import com.bola.nwcl.dal.mybatis.model.LectureHallRating;
import com.bola.nwcl.dal.mybatis.model.LectureHallRatingExample;

@Service
public class LectureHallRatingManagerImpl  extends ManagerImpl<LectureHallRating, LectureHallRatingExample, Long> implements LectureHallRatingManager{
	
	private LectureHallRatingMapper lectureHallRatingMapper;
	
	@Autowired
	public LectureHallRatingManagerImpl(LectureHallRatingMapper lectureHallRatingMapper) {
		this.mapper = lectureHallRatingMapper;
		this.lectureHallRatingMapper = lectureHallRatingMapper;
	}

	@Override
	public List<LectureHallRatingModel> getLimitRatingModel(long id, long buserId, int ratingCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", ratingCount);
		return lectureHallRatingMapper.getPageRatingModel(map);
	}

	@Override
	public Page<LectureHallRatingModel> getPageRatingModel(long id, long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		LectureHallRatingExample example_rating = new LectureHallRatingExample();
		example_rating.or().andLectureHallIdEqualTo(id);
		int count = lectureHallRatingMapper.countByExample(example_rating);
		List<LectureHallRatingModel> list = lectureHallRatingMapper.getPageRatingModel(map);
		Page<LectureHallRatingModel> pageData = new PageImpl<LectureHallRatingModel>(list, pager, count);
		return pageData;
	}

	@Override
	public LectureHallRatingModel getOneRatingModel(long id, long buserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		
		return lectureHallRatingMapper.getOneRatingModel(map);
	}

	@Override
	public void insertAndGetId(LectureHallRating lectureHallRating) {
		lectureHallRatingMapper.insertAndGetId(lectureHallRating);
	}

}
