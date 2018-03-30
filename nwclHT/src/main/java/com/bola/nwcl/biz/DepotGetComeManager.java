package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.CarParkRecord;
import com.bola.nwcl.dal.mybatis.model.CarParkRecordExample;

public interface DepotGetComeManager extends Manager<CarParkRecord, CarParkRecordExample, Long> {

	DataGrid dataGrid(CarParkRecord carParkRecord, PageHelper ph, HttpServletRequest request);
}
