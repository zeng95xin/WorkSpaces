package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.DoorControl;
import com.bola.nwcl.dal.mybatis.model.DoorControlExample;

public interface DoorControlManager extends Manager<DoorControl, DoorControlExample, Long>{
	
	DataGrid dataGrid(DoorControl doorControl, PageHelper ph,HttpServletRequest requset);
}
