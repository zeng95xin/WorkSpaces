package com.bola.nwcl.web.controller.maintenance;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.MaintenanceImgManager;
import com.bola.nwcl.biz.MaintenanceManager;
import com.bola.nwcl.biz.MaintenanceOfferManager;
import com.bola.nwcl.biz.RepairmanStatusManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.biz.ServiceUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.notify.JPushUtil_Back;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImgExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOffer;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOfferExample;
import com.bola.nwcl.dal.mybatis.model.RepairmanStatus;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;


/**
 * 维修价格管理 
 */
@Controller
@RequestMapping(value = "web/maintenance")
public class MaintenanceWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  MaintenanceManager maintenanceManager;
	@Autowired
	private  ServiceUserManager serviceUserManager;
	@Autowired
	private  BizUserManager bizUserManager;
	@Autowired
	private  RoomManager roomManager;
	@Autowired
	private  CommunityManager comManager;
	@Autowired
	private  RepairmanStatusManager repairmanStatusManager;
	@Autowired
	private  MaintenanceOfferManager maintenanceOfferManager;
	@Autowired
	private  MaintenanceImgManager maintenanceImgManager;
	
	/**
	 * 跳转到维修单据页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> coms = comManager.selectByExample(null);
		request.setAttribute("comus", coms);
		return "/maintain/maintain/maintain";
	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Maintenance Maintenance, PageHelper ph,HttpServletRequest request){	    
		
		return maintenanceManager.dataGrid(Maintenance, ph,request); 
	      		
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		
		return "/maintain/maintenanceAdd";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(Maintenance m) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			maintenanceManager.insert(m);
			
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
	@RequestMapping("/visit")
	@ResponseBody
	public BaseResult visit(HttpServletRequest request, Long id, String content) {
		Maintenance m = maintenanceManager.selectByPrimaryKey(id);
		m.setVisit(1);
		m.setReason(content);
		maintenanceManager.updateByPrimaryKeySelective(m);
		BaseResult baseResult = new BaseResult("SUCCESS", "回访成功");
		return baseResult;
	}
	
	/**
	 * 跳转到标签修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		
		Maintenance m = maintenanceManager.selectByPrimaryKey(id);
		if (m != null) {
			Room r = roomManager.selectByPrimaryKey(m.getRoomId());
			if (r != null) {
				m.setRoom(r.getUnitNo());
			}
			ServiceUser su = serviceUserManager.selectByPrimaryKey(m.getRepairMainId());
			if (su != null) {
				m.setRepairman(su.getName());
			}
			BizUser user = bizUserManager.selectByPrimaryKey(m.getBuserId());
			if (user != null) {
				m.setUser(user.getPhone());
			}
			MaintenanceOfferExample offerExample = new MaintenanceOfferExample();
			offerExample.or().andMaintenanceIdEqualTo(m.getId());
			List<MaintenanceOffer> olist = maintenanceOfferManager.selectByExample(offerExample);
			if (olist != null && olist.size() > 0) {
				Long money= 0l;
				for (MaintenanceOffer o : olist) {
					money += o.getProjectPrice();
					m.setPayStatus(o.getStatus());
				}
				if (money > 0) {
					money = money/100;
				}
				m.setPayMoney(money);
			}
			MaintenanceImgExample imgExample = new MaintenanceImgExample();
			imgExample.or().andMaintenanceIdEqualTo(m.getId());
			List<MaintenanceImg> imgs = maintenanceImgManager.selectByExample(imgExample);
			if (imgs != null && imgs.size() > 0) {
				m.setImages(imgs);
			}
		}
		request.setAttribute("m", m);
		ServiceUserExample example = new ServiceUserExample();
		example.or().andRoleEqualTo(3);
		List<ServiceUser> su = serviceUserManager.selectByExample(example);
		request.setAttribute("serviceUsers", su);
		return "/maintain/maintain/maintainEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(Maintenance m) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			Maintenance model = maintenanceManager.selectByPrimaryKey(m.getId());
			if (model == null) {
				throw new PatternException("消息不存在！");
			}
			if(null != m.getRepairMainId()){
				model.setRepairMainId(m.getRepairMainId());
				m.setStatus(3);
			}
			model.setDescription(m.getDescription());
			model.setProspectingDescription(m.getProspectingDescription());
			
			maintenanceManager.updateByPrimaryKeySelective(model);
			
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
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try{
			Maintenance m = maintenanceManager.selectByPrimaryKey(id);
			if (m == null) {
				throw new PatternException("消息不存在！");
			}
			ServiceUserExample example_suser = new ServiceUserExample();
			example_suser.or().andCommunityIdEqualTo(m.getCommunityId());
			List<ServiceUser> list_suser = serviceUserManager.selectByExample(example_suser);
			for (Iterator<ServiceUser> iterator = list_suser.iterator(); iterator.hasNext();) {
				ServiceUser serviceUser = iterator.next();
				try{
					JPushUtil_Back.sendBackMsgByType(serviceUser.getId(), "delete", "-1:"+m.getId() + ":" + m.getStatus());
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			
			maintenanceManager.deleteByPrimaryKey(id);
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
	 * 设置师傅繁忙状态页面
	 */
	@RequestMapping("/setStatusPage")
	public String setrsPage(HttpServletRequest request) {
		RepairmanStatus s = repairmanStatusManager.selectByPrimaryKey(1L);
		if (s != null) {
			request.setAttribute("model", s);
		}
		return "/maintain/maintain/setRepairmanStatus";
	}
	
	/**
	 * 设置师傅繁忙状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/setMansStatus")
	@ResponseBody
	public BaseResult setStatus(HttpSession session, RepairmanStatus rs) {
		BaseResult baseResult = new BaseResult("SUCCESS", "设置成功");
		try{
			if (rs.getId() != null && rs.getId() == 1) {
				RepairmanStatus s = repairmanStatusManager.selectByPrimaryKey(1L);
				s.setStatus(rs.getStatus());
				repairmanStatusManager.updateByPrimaryKeySelective(s);
			}else if (rs.getId() == null) {
				RepairmanStatus s = new RepairmanStatus();
				s.setId(1L);
				s.setStatus(rs.getStatus());
				repairmanStatusManager.insert(s);
			}{
				
			}
			//RepairmanStatus
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
