package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.UserOpinionImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.UserOpinionImgMapper;
import com.bola.nwcl.dal.mybatis.model.UserOpinionImg;
import com.bola.nwcl.dal.mybatis.model.UserOpinionImgExample;

@Service
public class UserOpinionImgManagerImpl  extends ManagerImpl<UserOpinionImg, UserOpinionImgExample, Long> implements UserOpinionImgManager{
	
	@SuppressWarnings("unused")
	private UserOpinionImgMapper userOpinionImgMapper;
	
	@Autowired
	public UserOpinionImgManagerImpl(UserOpinionImgMapper userOpinionImgMapper) {
		this.mapper = userOpinionImgMapper;
		this.userOpinionImgMapper = userOpinionImgMapper;
	}

}
