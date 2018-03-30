package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AdminExample;

public interface AdminMapper extends Mapper<Admin, AdminExample, Long> {
	void insertAndGetId(Admin admin);
}