package com.bola.nwcl.web.controller.activity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.ActivityRegisterManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.ActivityRegister;
import com.bola.nwcl.dal.mybatis.model.ActivityRegisterExample;
import com.bola.nwcl.dal.mybatis.model.ActivityRegisterExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.web.model.CommentWebModel;

@RequestMapping("web/ActivityRegisterWebController")
@Controller
public class ActivityRegisterWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired ActivityRegisterManager activityRegisterManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired RoomManager roomManager;
	/**
	 * 跳转到社区文化报名页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request,Long id) {
		request.setAttribute("id", id);
		return "/activity/activityRegister";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph,Long id,String account){
		DataGrid dg = new DataGrid();
		ActivityRegisterExample example=new ActivityRegisterExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		Criteria  c=example.or();
		c.andActivityIdEqualTo(id);
		//账号查询
		if(StringUtils.isNotBlank(account)){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+account+"%");
			List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
			if(bizUser.size()!=0){
				for (BizUser bizUser2 : bizUser) {
					list.add(bizUser2.getId());
				}
			}
			if (list.size()>0) {
				c.andBuserIdIn(list);
			} else {
				c.andBuserIdEqualTo(-1L);
			}
		}
		List<ActivityRegister> activityRegister=activityRegisterManager.selectByExample(example);
		List<CommentWebModel> list=null;
		list=new ArrayList<CommentWebModel>();
		if (activityRegister.size()>0) {
			for (ActivityRegister activityRegister2 : activityRegister) {
				CommentWebModel model=new CommentWebModel();
				ActivityRegister ne=activityRegisterManager.selectByPrimaryKey(activityRegister2.getId());
				if(ne!=null){
					model.setId(ne.getId());
					model.setRowAddTime(ne.getRowAddTime());
				}
				BizUser user=bizUserManager.selectByPrimaryKey(ne.getBuserId());
				if(user!=null){
					model.setNickName(user.getNickname());
					model.setAccount(user.getPhone());
					if(user.getCurrentRoomId() != null){
						Room room = roomManager.selectByPrimaryKey(user.getCurrentRoomId());
						if(room != null){
							model.setUnitNo(room.getUnitNo());
						}
					}
				}
				list.add(model);
			}
		}


		int total=activityRegisterManager.countByExample(example);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}


	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			activityRegisterManager.deleteByPrimaryKey(id);
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


	@RequestMapping("/batchDelete")
	@ResponseBody
	public BaseResult batchDelete(String ids) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			if (ids != null && ids.length() > 0) {
				for (String id : ids.split(",")) {
					if (id != null) {
						this.delete((long) Integer.parseInt(id));
					}
				}
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
}
