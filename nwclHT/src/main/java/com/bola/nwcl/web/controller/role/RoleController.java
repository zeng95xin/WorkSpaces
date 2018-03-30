package com.bola.nwcl.web.controller.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.MemuManager;
import com.bola.nwcl.biz.RoleManager;
import com.bola.nwcl.biz.RoleResourceManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.MemuExample;
import com.bola.nwcl.dal.mybatis.model.Role;
import com.bola.nwcl.dal.mybatis.model.RoleResource;
import com.bola.nwcl.dal.mybatis.model.RoleResourceExample;

/**
 * 角色管理
 * 
 * @author RJ
 * 
 */
@Controller
@RequestMapping(value = "web/role")
public class RoleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private MemuManager memuManager;
	@Autowired
	private RoleResourceManager roleResourceManager;

	/**
	 * 跳转角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/role/role";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Role role, PageHelper ph) {
		return roleManager.dataGrid(role, ph);
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/role/roleAdd";
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		Role imd = roleManager.selectByPrimaryKey(id);
		request.setAttribute("model", imd);
		return "/role/roleEdit";
	}
	
	/**
	 * 跳转到用户授权页面
	 * 
	 * @return
	 */
	@RequestMapping("/grantPage")
	public String grantPage(Integer id, HttpServletRequest request) {
		request.setAttribute("id", id);
		Role r = roleManager.selectRoleByPrimaryKey(id.longValue());
		request.setAttribute("role", r);
		return "/role/roleGrant";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, String name, String note) {
		BaseResult baseResult = new BaseResult("SUCCESS","成功");
		try {
			Role model = new Role();
			model.setRoleName(name);
			model.setRoleNote(note);
			model.setRowAddTime(new Date());
			roleManager.insert(model);			
			
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
	public BaseResult edit(HttpServletRequest request, Integer id, String name, String note ) {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		try {
			Role model = roleManager.selectByPrimaryKey(id);
			model.setRoleName(name);
			model.setRoleNote(note);
			roleManager.updateByPrimaryKeySelective(model);		
			
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
	public BaseResult delete(HttpServletRequest request,Integer id ) {
		BaseResult baseResult = new BaseResult("SUCCESS","删除成功");		
		try {			
			if (id == 1 || id ==2) {
				throw new PatternException("不能删除改角色！");
			}
			roleManager.deleteByPrimaryKey(id);			
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
	
	@RequestMapping("/grant")
	@ResponseBody
	public BaseResult grant(HttpServletRequest request, Role role) {
		BaseResult baseResult = new BaseResult("SUCCESS","授权成功");	
		Role rol = roleManager.selectByPrimaryKey(role.getId());
		try {
			if (rol == null) {
				throw new PatternException("角色不存在");
			}
			if (role.getResourceIds() != null && !role.getResourceIds().equalsIgnoreCase("")) {
				String[] ids = role.getResourceIds().split(","); 
				List<Long> rids = new ArrayList<Long>();
				for(String rid : ids){
					if (rid != null && !rid.equalsIgnoreCase("")) {
						rids.add(Long.valueOf(rid));
					}
				}
				// 角色关联表中当前授权信息时不变，没有便添加
				for (Long id : rids) {
					RoleResourceExample rrExample1 = new RoleResourceExample();
					rrExample1.or().andRoleIdEqualTo(role.getId()).andResourceIdEqualTo(id);
					RoleResource R = roleResourceManager.selectOneByExample(rrExample1);
					if (R == null) {
						RoleResource model = new RoleResource();
						model.setRoleId(role.getId());
						model.setResourceId(id);
						model.setRawAddTime(new Date());
						roleResourceManager.insert(model);
					}
				}
				// 角色关联表中授权信息多余或被取消了授权，删除
				RoleResourceExample rrExample2 = new RoleResourceExample();
				rrExample2.or().andRoleIdEqualTo(role.getId()).andResourceIdNotIn(rids);
				//List<RoleResource> rrce = roleResourceManager.selectByExample(rrExample2);
				roleResourceManager.deleteByExample(rrExample2);
			}else{
				// 被取消所有授权
				RoleResourceExample rrExample3 = new RoleResourceExample();
				rrExample3.or().andRoleIdEqualTo(role.getId());
				roleResourceManager.deleteByExample(rrExample3);
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
		logger.info("授权返回值：{}", baseResult.toString());
		return baseResult;
	}

	
}
