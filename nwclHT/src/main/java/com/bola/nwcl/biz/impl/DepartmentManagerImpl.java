package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.DepartmentManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.DepartmentMapper;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.DepartmentExample;
import com.bola.nwcl.dal.mybatis.model.DepartmentExample.Criteria;

@Service
public class DepartmentManagerImpl  extends ManagerImpl<Department, DepartmentExample, Integer> implements DepartmentManager{
	
	@SuppressWarnings("unused")
	private DepartmentMapper departmentMapper;
	
	@Autowired
	public DepartmentManagerImpl(DepartmentMapper departmentMapper) {
		this.mapper = departmentMapper;
		this.departmentMapper = departmentMapper;
	}

	@Override
	public DataGrid dataGrid(Department department, PageHelper ph) {
		DataGrid dg = new DataGrid();
		DepartmentExample example = new DepartmentExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		if (department != null) {
			Criteria c= example.or();
			if (department.getId() != null) {
				c.andIdEqualTo(department.getId());
			}
			/*if (lectureHall.getType() != null) {
				if (lectureHall.getType() == 1) {
					c.andTypeEqualTo(1);
				}
				if (lectureHall.getType() == 2) {
					c.andTypeEqualTo(2);
				}
				if (lectureHall.getType() == 3) {
					c.andTypeEqualTo(3);
				}
				if (lectureHall.getType() == 4) {
					c.andTypeEqualTo(4);
				}
			}*/
		}
		dg.setTotal(departmentMapper.countByExample(null));
		List<Department> imgs = departmentMapper.selectByExample(example);
		dg.setRows(imgs);
		
		return dg;
	}

}
