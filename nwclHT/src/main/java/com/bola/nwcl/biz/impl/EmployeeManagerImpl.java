package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.EmployeeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.DepartmentMapper;
import com.bola.nwcl.dal.mybatis.mapper.EmployeeMapper;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample.Criteria;

@Service
public class EmployeeManagerImpl  extends ManagerImpl<Employee, EmployeeExample, Long> implements EmployeeManager{
	
	@SuppressWarnings("unused")
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	public EmployeeManagerImpl(EmployeeMapper employeeMapper) {
		this.mapper = employeeMapper;
		this.employeeMapper = employeeMapper;
	}

	@Override
	public DataGrid dataGrid(Employee employee, PageHelper ph) {
		DataGrid dg = new DataGrid();
		EmployeeExample example = new EmployeeExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		if (employee != null) {
			Criteria c= example.or();
			if (employee.getId() != null) {
				c.andIdEqualTo(employee.getId());
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
		dg.setTotal(employeeMapper.countByExample(null));
		List<Employee> imgs = employeeMapper.selectByExample(example);
		if (imgs != null && imgs.size()>0) {
			for (int i = 0; i < imgs.size(); i++) {
				Department dp = departmentMapper.selectByPrimaryKey(imgs.get(i).getDeptId().intValue());
				if (dp != null) {
					imgs.get(i).setDeptName(dp.getDeptName());
				}
			}
		}
		dg.setRows(imgs);
		
		return dg;
	}

	@Override
	public void insertAndGetId(Employee employee) {
		employeeMapper.insertAndGetId(employee);
	}

}
