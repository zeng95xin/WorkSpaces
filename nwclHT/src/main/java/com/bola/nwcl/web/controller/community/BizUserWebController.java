package com.bola.nwcl.web.controller.community;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.AuthorizeCodeManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.AliDaYuSmsUtil;
import com.bola.nwcl.common.util.MD5Utils;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AuthorizeCode;
import com.bola.nwcl.dal.mybatis.model.AuthorizeCodeExample;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.BizUserExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.ServiceClockRoleConfig;
import com.bola.nwcl.web.model.BizUserWebModel;

@Controller
@RequestMapping("/web/BizUserWebController")
public class BizUserWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private BizUserManager bizUserManager;
	@Autowired
	RoomManager roomManager;
	@Autowired
	BizUserRoomManager bizUserRoomManager;
	@Autowired
	AuthorizeCodeManager authorizeCodeManager;

	/**
	 * 
	 * 跳转到住户管理
	 * 
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> coms = communityManager.selectByExample(null);
		request.setAttribute("comus", coms);
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			BuildingExample example = new BuildingExample();
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			example.or().andCommunityIdIn(communityIds);
			List<Building> bs = buildingManager.selectByExample(example);
			request.setAttribute("builings", bs);
		}
		return "/community/bizUser";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, BizUserWebModel bizUserWebModel,
			HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		BizUserExample example = new BizUserExample();
		
		Criteria c = example.or();
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		// 通过小区 / 楼栋查询
		if (bizUserWebModel.getCommunityId() != null && bizUserWebModel.getCommunityId() != -1&& StringUtils.isBlank(bizUserWebModel.getRoomNumber())) {
			List<Long> list =  new ArrayList<Long>();
			if (bizUserWebModel.getBuildingId() != null && bizUserWebModel.getBuildingId() != -1) {
				RoomExample roomExample = new RoomExample();
				roomExample.or().andBuildingIdEqualTo(bizUserWebModel.getBuildingId());
				List<Room> room = roomManager.selectByExample(roomExample);
				if (room.size() != 0) {
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}
			}else{
				BuildingExample buildingExample = new BuildingExample();
				buildingExample.or().andCommunityIdEqualTo(
						bizUserWebModel.getCommunityId());
				List<Building> building = buildingManager
						.selectByExample(buildingExample);
				if (building.size() != 0) {
					for (Building building2 : building) {
						RoomExample roomExample = new RoomExample();
						roomExample.or().andBuildingIdEqualTo(building2.getId());
						List<Room> room = roomManager.selectByExample(roomExample);
						if (room.size() != 0) {
							for (Room room2 : room) {
								list.add(room2.getId());
							}
						}
					}
				}
			}
			if (list.size() > 0) {
				c.andCurrentRoomIdIn(list);
			} else {
				c.andCurrentRoomIdEqualTo(-1L);
			}
		}
		// 单元号
		if (StringUtils.isNotBlank(bizUserWebModel.getOption())
				&& bizUserWebModel.getOption().equals("1")) {
			List<Long> list = null;
			list = new ArrayList<Long>();
			if (bizUserWebModel.getCommunityId() != null && bizUserWebModel.getCommunityId() != -1) {
				BuildingExample buildingExample = new BuildingExample();
				buildingExample.or().andCommunityIdEqualTo(bizUserWebModel.getCommunityId());
				List<Building> building = buildingManager.selectByExample(buildingExample);
				for (Building building2 : building) {
					RoomExample roomExample = new RoomExample();
					roomExample.or().andUnitNoLike("%" + bizUserWebModel.getKey() + "%").andBuildingIdEqualTo(building2.getId());
					List<Room> room = roomManager.selectByExample(roomExample);
					if (room.size() != 0) {
						for (Room room2 : room) {
							list.add(room2.getId());
						}
					}

				}
			} else {
				RoomExample roomExample = new RoomExample();
				roomExample.or().andUnitNoLike("%" + bizUserWebModel.getKey() + "%");
				List<Room> room = roomManager.selectByExample(roomExample);
				if (room.size() != 0) {
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}
			}
			if (list.size() > 0) {
				c.andCurrentRoomIdIn(list);
			} else {
				c.andCurrentRoomIdEqualTo(-1L);
			}
		}
		// 姓名
		if (StringUtils.isNotBlank(bizUserWebModel.getOption())
				&& bizUserWebModel.getOption().equals("2")) {
			c.andNameLike("%" + bizUserWebModel.getKey() + "%");
		}
		// 电话
		if (StringUtils.isNotBlank(bizUserWebModel.getOption())
				&& bizUserWebModel.getOption().equals("3")) {
			c.andPhoneLike("%" + bizUserWebModel.getKey() + "%");
		}
		// 昵称
		if (StringUtils.isNotBlank(bizUserWebModel.getOption())
				&& bizUserWebModel.getOption().equals("4")) {
			c.andNicknameLike("%" + bizUserWebModel.getKey() + "%");
		}
		int total = bizUserManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<BizUserWebModel> list = null;
		list = new ArrayList<BizUserWebModel>();
		List<BizUser> bizUser = bizUserManager.selectByExample(example);
		if (bizUser.size() > 0) {
			for (BizUser bizUser2 : bizUser) {
				BizUserWebModel model = new BizUserWebModel();
				BizUser bi = bizUserManager
						.selectByPrimaryKey(bizUser2.getId());
				if (bi != null) {
					model.setId(bi.getId());
					model.setName(bi.getName());
					model.setPhone(bi.getPhone());
					model.setHeadPortrait(bi.getHeadPortrait());
					model.setNickname(bi.getNickname());
					model.setSex(bi.getSex());
					model.setPoint(bi.getPoint());
				}
				Room room = roomManager.selectByPrimaryKey(bizUser2
						.getCurrentRoomId());
				if (room != null) {
					model.setUnitNo(room.getUnitNo());
				}
				BizUserRoomExample bizUserRoomExample = new BizUserRoomExample();
				bizUserRoomExample.or().andBizUserIdEqualTo(bizUser2.getId())
						.andRoomIdEqualTo(bizUser2.getCurrentRoomId());
				BizUserRoom bizUserRoom = bizUserRoomManager
						.selectOneByExample(bizUserRoomExample);
				if (bizUserRoom != null) {
					model.setOwner(bizUserRoom.getType());
				}
				list.add(model);
			}
		}
		
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 
	 * 跳转到编辑页面
	 * 
	 */
	@RequestMapping("/editPage")
	public String editPage(Long id, HttpServletRequest request) {
		BizUser user = bizUserManager.selectByPrimaryKey(id);
		request.setAttribute("bizUser", user);
		Room room = roomManager.selectByPrimaryKey(user.getCurrentRoomId());
		request.setAttribute("room", room);
		return "community/bizUserEdit";
	}
	
	/**
	 * 
	 * 跳转到编辑页面
	 * 
	 */
	@RequestMapping("/addBUserPage")
	public String addBUserPage(Long id, HttpServletRequest request) {
		BizUser user = bizUserManager.selectByPrimaryKey(id);
		request.setAttribute("bizUser", user);
		return "community/BUserAdd";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, String Pwd,
			String nickname,String nam,String sex, Long id, @RequestParam("file") MultipartFile file) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			bizUserManager.edit(request, Pwd, nickname,nam,sex, id, file);
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
	
	@RequestMapping("/addBUser")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, BizUser buser, String relation, Long bizUserId, @RequestParam("file") MultipartFile file) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			BizUser b = bizUserManager.selectByPrimaryKey(bizUserId);
			if (b == null) {
				throw new PatternException("业主不存在！");
			}
			BizUser user = new BizUser();
			if (!file.isEmpty()) {
				//定义一个 图片相对项目放的位置
				String uploadPath =  "/upload" + "/files/headPortrait";
				String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String fileName = UUID.randomUUID().toString().substring(0, 12);
				fileName = todayStr + fileName;
				String savePath = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
				user.setHeadPortrait(savePath);
			}
			user.setPassword(MD5Utils.format(buser.getPassword()));
			user.setName(buser.getName());
			user.setNickname(buser.getNickname());
			user.setSex(buser.getSex());
			user.setAcceptPush(1);
			user.setCurrentRoomId(buser.getCurrentRoomId());
			user.setPhone(buser.getPhone());
			user.setPoint(0);
			user.setStatus((short)1);
			user.setCommunityId(b.getCommunityId());
			user.setRowAddTime(new Date());
			bizUserManager.insertAndGetId(user);
			
			BizUserRoom ur = new BizUserRoom();
			ur.setBizUserId(user.getId());
			ur.setAuthorizeUserId(bizUserId);
			ur.setRoomId(buser.getCurrentRoomId());
			ur.setType(relation);
			ur.setStatus(0);
			bizUserRoomManager.insert(ur);
			
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
	 * 
	 * 跳转到生成授权码页面
	 * 
	 */
	@RequestMapping("/codePage")
	public String codePage(HttpServletRequest request) {
		return "/community/codePage";
	}

	@RequestMapping("/codePage2")
	public String codePage2(HttpServletRequest request) {
		String str_info = request.getSession().getAttribute("str_info")
				.toString();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] str = str_info.split(",");
		for (String s : str) {
			String[] ss = s.split(":");
			Map<String, String> map = new HashMap<String, String>();
			map.put("roomStr", ss[0]);
			map.put("id", ss[1]);
			list.add(map);

		}
		request.setAttribute("list", list);
		request.getSession().removeAttribute("str_info");
		return "/community/codePage2";
	}

	@RequestMapping("/updateInfo")
	@ResponseBody
	public BaseResult updateInfo(HttpServletRequest request, String msg) {
		BaseResult baseResult = new BaseResult("SUCCESS", "生成成功");
		try {
			if (StringUtils.isBlank(msg)) {
				throw new PatternException("输入信息为空");
			}
			baseResult = bizUserManager.updateInfoByWeb(msg);
			request.getSession().setAttribute("str_info",
					baseResult.getMessage());
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
		logger.info("生成授权码修改返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/generate2")
	@ResponseBody
	public BaseResult generate2(HttpServletRequest request, Long roomId,
			String type,String phone,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date validTime_) {
		BaseResult baseResult = new BaseResult("SUCCESS", "生成成功");
		try {
			if (StringUtils.isBlank(type)) {
				throw new PatternException("请选择授权类型！！！");
			}
			/*if (validTime == null) {
				throw new PatternException("有效时间不能为空！！！");
			}*/
			Date validTime = new Date(new Date().getTime()+1000*60*60l);
			String code = this.getCharAndNumr(8);
			AuthorizeCodeExample authorizeCodeExample = new AuthorizeCodeExample();
			authorizeCodeExample.or().andAuthorizeCodeEqualTo(code);
			List<AuthorizeCode> authorizeCode = authorizeCodeManager
					.selectByExample(authorizeCodeExample);
			if("01".equals(type)){
				type = "租客";
			} else if("02".equals(type)){
				type = "家属";
			}
			if (authorizeCode.size() > 0) {
				baseResult = generate2(request, roomId, type, phone, validTime);
			} else {
				AuthorizeCode model = new AuthorizeCode();
				model.setAuthorizeCode(code);
				model.setType(type);
				model.setValidTime(validTime);
				model.setAuthorizeUserId(-1l);
				model.setStatus(0);
				model.setRoomids(roomId+"");
				authorizeCodeManager.insert(model);
				baseResult.setMessage(code);
			}
			
			String smsParam="{code:\""+code+"\",product:\"i回家\"}";
			List<String> list = new ArrayList<String>(1);
			list.add(phone);
			AliDaYuSmsUtil.sendSms(list, smsParam, AliDaYuSmsUtil.smsTemplateCode_reg_buser, "注册验证");

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
		logger.info("生成授权码修改返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/generate")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, Long userId,
			String type,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date validTime) {
		BaseResult baseResult = new BaseResult("SUCCESS", "生成成功");
		try {
			if (userId == null) {
				throw new PatternException("请选择业主！！！");
			}
			if (StringUtils.isBlank(type)) {
				throw new PatternException("请选择授权类型！！！");
			}
			if (validTime == null) {
				throw new PatternException("有效时间不能为空！！！");
			}
			String code = this.getCharAndNumr(8);
			AuthorizeCodeExample authorizeCodeExample = new AuthorizeCodeExample();
			authorizeCodeExample.or().andAuthorizeCodeEqualTo(code);
			List<AuthorizeCode> authorizeCode = authorizeCodeManager
					.selectByExample(authorizeCodeExample);
			if (authorizeCode.size() > 0) {
				this.edit(request, userId, type, validTime);
			} else {
				AuthorizeCode model = new AuthorizeCode();
				model.setAuthorizeCode(code);
				model.setType(type);
				model.setValidTime(validTime);
				model.setAuthorizeUserId(userId);
				model.setStatus(0);
				authorizeCodeManager.insert(model);
				baseResult.setMessage(code);
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
		logger.info("生成授权码修改返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 
	 * 生成随机字符串
	 * 
	 */

	public String getCharAndNumr(int length) {
		String val = "";

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}

		return val;
	}
	
	@RequestMapping("/getBuilding")
	@ResponseBody
	public List<Building> getBuilding(HttpServletRequest request, Long communityId) {
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		BuildingExample example_building = new BuildingExample();
		example_building.or().andCommunityIdEqualTo(communityId);
		List<Building> buildings = buildingManager.selectByExample(example_building); 
		return buildings;
	}


	/**
	 * 
	 * 跳转新增住户管理
	 * 
	 */
	@RequestMapping("/addManager")
	public String addManager(HttpServletRequest request) {
		List<Community> coms = communityManager.selectByExample(null);
		request.setAttribute("comus", coms);
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			BuildingExample example = new BuildingExample();
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			example.or().andCommunityIdIn(communityIds);
			List<Building> bs = buildingManager.selectByExample(example);
			request.setAttribute("builings", bs);
		}
		List<BizUser> bizUser = bizUserManager.selectByExample(null);
		request.setAttribute("bizUser", bizUser);
		return "/community/bizUserAdd";
	}
	
	/**
	 * 
	 * 跳转新增住户管理
	 * 
	 */
	@RequestMapping("/delete")
		public BaseResult delete(HttpServletRequest request, Long id) {
			BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
			try {
				BizUser bizUser = new BizUser();
				bizUser.setId(id);
				BizUser bizUser2 = bizUserManager.selectByPrimaryKey(id);
				BizUserRoomExample bizUserRoomExample = new BizUserRoomExample();
				bizUserRoomExample.or().andBizUserIdEqualTo(bizUser2.getId())
				.andRoomIdEqualTo(bizUser2.getCurrentRoomId());
				bizUserRoomManager.deleteByExample(bizUserRoomExample);
				Room room = roomManager.selectByPrimaryKey(bizUser2.getCurrentRoomId());
				roomManager.deleteByPrimaryKey(room.getId());
				bizUserManager.deleteByPrimaryKey(id);
			} catch (PatternException e) {
				logger.info("参数校验失败,{}", e.getMessage());
				baseResult = new BaseResult(CodeEnum.PATTERN_ERROR.getCode(), e.getMessage());
			} catch (BusinessValidateException e) {
				logger.info("业务验证异常， 错误信息：{}", e.getMessage());
				baseResult = new BaseResult(CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
			} catch (BusinessDealException e) {
				logger.info("业务处理异常， 错误信息：{}", e.getMessage());
				baseResult = new BaseResult(CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("系统异常，{}", e.getMessage());
				baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
			}
			logger.info("删除返回值：{}", baseResult.toString());
			return baseResult;
		}
	
	
}
