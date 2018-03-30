package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.AnnouncementRatingModel;
import com.bola.nwcl.biz.AnnouncementRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementRatingMapper;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRating;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRatingExample;

@Service
public class AnnouncementRatingManagerImpl  extends ManagerImpl< AnnouncementRating,  AnnouncementRatingExample, Long> implements  AnnouncementRatingManager{
	
	private  AnnouncementRatingMapper  announcementRatingMapper;
	
	@Autowired
	public AnnouncementRatingManagerImpl(AnnouncementRatingMapper announcementRatingMapper) {
		this.mapper = announcementRatingMapper;
		this.announcementRatingMapper = announcementRatingMapper;
	}

	@Override
	public List<AnnouncementRatingModel> getLimitRatingModel(long id, long buserId, int ratingCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("limit", ratingCount);
		map.put("buserId", buserId);
		return announcementRatingMapper.getPageRatingModel(map);
	}

	@Override
	public Page<AnnouncementRatingModel> getPageRatingModel(long id, long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		AnnouncementRatingExample example_rating = new AnnouncementRatingExample();
		example_rating.or().andAnnouncementIdEqualTo(id);
		int count = announcementRatingMapper.countByExample(example_rating);
		List<AnnouncementRatingModel> list = announcementRatingMapper.getPageRatingModel(map);
		Page<AnnouncementRatingModel> pageData = new PageImpl<AnnouncementRatingModel>(list, pager, count);
		return pageData;
	}

	@Override
	public AnnouncementRatingModel getOneRatingModel(long id, long buserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		return announcementRatingMapper.getOneRatingModel(map);
	}

	@Override
	public void insertAndGetId(AnnouncementRating announcementRating) {
		announcementRatingMapper.insertAndGetId(announcementRating);
	}

}
