package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ThingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.ThingMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.ThingExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Thing;
import com.bola.nwcl.dal.mybatis.model.ThingExample;

@Service
public class ThingManagerImpl  extends ManagerImpl<Thing, ThingExample, Long> implements ThingManager{
	
	private ThingMapper thingMapper;
	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	public ThingManagerImpl(ThingMapper thingMapper) {
		this.mapper = thingMapper;
		this.thingMapper = thingMapper;
	}

	@Override
	public DataGrid dataGrid(Thing thing, PageHelper ph,HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		ThingExample example=new ThingExample();
		Criteria c = example.or();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		if(thing.getName()!=null){
			if(thing.getName().equals("name") && thing.getSize()!=null){
				c.andNameLike("%"+thing.getSize()+"%");
			}
			if(thing.getName().equals("compensationPrice") && thing.getSize()!=null){
				Float f = Float.parseFloat(thing.getSize());
				c.andCompensationPriceEqualTo(f);

			}
			if(thing.getName().equals("num")&&thing.getSize()!=null){
				Integer value=Integer.valueOf(thing.getSize());
				c.andNumEqualTo(value);
			}
		}
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		
			
		List<Thing> list=thingMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for( Thing t : list){
				Community com = communityMapper.selectByPrimaryKey(t.getCommunityId());
				if (com != null) {
					t.setCommunityName(com.getName());
				}
			}
		}
		dg.setRows(list);
		int total=thingMapper.countByExample(null);
		dg.setTotal(total);
		
		return dg;
	}

}
