package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.likeshare.LikeShareRatingSimpleModel;
import com.bola.nwcl.biz.LikeShareRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareRatingMapper;
import com.bola.nwcl.dal.mybatis.model.LikeShareRating;
import com.bola.nwcl.dal.mybatis.model.LikeShareRatingExample;

@Service
public class LikeShareRatingManagerImpl  extends ManagerImpl<LikeShareRating, LikeShareRatingExample, Long> implements LikeShareRatingManager{
	
	private LikeShareRatingMapper likeShareRatingMapper;
	
	@Autowired
	public LikeShareRatingManagerImpl(LikeShareRatingMapper likeShareRatingMapper) {
		this.mapper = likeShareRatingMapper;
		this.likeShareRatingMapper = likeShareRatingMapper;
	}

	@Override
	public LikeShareRatingSimpleModel getOneRatingModel(long id, long buserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		LikeShareRatingSimpleModel rating = likeShareRatingMapper.getOneRatingModel(map);
		return rating;
	}

	@Override
	public List<LikeShareRatingSimpleModel> getLimitRatingModel(long id, Long buserId, int ratingCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", ratingCount);
		List<LikeShareRatingSimpleModel> list = likeShareRatingMapper.getRatingModel(map);
		return list;
	}

	@Override
	public Page<LikeShareRatingSimpleModel> getPageRatingModel(long id, Long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		LikeShareRatingExample example_r = new LikeShareRatingExample();
		example_r.or().andLikeShareIdEqualTo(id);
		int count = likeShareRatingMapper.countByExample(example_r);
		
		List<LikeShareRatingSimpleModel> list = likeShareRatingMapper.getRatingModel(map);
		Page<LikeShareRatingSimpleModel> pageData = new PageImpl<LikeShareRatingSimpleModel>(list, pager, count);
		return pageData;
	}

	@Override
	public void insertAndGetId(LikeShareRating likeShareRating) {
		likeShareRatingMapper.insertAndGetId(likeShareRating);
	}

}
