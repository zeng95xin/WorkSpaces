package com.bola.nwcl.web.controller.clock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.AttendanceRecordManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.DepartmentManager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.ServiceClockAttendance;

@Controller
@RequestMapping(value = "web/AttendanceRecord")
public class AttendanceRecordController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepartmentManager departmentManager;

	@Autowired
	private CommunityManager communityManager;

	@Autowired
	private AttendanceRecordManager attendanceRecordManager;

	@RequestMapping("/getAllColckRecord")
	public String manager(HttpServletRequest request) {
		List<Community> coms = communityManager.selectByExample(null);
		List<Department> departments = departmentManager.selectByExample(null);
		request.setAttribute("comus", coms);
		request.setAttribute("departments", departments);
		return "/clock/AttendanceRecord";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(ServiceClockAttendance attendance, PageHelper ph,Integer role, HttpServletRequest request) {
		return attendanceRecordManager.dataGrid(attendance, ph,role, request);
	}
}
