package com.bola.nwcl.web.controller.convenience;

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
import com.bola.nwcl.biz.ConvenienceTelephoneManager;
import com.bola.nwcl.biz.ConvenienceTelephoneTypeManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseListResult;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephone;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneType;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneTypeExample;

/**
 * 便民电话管理 
 */
@Controller
@RequestMapping(value = "web/convenienceTelephone")
public class ConvenienceTelephoneWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConvenienceTelephoneManager convenienceTelephoneManager;
	@Autowired
	private ConvenienceTelephoneTypeManager convenienceTelephoneTypeManager;
	@Autowired
	private CommunityManager communityManager;
	/**
	 * 跳转到便民电话页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		ConvenienceTelephoneTypeExample typeExample = new ConvenienceTelephoneTypeExample();
		CommunityExample example_community = new CommunityExample();
		if (admin != null) {
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				typeExample.or().andCommunityIdIn(communityIds);
				example_community.or().andIdIn(communityIds);
			}
			List<ConvenienceTelephoneType> types = convenienceTelephoneTypeManager.selectByExample(typeExample);
			request.setAttribute("types", types);
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("comus", communities);
		}
		return "/convenience/convenienceTelephone";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(HttpServletRequest request, ConvenienceTelephone convenienceTelephone, PageHelper ph){	    
		
		return convenienceTelephoneManager.dataGrid(request, convenienceTelephone, ph); 
	      		
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
		ConvenienceTelephoneTypeExample typeExample = new ConvenienceTelephoneTypeExample();
		CommunityExample example_community = new CommunityExample();
		if (admin != null) {
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				typeExample.or().andCommunityIdIn(communityIds);
				example_community.or().andIdIn(communityIds);
			}
			List<ConvenienceTelephoneType> types = convenienceTelephoneTypeManager.selectByExample(typeExample);
			request.setAttribute("types", types);
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("comus", communities);
		}
		return "/convenience/convenienceTelephoneAdd";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, ConvenienceTelephone c) {

		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			convenienceTelephoneManager.insert(c);
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
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		
		ConvenienceTelephone c = convenienceTelephoneManager.selectByPrimaryKey(id);
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		CommunityExample example_community = new CommunityExample();
		if (admin != null) {
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
			}
			List<Community> communities = communityManager.selectByExample(example_community);
			request.setAttribute("comus", communities);
		}
		
		
		request.setAttribute("c", c);
		
		return "/convenience/convenienceTelephoneEdit";
	}

	/**
	 * 修改
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(ConvenienceTelephone c) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		
		try {
			convenienceTelephoneManager.updateByPrimaryKeySelective(c);
			
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
			convenienceTelephoneManager.deleteByPrimaryKey(id);
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
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getTypes")
	@ResponseBody
	public BaseListResult<ConvenienceTelephoneType> getTypes(Long communityId) {
		BaseListResult<ConvenienceTelephoneType> baseResult = new BaseListResult<ConvenienceTelephoneType>("SUCCESS", "成功");
		try{
			if (communityId == null) {
				throw new PatternException("Error");
			}
			ConvenienceTelephoneTypeExample example = new ConvenienceTelephoneTypeExample();
			example.or().andCommunityIdEqualTo(communityId);
			List<ConvenienceTelephoneType> list = convenienceTelephoneTypeManager.selectByExample(example);
			baseResult.setResult(list);
		}catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<ConvenienceTelephoneType>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<ConvenienceTelephoneType>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<ConvenienceTelephoneType>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseListResult<ConvenienceTelephoneType>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("得到分类返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/getType")
	@ResponseBody
	public List<ConvenienceTelephoneType> getType(ConvenienceTelephoneType type) {		
		List<ConvenienceTelephoneType> li=null;
		try{
			li=convenienceTelephoneTypeManager.typeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return li;
	}
}
