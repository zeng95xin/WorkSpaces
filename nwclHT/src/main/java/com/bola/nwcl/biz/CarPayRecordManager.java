package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.CarPayRecord;
import com.bola.nwcl.dal.mybatis.model.CarPayRecordExample;

public interface CarPayRecordManager extends Manager<CarPayRecord, CarPayRecordExample, Long> {

	DataGrid dataGrid(CarPayRecord carPayRecord, PageHelper ph, HttpServletRequest request);
}
