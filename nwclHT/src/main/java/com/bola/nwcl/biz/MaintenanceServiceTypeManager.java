package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.MaintenanceServiceType;
import com.bola.nwcl.dal.mybatis.model.MaintenanceServiceTypeExample;

public interface MaintenanceServiceTypeManager extends Manager<MaintenanceServiceType, MaintenanceServiceTypeExample, Long>{

	DataGrid dataGrid(HttpServletRequest request, MaintenanceServiceType maintenanceServiceType,
			PageHelper ph);

	
}
