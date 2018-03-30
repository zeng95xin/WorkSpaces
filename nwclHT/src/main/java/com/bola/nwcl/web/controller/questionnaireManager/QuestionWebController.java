package com.bola.nwcl.web.controller.questionnaireManager;

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

import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.QuestionItemManager;
import com.bola.nwcl.biz.QuestionManager;
import com.bola.nwcl.biz.QuestionnaireManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.biz.RoomQuestionnaireAnswerManager;
import com.bola.nwcl.biz.RoomQuestionnaireManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Question;
import com.bola.nwcl.dal.mybatis.model.QuestionExample;
import com.bola.nwcl.dal.mybatis.model.QuestionItem;
import com.bola.nwcl.dal.mybatis.model.QuestionItemExample;
import com.bola.nwcl.web.model.QuestionWebModel;

@Controller
@RequestMapping("web/QuestionWebController")
public class QuestionWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired QuestionnaireManager questionnaireManager;
	@Autowired QuestionManager questionManager;
	@Autowired QuestionItemManager questionItemManager;
	@Autowired RoomQuestionnaireManager roomQuestionnaireManager;
	@Autowired RoomQuestionnaireAnswerManager roomQuestionnaireAnswerManager;
	@Autowired CommunityManager communityManager;
	@Autowired BuildingManager buildingManager;
	@Autowired RoomManager roomManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired AdminManager adminManager;

	@RequestMapping("/questionPage")
	public String questionPage(HttpServletRequest request,Long questionId){
		request.setAttribute("questionId", questionId);
		return "questionnaireManager/question";
	}

	@RequestMapping("/questionDataGrid")
	@ResponseBody
	public DataGrid questionDataGrid(PageHelper ph,Long questionId){
		DataGrid dg = new DataGrid();
		QuestionExample example=new QuestionExample();
		int total=questionManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.or().andQuestionnaireIdEqualTo(questionId);
		example.setOrderByClause("row_add_time DESC");
		List<Question> question=questionManager.selectByExample(example);
		List<QuestionWebModel> list=null;
		list=new ArrayList<QuestionWebModel>();
		if (question.size()>0) {
			for (Question question2 : question) {
				QuestionWebModel model = new QuestionWebModel();
				Question qu=questionManager.selectByPrimaryKey(question2.getId());
				if (qu!=null) {
					model.setId(qu.getId());
					model.setTitle(qu.getContent());
					model.setType(qu.getType());
				}
				QuestionItemExample questionItemExample=new QuestionItemExample();
				questionItemExample.or().andQuestionIdEqualTo(question2.getId());
				List<QuestionItem> questionItem = questionItemManager.selectByExample(questionItemExample);
				String content="";
				if (questionItem.size()>0) {
					for (QuestionItem questionItem2 : questionItem) {
						content += questionItem2.getContent();
						content += ",";
					}
				}
				model.setContent(content);
				list.add(model);
			}

		}

		
		dg.setRows(list);
		dg.setTotal(total);
		return dg;

	}
	/**
	 * 跳转到问卷调查管理添加页面
	 * 
	 */
	@RequestMapping("addPage")
	public String addPage(HttpServletRequest request,Long questionId){
		request.setAttribute("id", questionId);
		return "questionnaireManager/questionAdd";
	}


	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request,Integer type,String title,String []content,String contentsss,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			if (StringUtils.isBlank(title)) {
				throw new PatternException("题目不能为空！！！");
			}
			questionManager.add(request, type, title, content, contentsss, id);
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
		Question question=questionManager.selectByPrimaryKey(id);
		QuestionItemExample example=new QuestionItemExample();
		example.or().andQuestionIdEqualTo(question.getId());
		List<QuestionItem> list=questionItemManager.selectByExample(example);
		request.setAttribute("questionItem", list);
		request.setAttribute("question", question);
		return "questionnaireManager/questionEdit";
	}


	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,Integer type,String title,String []content,String contentsss,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			if (StringUtils.isBlank(title)) {
				throw new PatternException("题目不能为空！！！");
			}
			questionManager.edit(request, type, title, content, contentsss, id);
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
			QuestionItemExample QuestionItemExample=new QuestionItemExample();
			QuestionItemExample.or().andQuestionIdEqualTo(id);
			questionItemManager.deleteByExample(QuestionItemExample);
			questionManager.deleteByPrimaryKey(id);

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
