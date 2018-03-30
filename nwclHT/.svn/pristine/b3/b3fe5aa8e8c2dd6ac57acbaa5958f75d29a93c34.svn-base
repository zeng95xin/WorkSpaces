package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.QuestionnaireManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.QuestionnaireMapper;
import com.bola.nwcl.dal.mybatis.model.Questionnaire;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireExample;

@Service
public class QuestionnaireManagerImpl  extends ManagerImpl<Questionnaire, QuestionnaireExample, Long> implements QuestionnaireManager{
	
	@SuppressWarnings("unused")
	private QuestionnaireMapper questionnaireMapper;
	
	@Autowired
	public QuestionnaireManagerImpl(QuestionnaireMapper questionnaireMapper) {
		this.mapper = questionnaireMapper;
		this.questionnaireMapper = questionnaireMapper;
	}

}
