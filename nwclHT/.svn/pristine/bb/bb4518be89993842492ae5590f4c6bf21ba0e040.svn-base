package com.bola.nwcl.biz.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bola.nwcl.biz.PropertyManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.PropertyMapper;
import com.bola.nwcl.dal.mybatis.model.Property;
import com.bola.nwcl.dal.mybatis.model.PropertyExample;


@Service
public class PropertyManagerImpl  extends ManagerImpl<Property, PropertyExample, Long> implements PropertyManager{
	
	private PropertyMapper propertyMapper;
	
	@Autowired
	public PropertyManagerImpl(PropertyMapper propertyMapper) {
		this.mapper = propertyMapper;
		this.propertyMapper = propertyMapper;
	}

	
	@Override
	public DataGrid dataGrid(Property property, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		PropertyExample example=new PropertyExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		
		List<Property> list=propertyMapper.selectByExample(example);
		dg.setRows(list);
		int total=propertyMapper.countByExample(null);
		dg.setTotal(total);
		
		return dg;
	}
	
}
