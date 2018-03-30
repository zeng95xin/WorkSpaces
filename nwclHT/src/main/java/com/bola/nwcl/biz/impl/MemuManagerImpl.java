package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.MemuManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.MemuMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoleResourceMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Memu;
import com.bola.nwcl.dal.mybatis.model.MemuExample;
import com.bola.nwcl.dal.mybatis.model.RoleResource;
import com.bola.nwcl.dal.mybatis.model.RoleResourceExample;
import com.bola.nwcl.web.model.Resource;
import com.bola.nwcl.web.model.Tree;

@Service
public class MemuManagerImpl  extends ManagerImpl<Memu, MemuExample, Long> implements MemuManager{
	
	@SuppressWarnings("unused")
	private MemuMapper memuMapper;
	
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
	@Autowired
	public MemuManagerImpl(MemuMapper memuMapper) {
		this.mapper = memuMapper;
		this.memuMapper = memuMapper;
	}

	@Override
	public List<Resource> treeGrid(Long pid) {
		List<Memu> l = null;
		List<Resource> lr = new ArrayList<Resource>();
		
		MemuExample example = new MemuExample();
		//example.or().andParentMemuIdIsNull();
		
		/*Map<String, Object> params = new HashMap<String, Object>();
		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 自查自己有权限的资源
			l = resourceDao.find("select distinct t from Tresource t join fetch t.tresourcetype type join fetch t.troles role join role.tusers user where user.id = :userId order by t.seq", params);
		} else {
			l = resourceDao.find("select distinct t from Tresource t join fetch t.tresourcetype type order by t.seq", params);
		}*/
		if(pid != null){
			example.or().andParentMemuIdEqualTo(pid);
		}else{
			example.or().andParentMemuIdIsNull();
		}
		l = memuMapper.selectByExample(example);

		if (l != null && l.size() > 0) {
			for (Memu t : l) {
				Resource r = new Resource();
				//BeanUtils.copyProperties(t, r);
				MemuExample example2 = new MemuExample();
				example2.or().andParentMemuIdEqualTo(t.getId());
				List<Memu> list=memuMapper.selectByExample(example2);
				if (list.size() > 0 && list != null ) {
					r.setState("closed");
				} else {
					r.setState("open");
				}
				
				r.setId(t.getId());
				r.setName(t.getMemuName());
				r.setUrl(t.getMemuUrl());
				r.setRemark(t.getNote());
				if (t.getParentMemuId() != null){
					r.setPid(t.getParentMemuId());
					Memu me = memuMapper.selectByPrimaryKey(t.getParentMemuId());
					r.setPname(me.getMemuName());
				}
				r.setTypeId(t.getMemuType());
				if (t.getMemuType() == 0) {
					r.setTypeName("菜单");
				}else if (t.getMemuType() == 1){
					r.setTypeName("功能");
				}
				//r.setTypeName(t.getTresourcetype().getName());
				if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
					r.setIconCls(t.getIcon());
				}
				lr.add(r);
			}
		}

		return lr;
	}

	@Override
	public List<Tree> tree(HttpSession session) {
		List<Memu> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Admin user =  (Admin)session.getAttribute("loginUser");
		Long role = user.getRoleId();
		
		RoleResourceExample roleResourceExample = new RoleResourceExample();
		roleResourceExample.or().andRoleIdEqualTo(role.intValue());
		List<RoleResource> rrs = roleResourceMapper.selectByExample(roleResourceExample);
		List<Long> resources = new ArrayList<>();
		if (rrs != null && rrs.size() > 0) {
			for(RoleResource rr : rrs){
				Long id = rr.getResourceId();
				if (id != null) {
					resources.add(id);
				}
			}
		}else{
			resources.add(-1L);// 防止sql语句报错
		}
		MemuExample example = new MemuExample();
		example.or().andMemuTypeEqualTo(0L).andIdIn(resources);
		l = memuMapper.selectByExample(example);
		if (l != null && l.size() > 0) {
			for (Memu r : l) {
				Tree tree = new Tree();
				//BeanUtils.copyProperties(r, tree);
				if (r.getParentMemuId() != null) {
					tree.setPid(r.getParentMemuId());
				}
				tree.setId(r.getId());
				tree.setText(r.getMemuName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getMemuUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<Tree> allTree() {
		List<Memu> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		l = memuMapper.selectByExample(null);
		if (l != null && l.size() > 0) {
			for (Memu r : l) {
				Tree tree = new Tree();
				//BeanUtils.copyProperties(r, tree);
				if (r.getParentMemuId() != null) {
					tree.setPid(r.getParentMemuId());
				}
				tree.setId(r.getId());
				tree.setText(r.getMemuName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getMemuUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public void insertAndGetId(Memu memu) {
		memuMapper.insertAndGetId(memu);
	}

	

}
