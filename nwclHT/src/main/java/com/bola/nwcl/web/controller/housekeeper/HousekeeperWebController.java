package com.bola.nwcl.web.controller.housekeeper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.HousekeeperManager;
import com.bola.nwcl.biz.HousekeeperUserMessageManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.biz.ServiceUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessage;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;
import com.bola.nwcl.web.model.HousekeeperUserMessageWebModel;
@RequestMapping("/web/HousekeeperWebController")
@Controller
public class HousekeeperWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BuildingManager buildingManager;
	@Autowired RoomManager roomManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired CommunityManager communityManager;
	@Autowired HousekeeperUserMessageManager housekeeperUserMessageManager;
	@Autowired HousekeeperManager housekeeperManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired ServiceUserManager serviceUserManager;
	/**
	 * 跳转到管家留言页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		//提前查询出所有的小区
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		return "housekeeper/housekeeper";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(HousekeeperUserMessageWebModel housekeeperUserMessageWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		HousekeeperUserMessageExample example=new HousekeeperUserMessageExample();
		Criteria  c=example.or();
		
		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin(普通管理员给出当前小区id)
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		
		//通过小区查询
		if(housekeeperUserMessageWebModel.getCommunityId()!=null&&housekeeperUserMessageWebModel.getCommunityId()!=-1){
			c.andCommunityIdEqualTo(housekeeperUserMessageWebModel.getCommunityId());
		}
		//选项查询
		//发起人
		if(StringUtils.isNotBlank(housekeeperUserMessageWebModel.getOption())&&housekeeperUserMessageWebModel.getOption().equals("1")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andNameLike("%"+housekeeperUserMessageWebModel.getKey()+"%");
			List<BizUser> us=bizUserManager.selectByExample(bizUserExample);
			if (us.size()>0) {
				for (BizUser bizUser : us) {
					list.add(bizUser.getId());
				}
			}
			if (list.size()>0) {
				c.andBuserIdIn(list);
			} else {
				c.andBuserIdEqualTo(-1L);
			}
		}
		//发起时间
		if(StringUtils.isNotBlank(housekeeperUserMessageWebModel.getOption())&&housekeeperUserMessageWebModel.getOption().equals("2")){
			Date addTime=DateUtils.strToDate(housekeeperUserMessageWebModel.getKey(), "yyyy-MM-dd");
			c.andRowAddTimeBetween(addTime, new Date(addTime.getTime()+1000*60*60*24));
		}
		/*//内容
		if(StringUtils.isNotBlank(housekeeperUserMessageWebModel.getOption())&&housekeeperUserMessageWebModel.getOption().equals("3")){
			c.andContentLike("%"+housekeeperUserMessageWebModel.getKey()+"%");
		}*/
		//房间号
		if(StringUtils.isNotBlank(housekeeperUserMessageWebModel.getOption())&&housekeeperUserMessageWebModel.getOption().equals("4")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			RoomExample roomExample=new RoomExample();
			roomExample.or().andRoomNoEqualTo(housekeeperUserMessageWebModel.getKey());
			List<Room> room=roomManager.selectByExample(roomExample);
			if(room.size()!=0){
				for (Room room2 : room) {
					list.add(room2.getId());
				}
			}
			if (list.size()>0) {
				c.andRoomIdIn(list);
			} else {
				c.andRoomIdEqualTo(-1L);
			}
		}
		//回应人
		if(StringUtils.isNotBlank(housekeeperUserMessageWebModel.getOption())&&housekeeperUserMessageWebModel.getOption().equals("5")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			ServiceUserExample housekeeperExample=new ServiceUserExample();
			housekeeperExample.or().andNicknameLike("%"+housekeeperUserMessageWebModel.getKey()+"%");
			List<ServiceUser> housekeeper=serviceUserManager.selectByExample(housekeeperExample);
			if (housekeeper.size()>0) {
				for (ServiceUser housekeeper2 : housekeeper) {
					list.add(housekeeper2.getId());
				}
			}
			if (list.size()>0) {
				c.andHousekeeperIdIn(list);
			} else {
				c.andHousekeeperIdEqualTo(-1L);
			}
		}
		int total=housekeeperUserMessageManager.selectCountByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setGroupByClause("buser_id,housekeeper_id");
		example.setOrderByClause("row_add_time DESC");
		
		List<HousekeeperUserMessage> housekeeperUserMessage=housekeeperUserMessageManager.selectByExample(example);
		List<HousekeeperUserMessageWebModel> list=null;
		list=new ArrayList<HousekeeperUserMessageWebModel>();
		if(housekeeperUserMessage.size()>0){
			for (HousekeeperUserMessage housekeeperUserMessage2 : housekeeperUserMessage) {
				
				HousekeeperUserMessageWebModel house=new HousekeeperUserMessageWebModel();
				house.setId(housekeeperUserMessage2.getId());
				if(housekeeperUserMessage2.getRoomId() != null){
					Room room=roomManager.selectByPrimaryKey(housekeeperUserMessage2.getRoomId());
					if (room!=null) {
						house.setRoomNumber(room.getUnitNo());
					}
				}
				//查询留言条数
				HousekeeperUserMessageExample housekeeperUserMessageExample=new HousekeeperUserMessageExample();
				Criteria cc=housekeeperUserMessageExample.or();
				cc.andBuserIdEqualTo(housekeeperUserMessage2.getBuserId()).andSendTypeEqualTo(1);
				//总条数
				int count=housekeeperUserMessageManager.countByExample(housekeeperUserMessageExample);
				cc.andStatusEqualTo(0);
				//未读条数
				int unreadCount=housekeeperUserMessageManager.countByExample(housekeeperUserMessageExample);
				house.setStatus(unreadCount+"/"+count);
				
				ServiceUser housekeeper = serviceUserManager.selectByPrimaryKey(housekeeperUserMessage2.getHousekeeperId());
				if (housekeeper!=null) {
					house.setName(housekeeper.getName());
				}
				BizUser bizUser=bizUserManager.selectByPrimaryKey(housekeeperUserMessage2.getBuserId());
				if (bizUser!=null) {
					house.setuserName(bizUser.getNickname());
					house.setPhone(bizUser.getPhone());
					
					
					BizUserRoomExample bizUserRoomExample=new BizUserRoomExample();
					bizUserRoomExample.or().andBizUserIdEqualTo(bizUser.getId()).andRoomIdEqualTo(bizUser.getCurrentRoomId());
					BizUserRoom bizUserRoom=bizUserRoomManager.selectOneByExample(bizUserRoomExample);
					if (bizUserRoom!=null) {
						house.setOwner(bizUserRoom.getType());
					}
					
				}
				
				list.add(house);
			}
			
		}
		dg.setRows(list);
		
		dg.setTotal(total);
		return dg;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			HousekeeperUserMessage housekeeperUserMessage=housekeeperUserMessageManager.selectByPrimaryKey(id);
			HousekeeperUserMessageExample example=new HousekeeperUserMessageExample();
			example.or().andBuserIdEqualTo(housekeeperUserMessage.getBuserId());
			housekeeperUserMessageManager.deleteByExample(example);
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("删除返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	
	@RequestMapping("/batchDelete")
	@ResponseBody
	public BaseResult batchDelete(String ids) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			if (ids != null && ids.length() > 0) {
				for (String id : ids.split(",")) {
					if (id != null) {
						this.delete((long) Integer.parseInt(id));
					}
				}
			}
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("删除返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	/**
	 * 
	 * 跳转到查看页面
	 * 
	 */
	
	@RequestMapping("/seePage")
	public String see(Long id,HttpServletRequest request){
		HousekeeperUserMessage housekeeperUserMessage=housekeeperUserMessageManager.selectByPrimaryKey(id);
		HousekeeperUserMessageExample example=new HousekeeperUserMessageExample();
		example.or().andBuserIdEqualTo(housekeeperUserMessage.getBuserId());
		List<HousekeeperUserMessage> house=housekeeperUserMessageManager.selectByExample(example);
		BizUser bizUser=bizUserManager.selectByPrimaryKey(housekeeperUserMessage.getBuserId());
		ServiceUser housekeeper = serviceUserManager.selectByPrimaryKey(housekeeperUserMessage.getHousekeeperId());
		request.setAttribute("housekeeperUserMessage", house);
		request.setAttribute("bizUser", bizUser);
		request.setAttribute("housekeeper", housekeeper);
		return "housekeeper/see";
	}
	
	

}
