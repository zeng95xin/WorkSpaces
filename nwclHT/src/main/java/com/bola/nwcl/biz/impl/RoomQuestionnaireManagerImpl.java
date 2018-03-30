package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RoomQuestionnaireManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RoomQuestionnaireMapper;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaire;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireExample;


@Service
public class RoomQuestionnaireManagerImpl extends ManagerImpl<RoomQuestionnaire, RoomQuestionnaireExample, Long> implements RoomQuestionnaireManager{
	@SuppressWarnings("unused")
	private RoomQuestionnaireMapper roomQuestionnaireMapper;
	
	@Autowired
	public RoomQuestionnaireManagerImpl(RoomQuestionnaireMapper roomQuestionnaireMapper) {
		this.mapper = roomQuestionnaireMapper;
		this.roomQuestionnaireMapper = roomQuestionnaireMapper;
	}
}
