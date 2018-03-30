package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.VisitorsStatisticsManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsStatisticsMapper;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatistics;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsExample;

@Service
public class VisitorsStatisticsManagerImpl  extends ManagerImpl<VisitorsStatistics, VisitorsStatisticsExample, Long> implements VisitorsStatisticsManager{
	
	@SuppressWarnings("unused")
	private VisitorsStatisticsMapper visitorsStatisticsMapper;
	
	@Autowired
	public VisitorsStatisticsManagerImpl(VisitorsStatisticsMapper visitorsStatisticsMapper) {
		this.mapper = visitorsStatisticsMapper;
		this.visitorsStatisticsMapper = visitorsStatisticsMapper;
	}

}
