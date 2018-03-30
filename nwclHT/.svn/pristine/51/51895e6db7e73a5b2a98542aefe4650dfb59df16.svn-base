package com.bola.nwcl.web.controller.opinion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.biz.UserOpinionImgManager;
import com.bola.nwcl.biz.UserOpinionManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.common.util.notify.JPushUtil_Buser;
import com.bola.nwcl.common.util.notify.NotifyUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.UserOpinion;
import com.bola.nwcl.dal.mybatis.model.UserOpinionExample;
import com.bola.nwcl.dal.mybatis.model.UserOpinionExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.UserOpinionImg;
import com.bola.nwcl.dal.mybatis.model.UserOpinionImgExample;
import com.bola.nwcl.web.model.OpinionWebModel;

@RequestMapping("web/OpinionWebController")
@Controller
public class OpinionWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired CommunityManager communityManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired BuildingManager buildingManager;
	@Autowired UserOpinionManager userOpinionManager;
	@Autowired UserOpinionImgManager userOpinionImgManager;
	@Autowired RoomManager roomManager;
	@Autowired NotifyUtil notifyUtil;
	/**
	 * 跳转到用户意见页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		return "opinion/opinion";
	}




	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(OpinionWebModel opinionWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		UserOpinionExample example=new UserOpinionExample();
		
		Criteria  c=example.or();
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin(普通管理员给出当前小区id)
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		//通过小区查询
		if(opinionWebModel.getCommunityId()!=null&&opinionWebModel.getCommunityId()!=-1&&(opinionWebModel.getBuilding()==null||opinionWebModel.getBuilding()==-1)){
			c.andCommunityIdEqualTo(opinionWebModel.getCommunityId());
		}


		//通过楼栋查询
		if(opinionWebModel.getBuilding()!=null&&opinionWebModel.getBuilding()!=-1){
			List<Long> list=null;
			list=new ArrayList<Long>();
			RoomExample roomExample=new RoomExample();
			roomExample.or().andBuildingIdEqualTo(opinionWebModel.getBuilding());
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
			if(list.size() > 0){
				c.andBuserIdIn(list);
			}else{
				c.andBuserIdEqualTo(-1L);
			}
		}


		//选项查询
		//房间号
		if(StringUtils.isNotBlank(opinionWebModel.getOption())&&opinionWebModel.getOption().equals("1")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			if(opinionWebModel.getCommunityId()!=null&&opinionWebModel.getCommunityId()!=-1&&(opinionWebModel.getBuilding()==null||opinionWebModel.getBuilding()==-1)){
				BuildingExample buildingExample=new BuildingExample();
				buildingExample.or().andCommunityIdEqualTo(opinionWebModel.getCommunityId());
				List<Building> building=buildingManager.selectByExample(buildingExample);
				for (Building building2 : building) {
					RoomExample roomExample=new RoomExample();
					roomExample.or().andRoomNoLike("%"+opinionWebModel.getKey()+"%").andBuildingIdEqualTo(building2.getId());
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
			}else if(opinionWebModel.getBuilding()!=null&&opinionWebModel.getBuilding()!=-1){
				RoomExample roomExample=new RoomExample();
				roomExample.or().andRoomNoLike("%"+opinionWebModel.getKey()+"%").andBuildingIdEqualTo(opinionWebModel.getBuilding());
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

			}else{

				RoomExample roomExample=new RoomExample();
				roomExample.or().andRoomNoLike("%"+opinionWebModel.getKey()+"%");
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
			if (list.size()>0) {
				c.andBuserIdIn(list);
			} else {
				c.andBuserIdEqualTo(-1L);
			}
		}


		//电话
		if(StringUtils.isNotBlank(opinionWebModel.getOption())&&opinionWebModel.getOption().equals("2")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+opinionWebModel.getKey()+"%");
			List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
			if (bizUser.size()>0) {
				for (BizUser bizUser2 : bizUser) {
					list.add(bizUser2.getId());
				}
			}
			if (list.size()>0) {
				c.andBuserIdIn(list);
			} else {
				c.andBuserIdEqualTo(-1L);
			}
		}
//		只需要查询业主发送到后台的类型
		c.andSendTypeEqualTo(1);
		
		int count=userOpinionManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		
		List<UserOpinion> userOpinion=userOpinionManager.selectByExample(example);
		List<OpinionWebModel> list=null;
		list=new ArrayList<OpinionWebModel>();
		if (userOpinion.size()>0) {
			for (UserOpinion userOpinion2 : userOpinion) {
				OpinionWebModel model=new OpinionWebModel();
				
				UserOpinionExample example_uo_replay = new UserOpinionExample();
				example_uo_replay.or().andReplayIdEqualTo(userOpinion2.getId());
				UserOpinion userOpinion_replay = userOpinionManager.selectOneByExample(example_uo_replay);
				if(userOpinion_replay != null){
					model.setReplay(1);
					model.setReplayContent(userOpinion_replay.getContent());
				}
				UserOpinion us=userOpinionManager.selectByPrimaryKey(userOpinion2.getId());
				if (us!=null) {
					model.setId(us.getId());
					model.setContent(us.getContent());
					model.setRowAddTime(us.getRowAddTime());
				}
				BizUser bizUser=bizUserManager.selectByPrimaryKey(userOpinion2.getBuserId());
				if(bizUser!=null){
					model.setUserName(bizUser.getName());
					model.setPhone(bizUser.getPhone());
					model.setNickname(bizUser.getNickname());
					if(bizUser.getCurrentRoomId() != null){
						Room room=roomManager.selectByPrimaryKey(bizUser.getCurrentRoomId());
						if (room!=null) {
							model.setRoomNumber(room.getRoomNo());
							model.setUnitNo(room.getUnitNo());
						}
					}
				}
				UserOpinionImgExample userOpinionImgExample=new UserOpinionImgExample();
				userOpinionImgExample.or().andUserOpinionIdEqualTo(userOpinion2.getId());
				UserOpinionImg img=userOpinionImgManager.selectOneByExample(userOpinionImgExample);
				if (img!=null) {
					model.setImgPath(img.getImgPathThumbnail());
				}
				list.add(model);
			}
		}
		dg.setRows(list);
		
		dg.setTotal(count);
		return dg;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			UserOpinionImgExample userOpinionImgExample=new UserOpinionImgExample();
			userOpinionImgExample.or().andUserOpinionIdEqualTo(id);
			List<UserOpinionImg> img=userOpinionImgManager.selectByExample(userOpinionImgExample);
			for (UserOpinionImg userOpinionImg : img) {
				FileManager.deleteFile(userOpinionImg.getImgPath());
				FileManager.deleteFile(userOpinionImg.getImgPathThumbnail());
				userOpinionImgManager.deleteByPrimaryKey(userOpinionImg.getId());

			}
			userOpinionManager.deleteByPrimaryKey(id);
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


	@RequestMapping("/imgPage")
	public String imgPage(HttpServletRequest request,Long id){
		UserOpinionImgExample userOpinionImgExample=new UserOpinionImgExample();
		userOpinionImgExample.or().andUserOpinionIdEqualTo(id);
		List<UserOpinionImg> list=userOpinionImgManager.selectByExample(userOpinionImgExample);
		request.setAttribute("img", list);
		return "/opinion/opinionImg";

	}
	
	@RequestMapping("/replay")
	@ResponseBody
	public BaseResult replay(HttpServletRequest request, Long id, String content) {
		BaseResult baseResult = new BaseResult("SUCCESS", "回复成功");
		try {
			if(id == null){
				throw new BusinessValidateException("该信息已经被删除了");
			}
			UserOpinion uo = userOpinionManager.selectByPrimaryKey(id);
			if(uo == null){
				throw new BusinessValidateException("该信息已经被删除了");
			}
			
			UserOpinionExample example_uo_replay = new UserOpinionExample();
			example_uo_replay.or().andReplayIdEqualTo(id);
			UserOpinion uo_replay = userOpinionManager.selectOneByExample(example_uo_replay);
			if(uo_replay != null){
				throw new BusinessValidateException("该信息已经被回复过了");
			}
			
			UserOpinion replay = new UserOpinion();
			Admin admin = (Admin)request.getSession().getAttribute("loginUser");
			replay.setBackId(admin.getId());
			replay.setContent(content);
			replay.setMessageType(1);
			replay.setSendType(0);
			replay.setReplayId(id);
			replay.setBuserId(uo.getBuserId());
			replay.setStatus(0);
			userOpinionManager.insertAndGetId(replay);
			
			BizUser bizUser = bizUserManager.selectByPrimaryKey(uo.getBuserId());
			// 消息
			notifyUtil.send("感谢您的宝贵意见", content, "23:" + replay.getId()+ ":-1", admin.getId(), bizUser.getId(), null);
			
			// 推送
			JPushChatModel jPushChatModel = new JPushChatModel();
			jPushChatModel.setType("23:" + replay.getId()+ ":-1");
			jPushChatModel.setTitle("感谢您的宝贵意见");
			jPushChatModel.setContent(content);
			JPushUtil_Buser.sendToBuser(jPushChatModel, bizUser);
			
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
		logger.info("回复意见反馈返回值：{}", baseResult.toString());
		return baseResult;
	}









}
