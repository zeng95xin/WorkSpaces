package com.bola.nwcl.web.controller.suser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.DepartmentManager;
import com.bola.nwcl.biz.EmployeeManager;
import com.bola.nwcl.biz.HousekeeperDetailManager;
import com.bola.nwcl.biz.PropertyManager;
import com.bola.nwcl.biz.RepairmanDetailManager;
import com.bola.nwcl.biz.ServiceUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.RepairmanTypeMapper;
import com.bola.nwcl.dal.mybatis.mapper.RepairmanTypeRelationMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;
import com.bola.nwcl.dal.mybatis.model.Property;
import com.bola.nwcl.dal.mybatis.model.RepairmanType;
import com.bola.nwcl.dal.mybatis.model.RepairmanTypeRelation;
import com.bola.nwcl.dal.mybatis.model.RepairmanTypeRelationExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.web.model.EmployeeModel;

/**
 * 员工管理
 * 
 * @author RJ
 * 
 */
@Controller
@RequestMapping(value = "web/suser")
public class SuserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ServiceUserManager serviceUserManager;
	@Autowired
	private PropertyManager propertyManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private DepartmentManager departmentManager;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private HousekeeperDetailManager housekeeperDetailManager;
	@Autowired
	private RepairmanDetailManager repairmanDetailManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired private RepairmanTypeMapper repairmanTypeMapper;
	@Autowired private RepairmanTypeRelationMapper repairmanTypeRelationMapper;
	
	/**
	 * 跳转角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> coms = communityManager.selectByExample(null);
		List<Department> departments = departmentManager.selectByExample(null);
		request.setAttribute("comus", coms);
		request.setAttribute("departments", departments);
		return "/suser/suser";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(ServiceUser suser, PageHelper ph,HttpServletRequest request) {
		return serviceUserManager.dataGrid(suser, ph, request);
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		List<Property> properties = propertyManager.selectByExample(null);
		request.setAttribute("props", properties);
		List<Department> dep = departmentManager.selectByExample(null);
		request.setAttribute("depts", dep);
		
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("coms", communities);
		}
		
		List<RepairmanType> types = repairmanTypeMapper.selectByExample(null);
		request.setAttribute("types", types);
		return "/suser/suserAdd";
	}
	
	private static final String saveDir = "/upload/files/deadPortrait";
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, String types, EmployeeModel em, @RequestParam("img") MultipartFile img) {
		BaseResult baseResult = new BaseResult("SUCCESS", "成功");
		try {
			if (em.getCommunityId() == null) {
				throw new PatternException("请选择小区");
			}
			serviceUserManager.addServiceUserByWeb(request, saveDir, em, img, types);
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
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		ServiceUser su = serviceUserManager.selectByPrimaryKey(id);
		EmployeeModel m = new EmployeeModel();
		if(su != null){
			m.setId(id);
			m.setName(su.getName());
			m.setNickname(su.getNickname());
			m.setAccount(su.getAccount());
			m.setHeadPortrait(su.getHeadPortrait());
			m.setCommunityId(su.getCommunityId());
			m.setSex(su.getSex());
			m.setRole(su.getRole().shortValue());
			m.setDeptId(su.getDeptId());
		}
		EmployeeExample example_e = new EmployeeExample();
		example_e.or().andSuserIdEqualTo(id);
		Employee e = employeeManager.selectOneByExample(example_e);
		if (e!=null) {
			m.setPropertyId(e.getPropertyId());
			m.setPosition(e.getPosition());
			m.setBirthday(e.getBirthday());
			m.setDeptId(e.getDeptId());
			m.setEdBackground(e.getEdBackground());
			m.setEmployeeNo(e.getEmployeeNo());
			m.setIdNumber(e.getIdNumber());
			m.setMobilephoneNumber(e.getMobilephoneNumber());
			m.setTelephoneNumber(e.getTelephoneNumber());
			m.setNote(e.getNote());
			m.setIntro(e.getIntro());
			m.setEmployedDate(e.getEmployedDate());
		}
		
		List<RepairmanType> types = repairmanTypeMapper.selectByExample(null);
		request.setAttribute("types", types);
		request.setAttribute("model", m);
		
		if (su.getRole() == 3) {
			RepairmanTypeRelationExample example_relation = new RepairmanTypeRelationExample();
			example_relation.or().andSuserIdEqualTo(id);
			List<RepairmanTypeRelation> relations = repairmanTypeRelationMapper
					.selectByExample(example_relation);
			String relations_str = "";
			for (RepairmanTypeRelation relation : relations) {
				relations_str += relation.getTypeId();
				relations_str += ",";
			}
			request.setAttribute("relations_str", relations_str);
		}
		
		List<Property> properties = propertyManager.selectByExample(null);
		request.setAttribute("props", properties);
		List<Department> dep = departmentManager.selectByExample(null);		
		request.setAttribute("depts", dep);
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("coms", communities);
		}
		return "/suser/suserEdit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, EmployeeModel em, @RequestParam("img") MultipartFile img, String types) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin
			if (em.getCommunityId() == null) {
				throw new PatternException("请选择小区");
			}
			serviceUserManager.updateServiceUserByWeb(request, saveDir, em, img, types);
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
	public BaseResult delete(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			serviceUserManager.deleteServiceUserByWeb(id);
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
	
	
	
}
