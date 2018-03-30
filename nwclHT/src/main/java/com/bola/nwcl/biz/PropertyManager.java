package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Property;
import com.bola.nwcl.dal.mybatis.model.PropertyExample;

public interface PropertyManager extends Manager<Property, PropertyExample, Long>{
	
	DataGrid dataGrid(Property property, PageHelper ph);
}
