package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.LectureHallRegisterManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LectureHallRegisterMapper;
import com.bola.nwcl.dal.mybatis.model.LectureHallRegister;
import com.bola.nwcl.dal.mybatis.model.LectureHallRegisterExample;

@Service
public class LectureHallRegisterManagerImpl  extends ManagerImpl<LectureHallRegister, LectureHallRegisterExample, Long> implements LectureHallRegisterManager{
	
	@SuppressWarnings("unused")
	private LectureHallRegisterMapper lectureHallRegisterMapper;
	
	@Autowired
	public LectureHallRegisterManagerImpl(LectureHallRegisterMapper lectureHallRegisterMapper) {
		this.mapper = lectureHallRegisterMapper;
		this.lectureHallRegisterMapper = lectureHallRegisterMapper;
	}

}
