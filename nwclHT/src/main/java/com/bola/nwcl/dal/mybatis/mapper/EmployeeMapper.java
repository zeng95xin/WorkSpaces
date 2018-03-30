package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;

public interface EmployeeMapper extends Mapper<Employee, EmployeeExample, Long> {
	void insertAndGetId(Employee employee);
}