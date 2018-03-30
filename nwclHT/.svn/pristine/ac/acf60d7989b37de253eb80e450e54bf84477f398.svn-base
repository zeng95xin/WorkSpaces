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
import com.bola.nwcl.biz.QuestionnaireClassifyManager;
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
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Question;
import com.bola.nwcl.dal.mybatis.model.QuestionExample;
import com.bola.nwcl.dal.mybatis.model.QuestionItemExample;
import com.bola.nwcl.dal.mybatis.model.Questionnaire;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireClassify;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireExample;
import com.bola.nwcl.web.model.QuestionnaireManagerWebModel;

@Controller
@RequestMapping("web/QuestionnaireManagerWebController")
public class QuestionnaireManagerWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired QuestionnaireManager questionnaireManager;
	@Autowired QuestionnaireClassifyManager questionnaireClassifyManager;
	@Autowired QuestionManager questionManager;
	@Autowired QuestionItemManager questionItemManager;
	@Autowired RoomQuestionnaireManager roomQuestionnaireManager;
	@Autowired RoomQuestionnaireAnswerManager roomQuestionnaireAnswerManager;
	@Autowired CommunityManager communityManager;
	@Autowired BuildingManager buildingManager;
	@Autowired RoomManager roomManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired AdminManager adminManager;
	/**
	 * 跳转到问卷调查管理页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		return "questionnaireManager/questionnaireManager";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph){
		DataGrid dg = new DataGrid();
		QuestionnaireExample example=new QuestionnaireExample();
		int total=questionnaireManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<Questionnaire> questionnaire=questionnaireManager.selectByExample(example);
		List<QuestionnaireManagerWebModel> list=null;
		list=new ArrayList<QuestionnaireManagerWebModel>();
		if (questionnaire.size()>0) {
			for (Questionnaire questionnaire2 : questionnaire) {
				QuestionnaireManagerWebModel model=new QuestionnaireManagerWebModel();
				Questionnaire qu=questionnaireManager.selectByPrimaryKey(questionnaire2.getId());
				if (qu!=null) {
					model.setId(qu.getId());
					model.setTitle(qu.getTitle());
					model.setRowAddTime(qu.getRowAddTime());
				}
				Admin admin=adminManager.selectByPrimaryKey(questionnaire2.getPublishPeopleId());
				if (admin!=null) {
					model.setPublishPeopleName(admin.getEmployeeName());
				}
				
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
	public String addPage(HttpServletRequest request){
		List<QuestionnaireClassify> questionnaireClassify = questionnaireClassifyManager.selectByExample(null);
		request.setAttribute("questionnaireClassify", questionnaireClassify);
		return "questionnaireManager/questionnaireManagerAdd";
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request,Long classifyId,String title) {
		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			if (StringUtils.isBlank(title)) {
				throw new PatternException("问卷标题不能为空！！！");
			}
			if(title.length() > 200){
				throw new PatternException("问卷标题超过200个字符！！！");
			}
			Long id=(Long)request.getSession().getAttribute("userId");
			QuestionnaireExample example=new QuestionnaireExample();
			example.or().andTitleEqualTo(title);
			List<Questionnaire> questionnaire=questionnaireManager.selectByExample(example);
			if (questionnaire.size()>0) {
				throw new PatternException("改问卷已经存在！！！");
			}else{
				Questionnaire model=new Questionnaire();
				model.setQuestionnaireClassifyId(classifyId);
				model.setTitle(title);
				model.setPublishPeopleId(id);
				questionnaireManager.insert(model);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}
	/**
	 * 跳转到问卷调查管理修改页面
	 * 
	 */
	@RequestMapping("editPage")
	public String editPage(HttpServletRequest request,Long id){
		Questionnaire questionnaire=questionnaireManager.selectByPrimaryKey(id);
		List<QuestionnaireClassify> questionnaireClassify = questionnaireClassifyManager.selectByExample(null);
		request.setAttribute("questionnaireClassify", questionnaireClassify);
		request.setAttribute("questionnaire", questionnaire);
		return "questionnaireManager/questionnaireManagerEdit";
	}
	
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,Long classifyId,String title,Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
			if (StringUtils.isBlank(title)) {
				throw new PatternException("问卷标题不能为空！！！");
			}
			Long userId=(Long)request.getSession().getAttribute("userId");
			QuestionnaireExample example=new QuestionnaireExample();
			example.or().andTitleEqualTo(title).andIdNotEqualTo(id);
			int i=questionnaireManager.countByExample(example);
			if (i>0) {
				throw new PatternException("改问卷已经存在！！！");
			}else{
				Questionnaire model=new Questionnaire();
				model.setId(id);
				model.setQuestionnaireClassifyId(classifyId);
				model.setTitle(title);
				model.setPublishPeopleId(userId);
				questionnaireManager.updateByPrimaryKeySelective(model);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			QuestionExample questionExample=new QuestionExample();
			questionExample.or().andQuestionnaireIdEqualTo(id);
			List<Question> question = questionManager.selectByExample(questionExample);
			if (question.size()>0) {
				for (Question question2 : question) {
					QuestionItemExample QuestionItemExample=new QuestionItemExample();
					QuestionItemExample.or().andQuestionIdEqualTo(question2.getId());
					questionItemManager.deleteByExample(QuestionItemExample);
					questionManager.deleteByPrimaryKey(question2.getId());
				}
			}
			questionnaireManager.deleteByPrimaryKey(id);
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
