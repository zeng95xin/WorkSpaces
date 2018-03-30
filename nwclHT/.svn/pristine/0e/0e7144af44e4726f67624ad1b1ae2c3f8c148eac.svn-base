package com.bola.nwcl.web.controller.questionnaireManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.QuestionnaireClassifyManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireClassify;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireClassifyExample;

/**
 * 问卷分类
 */

@Controller
@RequestMapping("web/QuestionnaireClassifyWebController")
public class QuestionnaireClassifyWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired QuestionnaireClassifyManager questionnaireClassifyManager;
	
	/**
	 * 跳转到问卷分类页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		return "questionnaireClassify/questionnaireClassify";
	}
	
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph,String classifyName){
		DataGrid dg = new DataGrid();
		QuestionnaireClassifyExample example=new QuestionnaireClassifyExample();
		if (StringUtils.isNotBlank(classifyName)) {
			example.or().andClassifynameLike("%"+classifyName+"%");
		}
		int total=questionnaireClassifyManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<QuestionnaireClassify> list=questionnaireClassifyManager.selectByExample(example);
		
		dg.setRows(list);
		dg.setTotal(total);
		return dg;

}
	/**
	 * 跳转到问卷调查管理添加页面
	 * 
	 */
	@RequestMapping("addPage")
	public String addPage(HttpServletRequest request){
		return "questionnaireClassify/questionnaireClassifyAdd";
	}


	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request,String classifyName) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			if (StringUtils.isBlank(classifyName)) {
				throw new PatternException("问卷分类不能为空！！！");
			}
			if (classifyName.length() > 40) {
				throw new PatternException("问卷分类名称不能超过40个字符！！！");
			}
			QuestionnaireClassify model=new QuestionnaireClassify();
			model.setClassifyname(classifyName);
			questionnaireClassifyManager.insert(model);
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
	 * 跳转到问卷调查管理修改页面
	 * 
	 */
	@RequestMapping("editPage")
	public String editPage(HttpServletRequest request,Long id){
		QuestionnaireClassify questionnaireClassify=questionnaireClassifyManager.selectByPrimaryKey(id);
		request.setAttribute("questionnaireClassify", questionnaireClassify);
		return "questionnaireClassify/questionnaireClassifyEdit";
	}


	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,String classifyName,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			if (StringUtils.isBlank(classifyName)) {
				throw new PatternException("问卷分类不能为空！！！");
			}
			QuestionnaireClassify model=new QuestionnaireClassify();
			model.setId(id);
			model.setClassifyname(classifyName);
			questionnaireClassifyManager.updateByPrimaryKeySelective(model);
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

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			questionnaireClassifyManager.deleteByPrimaryKey(id);

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
