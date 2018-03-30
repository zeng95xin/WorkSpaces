package com.bola.nwcl.biz.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.LabelManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.LabelMapper;
import com.bola.nwcl.dal.mybatis.model.Label;
import com.bola.nwcl.dal.mybatis.model.LabelExample;


@Service
public class LabelManagerImpl  extends ManagerImpl<Label, LabelExample, Long> implements LabelManager{
	
	private LabelMapper labelMapper;
	
	@Autowired
	public LabelManagerImpl(LabelMapper labelMapper) {
		this.mapper = labelMapper;
		this.labelMapper = labelMapper;
	}

	
	@Override
	public DataGrid dataGrid(Label label, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		LabelExample example=new LabelExample();
		example.setLimit(ph.getRows());
		example.setOffset(ph.getPage()-1*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<Label> list=labelMapper.selectByExample(example);
		dg.setRows(list);
		int total=labelMapper.countByExample(null);
		dg.setTotal(total);
		
		return dg;
	}
	
}
