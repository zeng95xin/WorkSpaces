package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.VideoResourceManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.VideoResourceMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.VideoResource;
import com.bola.nwcl.dal.mybatis.model.VideoResourceExample;
import com.bola.nwcl.dal.mybatis.model.VideoResourceExample.Criteria;

@Service
public class VideoResourceManagerImpl extends ManagerImpl<VideoResource, VideoResourceExample, Long>
		implements VideoResourceManager {
	@SuppressWarnings("unused")
	private VideoResourceMapper manager;
	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	public VideoResourceManagerImpl(VideoResourceMapper manager){
		this.mapper =  manager;
		this.manager = manager;
	}
	

	@Override
	public 	DataGrid dataGrid(VideoResource resource, PageHelper ph, HttpServletRequest request){
		DataGrid dg = new DataGrid();
		VideoResourceExample videoManagementExample = new VideoResourceExample();
		Criteria c = videoManagementExample.or();
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		if (resource != null) {
			if (resource.getRoleId() != null) {
				c.andRoleIdEqualTo(resource.getRoleId());
			}
		}
		dg.setTotal(manager.countByExample(videoManagementExample));
		videoManagementExample.setLimit(ph.getRows());
		videoManagementExample.setOffset((ph.getPage() - 1) * ph.getRows());
		List<VideoResource> list = manager.selectByExample(videoManagementExample);
		if (list != null && list.size() > 0) {
			for (VideoResource in : list) {
				Community com = communityMapper.selectByPrimaryKey(in.getId());
				if (com != null) {
					in.setId(com.getId());
				}
			}
		}
		dg.setRows(list);

		return dg;
	}
}
