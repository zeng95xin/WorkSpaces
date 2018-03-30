package com.bola.nwcl.biz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.AnnouncementRatingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRating;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRatingExample;

public interface AnnouncementRatingManager extends Manager<AnnouncementRating, AnnouncementRatingExample, Long>{
	List<AnnouncementRatingModel> getLimitRatingModel(long id, long buserId, int ratingCount);
	Page<AnnouncementRatingModel> getPageRatingModel(long id, long buserId, int page, int rows);
	AnnouncementRatingModel getOneRatingModel(long id, long buserId);
	void insertAndGetId(AnnouncementRating announcementRating);
}
