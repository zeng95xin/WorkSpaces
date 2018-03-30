package com.bola.nwcl.web.controller.notify;

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
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.NotifyImgManager;
import com.bola.nwcl.biz.NotifyManager;
import com.bola.nwcl.biz.NotifySendManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseListResult;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.ValidateUtil;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.notify.JPushUtil_Buser;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifyImg;
import com.bola.nwcl.dal.mybatis.model.NotifyImgExample;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.NotifySendExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.web.model.NotifyModelWeb;
import com.bola.nwcl.web.model.TreeModel;

/**
 * 通知管理
 */
@Controller
@RequestMapping(value = "web/notify")
public class NotifyWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NotifyManager notifyManager;
	@Autowired
	private NotifyImgManager notifyImgManager;
	@Autowired
	private NotifySendManager notifysendManager;
	@Autowired
	private BizUserManager bizUserManager;
	@Autowired
	private CommunityManager comManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private RoomManager roomManager;


	/**
	 * 跳转到物业公司页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> coms = comManager.selectByExample(null);
		request.setAttribute("comus", coms);
		return "/notify/notify";
	}

	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Notify notify, PageHelper ph,HttpServletRequest request) {
		return notifyManager.dataGrid(notify, ph,request);

	}

	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = comManager.selectByExample(example_community);
			request.setAttribute("communities", communities);
		}
		return "/notify/notifyAdd";
	}

	/**
	 * 跳转到选择接收人添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPeoplePage")
	public String addPeoplePage(HttpServletRequest request) {

		return "/notify/notifyPeopleAdd";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BaseResult add(NotifyModelWeb model, HttpServletRequest request, String pictures ) {		
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			if(model.getCommuntiyId() == null || model.getCommuntiyId() < 0){
				throw new BusinessDealException("请选择小区");
			}
			String vu=ValidateUtil.checkExcept(model);
			if (StringUtils.isNotEmpty(vu)) {
				throw new PatternException(vu);
			}

			Notify a = new Notify();

			a.setDetail(model.getDetail());
			a.setTitle(model.getTitle());
			a.setType(model.getType()+":-1:-1");
			a.setStatus(1);
			a.setPublishPeopleId(-1l);
			a.setCommunityId(model.getCommuntiyId());
			if (StringUtils.isNotBlank(model.getRecipient())) {
				a.setSendCount(model.getRecipient().split(",").length);
			}else{
				throw new PatternException("请选择接收人");
			}
			notifyManager.insertAndGetId(a);
			Long aid = a.getId();

			String name = model.getRecipient();
			if (StringUtils.isNotBlank(name)) {
				String[] names = name.split(",");
				if (names.length > 0) {
					for (int i = 0; i < names.length; i++) {
						if (StringUtils.isNotBlank(names[i])) {
							BizUserExample bexample = new BizUserExample();
							bexample.or().andIdEqualTo(Long.valueOf(names[i]));
							BizUser user = bizUserManager.selectOneByExample(bexample);
							NotifySend send = new NotifySend();
							if (user != null) {
								send.setBuserId(user.getId());
								send.setNotifyId(aid);
								send.setStatus(1);
								notifysendManager.insert(send);
								
							}
						}
					}
				}
			}else{
				throw new PatternException("请选择接收人");
			}

			// 添加图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						NotifyImg ai = new NotifyImg();
						ai.setNotifyId(aid);
						ai.setImgPath(path);
						ai.setImgPathThumbnail(path);
						ai.setRowAddTime(new Date());
						notifyImgManager.insert(ai);
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
	 * 跳转到标签修改页面(未使用)
	 * 
	 * @return
	 */
	@RequestMapping("/editPage1111111")
	public String editPage(HttpServletRequest request, Long id) {
		Notify n = notifyManager.selectByPrimaryKey(id);
		String[] ss = n.getType().split(":");
		if (StringUtils.isNotBlank(ss[0])) {
			n.setType(ss[0]);
		}
		NotifyImgExample example = new NotifyImgExample();
		example.or().andNotifyIdEqualTo(id);
		List<NotifyImg> a = notifyImgManager.selectByExample(example);
		if (a != null && a.size() > 0) {
			n.setImages(a);
		}
		request.setAttribute("model", n);
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = comManager.selectByExample(example_community);
			request.setAttribute("communities", communities);
		}
		return "/notify/notifyEdit";
	}

	/**
	 * 修改标签（未使用）
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit111111111111")
	@ResponseBody
	public BaseResult edit(NotifyModelWeb model, HttpServletRequest request, String pictures ) {

		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			if(model.getCommuntiyId() == null || model.getCommuntiyId() < 0){
				throw new BusinessDealException("请选择小区");
			}
			Long userid = (Long) request.getSession().getAttribute("userid");
			Notify a = notifyManager.selectByPrimaryKey(model.getId());
			if (a == null) {
				throw new PatternException("通知不存在");
			}
			a.setDetail(model.getDetail());
			a.setTitle(model.getTitle());
			a.setPublishPeopleId(userid);
			String[] ss = a.getType().split(":");
			a.setType("-1:"+ss[1]+":"+ss[2]);
			a.setCommunityId(model.getCommuntiyId());
			notifyManager.updateByPrimaryKeySelective(a);
			Long aid = a.getId();

			String name = model.getRecipient();
			if (name != null) {
				String[] names = name.split(",");
				if (names.length > 0) {
					for (int i = 0; i < names.length; i++) {
						if (StringUtils.isNotBlank(names[i])) {
							BizUserExample bexample = new BizUserExample();
							bexample.or().andIdEqualTo(Long.valueOf(names[i]));
							BizUser user = bizUserManager.selectOneByExample(bexample);
							NotifySend send = new NotifySend();
							if (user != null) {
								send.setBuserId(user.getId());
								send.setNotifyId(aid);
								notifysendManager.insert(send);
							}
						}
					}
				}
			}

			// 添加图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						NotifyImg ai = new NotifyImg();
						ai.setNotifyId(aid);
						ai.setImgPath(path);
						ai.setImgPathThumbnail(path);
						ai.setRowAddTime(new Date());
						notifyImgManager.insert(ai);
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
	 * 删除消息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS","删除成功");
		try {
			Notify img = notifyManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			notifyManager.deleteNotify(id);

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
	 *审核通知
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkFor")
	@ResponseBody
	public BaseResult check(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS","审核成功");
		try {
			Notify img = notifyManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			if (img.getStatus() == 1) {
				throw new PatternException("该信息已审核通过，请勿重复操作！");
			}
			img.setStatus(1);
			notifyManager.updateByPrimaryKeySelective(img);
			
			JPushChatModel jPushChatModel = new JPushChatModel();
			jPushChatModel.setType(img.getType());
			jPushChatModel.setTitle(img.getTitle());
			jPushChatModel.setContent(img.getDetail());
			
			NotifySendExample example_send = new NotifySendExample();
			example_send.or().andNotifyIdEqualTo(id);
			List<NotifySend> list_send = notifysendManager.selectByExample(example_send);
			List<Long> userId_list = new ArrayList<Long>(); 
			for (int i = 0; i < list_send.size(); i++) {
				NotifySend send = list_send.get(i);
				userId_list.add(send.getBuserId());
			}
			BizUserExample example_buser = new BizUserExample();
			example_buser.or().andAcceptPushEqualTo(1).andIdIn(userId_list);
			List<BizUser> list_buser = bizUserManager.selectByExample(example_buser);
			
			JPushUtil_Buser.sendToBuser(jPushChatModel, list_buser);
			
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
	 * 得到用户树，小区 》楼栋 》 用户
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getUserTree", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	private List<TreeModel> tees(HttpServletRequest req) {
		List<TreeModel> models = new ArrayList<TreeModel>();
		// 1 查询所有小区(admin) 小区管理员登录后返回当前小区的数据
		Admin admin = (Admin)req.getSession().getAttribute("loginUser");
		CommunityExample communityExample = new CommunityExample();
		if (admin.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) req.getSession().getAttribute("communityIds");
			communityExample.or().andIdIn(communityIds);
		}
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
						// 3. 查询该楼栋下所有用户
						RoomExample roomExample = new RoomExample();
						roomExample.or().andBuildingIdEqualTo(building.getId());
						List<Room> rooms = roomManager
								.selectByExample(roomExample);
						if (rooms != null && rooms.size() > 0) {
							List<Long> rids = new ArrayList<Long>();
							for (Room rom : rooms) {
								rids.add(rom.getId());
							}
							BizUserExample userExample = new BizUserExample();
							userExample.or().andCurrentRoomIdIn(rids);
							List<BizUser> users = bizUserManager
									.selectByExample(userExample);
							if (users != null && users.size() > 0) {
								List<TreeModel> models3 = new ArrayList<TreeModel>();
								for (BizUser user : users) {
									// 设立三级节点 --- 用户 ---
									TreeModel m3 = new TreeModel();
									m3.setId(user.getId());
									m3.setText(user.getNickname());
									m3.setIconCls("icon-user");
									m3.setId_type("user");
									models3.add(m3);
								}
								m2.setChildren(models3);
							}
						}
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
	public BaseListResult<String> fileuploadf(HttpServletRequest request, @RequestParam("imgs") MultipartFile[] imgs) {
		BaseListResult<String> baseResult = new BaseListResult<String>("SUCCESS","图片上传成功");
		String imgDir = "/upload/files/notify/";
		try {
			List<String> imgsPath = new ArrayList<String>();
			// 酒店图片上传
			if (null != imgs) {
				for (MultipartFile img1 : imgs) {
					String fileName = System.currentTimeMillis() + "";
					String path = FileUploadDeleteUtil.upload(img1, request, imgDir, fileName);
					imgsPath.add(path);
				}
				baseResult.setResult(imgsPath);
			}					
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<String>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
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
			baseResult = new BaseListResult<String>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("上传图片返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/deleteImg")
	@ResponseBody
	public BaseResult deleteImg(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS","图片删除成功");
		try {
			NotifyImg a = notifyImgManager.selectByPrimaryKey(id);
			if (a == null) {
				throw new PatternException("信息不存在！");
			}
			notifyImgManager.deleteByPrimaryKey(id);
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
