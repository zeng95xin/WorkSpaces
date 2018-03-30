package com.bola.nwcl.web.controller.honorEnjoy;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.HonorProductManager;
import com.bola.nwcl.biz.HonourEnjoyManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProduct;
import com.bola.nwcl.web.model.HonourEnjoyInfoModel;
/**
 * 业主账号管理 
 */
@Controller
@RequestMapping(value = "web/hProduct")
public class HonorProductController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HonourEnjoyManager heManager;

	@Autowired
	private HonorProductManager productManager;
	
	/**
	 * 跳转到酒店床位管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/hProductManager")
	public String manager(HttpServletRequest request, Long hid) {
		request.setAttribute("hid", hid);
		return "/honor/hproduct";
	}
	
	/**
	 * 跳转到酒店床位添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/productAddPage")
	public String addhp(HttpServletRequest request, Long hid) {
		request.setAttribute("hid", hid);
		return "/honor/hproductAdd";
	}
	/**
	 * 跳转到酒店床位修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/productEditPage")
	public String edithp(HttpServletRequest request, Long id) {
		HonourEnjoyProduct model = productManager.selectByPrimaryKey(id);
		request.setAttribute("model", model);
		return "/honor/hproductEdit";
	}
	
	
	
	/**
	 * 跳转到温泉票管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/sProductManager")
	public String manager2(HttpServletRequest request, Long hid) {
		request.setAttribute("hid", hid);
		return "/honor/sproduct";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Long hid, PageHelper ph){	    
		return productManager.dataGrid(hid, ph); 	      		
	}
	
	/**
	 * 添加酒店公寓页面
	 * @return
	 */
	@RequestMapping("/hotelAddPage")
	public String  addhpage() {
		return "/honor/hotelAdd";
	}
	/**
	 * 修改酒店公寓页面
	 * @return
	 */
	@RequestMapping("/hotelEditPage")
	public String  editpage(HttpServletRequest request, Long id) {
		HonourEnjoyInfoModel model = heManager.getInfo(id);
		request.setAttribute("model", model);
		return "/honor/hotelEdit";
	}
	
	@RequestMapping("/addProduct")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, HonourEnjoyProduct p) {
		BaseResult baseResult = new BaseResult("SUCCESS","添加成功");
		try {
			HonourEnjoyProduct model = new HonourEnjoyProduct();
			BeanUtils.copyProperties(p, model);
			model.setRowAddTime(new Date());
			productManager.insert(model);
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
	
	@RequestMapping("/editProduct")
	@ResponseBody
	public BaseResult edithotel(HttpServletRequest request, HonourEnjoyProduct p ) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改成功");
		try {
			HonourEnjoyProduct model = productManager.selectByPrimaryKey(p.getId());
			BeanUtils.copyProperties(p, model);
			productManager.updateByPrimaryKeySelective(model);
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
			HonourEnjoyProduct img = productManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			productManager.deleteByPrimaryKey(id);
			
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
