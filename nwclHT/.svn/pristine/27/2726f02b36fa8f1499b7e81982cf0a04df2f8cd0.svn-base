package com.bola.nwcl.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneType;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneTypeExample;

public interface ConvenienceTelephoneTypeManager extends Manager<ConvenienceTelephoneType, ConvenienceTelephoneTypeExample, Integer>{
	public DataGrid dataGrid(HttpServletRequest request, ConvenienceTelephoneType convenienceTelephoneType, PageHelper ph);

	List<ConvenienceTelephoneType> typeList();
}
