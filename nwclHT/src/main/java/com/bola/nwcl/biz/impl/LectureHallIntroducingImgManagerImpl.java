package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.LectureHallIntroducingImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LectureHallIntroducingImgMapper;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingImg;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingImgExample;

@Service
public class LectureHallIntroducingImgManagerImpl  extends ManagerImpl<LectureHallIntroducingImg, LectureHallIntroducingImgExample, Long> implements LectureHallIntroducingImgManager{
	
	@SuppressWarnings("unused")
	private LectureHallIntroducingImgMapper lectureHallIntroducingImgMapper;
	
	@Autowired
	public LectureHallIntroducingImgManagerImpl(LectureHallIntroducingImgMapper lectureHallIntroducingImgMapper) {
		this.mapper = lectureHallIntroducingImgMapper;
		this.lectureHallIntroducingImgMapper = lectureHallIntroducingImgMapper;
	}

}
