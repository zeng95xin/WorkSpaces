package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.AuthorizeCodeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.AuthorizeCodeMapper;
import com.bola.nwcl.dal.mybatis.model.AuthorizeCode;
import com.bola.nwcl.dal.mybatis.model.AuthorizeCodeExample;

@Service
public class AuthorizeCodeManagerImpl  extends ManagerImpl<AuthorizeCode, AuthorizeCodeExample, Long> implements AuthorizeCodeManager{
	
	@SuppressWarnings("unused")
	private AuthorizeCodeMapper authorizeCodeMapper;
	
	@Autowired
	public AuthorizeCodeManagerImpl(AuthorizeCodeMapper authorizeCodeMapper) {
		this.mapper = authorizeCodeMapper;
		this.authorizeCodeMapper = authorizeCodeMapper;
	}

}
