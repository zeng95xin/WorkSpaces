package com.bola.nwcl.web.controller.selectCommunity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

@RequestMapping("web/SelectCommunityWebController")
@Controller
public class SelectCommunityWebController {
	@Autowired CommunityManager communityManager;
	@Autowired BuildingManager buildingManager;
	@Autowired RoomManager roomManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired BizUserRoomManager bizUserRoomManager;

	@ResponseBody
	@RequestMapping("/selectCommunity")
	public List<Community> selectCommunity(){
		List<Community> community=communityManager.selectByExample(null);
		Community model=new Community();
		model.setId(-1L);
		model.setName("--请选择小区--");
		community.add(0, model);
		return community;
	}

	/**
	 * 
	 * 查询楼宇
	 *
	 */
	@RequestMapping("/selectBuilding")
	@ResponseBody
	public List<Building> selectBuilding(Long id){
		Building model=new Building();
		BuildingExample example=new BuildingExample();
		example.or().andCommunityIdEqualTo(id);
		List<Building> list=buildingManager.selectByExample(example);;
		model.setId((long) -1);
		model.setName("--请选择楼宇--");
		list.add(0,model);
		return list;
	}
	/**
	 * 
	 * 查询房间号
	 *
	 */
	@RequestMapping("/selectRoom")
	@ResponseBody
	public List<Room> selectRoom(Long id){
		Room model=new Room();
		RoomExample example=new RoomExample();
		example.or().andBuildingIdEqualTo(id);
		List<Room> list=roomManager.selectByExample(example);
		model.setId((long) -1);
		model.setRoomNo("--请选择房间号--");
		list.add(0,model);
		return list;
	}
	/**
	 * 
	 * 查询房主
	 *
	 */
	@RequestMapping("/selectUser")
	@ResponseBody
	public List<BizUser> selectUser(Long id){
		BizUserRoomExample example=new BizUserRoomExample();
		example.or().andRoomIdEqualTo(id).andTypeEqualTo("业主");
		List<BizUserRoom> bizUserRoom=bizUserRoomManager.selectByExample(example);
		List<BizUser> list=null;
		list=new ArrayList<BizUser>();
		if (bizUserRoom.size()>0) {
			for (BizUserRoom bizUserRoom2 : bizUserRoom) {
				BizUser bizUser=bizUserManager.selectByPrimaryKey(bizUserRoom2.getBizUserId());
				list.add(bizUser);
			}
		}
		BizUser model=new BizUser();
		model.setId((long) -1);
		model.setName("--请选择业主--");
		list.add(0,model);
		return list;
	}
}
