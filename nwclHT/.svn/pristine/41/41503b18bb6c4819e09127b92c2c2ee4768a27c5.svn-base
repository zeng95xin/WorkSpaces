package com.bola.nwcl.web.controller.activity;

import java.text.SimpleDateFormat;
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

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.biz.ActivityManager;
import com.bola.nwcl.biz.ActivityRatingManager;
import com.bola.nwcl.biz.ActivityRegisterManager;
import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.IndexesImgManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.common.util.notify.JPushUtil_Buser;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Activity;
import com.bola.nwcl.dal.mybatis.model.ActivityExample;
import com.bola.nwcl.dal.mybatis.model.ActivityExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.ActivityRatingExample;
import com.bola.nwcl.dal.mybatis.model.ActivityRegisterExample;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AdminExample;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.IndexesImgExample;
import com.bola.nwcl.web.model.Activitys;



/**
 * 社区文化
 * 
 */
@RequestMapping("web/activityController")
@Controller
public class ActivityWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired ActivityManager activityManager;
	@Autowired CommunityManager communityManager;
	@Autowired ActivityRatingManager activityRatingManager;
	@Autowired ActivityRegisterManager activityRegisterManager;
	@Autowired AdminManager adminManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired private IndexesImgManager indexesImgManager;
	
	/**
	 * 跳转到社区文化页面
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
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("communities", communities);
		}
		return "/activity/activity";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Activitys activity,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		ActivityExample example = new ActivityExample();
		
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		Criteria c = example.or();
		
		example.setOrderByClause("row_add_time DESC");
		// 通过栏目查询
		if (activity.getType() != null && activity.getType() != -1) {
			c.andTypeEqualTo(activity.getType());
		}
		if (StringUtils.isNoneBlank(activity.getSelectOption())
				&& activity.getSelectOption().equals("1")) {
			c.andTitleLike("%" + activity.getKey() + "%");
		}
		if (StringUtils.isNoneBlank(activity.getSelectOption())
				&& activity.getSelectOption().equals("2")) {
			c.andContentLike("%" + activity.getKey() + "%");
		}
		if (StringUtils.isNoneBlank(activity.getSelectOption())&& activity.getSelectOption().equals("4")) {
			Date addTime = DateUtils.strToDate(activity.getKey(), "yyyy-MM-dd");
			c.andRowAddTimeBetween(addTime, new Date(addTime.getTime() + 1000 * 60 * 60 * 24));
		}
		// 通过发布人查询
		if (StringUtils.isNoneBlank(activity.getSelectOption())
				&& activity.getSelectOption().equals("3")) {
			List<Long> list = null;
			list = new ArrayList<Long>();
			AdminExample adminExample = new AdminExample();
			adminExample.or().andNameLike("%" + activity.getKey() + "%");
			List<Admin> admin = adminManager.selectByExample(adminExample);
			for (Admin admin2 : admin) {
				list.add(admin2.getId());
			}
			if (list.size() > 0) {
				c.andPublishIdIn(list);
			} else {
				c.andPublishIdEqualTo(-1L);
			}
		}
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommuntiyIdIn(communityIds);
		}
		// 通过小区查询
		if (activity.getCommunity() != null && activity.getCommunity() != -1) {
			c.andCommuntiyIdEqualTo(activity.getCommunity());
		}

		List<Activity> list = activityManager.selectByExample(example);
		List<Activitys> activitys = null;
		activitys = new ArrayList<Activitys>();
		if (list.size() > 0) {
			for (Activity activity2 : list) {
				Activitys model = new Activitys();
				Activity act = activityManager.selectByPrimaryKey(activity2
						.getId());
				if (act != null) {
					model.setId(act.getId());
					model.setTitle(act.getTitle());
					model.setType(act.getType());
					model.setContent(act.getContent());
					model.setRowAddTime(act.getRowAddTime());
					model.setEndTime(act.getEndTime());
					model.setImgPath(act.getImgPath());
					model.setIsTop(act.getIsTop());
					if(act.getActivityEndTime().getTime() < new Date().getTime()){
						model.setState("活动已结束");
					}else {
						if (act.getEndTime().getTime() > new Date().getTime()) {
							model.setState("报名中");
						}else{
							model.setState("报名截止");
						}
					}
				}
				Admin ad = adminManager.selectByPrimaryKey(activity2
						.getPublishId());
				if (ad != null) {
					model.setPublish(ad.getEmployeeName());
				}
				activitys.add(model);
			}
		}
		dg.setRows(activitys);
		int total = activityManager.countByExample(example);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 跳转到社区文化添加页面
	 * 
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
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("communities", communities);
		}
		return "/activity/activityAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request,Activitys activity,String push, String recommended,String pushContent,@RequestParam("file") MultipartFile file) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			activityManager.insertGenId(request, activity, file, push, pushContent, recommended);
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
	 * 跳转到修改页面
	 * 
	 */
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id){
		Activity activity=activityManager.selectByPrimaryKey(id);
		request.setAttribute("activity", activity);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		request.setAttribute("endTime", sdf.format(activity.getEndTime()));
		request.setAttribute("activityEndTime", sdf.format(activity.getActivityEndTime()));
		
		String url = "app:2:"+activity.getId();
		IndexesImgExample example_index_img = new IndexesImgExample();
		example_index_img.or().andUrlEqualTo(url);
		if(indexesImgManager.countByExample(example_index_img) > 0){
			request.setAttribute("recommended", 1);
		}else{
			request.setAttribute("recommended", 2);
		}
		
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
		return "activity/activityEdit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,Activitys activity,@RequestParam("file") MultipartFile file,String push, String recommended,String pushContent) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			activityManager.edit(request, activity, file, push, recommended, pushContent);
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
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			ActivityRegisterExample activityRegisterExample=new ActivityRegisterExample();
			activityRegisterExample.or().andActivityIdEqualTo(id);
			activityRegisterManager.deleteByExample(activityRegisterExample);
			
			ActivityRatingExample activityRatingExample=new ActivityRatingExample();
			activityRatingExample.or().andActivityIdEqualTo(id);
			activityRatingManager.deleteByExample(activityRatingExample);
			
			Activity activity=activityManager.selectByPrimaryKey(id);
			if(StringUtils.isNoneBlank(activity.getImgPath())){
				FileManager.deleteFile(activity.getImgPath());
			}
			activityManager.deleteByPrimaryKey(id);
			
			BizUserExample bizUserExample = new BizUserExample();
			bizUserExample.or().andCommunityIdEqualTo(activity.getCommuntiyId());
			List<BizUser> bizUser = bizUserManager.selectByExample(bizUserExample);
			int type = activity.getType();
			// 推送
			JPushChatModel jPushChatModel = new JPushChatModel();
			jPushChatModel.setType("-8:" + id + ":" + type);
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
	
	/**
	 * 置顶
	 */
	@RequestMapping("/top")
	@ResponseBody
	public BaseResult top(Long id) {
		BaseResult result = new BaseResult("SUCCESS", "置顶成功");
		Activity a = activityManager.selectByPrimaryKey(id);
		if (a == null) {
			result.setMessage("活动已经被删除了");
		} else {
			if (a.getIsTop() == 0) {
				ActivityExample example = new ActivityExample();
				example.or().andIsTopEqualTo(1);
				int count = activityManager.countByExample(example);
				if (count >= 5) {
					result.setMessage("该数据最多只能置顶5条");
				} else {
					a.setIsTop(1);
					activityManager.updateByPrimaryKeySelective(a);
				}
			} else {
				result.setMessage("取消置顶成功");
				a.setIsTop(0);
				activityManager.updateByPrimaryKeySelective(a);
			}
		}
		return result;

	}
	
	
	

}
