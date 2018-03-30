package com.bola.nwcl.web.controller.indexPage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.ArticleReleaseManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.EmployeeManager;
import com.bola.nwcl.biz.HangPictureManager;
import com.bola.nwcl.biz.HousekeeperUserMessageManager;
import com.bola.nwcl.biz.LectureHallIntroducingManager;
import com.bola.nwcl.biz.MaintenanceManager;
import com.bola.nwcl.biz.MemuManager;
import com.bola.nwcl.biz.NotifyManager;
import com.bola.nwcl.biz.RoleManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.biz.SchedulingManager;
import com.bola.nwcl.biz.ServiceUserManager;
import com.bola.nwcl.biz.UserOpinionManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseObjectResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.ArticleRelease;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseExample;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.HangPictureExample;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessage;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducing;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingExample;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.MaintenanceExample;
import com.bola.nwcl.dal.mybatis.model.Memu;
import com.bola.nwcl.dal.mybatis.model.MemuExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifyExample;
import com.bola.nwcl.dal.mybatis.model.NotifyExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.Scheduling;
import com.bola.nwcl.dal.mybatis.model.SchedulingExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.UserOpinion;
import com.bola.nwcl.dal.mybatis.model.UserOpinionExample;
import com.bola.nwcl.web.model.EmployeeOnDuty;
import com.bola.nwcl.web.model.IndexPageInfo;

@Controller
@RequestMapping(value = "web/indexPage")
public class IndexPageController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private AdminManager adminManager;
	@Autowired
	private NotifyManager NotifyManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private UserOpinionManager opinionManager;
	@Autowired
	private ServiceUserManager serviceUserManager;
	@Autowired
	private SchedulingManager schedulingManager;
	@Autowired
	private MemuManager memuManager;
	@Autowired 
	private BizUserManager bizUserManager;
	@Autowired
	private BizUserRoomManager bizUserRoomManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private ArticleReleaseManager releaseManager;
	@Autowired
	private MaintenanceManager maintenanceManager;
	@Autowired
	private HousekeeperUserMessageManager housekepperManager;
	@Autowired
	private HangPictureManager hangPictureManager;
	@Autowired
	private LectureHallIntroducingManager introducingManager;
	
	
	@ResponseBody
	@RequestMapping(value="/getInfo",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public BaseObjectResult<IndexPageInfo> get(HttpSession session, HttpServletRequest request) {
		BaseObjectResult<IndexPageInfo> baseResult=new BaseObjectResult<IndexPageInfo>("SUCCESS", "成功");
		/**
		 *  step 1 : 获取最新发布的未审核三条消息
		 *  step 2 : 获取最新的三条反馈意见
		 *  step 3 : 获取今日所有的值班人员信息
		 *  step 4 : 返回 获取更多节点
		 *  step 5 : 获取最新3条放行数据
		 *  step 6 : 获取最新3条维修数据
		 *  step 7 : 获取最新3条管家留言
		 *  step 8 : 获取最新3条挂画管理数据
		 *  step 9 : 获取最新3条业主自荐数据
		 *  
		 */
		try {
			Long comId = -1L;
			Admin user = (Admin) request.getSession().getAttribute("loginUser");
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			if (user != null) {
				if (user.getRoleId() != 1) {
					comId = user.getCommunityId();
				}
			}
			List<Long> list = null;
			if ( comId != null && comId != -1L) {
				list = this.getusers(comId);
				if (list.size() < 1) {
					list.add(-1L);
				}
			}
			IndexPageInfo info = new IndexPageInfo();
			
			// s1
			NotifyExample aExample = new NotifyExample();
			Criteria c1 = aExample.or();
			c1.andStatusEqualTo(0);
			if (user.getRoleId() != 1) {
				c1.andCommunityIdIn(communityIds);
			}
			aExample.setOrderByClause("row_add_time DESC");
			aExample.setLimit(3);
			List<Notify> as = NotifyManager.selectByExample(aExample);
			info.setNotifies(as);
			
			//s2
			UserOpinionExample oExample = new UserOpinionExample();
			oExample.setOrderByClause("row_add_time DESC");
			oExample.setLimit(3);
			if (comId != null && comId != -1L) {				
				oExample.or().andBuserIdIn(list).andSendTypeEqualTo(1);
			}else{
				oExample.or().andSendTypeEqualTo(1);
			}
			List<UserOpinion> us = opinionManager.selectByExample(oExample);
			info.setOpinions(us);
			
			// s5
			ArticleReleaseExample releaseExample = new ArticleReleaseExample();
			if (user.getRoleId() != 1) {
				releaseExample.or().andCommunityIdIn(communityIds);
			}
			releaseExample.setOrderByClause("row_add_time DESC");
			releaseExample.setLimit(3);
			List<ArticleRelease> res = releaseManager.selectByExample(releaseExample);
			info.setReleases(res);
			
			// s6
			MaintenanceExample mExample = new MaintenanceExample();
			if (user.getRoleId() != 1) {
				mExample.or().andCommunityIdIn(communityIds);
			}
			mExample.setOrderByClause("row_add_time DESC");
			mExample.setLimit(3);
			List<Maintenance> maintenances = maintenanceManager.selectByExample(mExample);
			info.setMaintenances(maintenances);
			
			// s7
			HousekeeperUserMessageExample hmExample = new HousekeeperUserMessageExample();
			hmExample.setGroupByClause("buser_id,housekeeper_id");
			if (user.getRoleId() != 1) {
				hmExample.or().andCommunityIdIn(communityIds);
			}
			hmExample.setOrderByClause("row_add_time DESC");
			hmExample.setLimit(3);
			List<HousekeeperUserMessage> hms = housekepperManager.selectByExample(hmExample);
			info.sethMessages(hms);
			
			// s8
			HangPictureExample hpExample = new HangPictureExample();
			if (user.getRoleId() != 1) {
				hpExample.or().andCommunityIdIn(communityIds);
			}
			hpExample.setOrderByClause("row_add_time DESC");
			hpExample.setLimit(3);
			List<HangPicture> hps = hangPictureManager.selectByExample(hpExample);
			info.setHangPictures(hps);
			
			// s9
			LectureHallIntroducingExample lhExample = new LectureHallIntroducingExample();
			if (user.getRoleId() != 1) {
				lhExample.or().andCommunityIdIn(communityIds);
			}
			lhExample.setOrderByClause("row_add_time DESC");
			lhExample.setLimit(3);
			List<LectureHallIntroducing> lss = introducingManager.selectByExample(lhExample);
			info.setIntroducings(lss);
			
			//s3
			SchedulingExample sExample = new SchedulingExample();
			sExample.setGroupByClause("suser_id");
			if (user.getRoleId() != 1) {
				sExample.or().andCommunityIdIn(communityIds);
			}
			List<Scheduling> ss = schedulingManager.selectByExample(sExample);
			if (ss != null && ss.size() > 0){
				// 判断 管家 保安 维修
				List<EmployeeOnDuty> guanjia = new ArrayList<EmployeeOnDuty>(); 
				List<EmployeeOnDuty> baoan = new ArrayList<EmployeeOnDuty>(); 
				List<EmployeeOnDuty> weixiu = new ArrayList<EmployeeOnDuty>(); 
				for(Scheduling s : ss){
					ServiceUser suser = serviceUserManager.selectByPrimaryKey(s.getSuserId());
					if (suser != null) {
						EmployeeOnDuty on = new EmployeeOnDuty();
						EmployeeExample employeeExample = new EmployeeExample();
						employeeExample.or().andSuserIdEqualTo(suser.getId());
						Employee employee = employeeManager.selectOneByExample(employeeExample);
						if (employee != null) {
							on.setSuerId(s.getSuserId());
							on.setSuerName(suser.getName());
							on.setSuerPhone(employee.getMobilephoneNumber());
						}
						if (suser.getRole() == 1) {
							guanjia.add(on);
						}
						if (suser.getRole() == 2) {
							baoan.add(on);
						}
						if (suser.getRole() == 3) {
							weixiu.add(on);
						}
					}
				}
				info.setHousekeepers(guanjia);
				info.setRepairmen(weixiu);
				info.setSecurity(baoan);
			}
			// s4
			// 1
			MemuExample memuExample = new MemuExample();
			memuExample.or().andMemuUrlEqualTo("/web/notify/manager");
			Memu me = memuManager.selectOneByExample(memuExample);
			if (me != null) {
				info.setNotifyNode(me);
			}
			// 2
			MemuExample memuExample1 = new MemuExample();
			memuExample1.or().andMemuUrlEqualTo("/web/OpinionWebController/manager");
			Memu me1  = memuManager.selectOneByExample(memuExample1);
			if (me1 != null) {
				info.setOpinionNode(me1);
			}
			// 3
			MemuExample memuExample3 = new MemuExample();
			memuExample3.or().andMemuUrlEqualTo("/web/articleRelease/manager");
			Memu me3 = memuManager.selectOneByExample(memuExample3);
			if (me3 != null) {
				info.setReleaseNode(me3);
			}
			// 4
			MemuExample memuExample4 = new MemuExample();
			memuExample4.or().andMemuUrlEqualTo("/web/maintenance/manager");
			Memu me4 = memuManager.selectOneByExample(memuExample4);
			if (me4 != null) {
				info.setMaintenanceNode(me4);
			}
			// 5
			MemuExample memuExample5 = new MemuExample();
			memuExample5.or().andMemuUrlEqualTo("/web/HousekeeperWebController/manager");
			Memu me5 = memuManager.selectOneByExample(memuExample5);
			if (me5 != null) {
				info.setHousekeeperNode(me5);
			}
			// 6
			MemuExample memuExample6 = new MemuExample();
			memuExample6.or().andMemuUrlEqualTo("/web/hangPicture/manager");
			Memu me6 = memuManager.selectOneByExample(memuExample6);
			if (me6 != null) {
				info.setHangNode(me6);
			}
			// 7
			MemuExample memuExample7 = new MemuExample();
			memuExample7.or().andMemuUrlEqualTo("/web/IntroducingWebController/manager");
			Memu me7 = memuManager.selectOneByExample(memuExample7);
			if (me7 != null) {
				info.setIntoduceNode(me7);
			}
		baseResult.setResult(info);		
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseObjectResult<IndexPageInfo>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseObjectResult<IndexPageInfo>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseObjectResult<IndexPageInfo>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseObjectResult<IndexPageInfo>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("：{}", baseResult.toString());
		return baseResult;
	}
	
	// 得到小区用户
	private List<Long> getusers( Long communityId) {
		List<Long> list = new ArrayList<Long>();
		BuildingExample buildingExample=new BuildingExample();
		buildingExample.or().andCommunityIdEqualTo(communityId);
		List<Building> building=buildingManager.selectByExample(buildingExample);
		if(building.size()!=0){
			for (Building building2 : building) {
				RoomExample roomExample=new RoomExample();
				roomExample.or().andBuildingIdEqualTo(building2.getId());
				List<Room> room=roomManager.selectByExample(roomExample);
				if(room.size()!=0){
					for (Room room2 : room) {
						BizUserExample bizUserExample=new BizUserExample();
						bizUserExample.or().andCurrentRoomIdEqualTo(room2.getId());
						List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
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
	
	// 得到小区房间ID
	private List<Long> getRoomIds( Long communityId) {
		List<Long> list = new ArrayList<Long>();
		BuildingExample buildingExample=new BuildingExample();
		buildingExample.or().andCommunityIdEqualTo(communityId);
		List<Building> building=buildingManager.selectByExample(buildingExample);
		if(building.size()!=0){
			for (Building building2 : building) {
				RoomExample roomExample=new RoomExample();
				roomExample.or().andBuildingIdEqualTo(building2.getId());
				List<Room> room=roomManager.selectByExample(roomExample);
				if(room.size()!=0){
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}
			}
		}
		if (list.size() < 1) {
			list.add(-1L);
		}
		return list;
	}
	
}
