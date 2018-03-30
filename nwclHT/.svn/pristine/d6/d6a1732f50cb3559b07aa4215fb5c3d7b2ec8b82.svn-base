package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.IdlethingTypeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingTypeMapper;
import com.bola.nwcl.dal.mybatis.model.IdlethingType;
import com.bola.nwcl.dal.mybatis.model.IdlethingTypeExample;

@Service
public class IdlethingTypeManagerImpl  extends ManagerImpl<IdlethingType, IdlethingTypeExample, Long> implements IdlethingTypeManager{
	
	@SuppressWarnings("unused")
	private IdlethingTypeMapper idlethingTypeMapper;
	
	@Autowired
	public IdlethingTypeManagerImpl(IdlethingTypeMapper idlethingTypeMapper) {
		this.mapper = idlethingTypeMapper;
		this.idlethingTypeMapper = idlethingTypeMapper;
	}

}
