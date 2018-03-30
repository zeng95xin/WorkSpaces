package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ServiceClockManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.DepartmentMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceClockRoleConfigMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.ServiceClockRoleConfig;
import com.bola.nwcl.dal.mybatis.model.ServiceClockRoleConfigExample;
import com.bola.nwcl.dal.mybatis.model.ServiceClockRoleConfigExample.Criteria;
import com.bola.nwcl.web.model.ServiceClockRoleConfigModel;

@Service
public class ServiceClockManagerImpl extends ManagerImpl<ServiceClockRoleConfig, ServiceClockRoleConfigExample, Long>
		implements ServiceClockManager {
	
	private ServiceClockRoleConfigMapper serviceClockRoleConfigMapper;
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	public ServiceClockManagerImpl(ServiceClockRoleConfigMapper serviceClockRoleConfigMapper) {
		this.mapper = serviceClockRoleConfigMapper;
		this.serviceClockRoleConfigMapper = serviceClockRoleConfigMapper;
	}

	@Override
	public DataGrid dataGrid(ServiceClockRoleConfig roleConfig, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		ServiceClockRoleConfigExample clockRoleConfigExample = new ServiceClockRoleConfigExample();
		Criteria c = clockRoleConfigExample.or();
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		// 小区查询
		if (roleConfig.getCommunityId() != null && roleConfig.getCommunityId() != -1) {
			c.andCommunityIdEqualTo(roleConfig.getCommunityId());
		}
		// 通过部门查询
		if (roleConfig.getRole() != null && roleConfig.getRole() != -1) {
			c.andRoleEqualTo(roleConfig.getRole());
		}
		// 是否允许外勤打卡查询
		if (roleConfig.getAllowOutside() != null && roleConfig.getAllowOutside() != -1) {
			c.andAllowOutsideEqualTo(roleConfig.getAllowOutside());
		}
		int total = serviceClockRoleConfigMapper.countByExample(clockRoleConfigExample);
		clockRoleConfigExample.setLimit(ph.getRows());
		clockRoleConfigExample.setOffset((ph.getPage() - 1) * ph.getRows());
		clockRoleConfigExample.setOrderByClause("row_add_time DESC");
		List<ServiceClockRoleConfig> selectByExample = serviceClockRoleConfigMapper
				.selectByExample(clockRoleConfigExample);
		List<ServiceClockRoleConfigModel> list = new ArrayList<ServiceClockRoleConfigModel>(selectByExample.size());
		for (ServiceClockRoleConfig su : selectByExample) {
			ServiceClockRoleConfigModel model = new ServiceClockRoleConfigModel();
			BeanUtils.copyProperties(su, model);
			if (su.getRole() != null) {
				Department dt = departmentMapper.selectByPrimaryKey(su.getRole().intValue());
				model.setDeptName(dt.getDeptName());
			}
			Community com = communityMapper.selectByPrimaryKey(model.getCommunityId());
			if (c != null) {
				model.setCommunity(com.getName());
			}
			list.add(model);
		}

		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

}
