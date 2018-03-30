package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.IdlethingImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingImgMapper;
import com.bola.nwcl.dal.mybatis.model.IdlethingImg;
import com.bola.nwcl.dal.mybatis.model.IdlethingImgExample;

@Service
public class IdlethingImgManagerImpl  extends ManagerImpl<IdlethingImg, IdlethingImgExample, Long> implements IdlethingImgManager{
	
	@SuppressWarnings("unused")
	private IdlethingImgMapper idlethingImgMapper;
	
	@Autowired
	public IdlethingImgManagerImpl(IdlethingImgMapper idlethingImgMapper) {
		this.mapper = idlethingImgMapper;
		this.idlethingImgMapper = idlethingImgMapper;
	}

}
