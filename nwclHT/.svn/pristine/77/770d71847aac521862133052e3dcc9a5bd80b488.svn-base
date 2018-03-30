package com.bola.nwcl.web.controller.suser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.SchedulingManager;
import com.bola.nwcl.biz.ServiceUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.CommunityExample;
import com.bola.nwcl.dal.mybatis.model.Scheduling;
import com.bola.nwcl.dal.mybatis.model.SchedulingExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample.Criteria;

/**
 * 员工排版
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping(value = "web/scheduling")
public class SchedulingController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ServiceUserManager serviceUserManager;
	@Autowired
	private BuildingManager buildingManager;
	@Autowired
	private CommunityManager communityManager;
	@Autowired
	private SchedulingManager schedulingManager;
	
	/**
	 * 跳转角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> list = communityManager.selectByExample(null);
		request.setAttribute("list", list);
		return "/scheduling/scheduling";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(HttpServletRequest request, Integer page, Integer rows, Long communityId, Integer type){
		if(page == null || page <1){
			page = 1;
		}
		if(rows == null || rows < 1){
			rows = 10;
		}
		DataGrid dg = schedulingManager.dataGrid(request, page, rows, communityId, type);
		return dg;
	}
	
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		// 小区楼栋
		BuildingExample bExample = new BuildingExample();
		CommunityExample example_community = new CommunityExample();
		if (user != null) {
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				bExample.or().andCommunityIdIn(communityIds);
				example_community.or().andIdIn(communityIds);
			}
		}
		List<Building> building = buildingManager.selectByExample(bExample);
		List<Community> communitys = communityManager.selectByExample(example_community);
		request.setAttribute("communitys", communitys);
		request.setAttribute("building", building);
		return "/scheduling/schedulingAdd";
	}
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		
		CommunityExample communityExample = new CommunityExample();
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user != null) {
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				communityExample.or().andIdIn(communityIds);
			}
		}
		List<Community> communitys = communityManager.selectByExample(communityExample);
		
		
		Scheduling s = schedulingManager.selectByPrimaryKey(id);
		ServiceUser su = serviceUserManager.selectByPrimaryKey(s.getSuserId());
		if(su != null){
			ServiceUserExample example_su = new ServiceUserExample();
			example_su.or().andRoleEqualTo(su.getRole()).andCommunityIdEqualTo(s.getCommunityId());
			List<ServiceUser> list_su = serviceUserManager.selectByExample(example_su);
			request.setAttribute("type", su.getRole());
			request.setAttribute("list_su", list_su);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		request.setAttribute("startTime", sdf.format(s.getDutyStartTime()));
		request.setAttribute("endTime", sdf.format(s.getDutyEndTime()));
		
		BuildingExample example_building = new BuildingExample();
		example_building.or().andCommunityIdEqualTo(s.getCommunityId());
		List<Building> list_building = buildingManager.selectByExample(example_building);
		request.setAttribute("communitys", communitys);
		request.setAttribute("list_building", list_building);
		request.setAttribute("s", s);
		
//		SchedulingExample example = new SchedulingExample();
//		example.or().andSuserIdEqualTo(s.getSuserId()).andCommunityIdEqualTo(s.getCommunityId());
//		List<Scheduling> schedulings = schedulingManager.selectByExample(example);
//		String building="";
//		for (Scheduling scheduling : schedulings) {
//			building +=  scheduling.getBuildingId()+",";
//		}
		String building="";
		building +=  s.getBuildingId()+",";
		request.setAttribute("building", building);
		return "/scheduling/schedulingEdit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,Integer type, Long communityId, String building,
			Long suserId, Long id,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")Date startTime,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")Date endTime
			
			) {
		BaseResult baseResult;
		try {
			baseResult = new BaseResult("SUCCESS","修改成功");
			Scheduling sc = schedulingManager.selectByPrimaryKey(id);
			
//			SchedulingExample schedulingExample = new SchedulingExample();
//			schedulingExample.or().andSuserIdEqualTo(suserId).andSuserIdNotEqualTo(sc.getSuserId());
//			List<Scheduling> scheduling = schedulingManager.selectByExample(schedulingExample);
//			if(scheduling.size() > 0){
//				throw new PatternException("该员工已排班！");
//			}
			
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			
//			SchedulingExample example = new SchedulingExample();
//			example.or().andSuserIdEqualTo(sc.getSuserId()).andCommunityIdEqualTo(sc.getCommunityId());
//			schedulingManager.deleteByExample(example);
			schedulingManager.deleteByPrimaryKey(id);
			
			String [] buildingIds = building.split(",");
			for (String buildingId : buildingIds) {
				buildingId = buildingId.replaceAll("/", "");
				Scheduling s = new Scheduling();
				s.setBuildingId(Long.parseLong(buildingId));
				s.setCommunityId(communityId);
				s.setDutyEndTime(endTime);
				s.setDutyStartTime(startTime);
				s.setSuserId(suserId);
				s.setSchedulingUserId(user.getId());
				s.setRowAddTime(new Date());
				schedulingManager.insert(s);
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/getBuilding")
	@ResponseBody
	public List<Building> getBuilding(HttpServletRequest request, Long communityId) {
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		BuildingExample example_building = new BuildingExample();
		if (user != null) {
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				example_building.or().andCommunityIdIn(communityIds).andCommunityIdEqualTo(communityId);
			}else{
				example_building.or().andCommunityIdEqualTo(communityId);
			}
		}
		List<Building> buildings = buildingManager.selectByExample(example_building); 
		return buildings;
	}
	
	@RequestMapping("/getSuser")
	@ResponseBody
	public List<ServiceUser> getSuser(HttpServletRequest request, Integer type, Long communityId) {
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		ServiceUserExample example = new ServiceUserExample();
		Criteria c = example.or();
		c.andRoleEqualTo(type);
		c.andCommunityIdEqualTo(communityId);
		List<ServiceUser> buildings = serviceUserManager.selectByExample(example); 
		return buildings;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request,Integer type, Long communityId, String building,
			Long suserId,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")Date startTime,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")Date endTime
			) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin
//			SchedulingExample schedulingExample = new SchedulingExample();
//			schedulingExample.or().andSuserIdEqualTo(suserId);
//			List<Scheduling> scheduling = schedulingManager.selectByExample(schedulingExample);
//			if(scheduling.size() > 0){
//				throw new PatternException("该员工已排班！");
//			}
			String [] buildingIds = building.split(",");
			for (String buildingId : buildingIds) {
				buildingId = buildingId.replaceAll("/", "");
				Scheduling s = new Scheduling();
				s.setBuildingId(Long.parseLong(buildingId));
				s.setCommunityId(communityId);
				s.setDutyEndTime(endTime);
				s.setDutyStartTime(startTime);
				s.setSuserId(suserId);
				s.setSchedulingUserId(user.getId());
				s.setRowAddTime(new Date());
				schedulingManager.insert(s);
			}
		}  catch (PatternException e) {
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
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request,Long id) {
		BaseResult result;
		try {
			result = new BaseResult("SUCCESS","删除成功");
			Scheduling sc = schedulingManager.selectByPrimaryKey(id);
			SchedulingExample example = new SchedulingExample();
			example.or().andSuserIdEqualTo(sc.getSuserId()).andCommunityIdEqualTo(sc.getCommunityId());
			schedulingManager.deleteByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			result = new BaseResult("false","删除失败");
		}
		return result;
	}
	
	@RequestMapping("/batchDelete")
	@ResponseBody
	public BaseResult batchDelete(HttpServletRequest request,String ids) {
		BaseResult result;
		try {
			result = new BaseResult("SUCCESS","删除成功");
			String[] ids_ = ids.split(",");
			for(String idStr : ids_){
				delete(request, Long.valueOf(idStr));
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = new BaseResult("false","删除失败");
		}
		return result;
	}

}
