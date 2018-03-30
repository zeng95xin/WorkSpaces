package com.bola.nwcl.web.controller.community;

import java.util.Date;
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

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.DoorControlManager;
import com.bola.nwcl.biz.DoorRecordManager;
import com.bola.nwcl.biz.PropertyManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.DoorTypeMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.DoorControl;
import com.bola.nwcl.dal.mybatis.model.DoorRecord;
import com.bola.nwcl.dal.mybatis.model.DoorRecordExample;
import com.bola.nwcl.dal.mybatis.model.DoorType;

/**
 * 门禁管理 
 */
@Controller
@RequestMapping(value = "web/doorControl")
public class DoorContolWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DoorControlManager doorControlManager;
	@Autowired
	private CommunityManager comManager;
	@Autowired
	private PropertyManager propertyManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private DoorRecordManager doorRecordManager;
	@Autowired
	private BizUserManager bizUserManager;
	@Autowired
	private DoorTypeMapper doorTypeMapper;

	/**
	 * 跳转到门禁页面
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
			List<Community> communities = comManager.selectByExample(example_community);
			request.setAttribute("comus", communities);
		}
		return "/community/doorControl";
	}
	
	/**
	 * 跳转到记录页面
	 * 
	 * @return
	 */
	@RequestMapping("/recordManager")
	public String recordManager(HttpServletRequest request, Long hid) {
		request.setAttribute("hid", hid);
		return "/community/doorRecord";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DoorControl doorControl, PageHelper ph,HttpServletRequest request){	    

		return doorControlManager.dataGrid(doorControl, ph,request); 

	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/recordDataGrid")
	@ResponseBody
	public DataGrid dataGrid2(Long hid, PageHelper ph){	    
		DataGrid dg = new DataGrid();
		DoorRecordExample example = new DoorRecordExample();
		example.or().andDoorControlIdEqualTo(hid);
		int total=doorRecordManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		List<DoorRecord> list=doorRecordManager.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(DoorRecord model : list){
				BizUser user = bizUserManager.selectByPrimaryKey(model.getBuserId());
				if (user != null) {
					model.setUserName(user.getNickname());
					model.setUserAccount(user.getPhone());
				}
				if (model.getBuildingId() != null && model.getBuildingId() != -1) {
					Building building = buildingManager.selectByPrimaryKey(model.getBuildingId());
					if (building != null) {
						model.setBuildingName(building.getName());
					}
				}else{
					model.setBuildingName("大门");
				}
			}
		}
		dg.setRows(list);
		dg.setTotal(total);
		return dg; 	      		
	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		
		List<DoorType> doorTypes = doorTypeMapper.selectByExample(null);
		request.setAttribute("doorType", doorTypes);
		
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		if (admin != null) {
			CommunityExample example_community = new CommunityExample();
			BuildingExample example = new BuildingExample();
			if(admin.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_community.or().andIdIn(communityIds);
				example.or().andCommunityIdIn(communityIds);
			}
			List<Building> bs = buildingManager.selectByExample(example);
			request.setAttribute("buildings",bs);
			List<Community> communities = comManager.selectByExample(example_community);
			request.setAttribute("community", communities);
		}
		
		
		return "/community/doorControlAdd";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(DoorControl b,HttpServletRequest request) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			DoorControl model=new DoorControl();
			BeanUtils.copyProperties(b, model);
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin
			if(b.getCommunityId() == null){
				throw new PatternException("请选择小区！！！");
			}
			Community community=comManager.selectByPrimaryKey(b.getCommunityId());
			if (community!=null) {
				model.setCommunityName(community.getName());
				DoorType dType = doorTypeMapper.selectByPrimaryKey(community.getDoorTypeId());
				if (dType == null ) {
					throw new PatternException("当前小区未设置门禁类型，操作失败！");
				}
			}else{
				throw new PatternException("小区不存在！！！");
			}
			model.setCommunityId(b.getCommunityId());
			if (StringUtils.isBlank(b.getGateBuildingId())) {
				model.setGateBuildingId("-1");
			}
			model.setUseTimes(0L);
			model.setRowAddTime(new Date());
			doorControlManager.insert(model);

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
		List<Community> community=comManager.selectByExample(null);
		request.setAttribute("community",community);
		DoorControl b = doorControlManager.selectByPrimaryKey(id);
		request.setAttribute("model", b);
		BuildingExample example = new BuildingExample();
		example.or().andCommunityIdEqualTo(b.getCommunityId());
		List<Building> bs = buildingManager.selectByExample(example);
		request.setAttribute("buildings",bs);
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() != 1){
			Community com = comManager.selectByPrimaryKey(user.getCommunityId());
			request.setAttribute("com",com);
		}
		List<DoorType> doorTypes = doorTypeMapper.selectByExample(null);
		request.setAttribute("doorType", doorTypes);
		return "/community/doorControlEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(DoorControl b,HttpServletRequest request) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			DoorControl model=new DoorControl();
			BeanUtils.copyProperties(b, model);
			//判断是普通管理员还是admin
			if (StringUtils.isBlank(b.getGateBuildingId())) {
				model.setGateBuildingId("-1");
			}
			doorControlManager.updateByPrimaryKeySelective(model);
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
			DoorControl c = doorControlManager.selectByPrimaryKey(id);
			if ( c != null) {
				doorControlManager.deleteByPrimaryKey(id);
			}else{
				throw new PatternException("当前门禁不存在！");
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
