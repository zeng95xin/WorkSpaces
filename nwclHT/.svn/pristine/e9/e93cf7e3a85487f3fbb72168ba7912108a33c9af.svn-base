package com.bola.nwcl.web.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.AdminCommunityManager;
import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.EmployeeManager;
import com.bola.nwcl.biz.PropertyManager;
import com.bola.nwcl.biz.RoleManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.MD5Utils;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AdminCommunity;
import com.bola.nwcl.dal.mybatis.model.AdminCommunityExample;
import com.bola.nwcl.dal.mybatis.model.AdminExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.Property;
import com.bola.nwcl.dal.mybatis.model.Role;
import com.bola.nwcl.dal.mybatis.model.RoleExample;
import com.bola.nwcl.web.model.SessionInfo;

@Controller
@RequestMapping(value = "web/admin")
public class TAdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private AdminManager adminManager;
	@Autowired
	private PropertyManager propertyManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired private EmployeeManager employeeManager;
	@Autowired private AdminCommunityManager adminCommunityManager;
	
	
	@ResponseBody
	@RequestMapping(value="/login",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public BaseResult doLogin(HttpSession session, HttpServletRequest request, String name, String pwd, String randomCode ) {
		BaseResult baseResult=new BaseResult("SUCCESS", "登录成功");
		try {
			//执行登录
			if(StringUtils.isBlank(name)){
				throw new PatternException("请输入用户名");
			}
			if(StringUtils.isBlank(pwd)){
				throw new PatternException("请输入密码");
			}
			String password = MD5Utils.format32(pwd);
			AdminExample example = new AdminExample();
			example.or().andNameEqualTo(name);
			Admin user = adminManager.selectOneByExample(example);
			if (user == null) {
				throw new PatternException("账号或密码错误！");
			}
			if (!user.getPassword().equalsIgnoreCase(password)) {
				throw new PatternException("账号或密码错误！");
			}
			if (user.getStatus() == 0) {
				throw new PatternException("该帐号已停用，请联系管理员！");
				
			}
			//登录成功了 存session
			SessionInfo sessionInfo = new SessionInfo();
			BeanUtils.copyProperties(user, sessionInfo);
			sessionInfo.setResourceList(adminManager.resourceList(user.getId()));
			
			session.setAttribute("adminLoginStats", "账号ID");
			session.setAttribute("userId", user.getId());			
			session.setAttribute("loginUser", user);
			session.setAttribute("sessionInfo", sessionInfo);
			List<Long> communityIds;
			if(user.getRoleId() != 1){
				AdminCommunityExample example_admin_community = new AdminCommunityExample();
				example_admin_community.or().andAdminUserIdEqualTo(user.getId());
				List<AdminCommunity> list_admin_community = adminCommunityManager.selectByExample(example_admin_community);
				communityIds = new ArrayList<Long>(list_admin_community.size());
				if(list_admin_community.size() > 0){
					for(AdminCommunity temp : list_admin_community){
						communityIds.add(temp.getCommunityId());
					}
				}else{
					communityIds.add(-1l);
				}
			}else{
				CommunityExample communityExample = new CommunityExample();
				List<Community> list_commumitys = communityManager.selectByExample(communityExample);
				communityIds = new ArrayList<Long>(list_commumitys.size());
				for(Community temp : list_commumitys){
					communityIds.add(temp.getId());
				}
			}
			session.setAttribute("communityIds", communityIds);
			
			//session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
			session.setAttribute("loginname", user.getName());
			
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
		logger.info("：{}", baseResult.toString());
		return baseResult;
	}
	
	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/user/user";
	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Admin admin, PageHelper ph, HttpServletRequest request) {
		return adminManager.dataGrid(admin, ph, request);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("loginUser");
		CommunityExample communityExample = new CommunityExample();
		if (admin.getRoleId() != 1) {
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			communityExample.or().andIdIn(communityIds);
		}
		List<Community> community = communityManager.selectByExample(communityExample);
		request.setAttribute("community", community);
		RoleExample roleExample = new RoleExample();
		roleExample.or().andIdNotEqualTo(1);
		List<Role> roles = roleManager.selectByExample(roleExample);
		request.setAttribute("roles", roles);
		return "/user/userAdd";
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Admin imd = adminManager.selectByPrimaryKey(id);
		if (imd != null) {
			Property p = propertyManager.selectByPrimaryKey(imd.getPropertyId());
			if (p != null) {
				imd.setPropertyName(p.getName());
			}
			Community c = communityManager.selectByPrimaryKey(imd.getCommunityId());
			if (c != null) {
				imd.setCommunityName(c.getName());
			}
			Employee e = employeeManager.selectByPrimaryKey(imd.getEmployeeId());
			if (e != null) {
				imd.setUserName(e.getName());
			}
		}
		request.setAttribute("model", imd);
		List<Property> props = propertyManager.selectByExample(null);
		request.setAttribute("props", props);
		RoleExample roleExample = new RoleExample();
		roleExample.or().andIdNotEqualTo(1);
		List<Role> roles = roleManager.selectByExample(roleExample);
		request.setAttribute("roles", roles);
		Admin admin = (Admin) request.getSession().getAttribute("loginUser");
		CommunityExample communityExample = new CommunityExample();
		if (admin.getRoleId() == 2) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			communityExample.or().andIdIn(communityIds);
		}
		List<Community> community = communityManager.selectByExample(communityExample);
		
		if(imd.getRoleId() == 1){
			String communityId_strs = "";
			for(Community temp : community){
				communityId_strs += temp.getId() + ",";
			}
			request.setAttribute("communityId_strs", communityId_strs);
		}else{
			String communityId_strs = imd.getCommunityIds();
			if(null == communityId_strs){
				communityId_strs = "";
			}
			if(!communityId_strs.endsWith(",")){
				communityId_strs += ",";
			}
			request.setAttribute("communityId_strs", communityId_strs);
		}
		
		request.setAttribute("community", community);
		return "/user/userEdit";
	}
	
	/**
	 * 跳转到用户授权页面
	 * 
	 * @return
	 */
	@RequestMapping("/grantPage")
	public String grantPage(Long id, HttpServletRequest request) {
		request.setAttribute("id", id);
		Admin r = adminManager.selectByPrimaryKey(id);
		request.setAttribute("model", r);
		return "/user/userGrant";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, Admin a) {
		BaseResult baseResult = new BaseResult("SUCCESS","添加成功");
		try {
			AdminExample ex = new AdminExample();
			ex.or().andNameEqualTo(a.getName());
			Admin user = adminManager.selectOneByExample(ex);
			if (user != null) {
				throw new PatternException("该帐号已被使用！");
			}
			Admin model = new Admin();
			BeanUtils.copyProperties(a, model);
			if (StringUtils.trim(a.getPassword()).length() < 6 || StringUtils.trim(a.getPassword()).length() > 12 ) {
				throw new PatternException("请输入6 - 12 位的密码！");
			}
			model.setPassword(MD5Utils.format32(StringUtils.trim(a.getPassword())));
			adminManager.insertAndGetId(model);			
			String[] communityIds_array = model.getCommunityIds().split(",");
			if(communityIds_array.length > 0){
				for(String temp : communityIds_array){
					if(StringUtils.isNoneBlank(temp)){
						AdminCommunity adminCommunity = new AdminCommunity();
						adminCommunity.setAdminUserId(model.getId());
						adminCommunity.setCommunityId(Long.valueOf(temp));
						adminCommunity.setRowAddTime(new Date());
						adminCommunityManager.insert(adminCommunity);
					}
				}
			}
			model.setPassword(MD5Utils.format32(StringUtils.trim(a.getPassword())));
			model.setRowAddTime(new Date());
			model.setStatus((short) 1);
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
	public BaseResult edit(HttpServletRequest request, Long id, Admin admin, String newPwd ) {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		try {
			Admin model = adminManager.selectByPrimaryKey(id);
			BeanUtils.copyProperties(admin, model);
			if (StringUtils.isNotBlank(newPwd)) {
				if (StringUtils.trim(newPwd).length() < 6 || StringUtils.trim(newPwd).length() > 12 ) {
					throw new PatternException("请输入6 - 12 位的密码！");
				}
				model.setPassword(MD5Utils.format32(StringUtils.trim(newPwd)));
			}
			adminManager.updateByPrimaryKeySelective(model);
			
			AdminCommunityExample example_admin_community_delete = new AdminCommunityExample();
			example_admin_community_delete.or().andAdminUserIdEqualTo(id);
			adminCommunityManager.deleteByExample(example_admin_community_delete);
			String[] communityIds_array = admin.getCommunityIds().split(",");
			if(communityIds_array.length > 0){
				for(String temp : communityIds_array){
					if(StringUtils.isNoneBlank(temp)){
						AdminCommunity adminCommunity = new AdminCommunity();
						adminCommunity.setAdminUserId(model.getId());
						adminCommunity.setCommunityId(Long.valueOf(temp));
						adminCommunity.setRowAddTime(new Date());
						adminCommunityManager.insert(adminCommunity);
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
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request,Long id ) {
		BaseResult baseResult = new BaseResult("SUCCESS","删除成功");		
		try {			
			adminManager.deleteByPrimaryKey(id);			
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
	
	@RequestMapping("/logout")
	@ResponseBody
	public BaseResult logout(HttpSession session ) {
		BaseResult baseResult = new BaseResult("SUCCESS","注销成功");		
		try {		
			if (session != null) {
				session.invalidate();
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
		logger.info("注销返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	/**
	 * 跳转到编辑自己的密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/editCurrentUserPwdPage")
	public String editCurrentUserPwdPage() {
		return "/user/userEditPwd";
	}
	
	@RequestMapping("/editCurrentUserPwd")
	@ResponseBody
	public BaseResult editPwd(HttpSession session, String oldPwd, String pwd ) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改密码成功");		
		try {		
			if (session != null) {
				Admin user = (Admin) session.getAttribute("loginUser");
				Admin u = adminManager.selectByPrimaryKey(user.getId());
				if (u == null) {
					throw new PatternException("帐号不存在！");
				}
				if (u.getPassword().equalsIgnoreCase(MD5Utils.format32(oldPwd))) {// 说明原密码输入正确
					u.setPassword(MD5Utils.format32(pwd));
					adminManager.updateByPrimaryKeySelective(u);
					baseResult.setMessage("编辑密码成功，下次登录生效！");
				}else{
					throw new PatternException("原密码错误！");
				}
			}else{
				throw new PatternException("登录超时，请重新登录！");
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
		logger.info("修改密码返回值：{}", baseResult.toString());
		return baseResult;
	}

}
