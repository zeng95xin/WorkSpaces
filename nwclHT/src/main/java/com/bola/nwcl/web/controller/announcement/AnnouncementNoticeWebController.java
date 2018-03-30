package com.bola.nwcl.web.controller.announcement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.biz.AnnouncementImgManager;
import com.bola.nwcl.biz.AnnouncementManager;
import com.bola.nwcl.biz.AnnouncementModelManager;
import com.bola.nwcl.biz.AnnouncementSendManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseListResult;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.ValidateUtil;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.notify.JPushUtil_Buser;
import com.bola.nwcl.common.util.notify.NotifyUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Announcement;
import com.bola.nwcl.dal.mybatis.model.AnnouncementExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImg;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImgExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementModel;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.web.model.AnnouncementModelWeb;
import com.bola.nwcl.web.model.TreeModel;

/**
 * 通知管理
 */
@Controller
@RequestMapping(value = "web/announcementNotice")
public class AnnouncementNoticeWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AnnouncementManager announcementManager;
	@Autowired
	private AnnouncementImgManager announcementImgManager;
	@Autowired
	private AnnouncementSendManager announcementsendManager;
	@Autowired
	private BizUserManager bizUserManager;
	@Autowired
	private CommunityManager comManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private AnnouncementModelManager modelManager;
	@Autowired
	private NotifyUtil notifyUtil;

	/**
	 * 跳转到通知页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = comManager.selectByExample(example_community);
			request.setAttribute("comus", communities);
		}
		return "/announcement/announcement";
	}
		

	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Announcement announcement, PageHelper ph,
			HttpServletRequest request) {

		return announcementManager.dataGrid(announcement, ph, request);

	}

	/**
	 * 置顶
	 */
	@RequestMapping("/top")
	@ResponseBody
	public BaseResult top(Long id) {
		BaseResult result = new BaseResult("SUCCESS", "置顶成功");
		Announcement a = announcementManager.selectByPrimaryKey(id);
		if (a == null) {
			result.setMessage("公告已经被删除了");
		} else {
			if (a.getIsTop() == 0) {
				AnnouncementExample example = new AnnouncementExample();
				example.or().andIsTopEqualTo(1).andCommunityIdEqualTo(a.getCommunityId());
				int count = announcementManager.countByExample(example);
				if (count >= 3) {
					result.setMessage("小区最多只能有3个置顶公告");
				} else {
					a.setIsTop(1);
					announcementManager.updateByPrimaryKeySelective(a);
				}
			} else {
				result.setMessage("取消置顶成功");
				a.setIsTop(0);
				announcementManager.updateByPrimaryKeySelective(a);
			}
		}
		return result;

	}

	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		// 提前查询出所有的小区
		Admin admin = (Admin) request.getSession().getAttribute("loginUser");
		CommunityExample communityExample = new CommunityExample();
		if(admin.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			communityExample.or().andIdIn(communityIds);
		}
		List<Community> communitys = comManager
				.selectByExample(communityExample);
		request.setAttribute("communitys", communitys);
		List<AnnouncementModel> models = modelManager.selectByExample(null);
		request.setAttribute("models", models);
		return "/announcement/announcementAdd";
	}

	/**
	 * 跳转到选择接收人添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPeoplePage")
	public String addPeoplePage(HttpServletRequest request) {

		return "/announcement/announcementPeopleAdd";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BaseResult add(AnnouncementModelWeb model,
			HttpServletRequest request, String pictures, String communityIds) {

		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {

			Admin users = (Admin) request.getSession().getAttribute("loginUser");
			String vu = ValidateUtil.checkExcept(model);
			if (StringUtils.isNotEmpty(vu)) {
				throw new PatternException(vu);
			}
			if(StringUtils.isBlank(communityIds)){
				throw new PatternException("请选择小区");
			}
			String[] communityIds_str_array = communityIds.split(",");
			List<Long> communityIds_long = new ArrayList<Long>(communityIds_str_array.length);
			for (String string : communityIds_str_array) {
				if(StringUtils.isNoneBlank(string)){
					communityIds_long.add(Long.valueOf(string));
				}
			}
			for (Long commId : communityIds_long) {
				Announcement a = new Announcement();
				a.setDetail(model.getDetail());
				a.setTitle(model.getTitle());
				a.setPublishPeopleId(users.getId());
				a.setStatus(0);
				a.setSummary(model.getSummary());
				a.setIsTop(model.getIsTop());
				a.setPublishPeopleId(-1l);
				a.setCommunityId(commId);
				announcementManager.insertAndGetId(a);
				if (StringUtils.isNotBlank(pictures)) {
					String[] sPath = pictures.split(",");
					if (sPath != null) {
						for (String path : sPath) {
							AnnouncementImg ai = new AnnouncementImg();
							ai.setAnnouncementId(a.getId());
							ai.setImgPath(path);
							ai.setRowAddTime(new Date());
							announcementImgManager.insert(ai);
						}
					}
				}
				// 推送并发出消息
				if (model.getPush() == 1){
					
					BizUserExample example_buser = new BizUserExample();
					example_buser.or().andCommunityIdEqualTo(commId);
					
					List<BizUser> ulist = bizUserManager.selectByExample(example_buser);
					
					if(StringUtils.isNotBlank(model.getPushContent())){
						notifyUtil.send(model.getTitle(), model.getPushContent(), "10:"+a.getId()+":-1", -1L,ulist , "");
					}else{
						notifyUtil.send(model.getTitle(), model.getSummary(), "10:"+a.getId()+":-1", -1L,ulist , "");
					}
					if (model.getPush() == 1 && ulist.size() > 0) {
						JPushChatModel jm = new JPushChatModel();
						jm.setType("10:"+a.getId()+":-1");
						jm.setTitle(model.getTitle());
						jm.setContent(model.getPushContent());
						JPushUtil_Buser.sendToBuser(jm, ulist);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 跳转到标签修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		// 提前查询出所有的小区
		Admin admin = (Admin) request.getSession().getAttribute("loginUser");
		CommunityExample communityExample = new CommunityExample();
		if(admin.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			communityExample.or().andIdIn(communityIds);
		}
		List<Community> communitys = comManager
				.selectByExample(communityExample);
		request.setAttribute("communitys", communitys);
		Announcement a = announcementManager.selectByPrimaryKey(id);
		AnnouncementImgExample announcementImgExample = new AnnouncementImgExample();
		announcementImgExample.or().andAnnouncementIdEqualTo(id);
		List<AnnouncementImg> imgs = announcementImgManager
				.selectByExample(announcementImgExample);
		if (imgs != null && imgs.size() > 0) {
			a.setImages(imgs);
		}
		request.setAttribute("model", a);
		return "/announcement/announcementEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(AnnouncementModelWeb model,
			HttpServletRequest request, String pictures) {

		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			Admin users = (Admin) request.getSession()
					.getAttribute("loginUser");
			Announcement a = new Announcement();
			String vu = ValidateUtil.checkExcept(model);
			if (StringUtils.isNotEmpty(vu)) {
				throw new PatternException(vu);
			}
			if (a == null) {
				throw new PatternException("通知不存在");
			}
			a.setId(model.getId());
			a.setDetail(model.getDetail());
			a.setTitle(model.getTitle());
			a.setPublishPeopleId(users.getId());
			a.setSummary(model.getSummary());
			announcementManager.updateByPrimaryKeySelective(a);
			Long aid = a.getId();

			// 添加图片
			if (StringUtils.isNotBlank(pictures)) {
				String[] sPath = pictures.split(",");
				if (sPath != null) {
					for (String path : sPath) {
						AnnouncementImg ai = new AnnouncementImg();
						ai.setAnnouncementId(aid);
						ai.setImgPath(path);
						ai.setRowAddTime(new Date());
						announcementImgManager.insert(ai);
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 删除童子
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			Announcement img = announcementManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			announcementManager.deleteAnnouncement(id);
			
			
			BizUserExample bizUserExample = new BizUserExample();
			bizUserExample.or().andCommunityIdEqualTo(img.getCommunityId());
			List<BizUser> bizUser = bizUserManager.selectByExample(bizUserExample);
			// 推送
			JPushChatModel jPushChatModel = new JPushChatModel();
			jPushChatModel.setType("-10:"+img.getId()+":-1");
			jPushChatModel.setTitle("您有新的消息");
			jPushChatModel.setContent("您有新的消息");
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
		logger.info("删除图片返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 审核通知
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkFor")
	@ResponseBody
	public BaseResult check(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "审核成功");
		try {
			Announcement img = announcementManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			if (img.getStatus() == 1) {
				throw new PatternException("该信息已审核通过，请勿重复操作！");
			}
			img.setStatus(1);
			announcementManager.updateByPrimaryKeySelective(img);

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
		logger.info("审核返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 可置顶查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkTop")
	@ResponseBody
	public BaseResult checkTop(HttpServletRequest request, String comId) {
		BaseResult baseResult = new BaseResult("SUCCESS", "可置顶");
		try {
			String[] comIdArr = comId.split(",");
			for (String comIdStr : comIdArr) {
				Long comIdLong = Long.valueOf(comIdStr);
				AnnouncementExample example = new AnnouncementExample();
				example.or().andIsTopEqualTo(1).andCommunityIdEqualTo(comIdLong);
				long list = announcementManager.countByExample(example);
				if (list >= 3) {
					Community c = comManager.selectByPrimaryKey(comIdLong);
					throw new PatternException(c.getName() + "置顶数量已达上限！");
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
		logger.info("返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 得到楼栋树，小区 》楼栋
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getBuildingTree", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	private List<TreeModel> tees(HttpServletRequest req, Long communityId) {
		List<TreeModel> models = new ArrayList<TreeModel>();
		// 1 查询所有小区(admin) 小区管理员登录后返回当前小区的数据
		CommunityExample communityExample = new CommunityExample();
		communityExample.or().andIdEqualTo(communityId);
		
		List<Community> coms = comManager.selectByExample(communityExample);
		if (coms != null && coms.size() > 0) {
			for (Community com : coms) {
				// 设立一级父节点 ---- 小区-----
				TreeModel m1 = new TreeModel();
				m1.setId(com.getId());
				m1.setText(com.getName());
				m1.setIconCls("icon-home");
				// 2 查询该小区下所有楼栋
				BuildingExample bExample = new BuildingExample();
				bExample.or().andCommunityIdEqualTo(com.getId());
				List<Building> bus = buildingManager.selectByExample(bExample);
				if (bus != null && bus.size() > 0) {
					List<TreeModel> models2 = new ArrayList<TreeModel>();
					for (Building building : bus) {
						// 设立二级节点 --- 楼栋 ---
						TreeModel m2 = new TreeModel();
						m2.setId(building.getId());
						m2.setText(building.getName());
						m2.setIconCls("icon-building");
						models2.add(m2);
					}
					m1.setChildren(models2);
				}
				models.add(m1);
			}

		}

		return models;

	}

	@RequestMapping(value = "/fileUpload", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BaseListResult<String> fileuploadf(HttpServletRequest request,
			@RequestParam("imgs") MultipartFile[] imgs) {
		BaseListResult<String> baseResult = new BaseListResult<String>(
				"SUCCESS", "图片上传成功");
		String imgDir = "/upload/files/announcement/";
		try {
			List<String> imgsPath = new ArrayList<String>();
			// 图片上传
			if (null != imgs) {
				for (MultipartFile img1 : imgs) {
					
					String fileName = System.currentTimeMillis() + "";
					String path = FileUploadDeleteUtil.upload(img1, request,imgDir, fileName);
					imgsPath.add(path);
				}
				baseResult.setResult(imgsPath);
			}
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<String>(
					CodeEnum.PATTERN_ERROR.getCode(), e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<String>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<String>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseListResult<String>(
					CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("上传图片返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/deleteImg")
	@ResponseBody
	public BaseResult deleteImg(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "图片删除成功");
		try {
			AnnouncementImg a = announcementImgManager.selectByPrimaryKey(id);
			if (a == null) {
				throw new PatternException("信息不存在！");
			}
			announcementImgManager.deleteByPrimaryKey(id);
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
		logger.info("删除图片返回值：{}", baseResult.toString());
		return baseResult;
	}
}
