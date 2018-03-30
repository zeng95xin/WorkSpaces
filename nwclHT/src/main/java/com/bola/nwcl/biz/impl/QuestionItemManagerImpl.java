package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.QuestionItemManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.QuestionItemMapper;
import com.bola.nwcl.dal.mybatis.model.QuestionItem;
import com.bola.nwcl.dal.mybatis.model.QuestionItemExample;

@Service
public class QuestionItemManagerImpl  extends ManagerImpl<QuestionItem, QuestionItemExample, Long> implements QuestionItemManager{
	
	@SuppressWarnings("unused")
	private QuestionItemMapper questionItemMapper;
	
	@Autowired
	public QuestionItemManagerImpl(QuestionItemMapper questionItemMapper) {
		this.mapper = questionItemMapper;
		this.questionItemMapper = questionItemMapper;
	}

}
