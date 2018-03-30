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
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.ValidateUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.SecondCommunityExample;
import com.bola.nwcl.web.model.BuildingWebModel;

/**
 * 楼宇管理 
 */
@Controller
@RequestMapping(value = "web/building")
public class BuildingWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private CommunityManager comManager;
	@Autowired
	private PropertyManager propertyManager;
	@Autowired
	private RoomManager roomManager;

	/**
	 * 跳转到楼栋页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> coms = comManager.selectByExample(null);
		request.setAttribute("comus", coms);
		return "/community/building";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Building building, PageHelper ph,HttpServletRequest request){	    

		return buildingManager.dataGrid(building, ph,request); 

	}
	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() == 1){
			List<Community> community=comManager.selectByExample(null);
			request.setAttribute("community",community);
		}else{
			if (user.getCommunityId() != null) {
				SecondCommunityExample example = new SecondCommunityExample();
				example.or().andCommunityIdEqualTo(user.getCommunityId());
			}
		}
		return "/community/buildingAdd";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(BuildingWebModel b,HttpServletRequest request) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			String vu=ValidateUtil.checkExcept(b);
			if (StringUtils.isNotEmpty(vu)) {
				throw new PatternException(vu);
			}
			//去掉前后空格 
			String name=b.getName().trim();
			//判断字符长度
			if (name.length()>20) {
				throw new PatternException("楼栋名称不能超出20个字符！！！");
			}
			Building model=new Building();
			BeanUtils.copyProperties(b, model);
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin
			if(user.getRoleId() == 1){
				if(b.getCommunityId() == null){
					throw new PatternException("请选择小区！！！");
				}
				model.setCommunityId(b.getCommunityId());
			}else{
				model.setCommunityId(user.getCommunityId());
			}
			buildingManager.insert(model);

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
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		Building b = buildingManager.selectByPrimaryKey(id);
		//判断是普通管理员还是admin
		if(user.getRoleId() == 1){
			List<Community> community=comManager.selectByExample(null);
			request.setAttribute("community",community);
			// 二级
			SecondCommunityExample example = new SecondCommunityExample();
			example.or().andCommunityIdEqualTo(b.getCommunityId());
		}else{
			if (user.getCommunityId() != null) {
				SecondCommunityExample example = new SecondCommunityExample();
				example.or().andCommunityIdEqualTo(user.getCommunityId());
			}
		}
		request.setAttribute("b", b);
		return "/community/buildingEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(BuildingWebModel b,HttpServletRequest request) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");

		try {
			String vu=ValidateUtil.checkExcept(b);
			if (StringUtils.isNotEmpty(vu)) {
				throw new PatternException(vu);
			}
			//去掉前后空格 
			String name=b.getName().trim();
			//判断字符长度
			if (name.length()>20) {
				throw new PatternException("小区名称不能超出20个字符！！！");
			}
			Building model=new Building();
			BeanUtils.copyProperties(b, model);
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin
			if(user.getRoleId() == 1){
				if(b.getCommunityId() == null){
					throw new PatternException("请选择小区！！！");
				}
				model.setCommunityId(b.getCommunityId());
			}else{
				model.setCommunityId(user.getCommunityId());
			}
			buildingManager.updateByPrimaryKeySelective(model);

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
			Building c = buildingManager.selectByPrimaryKey(id);
			if ( c != null) {
				RoomExample rExample = new RoomExample();
				rExample.or().andBuildingIdEqualTo(id);
				List<Room> bs = roomManager.selectByExample(rExample);
				if (bs != null && bs.size() > 0) {
					throw new PatternException("当前楼栋已被使用，无法删除！");
				}
				buildingManager.deleteByPrimaryKey(id);
			}else{
				throw new PatternException("当前楼栋不存在！");
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
