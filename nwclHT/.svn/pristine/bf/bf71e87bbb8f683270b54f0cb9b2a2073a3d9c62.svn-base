package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ReportManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.ReportMapper;
import com.bola.nwcl.dal.mybatis.model.Report;
import com.bola.nwcl.dal.mybatis.model.ReportExample;

@Service
public class ReportManagerImpl  extends ManagerImpl<Report, ReportExample, Long> implements ReportManager{
	
	@SuppressWarnings("unused")
	private ReportMapper reportMapper;
	
	@Autowired
	public ReportManagerImpl(ReportMapper reportMapper) {
		this.mapper = reportMapper;
		this.reportMapper = reportMapper;
	}

}
