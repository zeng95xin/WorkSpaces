package com.bola.nwcl.web.controller.hang;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.HangPictureManager;
import com.bola.nwcl.biz.RepairmanDetailManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.biz.ServiceUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.JPushUtil;
import com.bola.nwcl.common.util.notify.JPushUtil_Buser;
import com.bola.nwcl.common.util.notify.NotifyUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetail;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetailExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample.Criteria;

/**
 * 挂画服务管理
 */
@Controller
@RequestMapping(value = "web/hangPicture")
public class HangPictureWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HangPictureManager hangPictureManager;

	@Autowired
	private ServiceUserManager serviceUserManager;
	@Autowired
	RepairmanDetailManager repairmanDetailManager;
	@Autowired
	BizUserManager bizUserManager;
	@Autowired RoomManager roomManager;
	@Autowired
	NotifyUtil notifyUtil;
	@Autowired CommunityManager communityManager;

	/**
	 * 跳转到维修单据页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		return "/hang/hangPicture";
	}

	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Long communityId, String key, String value, PageHelper ph,HttpServletRequest request) {
		return hangPictureManager.dataGrid(communityId, key, value, ph, request);

	}

	/**
	 * 跳转到标签修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		HangPicture t = hangPictureManager.selectByPrimaryKey(id);
		request.setAttribute("t", t);
		return "/hang/hangPictureEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, HangPicture t) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");

		try {

			hangPictureManager.updateByPrimaryKeySelective(t);

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
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id, HttpSession session) {
		/*
		 * SessionInfo sessionInfo = (SessionInfo)
		 * session.getAttribute(ConfigUtil .getSessionInfoName());
		 */
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			if (id != null) {// 不能删除自己
				hangPictureManager.deleteByPrimaryKey(id);
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
		logger.info("删除返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/assignPage")
	public String assignPage(HttpServletRequest request, Long id) {
		HangPicture t = hangPictureManager.selectByPrimaryKey(id);
		request.setAttribute("t", t);
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		ServiceUserExample example = new ServiceUserExample();
		Criteria c = example.or();
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			c.andCommunityIdEqualTo(user.getCommunityId());
		}
		c.andRoleEqualTo(3);
		List<ServiceUser> susers = serviceUserManager.selectByExample(example);
		request.setAttribute("susers", susers);
		return "/hang/assign";
	}

	@RequestMapping("/assign")
	@ResponseBody
	public BaseResult assign(HttpServletRequest request, HangPicture hp,Long repairmanId,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "指派成功");
		try {
			hp = hangPictureManager.selectByPrimaryKey(hp.getId());
			if (hp == null) {
				throw new BusinessValidateException("挂画单已经被删除了");
			}
			if (hp.getStatus() > 0) {
				throw new BusinessValidateException("挂画单已经被指派了");
			}
			if (hp.getStatus() == -1) {
				throw new BusinessValidateException("挂画单已经被取消了");
			}
			
			ServiceUser susers = serviceUserManager.selectByPrimaryKey(repairmanId);
			RepairmanDetailExample example = new RepairmanDetailExample();
			example.or().andRepairmainIdEqualTo(susers.getId());
			RepairmanDetail re = repairmanDetailManager.selectOneByExample(example);
			String title = "挂画单消息通知！";
			String content = "尊敬的业主，你的挂画订单我们已经派出工作人员为你上门服务，预计上门时间"
					+ hp.getExpectTime() + "。如有疑问请联系工作人员" + susers.getName()
					+ "，联系电话：" + re.getPhone()+"，感谢您的支持，祝您生活愉快！";
			JPushChatModel model = new JPushChatModel();
			
			BizUser bizUser=bizUserManager.selectByPrimaryKey(hp.getBuserId());
			//业主推送
			model.setType("3:"+id+":-1");
			model.setTitle(title);
			model.setContent(content);
			JPushUtil_Buser.sendToBuser(model, bizUser);
			//业主消息通知
			// 得到后台登录用户
			Admin user = (Admin) request.getSession().getAttribute("loginUser");
			notifyUtil.send("挂画单消息通知", content, "0:" + id+ ":-1", user.getId(), bizUser.getId(), null);
			
			Room room = roomManager.selectByPrimaryKey(bizUser.getCurrentRoomId());
			//维修人员推送
			List<String> alias = new ArrayList<String>();
			alias.add(susers.getId() + "suser");
			String content2 = "维修人员你好，业主"+bizUser.getName()+"需要挂画服务，请你在"
			+hp.getExpectTime()+"左右去上门服务。业主所住房间编号为"+room.getUnitNo()+"，业主联系电话为："+bizUser.getPhone()+"。";
			model.setContent(content2);
			JPushUtil.jPushAndroidAlert(alias, model);
			
			hp.setStatus(1);
			hp.setHangId(repairmanId);
			hangPictureManager.updateByPrimaryKeySelective(hp);
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
