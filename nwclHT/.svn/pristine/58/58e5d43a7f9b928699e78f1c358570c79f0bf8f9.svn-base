package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.AnnouncementModelManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementModelMapper;
import com.bola.nwcl.dal.mybatis.model.AnnouncementModel;
import com.bola.nwcl.dal.mybatis.model.AnnouncementModelExample;

@Service
public class AnnouncementModelManagerImpl  extends ManagerImpl<AnnouncementModel, AnnouncementModelExample, Integer> implements AnnouncementModelManager{
	
	@SuppressWarnings("unused")
	private AnnouncementModelMapper announcementModelMapper;
	
	@Autowired
	public AnnouncementModelManagerImpl(AnnouncementModelMapper announcementModelMapper) {
		this.mapper = announcementModelMapper;
		this.announcementModelMapper = announcementModelMapper;
	}

	@Override
	public DataGrid dataGrid(AnnouncementModel model, PageHelper ph) {
		DataGrid dg = new DataGrid();
		AnnouncementModelExample example=new AnnouncementModelExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<AnnouncementModel> list=announcementModelMapper.selectByExample(example);
		dg.setRows(list);
		int total=announcementModelMapper.countByExample(null);
		dg.setTotal(total);
		
		return dg;
	}

}
