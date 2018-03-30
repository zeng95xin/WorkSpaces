package com.bola.nwcl.web.controller.likeShare;

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
import com.bola.nwcl.biz.LikeShareImgManager;
import com.bola.nwcl.biz.LikeShareManager;
import com.bola.nwcl.biz.LikeShareRatingManager;
import com.bola.nwcl.biz.LikeShareZanManager;
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
import com.bola.nwcl.dal.mybatis.model.LikeShare;
import com.bola.nwcl.dal.mybatis.model.LikeShareExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.bola.nwcl.dal.mybatis.model.LikeShareImgExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareRating;
import com.bola.nwcl.dal.mybatis.model.LikeShareRatingExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.web.model.LikeShareWebModel;


@RequestMapping("web/LikeShareWebController")
@Controller
public class LikeShareWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired RoomManager roomManager;
	@Autowired CommunityManager communityManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired BuildingManager buildingManager;
	@Autowired LikeShareManager likeShareManager;
	@Autowired LikeShareImgManager likeShareImgManager;
	@Autowired LikeShareRatingManager likeShareRatingManager;
	@Autowired LikeShareZanManager likeShareZanManager;

	/**
	 * 跳转到爱分享页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		return "likeShare/likeShare";
	}



	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LikeShareWebModel likeShareWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		LikeShareExample example=new LikeShareExample();
		
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
		if(likeShareWebModel.getCommunityId()!=null&&likeShareWebModel.getCommunityId()!=-1&&(likeShareWebModel.getBuilding() == null ||likeShareWebModel.getBuilding()==-1)){
			c.andCommunityIdEqualTo(likeShareWebModel.getCommunityId());
		}
		
		//通过楼栋查询
		if(likeShareWebModel.getBuilding()!=null && likeShareWebModel.getBuilding()!=-1){
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
		//房间号
		if(StringUtils.isNotBlank(likeShareWebModel.getOption()) && likeShareWebModel.getOption().equals("4")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			if(likeShareWebModel.getBuilding()!=null && likeShareWebModel.getBuilding()!=-1){
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
			if(list.size() > 0){
				c.andRoomIdIn(list);
			}else{
				c.andRoomIdEqualTo(-1L);
			}
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
		int count=likeShareManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");

		List<LikeShare> likeShare=likeShareManager.selectByExample(example);
		List<LikeShareWebModel> list=null;
		list=new ArrayList<LikeShareWebModel>();
		if(likeShare.size()!=0){
			for (LikeShare like : likeShare) {
				LikeShareWebModel model=new LikeShareWebModel();
				model.setId(like.getId());
				model.setTitle(like.getTitle());
				model.setLabel(like.getLabel());
				model.setContent(like.getContent());
				model.setIsUserDelete(like.getIsUserDelete());
				model.setRowAddTime(like.getRowAddTime());
				if(like.getRoomId() != null){
					Room room=roomManager.selectByPrimaryKey(like.getRoomId());
					if (room!=null) {
						model.setRoomNumber(room.getRoomNo());
						model.setUnitNo(room.getUnitNo());
					}
				}
				BizUser bizUser=bizUserManager.selectByPrimaryKey(like.getBuserId());
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
				LikeShareImgExample likeShareImgExample=new LikeShareImgExample();
				likeShareImgExample.or().andLikeShareIdEqualTo(like.getId());
				LikeShareImg likeShareImg=likeShareImgManager.selectOneByExample(likeShareImgExample);
				if (likeShareImg!=null) {
					model.setImgPath(likeShareImg.getImgPathThumbnail());
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
		LikeShare likeShare=likeShareManager.selectByPrimaryKey(id);
		request.setAttribute("likeShare", likeShare);
		LikeShareImgExample example=new LikeShareImgExample();
		example.or().andLikeShareIdEqualTo(id);
		List<LikeShareImg> likeShareImg=likeShareImgManager.selectByExample(example);
		request.setAttribute("likeShareImg", likeShareImg);
		BizUser bizUser=bizUserManager.selectByPrimaryKey(likeShare.getBuserId());
		Room room=roomManager.selectByPrimaryKey(likeShare.getRoomId());
		request.setAttribute("bizUser", bizUser);
		request.setAttribute("room", room);
		return "likeShare/likeShareEdit";
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
			if(likeShareWebModel.getType() == -1){
				throw new PatternException("请选择分类！！！");
			}
			likeShareManager.edit(request, likeShareWebModel, file);
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
	 * 删除爱分享编辑页面图片
	 * 
	 */

	@RequestMapping("/deleteImg")
	@ResponseBody
	public BaseResult deleteImg(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			//删除爱分享图片
			LikeShareImg img=likeShareImgManager.selectByPrimaryKey(id);
			if (img != null) {
				if (img.getImgPath() != null) {
					FileManager.deleteFile(img.getImgPath());
				}
				if (img.getImgPathThumbnail() != null) {
					FileManager.deleteFile(img.getImgPathThumbnail());
				}
				likeShareImgManager.deleteByPrimaryKey(id);
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
			likeShareManager.delete(id);
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
			likeShareRatingManager.deleteByPrimaryKey(id);
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
		return "likeShare/activityComment";
	}

	@RequestMapping("/activityCommentDataGrid")
	@ResponseBody
	public DataGrid activityCommentDataGrid(LikeShareWebModel likeShareWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		LikeShareRatingExample example=new LikeShareRatingExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		com.bola.nwcl.dal.mybatis.model.LikeShareRatingExample.Criteria  c=example.or();
		c.andLikeShareIdEqualTo(likeShareWebModel.getId());
		//通过小区查询
		if(likeShareWebModel.getCommunityId()!=null&&likeShareWebModel.getCommunityId()!=-1&&likeShareWebModel.getBuilding()==-1&&StringUtils.isBlank(likeShareWebModel.getRoomNumber())){
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

		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() != 1){
			likeShareWebModel.setCommunityId(user.getCommunityId());
		}
		//通过小区查询
		if(likeShareWebModel.getCommunityId()!=null&&likeShareWebModel.getCommunityId()!=-1&&(likeShareWebModel.getBuilding()==null||likeShareWebModel.getBuilding()==-1)&&StringUtils.isBlank(likeShareWebModel.getRoomNumber())){
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
		List<LikeShareRating> likeShareRating=likeShareRatingManager.selectByExample(example);
		List<LikeShareWebModel> list=null;
		list=new ArrayList<LikeShareWebModel>();

		if (likeShareRating.size()>0) {
			for (LikeShareRating like : likeShareRating) {
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
		int total=likeShareRatingManager.countByExample(example);
		dg.setTotal(total);
		return dg;
	}

	@RequestMapping("/activityCommentEditPage")
	public String activityCommentEditPage(HttpServletRequest request,Long id){
		LikeShareRating likeShareRating=likeShareRatingManager.selectByPrimaryKey(id);
		request.setAttribute("likeShareRating",likeShareRating);
		return "/likeShare/activityCommentEdit";
	}


	@RequestMapping("/activityCommentEdit")
	@ResponseBody
	public BaseResult activityCommentEdit(HttpServletRequest request,String content,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			LikeShareRating model=new LikeShareRating();
			model.setContent(content);
			model.setId(id);
			likeShareRatingManager.updateByPrimaryKeySelective(model);
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
