package com.bola.nwcl.web.controller.introducing;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.LectureHallIntroducingImgManager;
import com.bola.nwcl.biz.LectureHallIntroducingManager;
import com.bola.nwcl.biz.LectureHallManager;
import com.bola.nwcl.biz.LectureShareItemManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.global.Configure;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.LectureHall;
import com.bola.nwcl.dal.mybatis.model.LectureHallExample;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducing;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingExample;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingImg;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingImgExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.web.model.Introducing;

/**
 * 业主自荐
 */
@RequestMapping("/web/IntroducingWebController")
@Controller
public class IntroducingWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CommunityManager communityManager;
	@Autowired
	LectureHallIntroducingManager lectureHallIntroducingManager;
	@Autowired
	LectureHallIntroducingImgManager lectureHallIntroducingImgManager;
	@Autowired
	LectureShareItemManager lectureShareItemManager;
	@Autowired
	BuildingManager buildingManager;
	@Autowired
	RoomManager roomManager;
	@Autowired
	BizUserManager bizUserManager;
	@Autowired
	private LectureHallManager lectureHallManager;

	/**
	 * 跳转到业主自荐页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request) {
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		request.setAttribute("user", user);
		// 提前查询出所有的小区
		List<Community> community = communityManager.selectByExample(null);
		request.setAttribute("community", community);
			
			return "introducing/introducing";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Introducing introducing, PageHelper ph,
			HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		LectureHallIntroducingExample example = new LectureHallIntroducingExample();
		
		Criteria c = example.or();
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		// 通过小区查询
		if (introducing.getCommunityId() != null
				&& introducing.getCommunityId() != -1) {
			List<Long> list = null;
			list = new ArrayList<Long>();
			BuildingExample buildingExample = new BuildingExample();
			buildingExample.or().andCommunityIdEqualTo(
					introducing.getCommunityId());
			List<Building> building = buildingManager
					.selectByExample(buildingExample);
			if (building.size() != 0) {
				for (Building building2 : building) {
					RoomExample roomExample = new RoomExample();
					roomExample.or().andBuildingIdEqualTo(building2.getId());
					List<Room> room = roomManager.selectByExample(roomExample);
					if (room.size() != 0) {
						for (Room room2 : room) {
							BizUserExample bizUserExample = new BizUserExample();
							bizUserExample.or().andCurrentRoomIdEqualTo(
									room2.getId());
							List<BizUser> bizUser = bizUserManager
									.selectByExample(bizUserExample);
							if (bizUser.size() != 0) {
								for (BizUser bizUser2 : bizUser) {
									list.add(bizUser2.getId());
								}
							}
						}
					}
				}
			}
			if (list.size() > 0) {
				c.andBuserIdIn(list);
			} else {
				c.andBuserIdEqualTo(-1L);
			}
		}
		// 选项查询
		// 标题
		if (StringUtils.isNotBlank(introducing.getOption())
				&& introducing.getOption().equals("1")) {
			c.andTitleLike("%" + introducing.getKey() + "%");
		}
		// 内容
		if (StringUtils.isNotBlank(introducing.getOption())
				&& introducing.getOption().equals("2")) {
			c.andContentLike("%" + introducing.getKey() + "%");
		}
		// 姓名
		if (StringUtils.isNotBlank(introducing.getOption())
				&& introducing.getOption().equals("3")) {
			c.andNameLike("%" + introducing.getKey() + "%");
		}
		// 电话
		if (StringUtils.isNotBlank(introducing.getOption())
				&& introducing.getOption().equals("4")) {
			c.andPhoneLike("%" + introducing.getKey() + "%");
		}
		// 发布时间
		if (StringUtils.isNotBlank(introducing.getOption())
				&& introducing.getOption().equals("5")) {
			Date addTime = DateUtils.strToDate(introducing.getKey(),
					"yyyy-MM-dd");
			c.andRowAddTimeBetween(addTime, new Date(addTime.getTime() + 1000
					* 60 * 60 * 24));
		}
		int count = lectureHallIntroducingManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<LectureHallIntroducing> lectureHallIntroducing = lectureHallIntroducingManager
				.selectByExample(example);
		List<Introducing> list = null;
		list = new ArrayList<Introducing>();
		if (lectureHallIntroducing.size() > 0) {
			for (LectureHallIntroducing le : lectureHallIntroducing) {
				LectureHallIntroducingImgExample leExample = new LectureHallIntroducingImgExample();
				leExample.or().andLectureHallIntroducingIdEqualTo(le.getId());
				// 这里只取了一张图片
				LectureHallIntroducingImg leImg = lectureHallIntroducingImgManager
						.selectOneByExample(leExample);
				LectureHallIntroducing model = lectureHallIntroducingManager
						.selectByPrimaryKey(le.getId());
				Introducing in = new Introducing();
				if (model != null) {
					in.setId(model.getId());
					in.setTitle(model.getTitle());
					in.setContent(model.getContent());
					in.setDescription(model.getDescription());
					in.setName(model.getName());
					in.setPhone(model.getPhone());
					in.setRowAddTime(model.getRowAddTime());
				}
				if (leImg != null) {
					if (leImg.getImgPathThumbnail() != null) {
						in.setImgPath(leImg.getImgPathThumbnail());
					}
				}

				list.add(in);
			}
		}
		dg.setRows(list);
		
		dg.setTotal(count);
		return dg;
	}
	
	
	@RequestMapping("/introducingDetails")
	public String introducingDetails(HttpServletRequest request,Long id){
		LectureHallIntroducing lecture=lectureHallIntroducingManager.selectByPrimaryKey(id);
		LectureHallIntroducingImgExample example = new LectureHallIntroducingImgExample();
		example.or().andLectureHallIntroducingIdEqualTo(id);
		List<LectureHallIntroducingImg> list=lectureHallIntroducingImgManager.selectByExample(example);
		request.setAttribute("lecture", lecture);
		request.setAttribute("lectureHallIntroducingImg", list);
		return "introducing/introducingDetails";
	}

	/**
	 * 
	 * 自荐说明跳转页面
	 * 
	 */
	@RequestMapping("/explain")
	public String explain(HttpServletRequest request) {
		/*List<LectureShareItem> list = lectureShareItemManager.selectByExample(null);
		request.setAttribute("itemList", list);*/
		String path = Configure.getRootUrl()+"/upload/files/introducing/introducingImg.jpg";
		request.setAttribute("paht", path);
		return "introducing/explain";
	}
	
	/**
	 * 
	 *  发布到业主讲堂跳转页面
	 * 
	 */
	@RequestMapping("/IntroducingSendPage")
	public String IntroducingSendPage(HttpServletRequest request,Long id) {
		LectureHallIntroducing lectureHallIntroducing = lectureHallIntroducingManager.selectByPrimaryKey(id);
		request.setAttribute("lectureHallIntroducing", lectureHallIntroducing);
		//提前查询出所有的小区
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		return "introducing/introducingSend";
	}
	
	/**
	 * 
	 * 验证是否发布过
	 * 
	 */
	
	@RequestMapping("/IntroducingSendY")
	@ResponseBody
	public BaseResult IntroducingSendY(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "验证成功");
		try {
			LectureHallExample example = new LectureHallExample();
			example.or().andIntroducingIdEqualTo(id);
			LectureHall lectureHall = lectureHallManager.selectOneByExample(example);
			if(lectureHall != null){
				throw new PatternException("该信息已经发布到业主讲堂了，请勿重复操作！！！");
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
		logger.info("验证返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	
	/**
	 * 
	 * 发布到业主讲堂
	 *
	 */
	
	@RequestMapping("/IntroducingSend")
	@ResponseBody
	public BaseResult IntroducingSend(HttpServletRequest request,Long id,Long communityId,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date activityEndTime,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date endTime) {
		BaseResult baseResult = new BaseResult("SUCCESS", "发布成功");
		try {
				if(communityId == null){
					throw new PatternException("请选择小区！！！");
					
				}
				if(endTime == null){
					throw new PatternException("请选择报名截止时间！！！");
					
				}
				if(activityEndTime == null){
					throw new PatternException("请选择活动结束时间！！！");
					
				}
				

					Admin user = (Admin)request.getSession().getAttribute("loginUser");
					//判断是普通管理员还是admin(普通管理员给出当前小区id)
					if(user.getRoleId() != 1){
						communityId = user.getCommunityId();
					}
					LectureHallIntroducing lectureHallIntroducing = lectureHallIntroducingManager.selectByPrimaryKey(id);
					
					LectureHall model = new LectureHall();
					model.setCommunityId(communityId);
					model.setPublishId(user.getId());
					if(lectureHallIntroducing !=null){
						LectureHallIntroducingImgExample leExample = new LectureHallIntroducingImgExample();
						leExample.or().andLectureHallIntroducingIdEqualTo(lectureHallIntroducing.getId());
						// 这里只取了一张图片
						LectureHallIntroducingImg leImg = lectureHallIntroducingImgManager.selectOneByExample(leExample);
						model.setIntroducingId(lectureHallIntroducing.getId());
						if(leImg != null){
							model.setImgPath(leImg.getImgPath());
						}
						model.setTitle(lectureHallIntroducing.getTitle());
						model.setContent(lectureHallIntroducing.getContent());
					}
					model.setActivityEndTime(activityEndTime);
					model.setEndTime(endTime);
					lectureHallManager.insert(model);
			
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
			lectureHallIntroducingManager.deleteByPrimaryKey(id);
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
	
	//修改自荐说明图片
	
	@RequestMapping("/editImg")
	@ResponseBody
	public String editImg(MultipartFile file,HttpServletRequest request){
		String path = Configure.getRootPath()+"/upload/files/introducing/introducingImg.jpg";
		try {
			file.transferTo(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Configure.getRootUrl()+"/upload/files/introducing/introducingImg.jpg";
	}

}
