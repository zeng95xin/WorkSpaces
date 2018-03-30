package com.bola.nwcl.biz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.lecture.LectureHallRatingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.LectureHallRating;
import com.bola.nwcl.dal.mybatis.model.LectureHallRatingExample;

public interface LectureHallRatingManager extends Manager<LectureHallRating, LectureHallRatingExample, Long>{
	List<LectureHallRatingModel> getLimitRatingModel(long id, long buserId, int ratingCount);
	Page<LectureHallRatingModel> getPageRatingModel(long id, long buserId, int page, int rows);
	LectureHallRatingModel getOneRatingModel(long id, long buserId);
	void insertAndGetId(LectureHallRating lectureHallRating);
}
