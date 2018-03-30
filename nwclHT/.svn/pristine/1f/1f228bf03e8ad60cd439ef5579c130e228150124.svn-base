package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Role;
import com.bola.nwcl.dal.mybatis.model.RoleExample;

public interface RoleManager extends Manager<Role, RoleExample, Integer>{
	DataGrid dataGrid(Role role, PageHelper pageHelper);
	Role selectRoleByPrimaryKey(Long id);
}
