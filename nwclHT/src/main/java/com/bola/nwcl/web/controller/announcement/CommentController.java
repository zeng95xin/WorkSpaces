package com.bola.nwcl.web.controller.announcement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.AnnouncementRatingManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRating;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRatingExample;
import com.bola.nwcl.dal.mybatis.model.BizUser;
/**
 * 业主账号管理 
 */
@Controller
@RequestMapping(value = "web/AnComment")
public class CommentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BizUserManager userManager;
	@Autowired
	private AnnouncementRatingManager ratingManager;
	
	/**
	 * 评论管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String ratingManager(HttpServletRequest request, Long hid) {
		request.setAttribute("hid", hid);
		return "/announcement/comment";
	}
	
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Long hid, PageHelper ph){	    
		DataGrid dg = new DataGrid();
		AnnouncementRatingExample example = new AnnouncementRatingExample();
		example.or().andAnnouncementIdEqualTo(hid);
		int total=ratingManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		List<AnnouncementRating> list=ratingManager.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(AnnouncementRating model : list){
				BizUser user = userManager.selectByPrimaryKey(model.getBuserId());
				if (user != null) {
					model.setUserName(user.getNickname());
					model.setUserAccount(user.getPhone());
				}
			}
		}
		dg.setRows(list);
		dg.setTotal(total);
		return dg; 	      		
	}
	
	/**
	 * 修改评论页面
	 * @return
	 */
	@RequestMapping("/commentEditPage")
	public String  editpage(HttpServletRequest request, Long id) {
		AnnouncementRating model = ratingManager.selectByPrimaryKey(id);
		BizUser user = userManager.selectByPrimaryKey(model.getBuserId());
		if (user != null) {
			model.setUserName(user.getNickname());
			model.setUserAccount(user.getPhone());
		}
		request.setAttribute("model", model);
		return "/announcement/commentEdit";
	}
	
	
	@RequestMapping("/editComment")
	@ResponseBody
	public BaseResult edithotel(HttpServletRequest request, AnnouncementRating p ) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改成功");
		try {
			AnnouncementRating model = ratingManager.selectByPrimaryKey(p.getId());
			model.setContent(p.getContent());
			ratingManager.updateByPrimaryKeySelective(model);
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
	public BaseResult delete(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS","删除成功");
		try {
			AnnouncementRating img = ratingManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			ratingManager.deleteByPrimaryKey(id);
			
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
