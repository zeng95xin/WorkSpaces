package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RoomQuestionnaireAnswerManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.RoomQuestionnaireAnswerMapper;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireAnswer;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireAnswerExample;


@Service
public class RoomQuestionnaireAnswerManagerImpl extends ManagerImpl<RoomQuestionnaireAnswer, RoomQuestionnaireAnswerExample, Long> implements RoomQuestionnaireAnswerManager{
	@SuppressWarnings("unused")
	private RoomQuestionnaireAnswerMapper roomQuestionnaireAnswerMapper;
	
	@Autowired
	public RoomQuestionnaireAnswerManagerImpl(RoomQuestionnaireAnswerMapper roomQuestionnaireAnswerMapper) {
		this.mapper = roomQuestionnaireAnswerMapper;
		this.roomQuestionnaireAnswerMapper = roomQuestionnaireAnswerMapper;
	}
}
