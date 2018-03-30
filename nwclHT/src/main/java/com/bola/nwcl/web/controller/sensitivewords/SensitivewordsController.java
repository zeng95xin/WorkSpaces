package com.bola.nwcl.web.controller.sensitivewords;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.SensitiveManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.Json;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Sensitive;




@Controller
@RequestMapping(value = "web/Sensitivewords")
public class SensitivewordsController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SensitiveManager sensitiveManager;
	@Autowired
	private AdminManager adminManager;
	
	/**
	 * <p>敏感词菜单跳转页</p>
	 * <p>2016-03-14</p>
	 * */	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/sensitivewords/words";
	}
	
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/sensitivewords/wordsAdd";
	}
	
	/**
	 * 跳转到标签修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Sensitive sensitive = sensitiveManager.selectByPrimaryKey(id);
		request.setAttribute("t", sensitive);
		return "/sensitivewords/wordsEdit";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Sensitive sensitive,PageHelper ph) {
		return sensitiveManager.dataGrid(sensitive, ph);
	}
	
	@RequestMapping("/GetName")
	@ResponseBody
	public Json dataGrid(Long userId) {
		Json json=new Json();
		try {
			Admin admin=adminManager.selectByPrimaryKey(userId);
			json.setMsg(admin.getName());
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		json.setSuccess(true);
		return  json;
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(Sensitive sensitive,HttpServletRequest request,HttpSession session) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			Long userId=Long.parseLong(session.getAttribute("userId").toString());
			sensitive.setBuserId(userId);
			sensitiveManager.insert(sensitive);
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
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(Sensitive sensitive,HttpServletRequest request) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			sensitiveManager.updateByPrimaryKeySelective(sensitive);	
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
			sensitiveManager.deleteByPrimaryKey(id);
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
