package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.VisitorsStatisticsDetailManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsStatisticsDetailMapper;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetail;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetailExample;

@Service
public class VisitorsStatisticsDetailManagerImpl  extends ManagerImpl<VisitorsStatisticsDetail, VisitorsStatisticsDetailExample, Long> implements VisitorsStatisticsDetailManager{
	
	@SuppressWarnings("unused")
	private VisitorsStatisticsDetailMapper visitorsStatisticsDetailMapper;
	
	@Autowired
	public VisitorsStatisticsDetailManagerImpl(VisitorsStatisticsDetailMapper visitorsStatisticsDetailMapper) {
		this.mapper = visitorsStatisticsDetailMapper;
		this.visitorsStatisticsDetailMapper = visitorsStatisticsDetailMapper;
	}

}
