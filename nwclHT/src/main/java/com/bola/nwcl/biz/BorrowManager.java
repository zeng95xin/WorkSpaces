package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Borrow;
import com.bola.nwcl.dal.mybatis.model.BorrowExample;
import com.bola.nwcl.dal.mybatis.model.BorrowThing;

public interface BorrowManager extends Manager<Borrow, BorrowExample, Long>{
	void insertAndGetId(Borrow borrow);

	DataGrid dataGrid(BorrowThing borrow, PageHelper ph,HttpServletRequest request);
}
