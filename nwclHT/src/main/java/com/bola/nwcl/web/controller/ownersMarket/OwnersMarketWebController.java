package com.bola.nwcl.web.controller.ownersMarket;

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
import com.bola.nwcl.biz.OwnersMarketImgManager;
import com.bola.nwcl.biz.OwnersMarketManager;
import com.bola.nwcl.biz.OwnersMarketRatingManager;
import com.bola.nwcl.biz.OwnersMarketZanManager;
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
import com.bola.nwcl.dal.mybatis.model.OwnersMarket;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketExample;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketImg;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketImgExample;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRating;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRatingExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.web.model.LikeShareWebModel;


@RequestMapping("web/OwnersMarketWebController")
@Controller
public class OwnersMarketWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired RoomManager roomManager;
	@Autowired CommunityManager communityManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired BuildingManager buildingManager;
	@Autowired OwnersMarketManager ownersMarketManager;
	@Autowired OwnersMarketImgManager ownersMarketImgManager;
	@Autowired OwnersMarketRatingManager ownersMarketRatingManager;
	@Autowired OwnersMarketZanManager ownersMarketZanManager;

	/**
	 * 跳转到业主市集页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		return "ownersMarket/ownersMarket";
	}


	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LikeShareWebModel likeShareWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		OwnersMarketExample example=new OwnersMarketExample();
		
		Criteria  c=example.or();

		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin(普通管理员给出当前小区id)
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		//通过小区查询
		if(likeShareWebModel.getCommunityId()!=null&&likeShareWebModel.getCommunityId()!=-1&&(likeShareWebModel.getBuilding()==null || likeShareWebModel.getBuilding()==-1)){
			c.andCommunityIdEqualTo(likeShareWebModel.getCommunityId());
		}
		//通过楼栋查询
		if(likeShareWebModel.getBuilding()!=null&&likeShareWebModel.getBuilding()!=-1){
			List<Long> list=null;
			list=new ArrayList<Long>();
			RoomExample roomExample=new RoomExample();
			roomExample.or().andBuildingIdEqualTo(likeShareWebModel.getBuilding());
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
		//房间号
		if(StringUtils.isNotBlank(likeShareWebModel.getOption())&&likeShareWebModel.getOption().equals("4")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			if(likeShareWebModel.getBuilding()!=null&&likeShareWebModel.getBuilding()!=-1){
				RoomExample roomExample=new RoomExample();
				roomExample.or().andRoomNoLike("%"+likeShareWebModel.getKey()+"%").andBuildingIdEqualTo(likeShareWebModel.getBuilding());
				List<Room> room=roomManager.selectByExample(roomExample);
				if(room.size()!=0){
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}

			}else{
				RoomExample roomExample=new RoomExample();
				roomExample.or().andRoomNoLike("%"+likeShareWebModel.getKey()+"%");
				List<Room> room=roomManager.selectByExample(roomExample);
				if(room.size()!=0){
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}
			}
			if (list.size()>0) {
				c.andRoomIdIn(list);
			} else {
				c.andRoomIdEqualTo(-1L);
			}
		}

		//标题
		if(StringUtils.isNotBlank(likeShareWebModel.getOption())&&likeShareWebModel.getOption().equals("1")){
			c.andTitleLike("%"+likeShareWebModel.getKey()+"%");
		}
		//发不时间
		if(StringUtils.isNotBlank(likeShareWebModel.getOption())&&likeShareWebModel.getOption().equals("2")){
			Date addTime=DateUtils.strToDate(likeShareWebModel.getKey(), "yyyy-MM-dd");
			c.andRowAddTimeBetween(addTime, new Date(addTime.getTime()+1000*60*60*24));
		}
		//内容
		if(StringUtils.isNotBlank(likeShareWebModel.getOption())&&likeShareWebModel.getOption().equals("3")){
			c.andContentLike("%"+likeShareWebModel.getKey()+"%");
		}
		//发布人
		if(StringUtils.isNotBlank(likeShareWebModel.getOption())&&likeShareWebModel.getOption().equals("5")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andNameEqualTo("%"+likeShareWebModel.getKey()+"%");
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
		if(StringUtils.isNotBlank(likeShareWebModel.getOption())&&likeShareWebModel.getOption().equals("6")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+likeShareWebModel.getKey()+"%");
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
		//敏感词
		if (likeShareWebModel.getIsSensitive()!=null) {
			c.andIsSensitiveEqualTo(1);
		}

		int count=ownersMarketManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<OwnersMarket> ownersMarket=ownersMarketManager.selectByExample(example);
		List<LikeShareWebModel> list=null;
		list=new ArrayList<LikeShareWebModel>();
		if(ownersMarket.size()>0){
			for (OwnersMarket ow : ownersMarket) {
				LikeShareWebModel model=new LikeShareWebModel();
				model.setId(ow.getId());
				model.setTitle(ow.getTitle());
				model.setLabel(ow.getLabel());
				model.setIsUserDelete(ow.getIsUserDelete());
				model.setContent(ow.getContent());
				model.setRowAddTime(ow.getRowAddTime());
				if(ow.getRoomId() != null){
					Room room=roomManager.selectByPrimaryKey(ow.getRoomId());
					if (room!=null) {
						model.setRoomNumber(room.getRoomNo());
						model.setUnitNo(room.getUnitNo());
					}
				}
				BizUser bizUser=bizUserManager.selectByPrimaryKey(ow.getBuserId());
				if(bizUser!=null){
					model.setUserName(bizUser.getName());
					model.setPhone(bizUser.getPhone());
					model.setNickname(bizUser.getNickname());

					/*BizUserRoomExample bizUserRoomExample=new BizUserRoomExample();
					bizUserRoomExample.or().andBizUserIdEqualTo(bizUser.getId()).andRoomIdEqualTo(bizUser.getCurrentRoomId());
					BizUserRoom bizUserRoom=bizUserRoomManager.selectOneByExample(bizUserRoomExample);
					if(bizUserRoom!=null){
						model.setOwner(bizUserRoom.getType());
					}*/
				}
				OwnersMarketImgExample ownersMarketImgExample=new OwnersMarketImgExample();
				ownersMarketImgExample.or().andOwnersMarketIdEqualTo(ow.getId());
				OwnersMarketImg ownersMarketImg=ownersMarketImgManager.selectOneByExample(ownersMarketImgExample);
				if (ownersMarketImg!=null) {
					model.setImgPath(ownersMarketImg.getImgPathThumbnail());
				}
				list.add(model);
			}

		}
		dg.setRows(list);
		
		dg.setTotal(count);
		return dg;
	}


	/**
	 * 跳转到爱修改页面
	 * 
	 */
	@RequestMapping("editPage")
	public String editPage(HttpServletRequest request,Long id){
		OwnersMarket ownersMarket=ownersMarketManager.selectByPrimaryKey(id);
		OwnersMarketImgExample example=new OwnersMarketImgExample();
		example.or().andOwnersMarketIdEqualTo(id);
		List<OwnersMarketImg> ownersMarketImg=ownersMarketImgManager.selectByExample(example);
		request.setAttribute("ownersMarketImg", ownersMarketImg);
		request.setAttribute("ownersMarket", ownersMarket);
		BizUser bizUser=bizUserManager.selectByPrimaryKey(ownersMarket.getBuserId());
		Room room=roomManager.selectByPrimaryKey(ownersMarket.getRoomId());
		request.setAttribute("bizUser", bizUser);
		request.setAttribute("room", room);
		return "ownersMarket/ownersMarketEdit";
	}




	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,LikeShareWebModel likeShareWebModel,@RequestParam("file") MultipartFile[] file) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			if (StringUtils.isBlank(likeShareWebModel.getTitle())) {
				throw new PatternException("标题不能为空！！！");
			}
			if(likeShareWebModel.getTitle().length() > 200){
				throw new PatternException("标题不能超过200个字符！！！");
			}
			if(StringUtils.isBlank(likeShareWebModel.getContent())){
				throw new PatternException("内容不能为空！！！");
			}
			if(likeShareWebModel.getContent().length() > 600){
				throw new PatternException("内容不能超过600个字符！！！");
			}
			ownersMarketManager.edit(request, likeShareWebModel, file);


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
			OwnersMarketImg img=ownersMarketImgManager.selectByPrimaryKey(id);
			if (img != null) {
				if (img.getImgPath() != null) {
					FileManager.deleteFile(img.getImgPath());
				}
				if (img.getImgPathThumbnail() != null) {
					FileManager.deleteFile(img.getImgPathThumbnail());
				}
				ownersMarketImgManager.deleteByPrimaryKey(id);
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
			ownersMarketManager.delete(id);
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
			ownersMarketRatingManager.deleteByPrimaryKey(id);
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
		return "ownersMarket/activityComment";
	}

	@RequestMapping("/activityCommentDataGrid")
	@ResponseBody
	public DataGrid activityCommentDataGrid(LikeShareWebModel likeShareWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		OwnersMarketRatingExample example=new OwnersMarketRatingExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		com.bola.nwcl.dal.mybatis.model.OwnersMarketRatingExample.Criteria  c=example.or();
		c.andOwnersMarketIdEqualTo(likeShareWebModel.getId());

		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() == 1){

		}
		//通过小区查询
		if(likeShareWebModel.getCommunityId()!=null&&likeShareWebModel.getCommunityId()!=-1&&(likeShareWebModel.getBuilding() == null||likeShareWebModel.getBuilding()==-1)&&StringUtils.isNotBlank(likeShareWebModel.getRoomNumber())){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BuildingExample buildingExample=new BuildingExample();
			buildingExample.or().andCommunityIdEqualTo(likeShareWebModel.getCommunityId());
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
		if(likeShareWebModel.getBuilding()!=null&&likeShareWebModel.getBuilding()!=-1&&StringUtils.isBlank(likeShareWebModel.getRoomNumber())){
			List<Long> list=null;
			list=new ArrayList<Long>();
			RoomExample roomExample=new RoomExample();
			roomExample.or().andBuildingIdEqualTo(likeShareWebModel.getBuilding());
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
		if(StringUtils.isNotBlank(likeShareWebModel.getRoomNumber())){
			List<Long> list=null;
			list=new ArrayList<Long>();
			if(likeShareWebModel.getCommunityId()!=null&&likeShareWebModel.getCommunityId()!=-1&&(likeShareWebModel.getBuilding()==null||likeShareWebModel.getBuilding()==-1)){
				BuildingExample buildingExample=new BuildingExample();
				buildingExample.or().andCommunityIdEqualTo(likeShareWebModel.getCommunityId());
				List<Building> building=buildingManager.selectByExample(buildingExample);
				for (Building building2 : building) {
					RoomExample roomExample=new RoomExample();
					roomExample.or().andRoomNoLike("%"+likeShareWebModel.getRoomNumber()+"%").andBuildingIdEqualTo(building2.getId());
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
			}else if(likeShareWebModel.getBuilding()!=null&&likeShareWebModel.getBuilding()!=-1){
				RoomExample roomExample=new RoomExample();
				roomExample.or().andRoomNoLike("%"+likeShareWebModel.getRoomNumber()+"%").andBuildingIdEqualTo(likeShareWebModel.getBuilding());
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
				roomExample.or().andRoomNoLike("%"+likeShareWebModel.getKey()+"%");
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
		if(StringUtils.isNotBlank(likeShareWebModel.getAccount())){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+likeShareWebModel.getAccount()+"%");
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
		List<OwnersMarketRating> ownersMarketRating=ownersMarketRatingManager.selectByExample(example);
		List<LikeShareWebModel> list=null;
		list=new ArrayList<LikeShareWebModel>();

		if (ownersMarketRating.size()>0) {
			for (OwnersMarketRating like : ownersMarketRating) {
				LikeShareWebModel model=new LikeShareWebModel();
				model.setId(like.getId());
				model.setContent(like.getContent());
				model.setRowAddTime(like.getRowAddTime());

				BizUser us=bizUserManager.selectByPrimaryKey(like.getBuserId());
				if(us!=null){
					model.setNickName(us.getNickname());
					model.setAccount(us.getPhone());
				}
				list.add(model);
			}
		}
		dg.setRows(list);
		int total=ownersMarketRatingManager.countByExample(example);
		dg.setTotal(total);
		return dg;
	}

	@RequestMapping("/activityCommentEditPage")
	public String activityCommentEditPage(HttpServletRequest request,Long id){
		OwnersMarketRating ownersMarketRating=ownersMarketRatingManager.selectByPrimaryKey(id);
		request.setAttribute("ownersMarketRating",ownersMarketRating);
		return "/ownersMarket/activityCommentEdit";
	}


	@RequestMapping("/activityCommentEdit")
	@ResponseBody
	public BaseResult activityCommentEdit(HttpServletRequest request,String content,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			OwnersMarketRating model=new OwnersMarketRating();
			model.setContent(content);
			model.setId(id);
			ownersMarketRatingManager.updateByPrimaryKeySelective(model);
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
