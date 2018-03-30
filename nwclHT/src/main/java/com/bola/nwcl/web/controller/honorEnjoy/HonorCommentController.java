package com.bola.nwcl.web.controller.honorEnjoy;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.HonorCommentManager;
import com.bola.nwcl.biz.HonorProductManager;
import com.bola.nwcl.biz.HonourEnjoyImgManager;
import com.bola.nwcl.biz.HonourEnjoyManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProduct;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRating;
import com.bola.nwcl.web.model.HonourEnjoyInfoModel;
/**
 * 业主账号管理 
 */
@Controller
@RequestMapping(value = "web/hComment")
public class HonorCommentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HonourEnjoyManager heManager;
	@Autowired
	private HonorProductManager productManager;
	@Autowired
	private HonorCommentManager commentManager; 
	@Autowired
	private BizUserManager userManager;
	
	/**
	 * 评论管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/hCommentManager")
	public String manager(HttpServletRequest request, Long hid) {
		request.setAttribute("hid", hid);
		return "/honor/comment";
	}
	
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Long hid, PageHelper ph){	    
		return commentManager.dataGrid(hid, ph); 	      		
	}
	
	/**
	 * 修改评论页面
	 * @return
	 */
	@RequestMapping("/commentEditPage")
	public String  editpage(HttpServletRequest request, Long id) {
		HonourEnjoyRating model = commentManager.selectByPrimaryKey(id);
		BizUser user = userManager.selectByPrimaryKey(model.getBuserId());
		if (user != null) {
			model.setUserNickname(user.getNickname());
			model.setUserAccount(user.getPhone());
		}
		request.setAttribute("model", model);
		return "/honor/commentEdit";
	}
	
	
	@RequestMapping("/editComment")
	@ResponseBody
	public BaseResult edithotel(HttpServletRequest request, HonourEnjoyRating p ) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改成功");
		try {
			HonourEnjoyRating model = commentManager.selectByPrimaryKey(p.getId());
			model.setContent(p.getContent());
			commentManager.updateByPrimaryKeySelective(model);
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
			HonourEnjoyRating img = commentManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			commentManager.deleteByPrimaryKey(id);
			
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
