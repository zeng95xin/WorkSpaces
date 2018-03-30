package com.bola.nwcl.web.controller.memu;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.MemuManager;
import com.bola.nwcl.biz.RoleResourceManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.mybatis.model.Memu;
import com.bola.nwcl.dal.mybatis.model.MemuExample;
import com.bola.nwcl.dal.mybatis.model.RoleResource;
import com.bola.nwcl.dal.mybatis.model.RoleResourceExample;
import com.bola.nwcl.web.model.Resource;
import com.bola.nwcl.web.model.Tree;

/**
 * 角色管理
 * 
 * @author RJ
 * 
 */
@Controller
@RequestMapping(value = "web/menu")
public class MemuController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
		return "/memu/memu";
	}

	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Resource> treeGrid(Long id) {
		return memuManager.treeGrid(id);
	}
	
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree() {
		return memuManager.allTree();
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		return memuManager.tree( session);
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/memu/memuAdd";
	}
	
	/**
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Memu m = memuManager.selectByPrimaryKey(id);
		Resource resource = new Resource();
		resource.setId(m.getId());
		resource.setName(m.getMemuName());
		resource.setRemark(m.getNote());
		resource.setPid(m.getParentMemuId());
		resource.setIconCls(m.getIcon());
		resource.setTypeId(m.getMemuType());
		resource.setUrl(m.getMemuUrl());
		request.setAttribute("resource", resource);
		return "/memu/memuEdit";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, Resource re) {
		BaseResult baseResult = new BaseResult("SUCCESS","成功");
		try {
			Memu model = new Memu();
			model.setMemuName(re.getName());
			model.setMemuType(re.getTypeId());
			model.setParentMemuId(re.getPid());
			model.setMemuUrl(re.getUrl());
			model.setIcon(re.getIconCls());
			model.setNote(re.getRemark());
			model.setRowAddTime(new Date());
			memuManager.insertAndGetId(model);		
			Long mid = model.getId();
			if (mid != null){
				RoleResource ror = new RoleResource();
				ror.setResourceId(mid);
				ror.setRoleId(1);
				ror.setRowAddTime(new Date());
				roleResourceManager.insert(ror);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, Resource re ) {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		try {
			Memu model = memuManager.selectByPrimaryKey(re.getId());
			model.setMemuName(re.getName());
			model.setMemuType(re.getTypeId());
			model.setParentMemuId(re.getPid());
			model.setMemuUrl(re.getUrl());
			model.setIcon(re.getIconCls());
			model.setNote(re.getRemark());
			memuManager.updateByPrimaryKeySelective(model);		
			
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
			RoleResourceExample resourceExample = new RoleResourceExample();
			resourceExample.or().andResourceIdEqualTo(id);
			roleResourceManager.deleteByExample(resourceExample);
			MemuExample memuExample = new MemuExample();
			memuExample.or().andParentMemuIdEqualTo(id);
			List<Memu> ms = memuManager.selectByExample(memuExample);
			if (ms != null && ms.size() > 0) {
				for(Memu m : ms){
					if (m.getId() != null) {
						RoleResourceExample resExample = new RoleResourceExample();
						resExample.or().andResourceIdEqualTo(m.getId());
						roleResourceManager.deleteByExample(resExample);
					}
				}
			}
			memuManager.deleteByExample(memuExample);
			memuManager.deleteByPrimaryKey(id);
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
