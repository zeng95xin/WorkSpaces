package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.ServiceClockRoleConfig;
import com.bola.nwcl.dal.mybatis.model.ServiceClockRoleConfigExample;

public interface ServiceClockManager extends Manager<ServiceClockRoleConfig, ServiceClockRoleConfigExample, Long> {

	DataGrid dataGrid(ServiceClockRoleConfig roleConfig, PageHelper ph, HttpServletRequest request);
}
