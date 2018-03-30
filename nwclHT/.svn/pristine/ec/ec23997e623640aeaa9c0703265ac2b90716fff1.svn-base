package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.RoleManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.RoleMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoleResourceMapper;
import com.bola.nwcl.dal.mybatis.model.Role;
import com.bola.nwcl.dal.mybatis.model.RoleExample;
import com.bola.nwcl.dal.mybatis.model.RoleExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.RoleResource;
import com.bola.nwcl.dal.mybatis.model.RoleResourceExample;

@Service
public class RoleManagerImpl  extends ManagerImpl<Role, RoleExample, Integer> implements RoleManager{
	
	@SuppressWarnings("unused")
	private RoleMapper roleMapper;
	@Autowired
	private RoleResourceMapper rrMapper;
	
	@Autowired
	public RoleManagerImpl(RoleMapper roleMapper) {
		this.mapper = roleMapper;
		this.roleMapper = roleMapper;
	}

	@Override
	public DataGrid dataGrid(Role role, PageHelper ph) {
		DataGrid dg = new DataGrid();
		RoleExample example = new RoleExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		if (role != null) {
			Criteria c= example.or();
			if (role.getId() != null) {
				c.andIdEqualTo(role.getId());
			}
			/*if (lectureHall.getType() != null) {
				if (lectureHall.getType() == 1) {
					c.andTypeEqualTo(1);
				}
				if (lectureHall.getType() == 2) {
					c.andTypeEqualTo(2);
				}
				if (lectureHall.getType() == 3) {
					c.andTypeEqualTo(3);
				}
				if (lectureHall.getType() == 4) {
					c.andTypeEqualTo(4);
				}
			}*/
		}
		dg.setTotal(roleMapper.countByExample(null));
		List<Role> imgs = roleMapper.selectByExample(example);
		dg.setRows(imgs);
		
		return dg;
	}

	@Override
	public Role selectRoleByPrimaryKey(Long id) {
		Role r = new Role();
		Role role = roleMapper.selectByPrimaryKey(id.intValue());
		if (role != null) {
			BeanUtils.copyProperties(role, r);
			r.setPid(role.getId());
			r.setPname(role.getRoleName());
			RoleResourceExample example = new RoleResourceExample();
			example.or().andRoleIdEqualTo(role.getId());
			List<RoleResource> rrs = rrMapper.selectByExample(example);
			if (rrs != null && rrs.size() > 0) {
				String ids = "";
				for (RoleResource rr : rrs) {
					if (!ids.equals("")) {
						ids += ",";
					}
					ids += rr.getResourceId();
				}
				r.setResourceIds(ids);
			}
		}
		return r;
	}

}
