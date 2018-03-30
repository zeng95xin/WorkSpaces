package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.SensitiveManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.SensitiveMapper;
import com.bola.nwcl.dal.mybatis.model.Sensitive;
import com.bola.nwcl.dal.mybatis.model.SensitiveExample;
import com.bola.nwcl.dal.mybatis.model.Thing;

@Service
public class SensitiveManagerImpl  extends ManagerImpl<Sensitive, SensitiveExample, Long> implements SensitiveManager{
	
	@SuppressWarnings("unused")
	private SensitiveMapper sensitiveMapper;
	
	@Autowired
	public SensitiveManagerImpl(SensitiveMapper sensitiveMapper) {
		this.mapper = sensitiveMapper;
		this.sensitiveMapper = sensitiveMapper;
	}

	@Override
	public DataGrid dataGrid(Sensitive sensitive, PageHelper ph) {
		DataGrid dg = new DataGrid();
		SensitiveExample sex=new SensitiveExample();
		sex.setLimit(ph.getRows());
		sex.setOffset((ph.getPage()-1)*ph.getRows());
		List<Sensitive> list=sensitiveMapper.selectByExample(sex);
		dg.setRows(list);
		int total=sensitiveMapper.countByExample(null);
		dg.setTotal(total);
		return dg;
	}

}
