package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;

public interface CommunityManager extends Manager<Community, CommunityExample, Long>{
	
	DataGrid dataGrid(Community community, PageHelper ph, HttpServletRequest request);
}
