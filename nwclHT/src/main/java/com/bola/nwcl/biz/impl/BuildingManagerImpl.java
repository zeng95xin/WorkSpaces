package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.BuildingExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.web.model.BuildingWebModel;


@Service
public class BuildingManagerImpl  extends ManagerImpl<Building, BuildingExample, Long> implements BuildingManager{

	private BuildingMapper buildingMapper;
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired
	public BuildingManagerImpl(BuildingMapper buildingMapper) {
		this.mapper = buildingMapper;
		this.buildingMapper = buildingMapper;
	}


	@Override
	public DataGrid dataGrid(Building building, PageHelper ph,HttpServletRequest request) {

		DataGrid dg = new DataGrid();
		BuildingExample example=new BuildingExample();
		
		if(building!=null){
			Criteria c=example.or();
			//得到用户对象
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin
			if(user.getRoleId() != 1){

				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				c.andCommunityIdIn(communityIds);
			}

			if(building.getCommunityId()!=null){
				c.andCommunityIdEqualTo(building.getCommunityId());
			}

			if(StringUtils.isNotBlank(building.getName())&&
					StringUtils.isNotBlank(building.getNote())){
				if(building.getName().equals("name")){					
					c.andNameLike("%"+building.getNote()+"%");

				}	
				if(building.getName().equals("address")){					
					c.andAddressLike("%"+building.getNote()+"%");
				}	
				if(building.getName().equals("note")){					
					c.andNoteLike("%"+building.getNote()+"%");
				}	
			}

		}
		int total=buildingMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");

		List<Building> bu=buildingMapper.selectByExample(example);
		List<BuildingWebModel> list=null;
		list=new ArrayList<BuildingWebModel>();
		if (bu.size()>0) {
			for (Building bud : bu) {
				BuildingWebModel model=new BuildingWebModel();
				Building bui=buildingMapper.selectByPrimaryKey(bud.getId());
				if (bui!=null) {
					model.setId(bui.getId());
					model.setName(bui.getName());
					model.setNote(bui.getNote());
					model.setAddress(bui.getAddress());
					model.setDevice(bui.getDevice());
				}
				Community community=communityMapper.selectByPrimaryKey(bud.getCommunityId());
				if (community!=null) {
					model.setCommunityName(community.getName());
				}

				list.add(model);
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);

		return dg;
	}

}
