package com.bola.nwcl.web.controller.neighborHelp;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.NeighborHelpImgManager;
import com.bola.nwcl.biz.NeighborHelpManager;
import com.bola.nwcl.biz.NeighborHelpMessageManager;
import com.bola.nwcl.biz.NeighborHelpZanManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.NeighborHelp;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImg;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImgExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessage;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessageExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.web.model.CommentWebModel;
import com.bola.nwcl.web.model.NeighborHelpWebModel;

@RequestMapping("web/NeighborHelpWebController")
@Controller
public class NeighborHelpWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired RoomManager roomManager;
	@Autowired NeighborHelpManager neighborHelpManager;
	@Autowired CommunityManager communityManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired BuildingManager buildingManager;
	@Autowired NeighborHelpMessageManager neighborHelpMessageManager;
	@Autowired NeighborHelpImgManager neighborHelpImgManager;
	@Autowired NeighborHelpZanManager neighborHelpZanManager;
	/**
	 * 跳转到邻里帮页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		return "neighborHelp/neighborHelp";
	}


	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(NeighborHelpWebModel neighborHelpWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		NeighborHelpExample example=new NeighborHelpExample();
		
		Criteria  c=example.or();

		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		//通过小区查询
		if(neighborHelpWebModel.getCommunityId()!=null&&neighborHelpWebModel.getCommunityId()!=-1&&(neighborHelpWebModel.getBuilding()==null || neighborHelpWebModel.getBuilding()==-1)){
			c.andCommunityIdEqualTo(neighborHelpWebModel.getCommunityId());
		}
		//通过楼栋查询
		if(neighborHelpWebModel.getBuilding()!=null&&neighborHelpWebModel.getBuilding()!=-1){
			List<Long> list=null;
			list=new ArrayList<Long>();
			RoomExample roomExample=new RoomExample();
			roomExample.or().andBuildingIdEqualTo(neighborHelpWebModel.getBuilding());
			List<Room> room=roomManager.selectByExample(roomExample);
			if(room.size()!=0){
				for (Room room2 : room) {
					list.add(room2.getId());
				}
			}
			if(list.size() > 0){
				c.andRoomIdIn(list);
			}else{
				c.andRoomIdEqualTo(-1L);
			}
		}



		//选项查询
		//标题
		if(StringUtils.isNotBlank(neighborHelpWebModel.getOption())&&neighborHelpWebModel.getOption().equals("1")){
			c.andTitleLike("%"+neighborHelpWebModel.getKey()+"%");
		}
		//发不时间
		if(StringUtils.isNotBlank(neighborHelpWebModel.getOption())&&neighborHelpWebModel.getOption().equals("2")){
			Date addTime=DateUtils.strToDate(neighborHelpWebModel.getKey(), "yyyy-MM-dd");
			c.andRowAddTimeBetween(addTime, new Date(addTime.getTime()+1000*60*60*24));
		}
		//内容
		if(StringUtils.isNotBlank(neighborHelpWebModel.getOption())&&neighborHelpWebModel.getOption().equals("3")){
			c.andContentLike("%"+neighborHelpWebModel.getKey()+"%");
		}

		//房间号
		if(StringUtils.isNotBlank(neighborHelpWebModel.getOption())&&neighborHelpWebModel.getOption().equals("4")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			if(neighborHelpWebModel.getBuilding()!=null&&neighborHelpWebModel.getBuilding()!=-1){
				RoomExample roomExample=new RoomExample();
				roomExample.or().andRoomNoLike("%"+neighborHelpWebModel.getKey()+"%").andBuildingIdEqualTo(neighborHelpWebModel.getBuilding());
				List<Room> room=roomManager.selectByExample(roomExample);
				if(room.size()!=0){
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}

			}else{
					RoomExample roomExample=new RoomExample();
					roomExample.or().andRoomNoLike("%"+neighborHelpWebModel.getKey()+"%");
					List<Room> room=roomManager.selectByExample(roomExample);
					if(room.size()!=0){
						for (Room room2 : room) {
							list.add(room2.getId());
						}
					}
			}
			if(list.size() > 0){
				c.andRoomIdIn(list);
			}else{
				c.andRoomIdEqualTo(-1L);
			}
		}
		//发布人
		if(StringUtils.isNotBlank(neighborHelpWebModel.getOption())&&neighborHelpWebModel.getOption().equals("5")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andNameEqualTo("%"+neighborHelpWebModel.getKey()+"%");
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
		//电话
		if(StringUtils.isNotBlank(neighborHelpWebModel.getOption())&&neighborHelpWebModel.getOption().equals("6")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+neighborHelpWebModel.getKey()+"%");
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
		//是否解决
		if (neighborHelpWebModel.getStatus()!=null && neighborHelpWebModel.getStatus() != -1) {
			c.andStatusEqualTo(neighborHelpWebModel.getStatus());
		}
		//敏感词
		if (neighborHelpWebModel.getIsSensitive()!=null) {
			c.andIsSensitiveEqualTo(1);
		}
		int count=neighborHelpManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");

		List<NeighborHelp> neighborHelp=neighborHelpManager.selectByExample(example);
		List<NeighborHelpWebModel> list=null;
		list=new ArrayList<NeighborHelpWebModel>();
		if(neighborHelp.size()!=0){
			for (NeighborHelp ne : neighborHelp) {
				NeighborHelpWebModel model=new NeighborHelpWebModel();
				model.setId(ne.getId());
				model.setTitle(ne.getTitle());
				model.setStatus(ne.getStatus());
				model.setContent(ne.getContent());
				model.setIsUserDelete(ne.getIsUserDelete());
				model.setLabel(ne.getLabel());
				model.setReward(ne.getReward());
				model.setRowAddTime(ne.getRowAddTime());
				if(ne.getRoomId() != null){
					Room room=roomManager.selectByPrimaryKey(ne.getRoomId());
					if (room!=null) {
						model.setRoomNumber(room.getRoomNo());
						model.setUnitNo(room.getUnitNo());
					}
				}
				NeighborHelpImgExample neighborHelpImgExample=new NeighborHelpImgExample();
				neighborHelpImgExample.or().andNeighborHelpIdEqualTo(ne.getId());
				NeighborHelpImg neighborHelpImg=neighborHelpImgManager.selectOneByExample(neighborHelpImgExample);
				if (neighborHelpImg != null) {
					model.setImgPath(neighborHelpImg.getImgPathThumbnail());
				}

				BizUser bizUser=bizUserManager.selectByPrimaryKey(ne.getBuserId());
				if (bizUser!=null) {
					model.setUserName(bizUser.getName());
					model.setPhone(bizUser.getPhone());
					model.setNickname(bizUser.getNickname());
					
					/*BizUserRoomExample bizUserRoomExample=new BizUserRoomExample();
					bizUserRoomExample.or().andBizUserIdEqualTo(bizUser.getId()).andRoomIdEqualTo(bizUser.getCurrentRoomId());
					BizUserRoom bizUserRoom=bizUserRoomManager.selectOneByExample(bizUserRoomExample);
					if (bizUserRoom!=null) {
						model.setOwner(bizUserRoom.getType());
					}*/
				}
				list.add(model);
			}

		}
		dg.setRows(list);
		
		dg.setTotal(count);
		return dg;
	}

	/**
	 * 跳转到邻里帮修改页面
	 * 
	 */
	@RequestMapping("editPage")
	public String editPage(HttpServletRequest request,Long id){
		NeighborHelp neighborHelp=neighborHelpManager.selectByPrimaryKey(id);
		NeighborHelpImgExample example=new NeighborHelpImgExample();
		example.or().andNeighborHelpIdEqualTo(id);
		List<NeighborHelpImg> neighborHelpImg=neighborHelpImgManager.selectByExample(example);
		request.setAttribute("neighborHelpImg", neighborHelpImg);
		request.setAttribute("neighborHelp", neighborHelp);
		BizUser bizUser=bizUserManager.selectByPrimaryKey(neighborHelp.getBuserId());
		Room room=roomManager.selectByPrimaryKey(neighborHelp.getRoomId());
		request.setAttribute("bizUser", bizUser);
		request.setAttribute("room", room);
		return "neighborHelp/neighborHelpEdit";
	}


	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,NeighborHelpWebModel neighborHelpWebModel,@RequestParam("file") MultipartFile[] file) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			if (StringUtils.isBlank(neighborHelpWebModel.getTitle())) {
				throw new PatternException("标题不能为空！！！");
			}
			if(neighborHelpWebModel.getTitle().length() > 200){
				throw new PatternException("标题不能超过200个字符！！！");
			}
			if(StringUtils.isBlank(neighborHelpWebModel.getContent())){
				throw new PatternException("内容不能为空！！！");
			}
			if(neighborHelpWebModel.getContent().length() > 600){
				throw new PatternException("内容不能超过600个字符！！！");
			}
			if(StringUtils.isBlank(neighborHelpWebModel.getReward())){
				throw new PatternException("悬赏不能为空！！！");
			}
			if(neighborHelpWebModel.getReward().length() > 600){
				throw new PatternException("悬赏不能超过600个字符！！！");
			}
			neighborHelpManager.edit(request, neighborHelpWebModel, file);

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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 
	 * 删除编辑页面图片
	 * 
	 */

	@RequestMapping("/deleteImg")
	@ResponseBody
	public BaseResult deleteImg(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			NeighborHelpImg img=neighborHelpImgManager.selectByPrimaryKey(id);
			if (img != null) {
				if (img.getImgPath() != null) {
					FileManager.deleteFile(img.getImgPath());
				}
				if (img.getImgPathThumbnail() != null) {
					FileManager.deleteFile(img.getImgPathThumbnail());
				}
				neighborHelpImgManager.deleteByPrimaryKey(id);
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


	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			neighborHelpManager.delete(id);
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



	@RequestMapping("/deleteComment")
	@ResponseBody
	public BaseResult deleteComment(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			neighborHelpMessageManager.deleteByPrimaryKey(id);
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


	@RequestMapping("/batchDeleteComment")
	@ResponseBody
	public BaseResult batchDeleteComment(String ids) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			if (ids != null && ids.length() > 0) {
				for (String id : ids.split(",")) {
					if (id != null) {
						this.deleteComment((long) Integer.parseInt(id));
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





	@RequestMapping("commentPage")
	public String commentPage(HttpServletRequest request,Long id){
		//提前查询出所有的小区
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		request.setAttribute("messageId", id);
		return "neighborHelp/activityComment";
	}

	@RequestMapping("/activityCommentDataGrid")
	@ResponseBody
	public DataGrid activityCommentDataGrid(Long communityId,Long buildingId,String account,String roomNumber,Long messageId,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		NeighborHelpMessageExample example=new NeighborHelpMessageExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		com.bola.nwcl.dal.mybatis.model.NeighborHelpMessageExample.Criteria  c=example.or();
		c.andNeighborHelpIdEqualTo(messageId);


		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() != 1){
			communityId = user.getCommunityId();
		}
		//通过小区查询
		if(communityId!=null&&communityId!=-1&&(buildingId == null || buildingId==-1)&&StringUtils.isBlank(roomNumber)){
			List<Long> list=null;
			list=new ArrayList<Long>();
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
			if(list.size() > 0){
				c.andBuserIdIn(list);
			}else{
				c.andBuserIdEqualTo(-1L);
			}
		}
		//通过楼栋查询
		if(buildingId!=null&&buildingId!=-1&&StringUtils.isBlank(roomNumber)){
			List<Long> list=null;
			list=new ArrayList<Long>();
			RoomExample roomExample=new RoomExample();
			roomExample.or().andBuildingIdEqualTo(buildingId);
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



		//房间号
		if(StringUtils.isNotBlank(roomNumber)){
			List<Long> list=null;
			list=new ArrayList<Long>();
			if(communityId!=null&&communityId!=-1&&(buildingId==null||buildingId==-1)){
				BuildingExample buildingExample=new BuildingExample();
				buildingExample.or().andCommunityIdEqualTo(communityId);
				List<Building> building=buildingManager.selectByExample(buildingExample);
				for (Building building2 : building) {
					RoomExample roomExample=new RoomExample();
					roomExample.or().andRoomNoLike("%"+roomNumber+"%").andBuildingIdEqualTo(building2.getId());
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
			}else if(buildingId!=null&&buildingId!=-1){
				RoomExample roomExample=new RoomExample();
				roomExample.or().andRoomNoLike("%"+roomNumber+"%").andBuildingIdEqualTo(buildingId);
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
				roomExample.or().andRoomNoLike("%"+roomNumber+"%");
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
		//账号查询
		if(StringUtils.isNotBlank(account)){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+account+"%");
			List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
			if(bizUser.size()!=0){
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
		List<NeighborHelpMessage> neighborHelpMessage=neighborHelpMessageManager.selectByExample(example);
		List<CommentWebModel> list=null;
		list=new ArrayList<CommentWebModel>();
		if (neighborHelpMessage.size()>0) {
			for (NeighborHelpMessage ne : neighborHelpMessage) {
				CommentWebModel model=new CommentWebModel();
				model.setId(ne.getId());
				model.setContent(ne.getContent());
				model.setRowAddTime(ne.getRowAddTime());

				BizUser us=bizUserManager.selectByPrimaryKey(ne.getBuserId());
				if(us!=null){
					model.setNickName(us.getNickname());
					model.setAccount(us.getPhone());
				}
				list.add(model);
			}
		}
		dg.setRows(list);
		int total=neighborHelpMessageManager.countByExample(example);
		dg.setTotal(total);
		return dg;
	}

	@RequestMapping("/activityCommentEditPage")
	public String activityCommentEditPage(HttpServletRequest request,Long id){
		NeighborHelpMessage neighborHelpMessage=neighborHelpMessageManager.selectByPrimaryKey(id);
		request.setAttribute("neighborHelpMessage",neighborHelpMessage);
		return "/neighborHelp/activityCommentEdit";
	}


	@RequestMapping("/activityCommentEdit")
	@ResponseBody
	public BaseResult activityCommentEdit(HttpServletRequest request,String content,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			NeighborHelpMessage model=new NeighborHelpMessage();
			model.setContent(content);
			model.setId(id);
			neighborHelpMessageManager.updateByPrimaryKeySelective(model);
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}




}
