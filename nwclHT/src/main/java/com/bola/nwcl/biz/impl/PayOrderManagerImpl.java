package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.PayOrderManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.PayOrderMapper;
import com.bola.nwcl.dal.mybatis.model.PayOrder;
import com.bola.nwcl.dal.mybatis.model.PayOrderExample;

@Service
public class PayOrderManagerImpl extends ManagerImpl<PayOrder, PayOrderExample, String> implements PayOrderManager {
	@SuppressWarnings("unused")
	private PayOrderMapper payOrderMapper;
	
	@Autowired
	public PayOrderManagerImpl(PayOrderMapper payOrderMapper) {
		this.mapper = payOrderMapper;
		this.payOrderMapper = payOrderMapper;
	}

}
