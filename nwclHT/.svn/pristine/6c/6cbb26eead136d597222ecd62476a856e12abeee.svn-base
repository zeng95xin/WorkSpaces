package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.LectureHallMessageManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LectureHallMessageMapper;
import com.bola.nwcl.dal.mybatis.model.LectureHallMessage;
import com.bola.nwcl.dal.mybatis.model.LectureHallMessageExample;

@Service
public class LectureHallMessageManagerImpl  extends ManagerImpl<LectureHallMessage, LectureHallMessageExample, Long> implements LectureHallMessageManager{
	
	@SuppressWarnings("unused")
	private LectureHallMessageMapper lectureHallMessageMapper;
	
	@Autowired
	public LectureHallMessageManagerImpl(LectureHallMessageMapper lectureHallMessageMapper) {
		this.mapper = lectureHallMessageMapper;
		this.lectureHallMessageMapper = lectureHallMessageMapper;
	}


}
