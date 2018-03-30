package com.bola.nwcl.web.controller.suser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.RepairmanTypeMapper;
import com.bola.nwcl.dal.mybatis.model.RepairmanType;
import com.bola.nwcl.dal.mybatis.model.RepairmanTypeExample;

/**
 * 员工管理
 * 
 * @author RJ
 * 
 */
@Controller
@RequestMapping(value = "web/repairmanType")
public class RepairmanTypeController {
	
	@Autowired private RepairmanTypeMapper repairmanTypeMapper;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 跳转角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/repairmanType/repairmanType";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph,HttpServletRequest request) {
		RepairmanTypeExample example_type = new RepairmanTypeExample();
		example_type.setLimit(ph.getRows());
		example_type.setOffset((ph.getPage()-1) * ph.getRows());
		List<RepairmanType> list = repairmanTypeMapper.selectByExample(example_type);
		int count = repairmanTypeMapper.countByExample(null);
		DataGrid dg = new DataGrid();
		dg.setRows(list);
		dg.setTotal(count);
		return dg;
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/repairmanType/repairmanTypeAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, RepairmanType type) {
		BaseResult baseResult = new BaseResult("SUCCESS", "成功");
		try {
//			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			repairmanTypeMapper.insert(type);
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
		RepairmanType type = repairmanTypeMapper.selectByPrimaryKey(id);
		request.setAttribute("model", type);
		return "/repairmanType/repairmanTypeEdit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, RepairmanType type) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
//			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			repairmanTypeMapper.updateByPrimaryKeySelective(type);
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
			repairmanTypeMapper.deleteByPrimaryKey(id);
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
