package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.AttendanceRecordManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.DepartmentMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceClockAttendanceMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.ServiceClockAttendance;
import com.bola.nwcl.dal.mybatis.model.ServiceClockAttendanceExample;
import com.bola.nwcl.dal.mybatis.model.ServiceClockAttendanceExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.web.model.ServiceClockAttendanceModel;

@Service
public class AttendanceRecordManagerImpl extends
		ManagerImpl<ServiceClockAttendance, ServiceClockAttendanceExample, Long> implements AttendanceRecordManager {

	private ServiceClockAttendanceMapper attendanceMapper;
	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	private ServiceUserMapper serviceUserMapper;

	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	public AttendanceRecordManagerImpl(ServiceClockAttendanceMapper attendanceMapper) {
		this.mapper = attendanceMapper;
		this.attendanceMapper = attendanceMapper;
	}

	@Override
	public DataGrid dataGrid(ServiceClockAttendance attendance, PageHelper ph,Integer role, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		ServiceClockAttendanceExample serviceClockAttendanceExample = new ServiceClockAttendanceExample();
		Criteria c = serviceClockAttendanceExample.or();
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		// 小区查询
		if (attendance.getCommunityId() != null && attendance.getCommunityId() != -1) {
			c.andCommunityIdEqualTo(attendance.getCommunityId());
		}
		//是否早退
		if (attendance.getIsEarly() != null && attendance.getIsEarly() != -1) {
			c.andIsEarlyEqualTo(attendance.getIsEarly());
		}
		//是否迟到
		if (attendance.getIsLate() != null && attendance.getIsLate() != -1) {
			c.andIsLateEqualTo(attendance.getIsLate());
		}
		
		/*// 部门查询
		if (role != null && role != -1) {
			ServiceUserExample serviceUserExample = new ServiceUserExample();
			serviceUserExample.or().andRoleEqualTo(role);
			List<ServiceUser> selectByExample = serviceUserMapper.selectByExample(serviceUserExample);
			for (ServiceUser serviceUser : selectByExample) {
				c.andSuserIdEqualTo(serviceUser.getId());
			}
		}*/
		int total = attendanceMapper.countByExample(serviceClockAttendanceExample);
		serviceClockAttendanceExample.setLimit(ph.getRows());
		serviceClockAttendanceExample.setOffset((ph.getPage() - 1) * ph.getRows());
		serviceClockAttendanceExample.setOrderByClause("row_add_time DESC");
		List<ServiceClockAttendance> selectByExample = attendanceMapper.selectByExample(serviceClockAttendanceExample);
		List<ServiceClockAttendanceModel> list = new ArrayList<ServiceClockAttendanceModel>(selectByExample.size());
		for (ServiceClockAttendance su : selectByExample) {
			ServiceClockAttendanceModel model = new ServiceClockAttendanceModel();
			BeanUtils.copyProperties(su, model);
			if (su.getCommunityId() != null) {
				ServiceUser dt = serviceUserMapper.selectByPrimaryKey(su.getSuserId());
				model.setUaerName(dt.getName());
				Department d = departmentMapper.selectByPrimaryKey(dt.getRole().intValue());
				model.setDeptName(d.getDeptName());
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
