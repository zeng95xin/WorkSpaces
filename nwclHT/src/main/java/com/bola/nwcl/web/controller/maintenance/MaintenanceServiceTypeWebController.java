package com.bola.nwcl.web.controller.maintenance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.MaintenanceServiceTypeManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceServiceType;

/**
 * 维修价格管理 
 */
@Controller
@RequestMapping(value = "web/maintenanceServiceType")
public class MaintenanceServiceTypeWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private  MaintenanceServiceTypeManager maintenanceServiceTypeManager;
	@Autowired
	private  CommunityManager communityManager;
	
	/**
	 * 跳转到维修价格页面
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
			request.setAttribute("comus", communities);
		}
		return "/maintain/maintenanceServiceType";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(HttpServletRequest request, MaintenanceServiceType maintenanceServiceType, PageHelper ph){	    
		
		return maintenanceServiceTypeManager.dataGrid(request, maintenanceServiceType, ph); 
	      		
	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
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
			request.setAttribute("comus", communities);
		}
		return "/maintain/maintenanceServiceTypeAdd";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, MaintenanceServiceType m) {

		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			maintenanceServiceTypeManager.insert(m);
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
	 * 跳转到标签修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		MaintenanceServiceType m = maintenanceServiceTypeManager.selectByPrimaryKey(id);
		request.setAttribute("m", m);
		return "/maintain/maintenanceServiceTypeEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(MaintenanceServiceType m) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		
		try {
			maintenanceServiceTypeManager.updateByPrimaryKeySelective(m);
			
		}catch (PatternException e) {
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
	/**
	 * 跳转到分类页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/classifyPage")
	public String classifyPage(HttpServletRequest request, Long type) {
		request.setAttribute("type", type);
		return "/maintain/maintenancePrice";
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id, HttpSession session) {
		/*SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil
				.getSessionInfoName());*/
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try{
		if (id != null) {// 不能删除自己
			maintenanceServiceTypeManager.deleteByPrimaryKey(id);
		 }
		}catch (PatternException e) {
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
