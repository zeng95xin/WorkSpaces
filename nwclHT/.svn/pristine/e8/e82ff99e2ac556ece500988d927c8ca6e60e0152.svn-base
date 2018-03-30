package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.lecture.LectureHallRatingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.LectureHallRating;
import com.bola.nwcl.dal.mybatis.model.LectureHallRatingExample;

public interface LectureHallRatingMapper extends Mapper<LectureHallRating, LectureHallRatingExample, Long> {
	List<LectureHallRatingModel> getPageRatingModel(Map<String, Object> condition);
	LectureHallRatingModel getOneRatingModel(Map<String, Object> condition);
	void insertAndGetId(LectureHallRating lectureHallRating);
}