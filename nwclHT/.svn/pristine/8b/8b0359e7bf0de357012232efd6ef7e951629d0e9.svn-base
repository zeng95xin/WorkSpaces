package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.security.SercurityVisitorsModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Visitors;
import com.bola.nwcl.dal.mybatis.model.VisitorsExample;

public interface VisitorsMapper extends Mapper<Visitors, VisitorsExample, Long> {
	void insertAndGetId(Visitors visitors);
	
	List<SercurityVisitorsModel> getSercurityVisitorsModel(Map<String, Object> condition);
}