package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.QuestionnaireClassifyManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.QuestionnaireClassifyMapper;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireClassify;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireClassifyExample;


@Service
public class QuestionnaireClassifyManagerImpl extends ManagerImpl<QuestionnaireClassify, QuestionnaireClassifyExample, Long> implements QuestionnaireClassifyManager{
	@SuppressWarnings("unused")
	private QuestionnaireClassifyMapper questionnaireClassifyMapper;
	
	@Autowired
	public QuestionnaireClassifyManagerImpl(QuestionnaireClassifyMapper questionnaireClassifyMapper) {
		this.mapper = questionnaireClassifyMapper;
		this.questionnaireClassifyMapper = questionnaireClassifyMapper;
	}
}
