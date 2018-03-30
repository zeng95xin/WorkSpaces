package com.bola.nwcl.web.controller.borrow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BorrowManager;
import com.bola.nwcl.biz.BorrowThingManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Borrow;
import com.bola.nwcl.dal.mybatis.model.BorrowThing;
import com.bola.nwcl.dal.mybatis.model.BorrowThingExample;


/**
 * 物品借用管理 
 */
@Controller
@RequestMapping(value = "web/borrow")
public class BorrowWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BorrowManager borrowManager;

	@Autowired 
	BorrowThingManager borrowThingManager;
	
	
	
	/**
	 * 跳转到维修单据页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/borrow/borrow";
	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(BorrowThing borrow, PageHelper ph,HttpServletRequest request){	    
		
		return borrowManager.dataGrid(borrow, ph, request); 
	      		
	}
	
	/**
	 * 
	 * 物品管理
	 * 
	 */
	@RequestMapping("/goodsManager")
	@ResponseBody
	public BaseResult goodsManager(Long id, Integer state) {
		BaseResult baseResult = new BaseResult("SUCCESS", "操作成功");
		try{
			Borrow model = new Borrow();
			model.setStatus(state);
			model.setId(id);
			borrowManager.updateByPrimaryKeySelective(model);
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
		logger.info("物品借用是否同意返回值：{}", baseResult.toString());
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
		if (id != null) {// 不能删除自己
			BorrowThingExample example = new BorrowThingExample();
			example.or().andBorrowIdEqualTo(id);
			borrowThingManager.deleteByExample(example);
			borrowManager.deleteByPrimaryKey(id);
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
