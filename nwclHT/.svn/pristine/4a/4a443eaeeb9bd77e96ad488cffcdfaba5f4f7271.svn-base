package com.bola.nwcl.web.controller.employee;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.DepartmentManager;
import com.bola.nwcl.biz.EmployeeManager;
import com.bola.nwcl.biz.PropertyManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.FileuploadHelper;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.Property;

/**
 * 员工管理
 * 
 * @author RJ
 * 
 */
@Controller
@RequestMapping(value = "web/employee")
public class EmployeeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private PropertyManager propertyManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private DepartmentManager departmentManager;

	/**
	 * 跳转角色管理页面
	 * 
	 * @return
	 */
	/*@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/employee/employee";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Employee employee, PageHelper ph) {
		return employeeManager.dataGrid(employee, ph);
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		List<Property> properties = propertyManager.selectByExample(null);
		request.setAttribute("props", properties);
		List<Department> dep = departmentManager.selectByExample(null);
		request.setAttribute("depts", dep);
		List<Community> coms = communityManager.selectByExample(null);
		request.setAttribute("coms", coms);
		return "/employee/employeeAdd";
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Employee imd = employeeManager.selectByPrimaryKey(id);
		Department d = departmentManager.selectByPrimaryKey(imd.getDeptId().intValue());
		if (d != null) {
			imd.setDeptName(d.getDeptName());
		}
		List<Property> properties = propertyManager.selectByExample(null);
		request.setAttribute("props", properties);
		List<Department> dep = departmentManager.selectByExample(null);		
		request.setAttribute("depts", dep);
		List<Community> coms = communityManager.selectByExample(null);
		request.setAttribute("coms", coms);
		request.setAttribute("model", imd);
		return "/employee/employeeEdit";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, Employee em,
			@RequestParam("img") MultipartFile img) {
		BaseResult baseResult = new BaseResult("SUCCESS", "成功");
		try {
			String imgDir = "/upload/files/deadPortrait/";
			String path1 = null;			
			if (null != img && !img.isEmpty()) {
				String imageMaxSize = "2";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName1 = System.currentTimeMillis() + "";// 无后缀的文件名
																	// 可按照时间戳生成一个
				FileuploadHelper.validate(img, imageType, imageMaxSize);
				FileName1 = FileUploadDeleteUtil.upload(img, request, imgDir,
						FileName1);
				path1 = FileName1;
			}
			Employee model = new Employee();
			BeanUtils.copyProperties(em, model);
			if (path1 != null) {
				model.setHeadPortrait(path1);			
			}
			model.setRowAddTime(new Date());
			employeeManager.insert(model);

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
	public BaseResult edit(HttpServletRequest request, Employee em,
			@RequestParam("img") MultipartFile img) {
		BaseResult baseResult = new BaseResult("SUCCESS", "编辑成功");
		try {
			String imgDir = "/upload/files/deadPortrait/";
			String path1 = null;			
			if (null != img && !img.isEmpty()) {
				String imageMaxSize = "2";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName1 = System.currentTimeMillis() + "";// 无后缀的文件名
																	// 可按照时间戳生成一个
				FileuploadHelper.validate(img, imageType, imageMaxSize);
				FileName1 = FileUploadDeleteUtil.upload(img, request, imgDir,
						FileName1);
				path1 = FileName1;
			}
			Employee model = employeeManager.selectByPrimaryKey(em.getId());
			BeanUtils.copyProperties(em, model);
			if (path1 != null) {
				model.setHeadPortrait(path1);			
			}
			model.setRowAddTime(new Date());
			employeeManager.updateByPrimaryKeySelective(model);

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
			employeeManager.deleteByPrimaryKey(id);
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
	}*/

}
