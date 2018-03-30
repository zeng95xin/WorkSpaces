package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.VideoResource;
import com.bola.nwcl.dal.mybatis.model.VideoResourceExample;

public interface VideoResourceManager extends Manager<VideoResource, VideoResourceExample, Long>{
	DataGrid dataGrid(VideoResource resource, PageHelper ph, HttpServletRequest request);

}
