package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.IdlethingZanManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingZanMapper;
import com.bola.nwcl.dal.mybatis.model.IdlethingZan;
import com.bola.nwcl.dal.mybatis.model.IdlethingZanExample;

@Service
public class IdlethingZanManagerImpl  extends ManagerImpl<IdlethingZan, IdlethingZanExample, Long> implements IdlethingZanManager{
	
	@SuppressWarnings("unused")
	private IdlethingZanMapper idlethingZanMapper;
	
	@Autowired
	public IdlethingZanManagerImpl(IdlethingZanMapper idlethingZanMapper) {
		this.mapper = idlethingZanMapper;
		this.idlethingZanMapper = idlethingZanMapper;
	}

}
