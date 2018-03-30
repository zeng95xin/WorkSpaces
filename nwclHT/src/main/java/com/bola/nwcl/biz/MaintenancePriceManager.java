package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.MaintenancePrice;
import com.bola.nwcl.dal.mybatis.model.MaintenancePriceExample;

public interface MaintenancePriceManager extends Manager<MaintenancePrice, MaintenancePriceExample, Long>{

	DataGrid dataGrid(MaintenancePrice maintenancePrice, PageHelper ph, Long type, HttpServletRequest request);
}
