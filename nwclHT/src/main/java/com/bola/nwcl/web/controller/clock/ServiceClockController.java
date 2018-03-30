package com.bola.nwcl.web.controller.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.DepartmentManager;
import com.bola.nwcl.biz.ServiceClockManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.ServiceClockRoleConfig;

@Controller
@RequestMapping(value = "web/Clock")
public class ServiceClockController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ServiceClockManager serviceClockManager;

	@Autowired
	private DepartmentManager departmentManager;

	@Autowired
	private CommunityManager communityManager;

	@RequestMapping("/getAllColckRecord")
	public String manager(HttpServletRequest request) {
		List<Community> coms = communityManager.selectByExample(null);
		List<Department> departments = departmentManager.selectByExample(null);
		request.setAttribute("comus", coms);
		request.setAttribute("departments", departments);
		return "/clock/clock";
	}
	@RequestMapping("/dt")
	public String dt(HttpServletRequest request) {
		return "/clock/clockdt";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(ServiceClockRoleConfig roleConfig, PageHelper ph, HttpServletRequest request) {
		return serviceClockManager.dataGrid(roleConfig, ph, request);
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		List<Department> dep = departmentManager.selectByExample(null);
		request.setAttribute("depts", dep);

		Admin admin = (Admin) request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			if (admin.getRoleId() != 1) {
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("coms", communities);
		}
		return "/clock/clockAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, Long communityId, Integer role, String longitude,
			 String distance, Integer allowOutside, String inInterval, String outInterval,String region,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date clockInTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date clockOutTime) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			ServiceClockRoleConfig serviceClockRoleConfig = new ServiceClockRoleConfig();
			if (communityId == null) {
				throw new PatternException("请选择小区");
			}
			String[] str=new String[2];
			str=longitude.split(",");
			String a= str[0];
			String b= str[1];
			serviceClockRoleConfig.setRegion(region);
			serviceClockRoleConfig.setCommunityId(communityId);
			serviceClockRoleConfig.setRole(role);
			serviceClockRoleConfig.setLongitude(Double.parseDouble(a));
			serviceClockRoleConfig.setLatitude(Double.parseDouble(b));
			serviceClockRoleConfig.setDistance(Double.parseDouble(distance));
			serviceClockRoleConfig.setAllowOutside(0);
			serviceClockRoleConfig.setClockInTime(clockInTime);
			serviceClockRoleConfig.setClockOutTime(clockOutTime);
			serviceClockRoleConfig.setInInterval(Double.parseDouble(inInterval));
			serviceClockRoleConfig.setOutInterval(Double.parseDouble(outInterval));
			serviceClockRoleConfig.setRawAddTime(new Date());
			serviceClockManager.insert(serviceClockRoleConfig);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			ServiceClockRoleConfig serviceClockRoleConfig = new ServiceClockRoleConfig();
			serviceClockRoleConfig.setId(id);
			serviceClockManager.deleteByPrimaryKey(id);
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

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		CommunityExample communityExample = new CommunityExample();
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user != null) {
			if (user.getRoleId() != 1) {
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				communityExample.or().andIdIn(communityIds);
			}
		}
		List<Community> communitys = communityManager.selectByExample(communityExample);
		ServiceClockRoleConfig s = serviceClockManager.selectByPrimaryKey(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		request.setAttribute("startTime", sdf.format(s.getClockInTime()));
		request.setAttribute("endTime", sdf.format(s.getClockOutTime()));
		List<Department> dep = departmentManager.selectByExample(null);
		request.setAttribute("depts", dep);
		BuildingExample example_building = new BuildingExample();
		example_building.or().andCommunityIdEqualTo(s.getCommunityId());
		request.setAttribute("communitys", communitys);
		request.setAttribute("s", s);
		ServiceClockRoleConfig selectByPrimaryKey = serviceClockManager.selectByPrimaryKey(id);
		request.setAttribute("model", selectByPrimaryKey);
		return "/clock/clockEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,Long id, Long communityId, Integer role, String longitude,
			String distance, Integer allowOutside, String inInterval, String outInterval,String region,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date clockInTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date clockOutTime) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			if (communityId == null) {
				throw new PatternException("请选择小区");
			}
			String[] str=new String[2];
			str=longitude.split(",");
			String a= str[0];
			String b= str[1];
			ServiceClockRoleConfig selectByPrimaryKey = serviceClockManager.selectByPrimaryKey(id);
			selectByPrimaryKey.setRegion(region);
			selectByPrimaryKey.setCommunityId(communityId);
			selectByPrimaryKey.setRole(role);
			selectByPrimaryKey.setLongitude(Double.parseDouble(a));
			selectByPrimaryKey.setLatitude(Double.parseDouble(b));
			selectByPrimaryKey.setDistance(Double.parseDouble(distance));
			selectByPrimaryKey.setAllowOutside(0);
			selectByPrimaryKey.setClockInTime(clockInTime);
			selectByPrimaryKey.setClockOutTime(clockOutTime);
			selectByPrimaryKey.setInInterval(Double.parseDouble(inInterval));
			selectByPrimaryKey.setOutInterval(Double.parseDouble(outInterval));
			selectByPrimaryKey.setRawUpdateTime(new Date());
			serviceClockManager.updateByPrimaryKeySelective(selectByPrimaryKey);
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}
}
