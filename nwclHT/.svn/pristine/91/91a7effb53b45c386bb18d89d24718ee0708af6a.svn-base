package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.mybatis.model.Scheduling;
import com.bola.nwcl.dal.mybatis.model.SchedulingExample;

public interface SchedulingManager extends Manager<Scheduling, SchedulingExample, Long>{
	
	DataGrid dataGrid(HttpServletRequest request, Integer page, Integer rows, Long communityId, Integer type);
}
