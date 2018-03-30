package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.LectureHallIntroducingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LectureHallIntroducingMapper;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducing;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingExample;

@Service
public class LectureHallIntroducingManagerImpl  extends ManagerImpl<LectureHallIntroducing, LectureHallIntroducingExample, Long> implements LectureHallIntroducingManager{
	
	private LectureHallIntroducingMapper lectureHallIntroducingMapper;
	
	@Autowired
	public LectureHallIntroducingManagerImpl(LectureHallIntroducingMapper lectureHallIntroducingMapper) {
		this.mapper = lectureHallIntroducingMapper;
		this.lectureHallIntroducingMapper = lectureHallIntroducingMapper;
	}
	
	@Override
	public void insertAndGetId(LectureHallIntroducing lectureHallIntroducing){
		lectureHallIntroducingMapper.insertAndGetId(lectureHallIntroducing);
	}

}
