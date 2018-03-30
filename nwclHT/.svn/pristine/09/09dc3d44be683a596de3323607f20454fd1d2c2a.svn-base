package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.BorrowThingModel;
import com.bola.nwcl.biz.BorrowThingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.BorrowThingMapper;
import com.bola.nwcl.dal.mybatis.model.BorrowThing;
import com.bola.nwcl.dal.mybatis.model.BorrowThingExample;

@Service
public class BorrowThingManagerImpl  extends ManagerImpl<BorrowThing, BorrowThingExample, Long> implements BorrowThingManager{
	
	private BorrowThingMapper borrowThingMapper;
	
	@Autowired
	public BorrowThingManagerImpl(BorrowThingMapper borrowThingMapper) {
		this.mapper = borrowThingMapper;
		this.borrowThingMapper = borrowThingMapper;
	}

	@Override
	public List<BorrowThingModel> getBorrowThingDetail(Long id) {
		return borrowThingMapper.getBorrowThingDetail(id);
	}

}
