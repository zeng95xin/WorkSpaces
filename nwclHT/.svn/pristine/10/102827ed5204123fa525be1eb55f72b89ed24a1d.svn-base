package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.repairman.RepairmainModel;
import com.bola.nwcl.biz.RepairmanDetailManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.RepairmanDetailMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.SchedulingMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetail;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetailExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.Scheduling;
import com.bola.nwcl.dal.mybatis.model.SchedulingExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;

@Service
public class RepairmanDetailManagerImpl  extends ManagerImpl<RepairmanDetail, RepairmanDetailExample, Long> implements RepairmanDetailManager{
	
	private RepairmanDetailMapper repairmanDetailMapper;
	
	@Autowired
	private SchedulingMapper schedulingMapper;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	private ServiceUserMapper serviceUserMapper;
	
	@Autowired
	public RepairmanDetailManagerImpl(RepairmanDetailMapper repairmanDetailMapper) {
		this.mapper = repairmanDetailMapper;
		this.repairmanDetailMapper = repairmanDetailMapper;
	}

	@Override
	public List<RepairmainModel> getAllRepairman(BizUser buser, Long commnityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		long comm = -1;
		if(commnityId == null){
			Room r = roomMapper.selectByPrimaryKey(buser.getCurrentRoomId());
			Building b = buildingMapper.selectByPrimaryKey(r.getBuildingId());
			Community c = communityMapper.selectByPrimaryKey(b.getCommunityId());
			comm = c.getId();
		}else{
			comm = commnityId;
		}
		map.put("communityId", comm);
		map.put("repair_service_user_type", 3);
		List<RepairmainModel> list = repairmanDetailMapper.getAllRepairman(map);
		if(list.size() < 1){
			ServiceUserExample example_su = new ServiceUserExample();
			example_su.or().andRoleEqualTo(3).andCommunityIdEqualTo(comm);
			List<ServiceUser> list_su = serviceUserMapper.selectByExample(example_su);
			List<Long> list_sids = new ArrayList<Long>();
			for(ServiceUser su : list_su){
				list_sids.add(su.getId());
			}
			SchedulingExample example_s = new SchedulingExample();
			example_s.or().andDutyStartTimeGreaterThan(new Date()).andSuserIdIn(list_sids);
			example_s.setLimit(1);
			List<Scheduling> list_sc = schedulingMapper.selectByExample(example_s);
			String str = "";
			if(list_sc == null || list_sc.size() < 1){
				str = ",请等待小区管理员安排值班维修人员";
			}else{
				str = ",上班时间："+DateUtils.DateToStr(DateUtils.YYYY_MM_DD_HH_MM, list_sc.get(0).getDutyStartTime());
			}
			throw new BusinessValidateException("当前时间小区还没有值班的维修人员"+str);
		}
		return list;
	}

}
