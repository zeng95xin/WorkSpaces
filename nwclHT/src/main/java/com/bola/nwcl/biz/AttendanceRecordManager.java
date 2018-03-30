package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.ServiceClockAttendance;
import com.bola.nwcl.dal.mybatis.model.ServiceClockAttendanceExample;

public interface AttendanceRecordManager extends Manager<ServiceClockAttendance, ServiceClockAttendanceExample, Long> {

	DataGrid dataGrid(ServiceClockAttendance attendance, PageHelper ph,Integer role, HttpServletRequest request);
}
