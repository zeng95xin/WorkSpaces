package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.VideoManageManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.VideoManagementMapper;
import com.bola.nwcl.dal.mybatis.mapper.VideoResourceMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.VideoManagement;
import com.bola.nwcl.dal.mybatis.model.VideoManagementExample;
import com.bola.nwcl.dal.mybatis.model.VideoManagementExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.VideoResource;
import com.bola.nwcl.dal.mybatis.model.VideoResourceExample;

@Service
public class VideoManageManagerImpl extends ManagerImpl<VideoManagement, VideoManagementExample, Long>
		implements VideoManageManager {
	@SuppressWarnings("unused")
	private VideoManagementMapper manageManager;

	@Autowired
	private CommunityMapper communityMapper;

	@Autowired
	private VideoResourceMapper mappe;

	@Autowired
	public VideoManageManagerImpl(VideoManagementMapper manageManager) {
		this.mapper = manageManager;
		this.manageManager = manageManager;
	}

	@Override
	public DataGrid dataGrid(VideoManagement management, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		VideoManagementExample videoManagementExample = new VideoManagementExample();
		Criteria c = videoManagementExample.or();
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (management != null) {
			if (management.getName() != null) {
				c.andNameEqualTo(management.getName());
			}
		}
		dg.setTotal(manageManager.countByExample(videoManagementExample));
		videoManagementExample.setLimit(ph.getRows());
		videoManagementExample.setOffset((ph.getPage() - 1) * ph.getRows());
		videoManagementExample.setOrderByClause("rowAddTime DESC");
		List<VideoManagement> list = manageManager.selectByExample(videoManagementExample);
		if (list != null && list.size() > 0) {
			for (VideoManagement in : list) {
				Community com = communityMapper.selectByPrimaryKey(in.getId());
				if (com != null) {
					in.setName(com.getName());
				}
			}
		}
		dg.setRows(list);

		return dg;
	}

	@Override
	public VideoManagement selectRoleByPrimaryKey(Long id) {
		VideoManagement r = new VideoManagement();
		VideoManagement role = manageManager.selectByPrimaryKey(id.longValue());
		if (role != null) {
			BeanUtils.copyProperties(role, r);
			r.setPid((int) ((long) role.getId()));
			r.setPname(role.getName());
			VideoResourceExample example = new VideoResourceExample();
			example.or().andVideoIdEqualTo(role.getId());
			List<VideoResource> rrs = mappe.selectByExample(example);
			if (rrs != null && rrs.size() > 0) {
				String ids = "";
				for (VideoResource rr : rrs) {
					if (!ids.equals("")) {
						ids += ",";
					}
					ids += rr.getRoleId();
				}
				r.setResourceIds(ids);
			}
		}
		return r;
	}
}
