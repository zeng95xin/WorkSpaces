package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.SchedulingModel;
import com.bola.nwcl.biz.SchedulingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.SchedulingMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Scheduling;
import com.bola.nwcl.dal.mybatis.model.SchedulingExample;
import com.bola.nwcl.dal.mybatis.model.SchedulingExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;

@Service
public class SchedulingManagerImpl  extends ManagerImpl<Scheduling, SchedulingExample, Long> implements SchedulingManager{
	
	private SchedulingMapper schedulingMapper;
	
	@Autowired
	private ServiceUserMapper serviceUserMapper;
	@Autowired
	private CommunityMapper cmmunityMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	public SchedulingManagerImpl(SchedulingMapper schedulingMapper) {
		this.mapper = schedulingMapper;
		this.schedulingMapper = schedulingMapper;
	}

	@Override
	public DataGrid dataGrid(HttpServletRequest request, Integer page,
			Integer rows, Long communityId, Integer type) {
		int offset = (page - 1) * rows;
		SchedulingExample example_s = new SchedulingExample();
		Criteria c = example_s.or();
		
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		if(user.getRoleId() != -1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		if(null != communityId && communityId > -1){
			c.andCommunityIdEqualTo(communityId);
		}
		if(type != null && type != -1){
			ServiceUserExample example_su = new ServiceUserExample();
			example_su.or().andRoleEqualTo(type.intValue());
			List<ServiceUser> list_su = serviceUserMapper.selectByExample(example_su);
			if (list_su != null && list_su.size() > 0) {
				List<Long> ids = new ArrayList<Long>(list_su.size());
				for(ServiceUser su : list_su){
					ids.add(su.getId());
				}
				c.andSuserIdIn(ids);
			}
		}
		int total = schedulingMapper.selectCountByExample(example_s);
		example_s.setLimit(rows);
		example_s.setOffset(offset);
		example_s.setOrderByClause("row_add_time DESC");
//		example_s.setGroupByClause("community_id,suser_id");
		List<Scheduling> list = schedulingMapper.selectByExample(example_s);
		List<SchedulingModel> list_model = new ArrayList<SchedulingModel>();
		for(Scheduling s : list){
			SchedulingModel model = new SchedulingModel();
			BeanUtils.copyProperties(s, model);
			if(s.getCommunityId() != null){
				Community co = cmmunityMapper.selectByPrimaryKey(s.getCommunityId());
				if(co != null){
					model.setCommunityName(co.getName());
				}
			}
			
			if(s.getSuserId() != null){
//				SchedulingExample example = new SchedulingExample();
//				example.or().andSuserIdEqualTo(s.getSuserId());
//				List<Scheduling> scheduling = schedulingMapper.selectByExample(example);
//				String name="";
//				for(Scheduling sc:scheduling){
//					if(sc.getBuildingId() != null){
//						Building co = buildingMapper.selectByPrimaryKey(sc.getBuildingId());
//						if(co != null){
//							name+=co.getName()+",";
//						}
//					}
//				}
				String name="";
				Building building = buildingMapper.selectByPrimaryKey(s.getBuildingId());
				if(building != null){
					name+=building.getName();
				}
//				if(!"".equals(name) && name != null){
//					name = name.substring(0, name.length()-1);
//				}
				model.setBuildingName(name);
				ServiceUser co = serviceUserMapper.selectByPrimaryKey(s.getSuserId());
				if(co != null){
					model.setSuserName(co.getName());
					if (co.getRole() != null) {
						if (co.getRole() == 1) {
							model.setSerciveName("管家");
						}
						if (co.getRole() == 2) {
							model.setSerciveName("保安");
						}
						if (co.getRole() == 3) {
							model.setSerciveName("维修人员");
						}
					}
				}
			}
			list_model.add(model);
		}
		
		DataGrid dg = new DataGrid();
		dg.setRows(list_model);
		dg.setTotal(total);
		return dg;
	}


}
