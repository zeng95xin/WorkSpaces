package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.AdminCommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.AdminMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.MemuMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoleMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoleResourceMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AdminCommunity;
import com.bola.nwcl.dal.mybatis.model.AdminCommunityExample;
import com.bola.nwcl.dal.mybatis.model.AdminExample;
import com.bola.nwcl.dal.mybatis.model.AdminExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.Memu;
import com.bola.nwcl.dal.mybatis.model.MemuExample;
import com.bola.nwcl.dal.mybatis.model.Role;
import com.bola.nwcl.dal.mybatis.model.RoleResource;
import com.bola.nwcl.dal.mybatis.model.RoleResourceExample;

@Service
public class AdminManagerImpl extends ManagerImpl<Admin, AdminExample, Long>
		implements AdminManager {

	@SuppressWarnings("unused")
	private AdminMapper adminMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Autowired
	private MemuMapper memuMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired private  AdminCommunityMapper adminCommunityMapper;

	@Autowired
	public AdminManagerImpl(AdminMapper adminMapper) {
		this.mapper = adminMapper;
		this.adminMapper = adminMapper;
	}

	@Override
	public DataGrid dataGrid(Admin admin, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		AdminExample example = new AdminExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		Criteria c = example.or();
		if (admin != null) {
			if (admin.getId() != null) {
				c.andIdEqualTo(admin.getId());
			}
			if (StringUtils.isNotBlank(admin.getName())) {
				c.andNameLike("%"+admin.getName()+"%");
			}
		}
		Admin adminLogin = (Admin) request.getSession().getAttribute("loginUser");
		List<Integer> list_type = new ArrayList<>(2);
		if(adminLogin.getType() == 2){
			list_type.add(2);
			list_type.add(3);
			c.andTypeIn(list_type);
		}else if(adminLogin.getType() == 3){
			list_type.add(3);
			c.andTypeIn(list_type);
		}
		
		if(adminLogin.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			AdminCommunityExample example_admin_community = new AdminCommunityExample();
			example_admin_community.or().andCommunityIdIn(communityIds);
			List<AdminCommunity> acs = adminCommunityMapper.selectByExample(example_admin_community);
			List<Long> adminUserIds = new ArrayList<Long>(acs.size());
			for(AdminCommunity temp : acs){
				adminUserIds.add(temp.getAdminUserId());
			}
			if(adminUserIds.size() < 1){
				adminUserIds.add(-1l);
			}
			c.andIdIn(adminUserIds);
		}
		dg.setTotal(adminMapper.countByExample(null));
		List<Admin> imgs = adminMapper.selectByExample(example);
		if (imgs != null && imgs.size() > 0) {
			for(Admin a : imgs){
				Role R = roleMapper.selectByPrimaryKey(a.getRoleId().intValue());
				if (R != null) {
					a.setRoleName(R.getRoleName());
				}
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				CommunityExample example_community = new CommunityExample();
				example_community.or().andIdIn(communityIds);
				List<Community> communitys = communityMapper.selectByExample(example_community);
				String communityName = "";
				for (Community community2 : communitys) {
					communityName += community2.getName();
					communityName += ",";
				}
				a.setCommunityName(communityName);
			}
		}
		dg.setRows(imgs);

		return dg;
	}

	@Override
	public List<String> resourceList(Long id) {
		List<String> resourceList = new ArrayList<String>();
		Admin user = adminMapper.selectByPrimaryKey(id);
		if (user != null) {
			Long roleId = user.getRoleId();
			RoleResourceExample rreExample = new RoleResourceExample();
			rreExample.or().andRoleIdEqualTo(roleId.intValue());
			List<RoleResource> resources = roleResourceMapper.selectByExample(rreExample);
			if (resources != null && resources.size() > 0) {
				List<Long> resourceIds = new ArrayList<Long>();
				for (RoleResource resource : resources) {
					Long rid = resource.getResourceId();
					resourceIds.add(rid);
				}
				MemuExample memuExample = new MemuExample();
				memuExample.or().andIdIn(resourceIds);
				List<Memu> menu = memuMapper.selectByExample(memuExample);
				if (menu != null && menu.size() > 0) {
					for (Memu m : menu) {
						if (m != null && m.getMemuUrl() != null) {
							resourceList.add(m.getMemuUrl());
						}
					}
				}
			}
		}
		return resourceList;
	}

	@Override
	public void insertAndGetId(Admin admin) {
		adminMapper.insertAndGetId(admin);
	}

}
