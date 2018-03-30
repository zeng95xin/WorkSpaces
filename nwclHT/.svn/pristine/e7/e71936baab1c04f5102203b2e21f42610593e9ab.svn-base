package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.IdlethingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Idlething;
import com.bola.nwcl.dal.mybatis.model.IdlethingExample;

public interface IdlethingMapper extends Mapper<Idlething, IdlethingExample, Long> {
	void insertAndGetId(Idlething idlething);
	List<IdlethingModel> getAllIdlething(Map<String,Object> condition);
}