package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.BorrowManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.BorrowMapper;
import com.bola.nwcl.dal.mybatis.mapper.BorrowThingMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.ThingMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Borrow;
import com.bola.nwcl.dal.mybatis.model.BorrowExample;
import com.bola.nwcl.dal.mybatis.model.BorrowExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.BorrowThing;
import com.bola.nwcl.dal.mybatis.model.BorrowThingExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Thing;
import com.bola.nwcl.web.model.BorrowWebModel;


@Service
public class BorrowManagerImpl  extends ManagerImpl<Borrow, BorrowExample, Long> implements BorrowManager{
	
	private BorrowMapper borrowMapper;
	
	@Autowired
	private BorrowThingMapper btMapper;
	@Autowired
	private ThingMapper thingMapper;
	@Autowired
	private BizUserMapper bizUserMapper;
	@Autowired
	private RoomMapper roomMapper;
	@Autowired BuildingMapper buildingMapper;
	@Autowired
	public BorrowManagerImpl(BorrowMapper borrowMapper) {
		this.mapper = borrowMapper;
		this.borrowMapper = borrowMapper;
	}

	@Override
	public void insertAndGetId(Borrow borrow) {
		borrowMapper.insertAndGetId(borrow);
	}
	

	@Override
	public DataGrid dataGrid(BorrowThing borrow, PageHelper ph,HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		BorrowExample example=new BorrowExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		Criteria c= example.or();
		if (borrow.getStatus() != null) {
			c.andStatusEqualTo(borrow.getStatus());
		}
		//得到用户对象
				Admin user = (Admin)request.getSession().getAttribute("loginUser");
				//判断是普通管理员还是admin
				if(user.getRoleId() != 1){
					//通过小区查询
					@SuppressWarnings("unchecked")
					List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
					c.andCommunityIdIn(communityIds);
				}
			List<BorrowWebModel> list=null;
			list=new ArrayList<BorrowWebModel>();
			List<Borrow> bor = borrowMapper.selectByExample(example);
			if (bor != null && bor.size() != 0) {
				for (Borrow borrow2 : bor) {
					BorrowWebModel model = new BorrowWebModel();
					Borrow br = borrowMapper.selectByPrimaryKey(borrow2.getId());
					if(br != null){
						model.setId(br.getId());
						model.setRoomNo(br.getRoomId());
						model.setState(br.getStatus());
						model.setBorrowReason(br.getReason());
						model.setBorrowTime(br.getRowAddTime());
					}
					BizUser us = bizUserMapper.selectByPrimaryKey(borrow2.getBuserId());
					if (us != null) {
						model.setUserName(us.getName());
					}
					BorrowThingExample borrowThingExample = new BorrowThingExample();
					borrowThingExample.or().andBorrowIdEqualTo(borrow2.getId());
					List<BorrowThing> li=btMapper.selectByExample(borrowThingExample);
					if(li != null && li.size() != 0){
						for (BorrowThing borrowThing : li) {
							Thing thing = thingMapper.selectByPrimaryKey(borrowThing.getThingId());
							if (thing != null) {
								model.setThingName(thing.getName());
							}
						}
						
					}
				list.add(model);	
				}
				
			}
		
		dg.setRows(list);
		int total=borrowMapper.countByExample(example);
		dg.setTotal(total);
		
		return dg;
	}

}
