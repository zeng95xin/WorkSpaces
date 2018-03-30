package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.MaintenanceServiceTypeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceServiceTypeMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.MaintenanceServiceType;
import com.bola.nwcl.dal.mybatis.model.MaintenanceServiceTypeExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceServiceTypeExample.Criteria;


@Service
public class MaintenanceServiceTypeManagerImpl  extends ManagerImpl<MaintenanceServiceType,  MaintenanceServiceTypeExample, Long> implements  MaintenanceServiceTypeManager{
	
	private  MaintenanceServiceTypeMapper  maintenanceServiceTypeMapper;
	
	@Autowired
	private CommunityManager communityManager;
	
	@Autowired
	public MaintenanceServiceTypeManagerImpl(MaintenanceServiceTypeMapper maintenanceServiceTypeMapper) {
		this.mapper = maintenanceServiceTypeMapper;
		this.maintenanceServiceTypeMapper = maintenanceServiceTypeMapper;
	}

	@Override
	public DataGrid dataGrid(HttpServletRequest request, MaintenanceServiceType maintenanceServiceType,
			PageHelper ph) {
		DataGrid dg = new DataGrid();
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		MaintenanceServiceTypeExample example=new MaintenanceServiceTypeExample();
		Criteria cr = example.or();
		if (admin.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			cr.andCommunityIdIn(communityIds);
		}
		if (maintenanceServiceType.getCommunityId() != null) {
			cr.andCommunityIdEqualTo(maintenanceServiceType.getCommunityId());
		}
		int total=maintenanceServiceTypeMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<MaintenanceServiceType> list=maintenanceServiceTypeMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(MaintenanceServiceType c : list){
				Community community = communityManager.selectByPrimaryKey(c.getCommunityId());
				if (community != null) {
					c.setCommunityName(community.getName());
				}
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);
		
		return dg;
	}

	

}
