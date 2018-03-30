package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Sensitive;
import com.bola.nwcl.dal.mybatis.model.SensitiveExample;
import com.bola.nwcl.dal.mybatis.model.Thing;

public interface SensitiveManager extends Manager<Sensitive, SensitiveExample, Long>{
	DataGrid dataGrid(Sensitive sensitive, PageHelper ph);
}
