package com.bola.nwcl.web.controller.community;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.PropertyManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.ValidateUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.DoorTypeMapper;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.DoorType;
import com.bola.nwcl.dal.mybatis.model.Property;
import com.bola.nwcl.web.model.CommunityWebModel;

/**
 * 小区管理 
 */
@Controller
@RequestMapping(value = "web/community")
public class CommunityWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private PropertyManager propertyManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private DoorTypeMapper doorTypeMapper;
	/**
	 * 跳转到物业公司页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/community/community";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Community community, PageHelper ph,HttpServletRequest request){	    
		return communityManager.dataGrid(community, ph,request); 
	      		
	}
	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		List<Property> property=propertyManager.selectByExample(null);
		request.setAttribute("property",property);
		return "/community/communityAdd";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(CommunityWebModel c) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			String vu=ValidateUtil.checkExcept(c);
			if (StringUtils.isNotEmpty(vu)) {
				throw new PatternException(vu);
			}
			//去掉前后空格 
			String name=c.getName().trim();
			//判断字符长度
			if (name.length()>20) {
				throw new PatternException("小区名称不能超出20个字符！！！");
			}
			Community model=new Community();
			BeanUtils.copyProperties(c, model);
			communityManager.insert(model);
			
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
		Community c = communityManager.selectByPrimaryKey(id);
		request.setAttribute("c", c);
		List<Property> property=propertyManager.selectByExample(null);
		request.setAttribute("property",property);
		List<DoorType> doorTypes =doorTypeMapper.selectByExample(null);
		request.setAttribute("doorTypes",doorTypes);
		return "/community/communityEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(CommunityWebModel c) {

		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			String vu=ValidateUtil.checkExcept(c);
			if (StringUtils.isNotEmpty(vu)) {
				throw new PatternException(vu);
			}
			//去掉前后空格 
			String name=c.getName().trim();
			//判断字符长度
			if (name.length()>20) {
				throw new PatternException("小区名称不能超出20个字符！！！");
			}
			Community model=new Community();
			BeanUtils.copyProperties(c, model);
			model.setId(c.getId());
			model.setAddress(c.getAddress());
			model.setContactPeople(c.getContactPeople());
			model.setContactPhone(c.getContactPhone());
			model.setName(c.getName());
			model.setPropertyId(c.getPropertyId());
			model.setSerial(c.getSerial());
			model.setNote(c.getNote());
			
			communityManager.updateByPrimaryKeySelective(model);
			
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

	/**
	 * 删除标签
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
			Community c = communityManager.selectByPrimaryKey(id);
			if ( c != null) {
				BuildingExample beExample = new BuildingExample();
				beExample.or().andCommunityIdEqualTo(id);
				List<Building> bs = buildingManager.selectByExample(beExample);
				if (bs != null && bs.size() > 0) {
					throw new PatternException("当前小区已被使用，无法删除！");
				}
				communityManager.deleteByPrimaryKey(id);
			}else{
				throw new PatternException("当前小区不存在！");
			}

		
		}
		catch (PatternException e) {
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
