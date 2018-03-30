package com.bola.nwcl.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.BerificationCodeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.BerificationCodeMapper;
import com.bola.nwcl.dal.mybatis.model.BerificationCode;
import com.bola.nwcl.dal.mybatis.model.BerificationCodeExample;

@Service
public class BerificationCodeManagerImpl  extends ManagerImpl<BerificationCode, BerificationCodeExample, Long> implements BerificationCodeManager{
	
	private BerificationCodeMapper berificationCodeMapper;
	
	@Autowired
	public BerificationCodeManagerImpl(BerificationCodeMapper berificationCodeMapper) {
		this.mapper = berificationCodeMapper;
		this.berificationCodeMapper = berificationCodeMapper;
	}

	@Override
	public BerificationCode selectMaxByPhone(Map<String,Object> condition) {
		return berificationCodeMapper.selectMaxByPhone(condition);
	}

}
