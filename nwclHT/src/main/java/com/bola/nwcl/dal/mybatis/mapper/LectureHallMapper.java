package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.LectureHall;
import com.bola.nwcl.dal.mybatis.model.LectureHallExample;

public interface LectureHallMapper extends Mapper<LectureHall, LectureHallExample, Long> {
	void insertGenId(LectureHall lectureHall) throws Exception;
}