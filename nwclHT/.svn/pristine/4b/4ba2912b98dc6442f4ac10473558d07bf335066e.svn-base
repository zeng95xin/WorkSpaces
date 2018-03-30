package com.bola.nwcl.web.controller.indexImg;

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

import com.bola.nwcl.biz.ActivityManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.HonourEnjoyManager;
import com.bola.nwcl.biz.IndexesImgManager;
import com.bola.nwcl.biz.LectureHallIntroducingManager;
import com.bola.nwcl.biz.LectureHallManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseObjectResult;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.FileuploadHelper;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Activity;
import com.bola.nwcl.dal.mybatis.model.ActivityExample;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoy;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyExample;
import com.bola.nwcl.dal.mybatis.model.IndexesImg;
import com.bola.nwcl.dal.mybatis.model.LectureHall;
import com.bola.nwcl.dal.mybatis.model.LectureHallExample;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducing;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducingExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

/**
 * 首页图片管理
 * 
 * @author RJ
 * 
 */
@Controller
@RequestMapping(value = "web/indexImg")
public class IndexImgController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IndexesImgManager indexesImgManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private BizUserManager bizUserManager;
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private HonourEnjoyManager honourEnjoyManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private LectureHallManager lectureHallManager;
	@Autowired
	private ActivityManager activityManager;
	@Autowired
	private  LectureHallIntroducingManager introducingManager;
	

	/**
	 * 跳转图片管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/indexImg/indexImg";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(IndexesImg indexesImg, PageHelper ph, HttpServletRequest request) {
		return indexesImgManager.dataGrid(indexesImg, ph,request);
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		CommunityExample communityExample = new CommunityExample();
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user != null) {
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				communityExample.or().andIdIn(communityIds);
			}
		}
		List<Community> coms = communityManager.selectByExample(communityExample);
		request.setAttribute("Communities", coms);
		return "/indexImg/indexImgAdd";
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		IndexesImg imd = indexesImgManager.selectByPrimaryKey(id);
		CommunityExample communityExample = new CommunityExample();
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user != null) {
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				communityExample.or().andIdIn(communityIds);
			}
		}
		List<Community> coms = communityManager.selectByExample(communityExample);
		request.setAttribute("Communities", coms);
		String url = imd.getUrl();
		if (StringUtils.isNotBlank(url)) {
			if(StringUtils.startsWith(url, "app:")){
				imd.setUrlType(2);
				String [] ss = StringUtils.split(url, ":");
				if (StringUtils.isNotBlank(ss[1])) {
					imd.setAppType(ss[1]);
				}
				if (StringUtils.isNotBlank(ss[2])) {
					Long sid = Long.valueOf(ss[2]);
					if(ss[1].equals("1")){
						LectureHall n = lectureHallManager.selectByPrimaryKey(sid);
						if (n != null) {
							imd.setAppTitle(n.getTitle());
							imd.setAppId(n.getId());
						}
					}
					if(ss[1].equals("2")){
						Activity n = activityManager.selectByPrimaryKey(sid);
						if (n != null) {
							imd.setAppTitle(n.getTitle());
							imd.setAppId(n.getId());
						}
					}
					if(ss[1].equals("3")){
						LectureHallIntroducing n = introducingManager.selectByPrimaryKey(sid);
						if (n != null) {
							imd.setAppTitle(n.getTitle());
							imd.setAppId(n.getId());
						}
					}
					if(ss[1].equals("4")){
						HonourEnjoy n = honourEnjoyManager.selectByPrimaryKey(sid);
						if (n != null) {
							imd.setAppTitle(n.getTitle());
							imd.setAppId(n.getId());
						}
					}
				}
			}else{
				imd.setUrlType(1);
			}
		}
		request.setAttribute("model", imd);
		return "/indexImg/indexImgEdit";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, Integer type, String url, Long communityId, String title,
			@RequestParam("img1") MultipartFile img1,
			@RequestParam("img2") MultipartFile img2) {
		BaseResult baseResult = new BaseResult("SUCCESS","成功");
		String imgDir = "/upload/files/indexImg/";
		try {
			String path1 = null;
			String path2 = null;
			if (communityId == null || communityId < 1) {
				throw new PatternException("请选择小区！");
			}
			if (null != img1) {
				String imageMaxSize = "20";// 允许文件的最大 单位
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName1 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(img1, imageType, imageMaxSize);
				FileName1 = FileUploadDeleteUtil.upload(img1, request, imgDir, FileName1);
				path1 = FileName1;
			}
			if (null != img2) {
				String imageMaxSize = "20";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName2 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(img2, imageType, imageMaxSize);
				FileName2 = FileUploadDeleteUtil.upload(img2, request, imgDir, FileName2);
				path2 =FileName2;
			}
			IndexesImg model = new IndexesImg();
			model.setType(type);
			model.setUrl(url);
			model.setCommunityId(communityId);
			model.setImgPath(path1);
			model.setImgPathThumbnail(path2);
			model.setRowAddTime(new Date());
			model.setTitle(title);
			indexesImgManager.insert(model);			
			
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
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,Long id, Integer type, String url, Long communityId,
			@RequestParam("img1") MultipartFile img1,
			@RequestParam("img2") MultipartFile img2) {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		String imgDir = "/upload/files/indexImg/";
		try {
			String path1 = null;
			String path2 = null;
			if (communityId == null) {
				throw new PatternException("请选择小区！");
			}
			if (null != img1 && !img1.isEmpty()) {
				String imageMaxSize = "20";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName1 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(img1, imageType, imageMaxSize);
				FileName1 = FileUploadDeleteUtil.upload(img1, request, imgDir, FileName1);
				path1 =  FileName1;
			}
			if (null != img2 && !img2.isEmpty()) {
				String imageMaxSize = "20";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName2 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(img2, imageType, imageMaxSize);
				FileName2 = FileUploadDeleteUtil.upload(img2, request, imgDir, FileName2);
				path2 = FileName2;
			}
			IndexesImg model = indexesImgManager.selectByPrimaryKey(id);
			model.setType(type);
			model.setCommunityId(communityId);
			model.setUrl(url);
			if (path1!=null) {
				model.setImgPath(path1);
			}
			if (path2!=null) {
				model.setImgPathThumbnail(path2);
			}
			indexesImgManager.updateByPrimaryKeySelective(model);		
			
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
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request,Long id ) {
		BaseResult baseResult = new BaseResult("SUCCESS","删除成功");		
		try {			
			indexesImgManager.deleteByPrimaryKey(id);			
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
	
	@RequestMapping("/getUrl")
	@ResponseBody
	public BaseObjectResult<Object> getUrl(HttpServletRequest request,Long id, Long communityId) {
		BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>("SUCCESS","成功");		
		try {
			if (id != null) {
				if (id == 1) {
					// 业主讲堂
					LectureHallExample lectureHallExample = new LectureHallExample();
					if (communityId != null) {
						lectureHallExample.or().andCommunityIdEqualTo(communityId);
					}else{
						lectureHallExample.or().andCommunityIdEqualTo(-1L);
					}
					Object ns = lectureHallManager.selectByExample(lectureHallExample);
					baseResult.setResult(ns);
				}
				if (id == 2) {
					// 社区文化
					ActivityExample activityExample = new ActivityExample();
					if (communityId != null) {
						activityExample.or().andCommuntiyIdEqualTo(communityId);
					}else{
						activityExample.or().andCommuntiyIdEqualTo(-1L);
					}
					
					Object ls = activityManager.selectByExample(activityExample);
					baseResult.setResult(ls);
				}
				if (id == 3) {
					// 自荐列表
					LectureHallIntroducingExample introducingExample = new LectureHallIntroducingExample();
					if (communityId != null) {
						List<Long> ids = this.getUserIdByComId(communityId);
						if (ids != null && ids.size() > 0) {
							introducingExample.or().andBuserIdIn(ids);
						}else{
							List<Long> id1s = new ArrayList<>();
							id1s.add(-1L);
							introducingExample.or().andBuserIdIn(id1s);
						}
					}else{
						List<Long> id1s = new ArrayList<>();
						id1s.add(-1L);
						introducingExample.or().andBuserIdIn(id1s);
					}
					Object os = introducingManager.selectByExample(introducingExample);
					baseResult.setResult(os);
				}
				if (id == 4) {
					// 尊享
					HonourEnjoyExample honourEnjoyExample = new HonourEnjoyExample();
					if (communityId != null) {
						honourEnjoyExample.or().andCommunityIdEqualTo(communityId);
					}else{
						honourEnjoyExample.or().andCommunityIdEqualTo(-1L);
					}
					Object hs = honourEnjoyManager.selectByExample(honourEnjoyExample);
					baseResult.setResult(hs);
				}
			}
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseObjectResult<Object>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseObjectResult<Object>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseObjectResult<Object>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseObjectResult<Object>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	// 通过小区ID 得到 用户ID list
	private List<Long> getUserIdByComId(Long comId){
		List<Long> list= new ArrayList<Long>();
		BuildingExample buildingExample=new BuildingExample();
		buildingExample.or().andCommunityIdEqualTo(comId);
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
							for (BizUser bizUser2 : bizUser){
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
