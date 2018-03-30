package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.BuserCouponManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuserCouponMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.BuserCoupon;
import com.bola.nwcl.dal.mybatis.model.BuserCouponExample;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.NotifySendExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

@Service
public class BuserCouponManagerImpl  extends ManagerImpl<BuserCoupon, BuserCouponExample, Long> implements BuserCouponManager{
	
	@SuppressWarnings("unused")
	private BuserCouponMapper buserCouponMapper;
	
	@Autowired
	private BizUserMapper bizUserMapper;
	@Autowired BuildingMapper buildingMapper;
	@Autowired RoomMapper roomMapper;
	
	@Autowired
	public BuserCouponManagerImpl(BuserCouponMapper buserCouponMapper) {
		this.mapper = buserCouponMapper;
		this.buserCouponMapper = buserCouponMapper;
	}

	@Override
	public DataGrid dataGrid(Long cid, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		BuserCouponExample example = new BuserCouponExample();
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(admin.getRoleId() == 2){
			example.or().andBuserIdIn(this.getUserIds(admin.getCommunityId()));
		}
		Long pid = cid;
		if (pid != null) {
			example.or().andCouponIdEqualTo(pid);
		}else{
			example.or().andCouponIdEqualTo(-1L);
		}
		dg.setTotal(buserCouponMapper.countByExample(example));
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		
		List<BuserCoupon> imgs = buserCouponMapper.selectByExample(example);
		if (imgs != null && imgs.size() > 0) {
			for(BuserCoupon r : imgs){
				BizUser user = bizUserMapper.selectByPrimaryKey(r.getBuserId());
				if (user != null) {
					r.setUserNickName(user.getNickname());
					r.setUserAccount(user.getPhone());
					r.setUserName(user.getName());
				}
			}
		}
		dg.setRows(imgs);
		
		return dg;
	}

		private List<Long> getUserIds(Long communityId){
			List<Long> list = new ArrayList<Long>();
			BuildingExample buildingExample=new BuildingExample();
			buildingExample.or().andCommunityIdEqualTo(communityId);
			List<Building> building=buildingMapper.selectByExample(buildingExample);
			if(building.size()!=0){
				for (Building building2 : building) {
					RoomExample roomExample=new RoomExample();
					roomExample.or().andBuildingIdEqualTo(building2.getId());
					List<Room> room=roomMapper.selectByExample(roomExample);
					if(room.size()!=0){
						for (Room room2 : room) {
							BizUserExample bizUserExample=new BizUserExample();
							bizUserExample.or().andCurrentRoomIdEqualTo(room2.getId());
							List<BizUser> bizUser=bizUserMapper.selectByExample(bizUserExample);
							if(bizUser.size()!=0){
								for (BizUser bizUser2 : bizUser) {
										list.add(bizUser2.getId());
								}
							}
						}
					}
				}
			}	
			return list;
		}
}
