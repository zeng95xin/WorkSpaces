package com.bola.nwcl.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.MemuManager;
import com.bola.nwcl.biz.RoleResourceManager;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AdminExample;
import com.bola.nwcl.dal.mybatis.model.Memu;
import com.bola.nwcl.dal.mybatis.model.MemuExample;
import com.bola.nwcl.dal.mybatis.model.RoleResource;
import com.bola.nwcl.dal.mybatis.model.RoleResourceExample;

public class AdminRefreshUtil {
	
	@Autowired private AdminManager adminManager;
	@Autowired private MemuManager memuManager;
	@Autowired private RoleResourceManager roleResourceManager;
	
	public void refresh(String path, Long community_id){
		MemuExample example_memu = new MemuExample();
		example_memu.or().andMemuUrlLike("%" + path + "%");
		List<Memu> memus = memuManager.selectByExample(example_memu);
		
		List<Long> resource_ids = new ArrayList<>(memus.size());
		
		for(Memu temp : memus){
			if(!resource_ids.contains(temp.getId())){
				resource_ids.add(temp.getId());
			}
		}
		
		RoleResourceExample example_role_resource = new RoleResourceExample();
		example_role_resource.or().andResourceIdIn(resource_ids);
		List<RoleResource> role_resources = roleResourceManager.selectByExample(example_role_resource);
		
		List<Long> role_ids = new ArrayList<>(role_resources.size());
		
		for(RoleResource temp : role_resources){
			if(!role_ids.contains(temp.getId())){
				role_ids.add(temp.getId());
			}
		}
		
		AdminExample example_admin = new AdminExample();
		if(null != community_id){
			example_admin.or().andRoleIdIn(role_ids).andCommunityIdEqualTo(community_id);
		}else{
			example_admin.or().andRoleIdIn(role_ids);
		}
		Admin admin = new Admin();
		admin.setRefreshStatus(1);
		admin.setRefreshTime(new Date());
		adminManager.updateByExampleSelective(admin, example_admin);
		
	}
	
	
}
