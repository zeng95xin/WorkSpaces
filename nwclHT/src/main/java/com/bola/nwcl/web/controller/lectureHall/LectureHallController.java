package com.bola.nwcl.web.controller.lectureHall;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.IndexesImgManager;
import com.bola.nwcl.biz.LectureHallManager;
import com.bola.nwcl.biz.LectureHallMessageManager;
import com.bola.nwcl.biz.LectureHallRegisterManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.notify.JPushUtil_Buser;
import com.bola.nwcl.common.util.notify.NotifyUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.IndexesImgExample;
import com.bola.nwcl.dal.mybatis.model.LectureHall;
import com.bola.nwcl.dal.mybatis.model.LectureHallExample;
import com.bola.nwcl.dal.mybatis.model.LectureHallExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.LectureHallRegister;
import com.bola.nwcl.dal.mybatis.model.LectureHallRegisterExample;
import com.bola.nwcl.web.model.Activitys;


/**
 * 业主讲堂管理
 * 
 * @author RJ
 * 
 */
@Controller
@RequestMapping(value = "web/lectureHall")
public class LectureHallController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private IndexesImgManager indexesImgManager;
	@Autowired
	private LectureHallManager lectureHallManager;
	@Autowired
	private CommunityManager communityManager;
	
	@Autowired
	private LectureHallMessageManager lectureHallMessageManager;
	
	@Autowired LectureHallRegisterManager lectureHallRegisterManager;
	@Autowired BizUserManager bizUserManager;
	
	@Autowired AdminManager adminManager;
	
	@Autowired NotifyUtil notifyUtil;
	
	/**
	 * 跳转图片管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		//提前查询出所有的小区
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("communities", communities);
		}
		return "/lectureHall/lectureHall";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LectureHall lectureHall, PageHelper ph,HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		LectureHallExample example = new LectureHallExample();
		Criteria c = example.or();
		
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		if (lectureHall != null) {
			if (lectureHall.getId() != null) {
				c.andIdEqualTo(lectureHall.getId());
			}

			if (StringUtils.isNotBlank(lectureHall.getSname())) {
				if ("title".equalsIgnoreCase(lectureHall.getSname())) {
					c.andTitleLike("%" + lectureHall.getSvalue() + "%");
				}
				if ("content".equalsIgnoreCase(lectureHall.getSname())) {
					c.andContentLike("%" + lectureHall.getSvalue() + "%");
				}

			}
			// 通过小区查询
			if (lectureHall.getCommunityId() != null
					&& lectureHall.getCommunityId() != -1) {
				c.andCommunityIdEqualTo(lectureHall.getCommunityId());
			}
		}
		dg.setTotal(lectureHallManager.countByExample(example));
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<LectureHall> list = lectureHallManager.selectByExample(example);
		List<Activitys> activitys = null;
		activitys = new ArrayList<Activitys>();
		if (list.size() > 0) {
			for (LectureHall lectureHall2 : list) {
				Activitys model = new Activitys();
				LectureHall act = lectureHallManager.selectByPrimaryKey(lectureHall2.getId());
				if (act != null) {
					model.setId(act.getId());
					model.setTitle(act.getTitle());
					model.setContent(act.getContent());
					model.setRowAddTime(act.getRowAddTime());
					model.setEndTime(act.getEndTime());
					model.setImgPath(act.getImgPath());
					model.setIsTop(act.getIsTop());

					if (act.getActivityEndTime().getTime() < new Date().getTime()) {
						model.setState("活动已结束");
					} else {
						if (act.getEndTime().getTime() > new Date().getTime()) {
							model.setState("报名中");
						} else {
							model.setState("报名截止");
						}
					}
				}
				Admin ad = adminManager.selectByPrimaryKey(lectureHall2
						.getPublishId());
				if (ad != null) {
					model.setPublish(ad.getEmployeeName());
				}
				activitys.add(model);
			}
		}
		
		dg.setRows(activitys);

		return dg;
	}

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
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("communities", communities);
		}
		return "/lectureHall/lectureHallAdd";
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		//提前查询出所有的小区
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("communities", communities);
		}
		LectureHall imd = lectureHallManager.selectByPrimaryKey(id);
		request.setAttribute("model", imd);
		String url = "app:1:"+imd.getId();
		IndexesImgExample example_index_img = new IndexesImgExample();
		example_index_img.or().andUrlEqualTo(url);
		if(indexesImgManager.countByExample(example_index_img) > 0){
			request.setAttribute("recommended", 1);
		}else{
			request.setAttribute("recommended", 2);
		}
		
		return "/lectureHall/lectureHallEdit";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, LectureHall hall,String push, String recommended,String pushContent, String endTime1,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date activityEndTime1,
			@RequestParam("img1") MultipartFile img1) {
		BaseResult baseResult = new BaseResult("SUCCESS","成功");
		
		try {
			if(hall.getCommunityId() == null || hall.getCommunityId().intValue() == -1){
				throw new PatternException("请选择小区！！！");
			}
			lectureHallManager.insertGenId(request, hall, push, pushContent, recommended, endTime1, activityEndTime1, img1);	

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
	/*@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")*/
	public BaseResult edit(HttpServletRequest request, LectureHall hall,String push, String recommended,String pushContent, String endTime1,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date activityEndTime1,
			@RequestParam("img1") MultipartFile img1) {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		
		try {
			if(hall.getCommunityId() == null || hall.getCommunityId().intValue() == -1){
				throw new PatternException("请选择小区！！！");
			}
			lectureHallManager.edit(request, hall, push, recommended, pushContent, endTime1, activityEndTime1, img1);	

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
			lectureHallManager.deleteByPrimaryKey(id);			
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
	
		@RequestMapping("/lectureHallMessagePage")
		public String lectureHallMessage(Long id,HttpServletRequest request){
			request.setAttribute("id", id);
			return "lectureHall/lectureHallMessage";
		}
	
	
	
	@RequestMapping("/lectureHallMessage")
	@ResponseBody
	public BaseResult lectureHallMessage(HttpServletRequest request,Long id ,String title, String content) {
		BaseResult baseResult = new BaseResult("SUCCESS","发送成功");
		try {
			/*if(StringUtils.isBlank(title)){
				throw new PatternException("请输入标题！！！");
			}*/
			if(StringUtils.isBlank(content)){
				throw new PatternException("请输入内容！！！");
			}
			/*if(title.length() > 50){
				throw new PatternException("标题不能大于50个字符！！！");
			}*/
			if(content.length() > 50){
				throw new PatternException("内容不能大于200个字符！！！");
			}
			Admin user = (Admin) request.getSession().getAttribute("loginUser");
			JPushChatModel model = new JPushChatModel();
			model.setType("11:"+id+":-1");
			model.setTitle("业主讲堂提示");
			model.setContent(content);
			
			LectureHallRegisterExample example = new LectureHallRegisterExample();
			example.or().andLectureHallIdEqualTo(id);
			List <LectureHallRegister> lectureHallRegister = lectureHallRegisterManager.selectByExample(example);
			for (LectureHallRegister lectureHallRegister2 : lectureHallRegister) {
				BizUser bizUser=bizUserManager.selectByPrimaryKey(lectureHallRegister2.getBuserId());
				JPushUtil_Buser.sendToBuser(model, bizUser);
				//消息
				notifyUtil.send("业主讲堂提示", content, "11:" + id+ ":-1", user.getId(), bizUser.getId(), null);
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
		logger.info("业主讲堂消息发送返回值：{}", baseResult.toString());
		return baseResult;
	}

	/**
	 * 置顶
	 */
	@RequestMapping("/top")
	@ResponseBody
	public BaseResult top(Long id) {
		BaseResult result = new BaseResult("SUCCESS", "置顶成功");
		LectureHall a = lectureHallManager.selectByPrimaryKey(id);
		if (a == null) {
			result.setMessage("活动已经被删除了");
		} else {
			if (a.getIsTop() == 0) {
				LectureHallExample example = new LectureHallExample();
				example.or().andIsTopEqualTo(1);
				int count = lectureHallManager.countByExample(example);
				if (count >= 5) {
					result.setMessage("该数据最多只能置顶5条");
				} else {
					a.setIsTop(1);
					lectureHallManager.updateByPrimaryKeySelective(a);
				}
			} else {
				result.setMessage("取消置顶成功");
				a.setIsTop(0);
				lectureHallManager.updateByPrimaryKeySelective(a);
			}
		}
		return result;

	}

}
