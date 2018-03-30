package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.HangPictureExample;

public interface HangPictureManager extends Manager<HangPicture, HangPictureExample, Long>{
	void insertAndGetId(HangPicture hangPicture);

	DataGrid dataGrid(Long communityId,String key,String value,PageHelper ph,HttpServletRequest request);
}
