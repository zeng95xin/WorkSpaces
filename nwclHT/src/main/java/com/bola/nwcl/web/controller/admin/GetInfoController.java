package com.bola.nwcl.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.EmployeeManager;
import com.bola.nwcl.biz.PropertyManager;
import com.bola.nwcl.biz.RoleManager;
import com.bola.nwcl.biz.DoorControlManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseListResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;

@Controller
@RequestMapping(value = "web/info")
public class GetInfoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private AdminManager adminManager;
	@Autowired
	private PropertyManager propertyManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private DoorControlManager doorControlManager;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private BuildingManager buildingManager;
	
	// 得到当前物业下的所有小区
	@ResponseBody
	@RequestMapping(value="/getCommunities",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public BaseListResult<Community> getCommunities(HttpServletRequest request, Long propertyId ) {
		BaseListResult<Community> baseResult=new BaseListResult<Community>("SUCCESS", "成功");
		try {
			CommunityExample example = new CommunityExample();
			example.or().andPropertyIdEqualTo(propertyId);
			List<Community> coms = communityManager.selectByExample(example);
			if (coms != null) {
				baseResult.setResult(coms);
			}			
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<Community>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<Community>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<Community>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseListResult<Community>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("：{}", baseResult.toString());
		return baseResult;
	}
	
	
	// 得到当前小区下的所有员工
	@ResponseBody
	@RequestMapping(value="/getEmployees",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public BaseListResult<Employee> getUser(HttpServletRequest request, Long communityId ) {
		BaseListResult<Employee> baseResult=new BaseListResult<Employee>("SUCCESS", "成功");
		
		try {
			EmployeeExample example = new EmployeeExample();
			example.or().andPropertyIdEqualTo(communityId);
			List<Employee> coms = employeeManager.selectByExample(example);
			if (coms != null) {
				baseResult.setResult(coms);
			}
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<Employee>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<Employee>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<Employee>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseListResult<Employee>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("：{}", baseResult.toString());
		return baseResult;
	}
	
	
		// 得到当前小区下的所有二级小区
		@ResponseBody
		@RequestMapping(value="/getBuildings",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
		public BaseListResult<Building> getSecondCommunities(HttpServletRequest request, Long communityId ) {
			BaseListResult<Building> baseResult=new BaseListResult<Building>("SUCCESS", "成功");
			try {
				BuildingExample example = new BuildingExample();
				example.or().andCommunityIdEqualTo(communityId);
				List<Building> bs = buildingManager.selectByExample(example);
				baseResult.setResult(bs);
			} catch (PatternException e) {
				logger.info("参数校验失败,{}", e.getMessage());
				baseResult = new BaseListResult<Building>(CodeEnum.PATTERN_ERROR.getCode(),
						e.getMessage());
			} catch (BusinessValidateException e) {
				logger.info("业务验证异常， 错误信息：{}", e.getMessage());
				baseResult = new BaseListResult<Building>(
						CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
			} catch (BusinessDealException e) {
				logger.info("业务处理异常， 错误信息：{}", e.getMessage());
				baseResult = new BaseListResult<Building>(
						CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("系统异常，{}", e.getMessage());
				baseResult = new BaseListResult<Building>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
			}
			logger.info("：{}", baseResult.toString());
			return baseResult;
		}
		
	

}
