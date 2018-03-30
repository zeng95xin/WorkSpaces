package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Thing;
import com.bola.nwcl.dal.mybatis.model.ThingExample;

public interface ThingManager extends Manager<Thing, ThingExample, Long>{
	DataGrid dataGrid(Thing thing, PageHelper ph,HttpServletRequest request);
}
