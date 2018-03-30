package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.api.model.AuthorizeUserModel;
import com.bola.nwcl.api.model.BizUserCouponModel;
import com.bola.nwcl.api.model.BizUserLingLingModel;
import com.bola.nwcl.api.model.RoomModel;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.AliDaYuSmsUtil;
import com.bola.nwcl.common.util.MD5Utils;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.lingling.impl.LingLingUtil;
import com.bola.nwcl.common.util.property.PropertyValidate;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BerificationCodeMapper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserRoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuserCouponMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.LinglingOwnerMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomPhoneMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AuthorizeCode;
import com.bola.nwcl.dal.mybatis.model.BerificationCode;
import com.bola.nwcl.dal.mybatis.model.BerificationCodeExample;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.BizUserExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.BuserCouponExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.LinglingOwner;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.RoomPhone;
import com.bola.nwcl.dal.mybatis.model.RoomPhoneExample;
import com.bola.nwcl.web.model.Tree;

@Service
public class BizUserManagerImpl extends
		ManagerImpl<BizUser, BizUserExample, Long> implements BizUserManager {

	private BizUserMapper bizUserMapper;

	@Autowired
	private BuserCouponMapper buserCouponMapper;

	@Autowired
	private RoomPhoneMapper roomPhoneMapper;

	@Autowired
	private BerificationCodeMapper berificationCodeMapper;

	@Autowired
	private BuildingMapper buildingMapper;

	@Autowired
	private CommunityMapper communityMapper;

	@Autowired
	private RoomMapper roomMapper;

	@Autowired
	private BizUserRoomMapper bizUserRoomMapper;

	@Autowired
	private LingLingUtil lingLingUtil;

	@Autowired
	private LinglingOwnerMapper linglingOwnerMapper;

	@Autowired
	public BizUserManagerImpl(BizUserMapper bizUserMapper) {
		this.mapper = bizUserMapper;
		this.bizUserMapper = bizUserMapper;
	}

	@Override
	public List<RoomModel> getUserRooms(String phone) {
		return bizUserMapper.getUserRooms(phone);
	}

	@Override
	public List<RoomModel> getUserActiveRooms(long id) {
		return bizUserMapper.getUserActiveRooms(id);
	}

	@Override
	public void insertAndGetId(BizUser bizUser) {
		bizUserMapper.insertAndGetId(bizUser);
	}

	@Override
	public List<RoomModel> getUserAllRooms(long id) {
		return bizUserMapper.getUserAllRooms(id);
	}

	@Override
	public List<AuthorizeUserModel> getAllAuthorizeUser(long buserId) {
		return bizUserMapper.getAllAuthorizeUser(buserId);
	}

	@Override
	public Page<BizUserCouponModel> getUserAllCoupon(long buserId, int page,
			int rows, Integer type) {

		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<BizUserCouponModel> list = null;

		Map<String, Object> condition = new HashMap<String, Object>(3);
		condition.put("buserId", buserId);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());

		if (type == null || (type.intValue() != 0 && type.intValue() != 1)) {
			condition.put("condition", -1);
		} else if (type.intValue() == 0) {
			condition.put("condition", 0);
		} else if (type.intValue() == 1) {
			condition.put("condition", 1);
		}
		list = bizUserMapper.getUserAllCoupon(condition);

		BuserCouponExample example = new BuserCouponExample();
		example.or().andBuserIdEqualTo(buserId);
		count = buserCouponMapper.countByExample(example);

		Page<BizUserCouponModel> pageData = new PageImpl<BizUserCouponModel>(
				list, pager, count);

		return pageData;
	}

	@Override
	public int getCouponUnusedCount(long id) {
		return buserCouponMapper.getCouponUnusedCount(id);
	}

	@Override
	public DataGrid dataGrid(BizUser user, PageHelper ph, HttpServletRequest request) {

		DataGrid dg = new DataGrid();
		BizUserExample example = new BizUserExample();
		
		Criteria c = example.or();
		if (user != null) {
			if (user.getStatus() != null) {
				c.andStatusEqualTo(user.getStatus());
			}
			if (StringUtils.isNotBlank(user.getNickname())
					&& StringUtils.isNotBlank(user.getName())) {
				if (user.getNickname().equals("phone")) {
					c.andPhoneLike("%" + user.getName() + "%");
				}
				if (user.getNickname().equals("sex")) {
					c.andSexEqualTo(user.getName());
				}
				if (user.getNickname().equals("rowAddTime")) {
					Date date = new Date(user.getName());
					c.andRowAddTimeEqualTo(date);
				}
			}
		}
		
		Admin auser = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (auser.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		
		int total = bizUserMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<BizUser> list = bizUserMapper.selectByExample(example);
		dg.setRows(list);
		
		dg.setTotal(total);

		return dg;
	}

	@Override
	public void updateSendBerificationCode(HttpServletRequest request,
			String phone, String type) throws Exception {
		if (StringUtils.isBlank(type)) {
			throw new PatternException("发送类型不能为空");
		}
		if (StringUtils.isBlank(phone)) {
			throw new PatternException("发送手机不能为空");
		}
		BerificationCode b_code = new BerificationCode();
		if ("1".equals(type)) {
			BizUserExample example_buser = new BizUserExample();
			example_buser.or().andPhoneEqualTo(phone);

			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$");
			Matcher m = p.matcher(phone);
			if (!m.find()) {
				throw new PatternException("该手机号码格式错误");
			}

			List<BizUser> list_buser = bizUserMapper
					.selectByExample(example_buser);
			if (list_buser != null && list_buser.size() > 0) {
				throw new BusinessValidateException("该手机号码已经注册");
			}

			String str = PropertyValidate.validate(phone);
			if (StringUtils.isBlank(str)) {
				throw new BusinessValidateException("该手机号码不是系统预留的业主手机");
			}

			JSONArray j_array = JSONArray.parseArray(str);

			// {"ID": 1,"UnitNO": "ZQ-B2-1602",单元编号
			// {"ID": 1,"UnitNO": "DF-CK-B1-1103",单元编号
			// "UnitType": 0,物业单元类型(0-住宅;1-车位;2-商铺）
			// "UnitDesc":null,物业单元备注
			// "PropertyID": 4013,物业小区编号
			// "PropertyName": "伴月花园"}物业小区名称
			for (int i = 0; i < j_array.size(); i++) {
				JSONObject j = j_array.getJSONObject(i);
				String PropertyName = j.getString("PropertyName");
				String UnitNO = j.getString("UnitNO");
				String UnitType = j.getString("UnitType");
				String UnitDesc = j.getString("UnitDesc");
				String[] nos = UnitNO.split("-");
				if (nos.length < 3) {
					throw new BusinessValidateException("房间读取错误,请联系管理员");
				}
				String community_serial = nos[0];
				String building_str = nos[1];
				String floor_str, room_no_str;
				if (nos.length > 3) {
					floor_str = nos[2];
					room_no_str = nos[3];
				} else {
					if (nos[2].length() > 3) {
						floor_str = nos[2].substring(0, 2);
						room_no_str = nos[2].substring(2);
					} else {
						floor_str = nos[2].substring(0, 1);
						room_no_str = nos[2].substring(1);
					}
				}
				CommunityExample example_community = new CommunityExample();
				example_community.or().andSerialEqualTo(community_serial)
						.andNameEqualTo(PropertyName);

				List<Community> list_community = communityMapper
						.selectByExample(example_community);
				Community community = null;
				if (list_community == null || list_community.size() < 1) {
					community = new Community();
					community.setSerial(community_serial);
					community.setName(PropertyName);
					communityMapper.insertAndGetId(community);
					try {
						lingLingUtil.addResidential(community.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					community = list_community.get(0);
				}
				BuildingExample example_building = new BuildingExample();
				example_building.or().andNameEqualTo(building_str);
				List<Building> list_building = buildingMapper
						.selectByExample(example_building);
				Building building = new Building();
				if (list_building == null || list_building.size() < 1) {
					building = new Building();
					building.setCommunityId(community.getId());
					building.setName(building_str);
					buildingMapper.insertAndGetId(building);
				} else {
					building = list_building.get(0);
				}

				RoomExample example_room = new RoomExample();
				example_room.or().andBuildingIdEqualTo(building.getId())
						.andFloorEqualTo(floor_str).andUnitEqualTo(UnitType)
						.andRoomNoEqualTo(room_no_str).andUnitNoEqualTo(UnitNO);
				List<Room> list_room = roomMapper.selectByExample(example_room);
				Room room = null;
				if (list_room == null || list_room.size() < 1) {
					room = new Room();
					room.setBuildingId(building.getId());
					room.setFloor(floor_str);
					room.setUnit(UnitType);
					room.setRoomNo(room_no_str);
					room.setNote(UnitDesc);
					room.setUnitNo(UnitNO);
					roomMapper.insertAndGetId(room);
				} else {
					room = list_room.get(0);
				}
				RoomPhoneExample example_room_phone = new RoomPhoneExample();
				example_room_phone.or().andRoomIdEqualTo(room.getId())
						.andPhoneEqualTo(phone);
				List<RoomPhone> list_room_phone = roomPhoneMapper
						.selectByExample(example_room_phone);
				if (list_room_phone == null || list_room_phone.size() < 1) {
					RoomPhone room_phone = new RoomPhone();
					room_phone.setPhone(phone);
					room_phone.setRoomId(room.getId());
					roomPhoneMapper.insert(room_phone);
				}
			}
			b_code.setType((short) 1);
		} else if ("2".equals(type)) {
			BizUserExample example_buser = new BizUserExample();
			example_buser.or().andPhoneEqualTo(phone);
			List<BizUser> list_buser = bizUserMapper
					.selectByExample(example_buser);
			if (list_buser == null || list_buser.size() < 1) {
				throw new BusinessValidateException("该手机号码还未注册住户");
			}
			b_code.setType((short) 2);
			b_code.setBizUserId(list_buser.get(0).getId());
		} else if ("3".equals(type)) {
			Object obj_code = request.getSession()
					.getAttribute("authorizeCode");
			if (obj_code == null) {
				throw new BusinessValidateException("请先验证授权码");
			}
			BizUserExample example_buser = new BizUserExample();
			example_buser.or().andPhoneEqualTo(phone);
			List<BizUser> list_buser = bizUserMapper
					.selectByExample(example_buser);
			if (list_buser != null && list_buser.size() > 0) {
				throw new BusinessValidateException("该手机号码已经注册");
			}
			AuthorizeCode code = (AuthorizeCode) request.getSession()
					.getAttribute("authorizeCode");
			b_code.setBizUserId(code.getAuthorizeUserId());
			b_code.setType((short) 3);
		} else {
			throw new BusinessValidateException("验证码类型错误");
		}
		BerificationCodeExample example_bc = new BerificationCodeExample();
		Date begin = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(begin);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		begin = c.getTime();
		Date end = new Date(begin.getTime() + 1000 * 60 * 60 * 24);
		example_bc.or().andPhoneEqualTo(phone)
				.andTypeEqualTo(Short.valueOf(type))
				.andRowAddTimeBetween(begin, end);
		int count = berificationCodeMapper.countByExample(example_bc);
		if (count >= 5000) {
			throw new BusinessDealException(
					"每个手机每天只能发送5000次注册或者5000次修改密码的验证码,请明天再来");
		}
		b_code.setPhone(phone);
		String code = String.valueOf(RandomUtils.nextInt(1000, 9999));
		b_code.setBerificationCode(code);
		b_code.setValidTime(new Date(System.currentTimeMillis() + 1000 * 60 * 5));
		b_code.setStatus(0);
		berificationCodeMapper.insert(b_code);
		request.getSession().setAttribute("b_code", b_code);
		List<String> list_phones = new ArrayList<String>(1);
		list_phones.add(phone);
		// list_phones.add("13527471462");
		System.out.println(code);
		String smsParam = "{code:\"" + code + "\",product:\"i回家\"}";
		// SmsUtils.sendSms(list_phones, "您好，您的验证码是" + code);
		if (type.equals("2")) {
			AliDaYuSmsUtil.sendSms(list_phones, smsParam,
					AliDaYuSmsUtil.smsTemplateCode_fingpwd, "变更验证");
		} else {
			AliDaYuSmsUtil.sendSms(list_phones, smsParam,
					AliDaYuSmsUtil.smsTemplateCode_reg_buser, "注册验证");
		}
	}

	@Override
	public BizUser updateRegister(HttpServletRequest request, String password,
			String room_id, String nickname, String type) throws Exception {
		if (StringUtils.isBlank(password)) {
			throw new PatternException("密码不能为空");
		}
		if (StringUtils.isBlank(room_id)) {
			throw new PatternException("至少绑定一个房间");
		}
		if (StringUtils.isBlank(type)) {
			throw new PatternException("注册类型不能为空");
		}
		if (!"1".equals(type) && !"2".equals(type) && !"3".equals(type)) {
			throw new PatternException("type只能为1,2,3");
		}
		if (request.getSession().getAttribute("list_rooms") == null) {
			throw new BusinessDealException("请先验证验证码");
		}
		String phone = request.getSession().getAttribute("phone").toString();
		@SuppressWarnings("unchecked")
		List<RoomModel> list_rooms = (List<RoomModel>) request.getSession()
				.getAttribute("list_rooms");
		String[] room_ids = room_id.split(",");
		List<Long> ids = new ArrayList<Long>(room_ids.length);
		for (String id : room_ids) {
			try {
				Long temp = Long.valueOf(id);
				boolean isValidate = false;
				for (RoomModel rm : list_rooms) {
					if (rm.getId() == (long) temp) {
						isValidate = true;
						break;
					}
				}
				if (!isValidate) {
					throw new BusinessDealException("不能绑定该房间,请重新选择绑定房间");
				}
				ids.add(temp);
			} catch (NumberFormatException e) {
				throw new PatternException("房间id格式错误,请重新选择绑定房间");
			}
		}
		BerificationCode b_code = (BerificationCode) request.getSession()
				.getAttribute("b_code");
		BizUserExample example_buser = new BizUserExample();
		example_buser.or().andPhoneEqualTo(phone);
		List<BizUser> list_buser = bizUserMapper.selectByExample(example_buser);
		// BizUser buser = bizUserMapper.selectOneByExample(example_buser);
		if (list_buser != null && list_buser.size() > 0) {
			throw new BusinessValidateException("该手机号码已经注册,请直接登录");
		}
		if ("1".equals(type) && b_code.getType() != 1) {
			throw new BusinessValidateException("该验证码不能用于注册业主");
		}

		BizUser buser_register = new BizUser();
		buser_register.setNickname(nickname);
		buser_register.setPassword(MD5Utils.format(password));
		buser_register.setPhone(phone);
		buser_register.setStatus((short) 1);
		buser_register.setPoint(0);
		bizUserMapper.insertAndGetId(buser_register);

		for (Long id : ids) {
			BizUserRoom temp = new BizUserRoom();
			temp.setBizUserId(buser_register.getId());
			temp.setRoomId(id);
			temp.setStatus(0);
			if (type.equals("1")) {
				temp.setAuthorizeUserId(null);
				temp.setType("业主");
			} else if (type.equals("2")) {
				temp.setAuthorizeUserId((long) request.getSession()
						.getAttribute("authorize_user_id"));
				temp.setType("家属");
			} else if (type.equals("3")) {
				temp.setAuthorizeUserId((long) request.getSession()
						.getAttribute("authorize_user_id"));
				temp.setType("租客");
			}
			bizUserRoomMapper.insert(temp);
			/*
			 * Room room = roomMapper.selectByPrimaryKey(id); Building building
			 * = buildingMapper.selectByPrimaryKey(room.getBuildingId());
			 * Community community =
			 * communityMapper.selectByPrimaryKey(building.getCommunityId());
			 */
			// lingLingUtil.addOwner(buser_register.getId(), community.getId(),
			// building.getId(), UUID.randomUUID().toString().substring(0, 10));
		}
		lingLingUtil.addOwner(buser_register.getId(),
				"" + buser_register.getId(), buser_register.getPhone(), UUID
						.randomUUID().toString().substring(0, 10), null, null);
		request.getSession().removeAttribute("isValidateBerificationCode");
		request.getSession().removeAttribute("authorizeCode");
		request.getSession().removeAttribute("b_code");
		request.getSession().removeAttribute("list_rooms");
		request.getSession().removeAttribute("authorize_user_id");
		return buser_register;
	}

	@Override
	public BizUserLingLingModel updateGetUserLingLingKey(long buserId) {
		/*
		 * LinglingOwnerExample example_owner = new LinglingOwnerExample();
		 * example_owner.or().andBuserIdEqualTo(buserId); List<LinglingOwner>
		 * list_owner = linglingOwnerMapper.selectByExample(example_owner);
		 * if(list_owner == null || list_owner.size() < 1){ throw new
		 * BusinessValidateException("当前用户未注册门禁系统"); } LinglingOwner owner =
		 * list_owner.get(0);
		 */

		LinglingOwner owner = lingLingUtil.ownerLogin(buserId);
		BizUserLingLingModel model = new BizUserLingLingModel();
		model.setId(buserId);
		model.setRegcode(owner.getRegcode());
		model.setEncryptStr(owner.getEncryptstr());
		return model;
	}

	@Override
	public BaseResult updateInfoByWeb(String msg) {
		BaseResult baseResult = new BaseResult("SUCCESS", "生成成功");
		String str_info = "";
		if (StringUtils.isNumeric(msg)) {
			BizUserExample example_bizUser = new BizUserExample();
			example_bizUser.or().andPhoneEqualTo(msg);
			example_bizUser.setOrderByClause(" id desc");
			List<BizUser> list_buser = bizUserMapper.selectByExample(example_bizUser);
			if(list_buser.size() < 1){
				throw new BusinessDealException("该手机号业主尚未注册");
			}
			BizUser buser = list_buser.get(0);
			BizUserRoomExample example_buser_room = new BizUserRoomExample();
			example_buser_room.or().andBizUserIdEqualTo(buser.getId());
			List<BizUserRoom> list_buser_room = bizUserRoomMapper.selectByExample(example_buser_room);
			for(BizUserRoom temp : list_buser_room){
				
				Room room = roomMapper.selectByPrimaryKey(temp.getRoomId());
				if(null == room){
					continue;
				}
				Building building = buildingMapper.selectByPrimaryKey(room.getBuildingId());
				if(null == building){
					continue;
				}
				Community community = communityMapper.selectByPrimaryKey(building.getCommunityId());
				if(null == community){
					continue;
				}
				String roomStr = community.getName() + building.getName()
						+ room.getFloor() + room.getRoomNo();
				String room_id = room.getId() + "";
				
				str_info += roomStr + ":" + room_id + ",";
			}
			baseResult.setMessage(str_info);
		} else {
			throw new BusinessValidateException("输入的格式错误");
		}
		return baseResult;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void edit(HttpServletRequest request,String Pwd,
			String nickname,String nam,String sex, Long id, MultipartFile file) throws Exception {
		// 删除原来的头像
		//BizUser user = bizUserMapper.selectByPrimaryKey(id);
		//FileManager.deleteFile(user.getHeadPortrait());
		
		//定义一个 图片相对项目放的位置
		String uploadPath =  "/upload" + "/files/headPortrait";
		String todayStr = new SimpleDateFormat("yyyyMMdd")
		.format(new Date());
		String fileName = UUID.randomUUID().toString()
				.substring(0, 12);
		fileName = todayStr + fileName;
		String savePath = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
		String password = MD5Utils.format(Pwd);
		BizUser model = new BizUser();
		model.setId(id);
		model.setNickname(nickname);
		model.setName(nam);
		model.setSex(sex);
		model.setPassword(password);
		model.setHeadPortrait(savePath);
		bizUserMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public List<Tree> allTree() {
		List<BizUser> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		l = bizUserMapper.selectByExample(null);
		if (l != null && l.size() > 0) {
			for (BizUser r : l) {
				Tree tree = new Tree();
				//BeanUtils.copyProperties(r, tree);
				tree.setId(r.getId());
				tree.setText(r.getNickname());
				tree.setIconCls(r.getHeadPortrait());
				Map<String, Object> attr = new HashMap<String, Object>();
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	
}
