package com.bola.nwcl.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.VisitorPassportRecordManager;
import com.bola.nwcl.biz.VisitorsManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Community;



/**
 * 社区文化
 * 
 */
@RequestMapping("api/visitorPassportRecord")
@Controller
public class VisitorPassportRecordController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private VisitorsManager visitorsManager;
	@Autowired private VisitorPassportRecordManager visitorPassportRecordManager;
	@Autowired private CommunityManager communityManager;
	
	@RequestMapping("/reviceNotice")
	@ResponseBody
//	{"noticeName":"visitorQrcodeOpen",
//	"msg":{"qrcodeKey":"08B1D0EB63031EEDE4A32487F11863202F5DC8CBDEB091C014168E2B6AFFE8FE5055CAF086F3B6CC9AAC27B91877727B1877727B1831BC33204905CA",
//	"deviceCode":"A28F366B8954E","openTime":1482140299000,"deviceId":2789}}
//	{"noticeName":"ownerQrcodeOpen",
//	"msg":{"lingLingId":"03f9006355","deviceCode":"A28F366B8954E","openTime":1482140589000,"deviceId":2789}}
	public BaseResult reviceNotice(HttpServletRequest request, String noticeMsg) {
		visitorPassportRecordManager.updateReviceNotice(noticeMsg);
		BaseResult result = new BaseResult("SUCCESS", "成功");
		return result;
	}
	
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		List<Community> list_communitys = communityManager.selectByExample(null);
		request.setAttribute("communitys", list_communitys);
		return "visitorPassportRecord/visitorPassportRecord";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(HttpServletRequest request, PageHelper ph, Long communityId, 
			Long type, String name, String phone,
			String roomNo, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime
			){
		Map<String, Object> params = new HashMap<>(10);
		params.put("roomNo", ph.getPage());
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("page", ph.getPage());
		params.put("rows", ph.getRows());
		params.put("communityId", communityId);
		params.put("type", type);
		@SuppressWarnings("unchecked")
		List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
		params.put("communityIds", communityIds);
		if(StringUtils.hasText(name)){
			name = "%" + name + "%";
			params.put("name", name);
		}
		if(StringUtils.hasText(phone)){
			phone = "%" + phone + "%";
			params.put("phone", phone);
		}
		DataGrid dg = visitorsManager.dataGridVisitorPassportRecord(params);
		return dg;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			visitorsManager.deleteVisitorPassportRecord(id);
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
			visitorsManager.deleteBatchVisitorPassportRecord(ids);
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
