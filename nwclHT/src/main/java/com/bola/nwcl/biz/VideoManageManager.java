package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.VideoManagement;
import com.bola.nwcl.dal.mybatis.model.VideoManagementExample;

public interface VideoManageManager extends Manager<VideoManagement, VideoManagementExample, Long> {
	DataGrid dataGrid(VideoManagement management, PageHelper ph, HttpServletRequest request);

	VideoManagement selectRoleByPrimaryKey(Long id);
}
