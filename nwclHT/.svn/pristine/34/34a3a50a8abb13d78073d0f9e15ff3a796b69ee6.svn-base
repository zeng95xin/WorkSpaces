package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.DoorRecordManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.DoorRecordMapper;
import com.bola.nwcl.dal.mybatis.model.DoorRecord;
import com.bola.nwcl.dal.mybatis.model.DoorRecordExample;

@Service
public class DoorRecordManagerImpl extends ManagerImpl<DoorRecord, DoorRecordExample, Long> implements DoorRecordManager {

	private DoorRecordMapper doorControlMapper;
	@Autowired
	public DoorRecordManagerImpl(DoorRecordMapper doorControlMapper) {
		this.mapper = doorControlMapper;
		this.doorControlMapper = doorControlMapper;
	}
}
