package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.AnnouncementRatingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRating;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRatingExample;

public interface AnnouncementRatingMapper extends Mapper<AnnouncementRating, AnnouncementRatingExample, Long> {
	List<AnnouncementRatingModel> getPageRatingModel(Map<String, Object> condition);
	AnnouncementRatingModel getOneRatingModel(Map<String, Object> condition);
	void insertAndGetId(AnnouncementRating announcementRating);
	
	
	List<AnnouncementRatingModel> getAnnouncementSensitiveRating(Map<String, Object> condition);
}