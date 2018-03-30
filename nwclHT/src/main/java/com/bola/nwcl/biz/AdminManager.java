package com.bola.nwcl.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AdminExample;

public interface AdminManager extends Manager<Admin, AdminExample, Long>{
	DataGrid dataGrid(Admin admin, PageHelper pageHelper, HttpServletRequest request);
	List<String> resourceList(Long id);
	void insertAndGetId(Admin admin);
}
