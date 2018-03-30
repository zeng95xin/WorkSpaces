package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.CarPayItem;
import com.bola.nwcl.dal.mybatis.model.CarPayItemExample;

public interface CarPayItemManager extends Manager<CarPayItem, CarPayItemExample, Long> {

	DataGrid dataGrid(CarPayItem carPayItem, PageHelper ph, HttpServletRequest request);
}
