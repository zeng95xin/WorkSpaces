package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.DoorControlManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.DoorControlMapper;
import com.bola.nwcl.dal.mybatis.mapper.DoorTypeMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.DoorControl;
import com.bola.nwcl.dal.mybatis.model.DoorControlExample;
import com.bola.nwcl.dal.mybatis.model.DoorControlExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.DoorType;


@Service
public class DoorControlManagerImpl  extends ManagerImpl<DoorControl, DoorControlExample, Long> implements DoorControlManager{

	private DoorControlMapper doorControlMapper;
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private DoorTypeMapper doorTypeMapper;
	@Autowired
	public DoorControlManagerImpl(DoorControlMapper doorControlMapper) {
		this.mapper = doorControlMapper;
		this.doorControlMapper = doorControlMapper;
	}


	@Override
	public DataGrid dataGrid(DoorControl doorControl, PageHelper ph,HttpServletRequest request) {

		DataGrid dg = new DataGrid();
		DoorControlExample example=new DoorControlExample();
		if(doorControl!=null){
			Criteria c=example.or();
			//得到用户对象
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				c.andCommunityIdIn(communityIds);
			}
			if(doorControl.getCommunityId()!=null){
				c.andCommunityIdEqualTo(doorControl.getCommunityId());
			}

			if(StringUtils.isNotBlank(doorControl.getName())&&
					StringUtils.isNotBlank(doorControl.getNote())){
				if(doorControl.getName().equals("name")){					
					c.andNameLike("%"+doorControl.getNote()+"%");
				}	
				if(doorControl.getName().equals("device")){					
					c.andDeviceLike("%"+doorControl.getNote()+"%");
				}	
			}

		}
		int total=doorControlMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<DoorControl> bu=doorControlMapper.selectByExample(example);
		List<DoorControl> list=new ArrayList<DoorControl>();
		if (bu.size()>0) {
			for (DoorControl bud : bu) {
				DoorControl model=doorControlMapper.selectByPrimaryKey(bud.getId());
				
				Community community=communityMapper.selectByPrimaryKey(bud.getCommunityId());
				if (community!=null) {
					model.setCommunityName(community.getName());
					DoorType dType = doorTypeMapper.selectByPrimaryKey(community.getDoorTypeId());
					if (dType != null ) {
						model.setDoorTypeName(dType.getName());
					}
				}
				Building building = buildingMapper.selectByPrimaryKey(bud.getBuildingId());
				if (building != null) {
					model.setBuildingName(building.getName());
				}
				if (bud.getBuildingId() == -1) {
					model.setBuildingName("大门");
				}
				DoorType dType = doorTypeMapper.selectByPrimaryKey(bud.getDoorTypeId());
				if (dType != null ) {
					model.setDoorTypeName(dType.getName());
				}

				list.add(model);
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);

		return dg;
	}

}
