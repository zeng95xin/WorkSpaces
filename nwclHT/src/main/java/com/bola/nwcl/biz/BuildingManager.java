package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;

public interface BuildingManager extends Manager<Building, BuildingExample, Long>{
	
	DataGrid dataGrid(Building building, PageHelper ph,HttpServletRequest requset);
}
