package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;

public interface EmployeeManager extends Manager<Employee, EmployeeExample, Long>{
	void insertAndGetId(Employee employee);
	DataGrid dataGrid(Employee employee, PageHelper pageHelper);
}
