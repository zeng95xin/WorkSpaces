package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecord;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordExample;

public interface ServiceWordRecordManager extends Manager<ServiceWordRecord, ServiceWordRecordExample, Long> {

	DataGrid dataGrid(ServiceWordRecord serviceWordRecord, PageHelper ph, HttpServletRequest request);
}
