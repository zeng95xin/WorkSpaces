package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.DoorTypeMapper;
import com.bola.nwcl.dal.mybatis.mapper.PropertyMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.DoorType;
import com.bola.nwcl.dal.mybatis.model.Property;

@Service
public class CommunityManagerImpl  extends ManagerImpl<Community, CommunityExample, Long> implements CommunityManager{
	
	@SuppressWarnings("unused")
	private CommunityMapper communityMapper;
	
	@Autowired
	private PropertyMapper propertyMapper;
	@Autowired
	private DoorTypeMapper doorTypeMapper;
	
	@Autowired
	public CommunityManagerImpl(CommunityMapper communityMapper) {
		this.mapper = communityMapper;
		this.communityMapper = communityMapper;
	}

	
	@Override
	public DataGrid dataGrid(Community community, PageHelper ph, HttpServletRequest request) {
		
		DataGrid dg = new DataGrid();
		CommunityExample example=new CommunityExample();
		
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			example.or().andIdIn(communityIds);
		}
		int total=communityMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<Community> list=communityMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(Community c : list){
				Property p = propertyMapper.selectByPrimaryKey(c.getPropertyId());
				if (p != null) {
					c.setPropertyName(p.getName());
				}
				DoorType dtype = doorTypeMapper.selectByPrimaryKey(c.getDoorTypeId());
				if (dtype != null) {
					c.setDoorTypeName(dtype.getName());
				}
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);
		
		return dg;
	}
	
}
