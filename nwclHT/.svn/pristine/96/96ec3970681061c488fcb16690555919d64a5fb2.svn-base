package com.bola.nwcl.web.controller.questionnaire;

import java.util.ArrayList;
import java.util.Date;
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
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AdminExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Questionnaire;
import com.bola.nwcl.dal.mybatis.model.QuestionnaireExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaire;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireAnswerExample;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireExample;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireExample.Criteria;
import com.bola.nwcl.web.model.QuestionnaireWebModel;

@Controller
@RequestMapping("web/QuestionnaireWebController")
public class QuestionnaireWebController {
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
	/**
	 * 跳转到问卷调查页面
	 * 
	 */
	@RequestMapping("manager")
	public String manager(HttpServletRequest request){
		//提前查询出所有的小区
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		return "questionnaire/questionnaire";
	}
	
	
	public List<Long> getRoomId(Long id){
		List<Long> list=null;
		list=new ArrayList<Long>();
		BuildingExample buildingExample=new BuildingExample();
		buildingExample.or().andCommunityIdEqualTo(id);
		List<Building> building=buildingManager.selectByExample(buildingExample);
		if(building.size()!=0){
			for (Building building2 : building) {
				RoomExample roomExample=new RoomExample();
				roomExample.or().andBuildingIdEqualTo(building2.getId());
				List<Room> room=roomManager.selectByExample(roomExample);
				if(room.size()!=0){
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}
			}
		}
		return list;
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(QuestionnaireWebModel questionnaireWebModel,PageHelper ph,HttpServletRequest request){
		DataGrid dg = new DataGrid();
		RoomQuestionnaireExample example=new RoomQuestionnaireExample();
		
		Criteria  c=example.or();

		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin(普通管理员给出当前小区id)
		if(user.getRoleId() != 1){
			questionnaireWebModel.setCommunityId(user.getCommunityId());
		}
			//通过小区查询
			if(questionnaireWebModel.getCommunityId()!=null&&questionnaireWebModel.getCommunityId()!=-1){
				List<Long> list=null;
				list=new ArrayList<Long>();
				BuildingExample buildingExample=new BuildingExample();
				buildingExample.or().andCommunityIdEqualTo(questionnaireWebModel.getCommunityId());
				List<Building> building=buildingManager.selectByExample(buildingExample);
				if(building.size()!=0){
					for (Building building2 : building) {
						RoomExample roomExample=new RoomExample();
						roomExample.or().andBuildingIdEqualTo(building2.getId());
						List<Room> room=roomManager.selectByExample(roomExample);
						if(room.size()!=0){
							for (Room room2 : room) {
								list.add(room2.getId());
							}
						}
					}
				}
				if(list.size() > 0){
					c.andRoomIdIn(list);
				}else{
					c.andRoomIdEqualTo(-1L);
				}
			}

		
		//选项查询
		//标题查询
		if(StringUtils.isNotBlank(questionnaireWebModel.getOption())&&questionnaireWebModel.getOption().equals("1")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			QuestionnaireExample questionnaireExample=new QuestionnaireExample();
			questionnaireExample.or().andTitleLike("%"+questionnaireWebModel.getKey()+"%");
			List<Questionnaire> questionnaire=questionnaireManager.selectByExample(questionnaireExample);
			if (questionnaire.size()>0) {
				for (Questionnaire questionnaire2 : questionnaire) {
					list.add(questionnaire2.getId());
				}
			}
			if(list.size() > 0){
				c.andQuestionnaireIdIn(list);
			}else{
				c.andQuestionnaireIdEqualTo(-1L);
			}
		}
		//发布人查询
		if(StringUtils.isNotBlank(questionnaireWebModel.getOption())&&questionnaireWebModel.getOption().equals("2")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			AdminExample adminExaple=new AdminExample();
			adminExaple.or().andEmployeeNameLike("%"+questionnaireWebModel.getKey()+"%");
			List<Admin> admin=adminManager.selectByExample(adminExaple);
			for (Admin admin2 : admin) {
				QuestionnaireExample questionnaireExample=new QuestionnaireExample();
				questionnaireExample.or().andPublishPeopleIdEqualTo(admin2.getId());
				List<Questionnaire> questionnaire=questionnaireManager.selectByExample(questionnaireExample);
				if (questionnaire.size()>0) {
					for (Questionnaire questionnaire2 : questionnaire) {
						list.add(questionnaire2.getId());
					}
				}
			}
			if(list.size() > 0){
				c.andQuestionnaireIdIn(list);
			}else{
				c.andQuestionnaireIdEqualTo(-1L);
			}
		}
		//发布时间
		if(StringUtils.isNotBlank(questionnaireWebModel.getOption())&&questionnaireWebModel.getOption().equals("3")){
			Date addTime=DateUtils.strToDate(questionnaireWebModel.getKey(), "yyyy-MM-dd");
			c.andRowAddTimeBetween(addTime, new Date(addTime.getTime()+1000*60*60*24));
		}
		int total=roomQuestionnaireManager.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<RoomQuestionnaire> roomQuestionnaire=roomQuestionnaireManager.selectByExample(example);
		List<QuestionnaireWebModel> list=null;
		list=new ArrayList<QuestionnaireWebModel>();
		if (roomQuestionnaire.size()>0) {
			for (RoomQuestionnaire roomQuestionnaire2 : roomQuestionnaire) {
				QuestionnaireWebModel model=new QuestionnaireWebModel();
				RoomQuestionnaire qu=roomQuestionnaireManager.selectByPrimaryKey(roomQuestionnaire2.getId());
				if (qu!=null) {
					model.setId(qu.getId());
					model.setRowAddTime(qu.getRowUpdateTime());
					String column=DateUtils.DateToStr("yyyy-MM-dd", qu.getRowUpdateTime());
					model.setColumn(column+"的问卷调查");

				}
				Room room=roomManager.selectByPrimaryKey(roomQuestionnaire2.getRoomId());
				if (room!=null) {
					model.setRoomNumber(room.getRoomNo());
				}
				Questionnaire questionnaire=questionnaireManager.selectByPrimaryKey(roomQuestionnaire2.getQuestionnaireId());
				if (questionnaire!=null) {
					model.setTitle(questionnaire.getTitle());

					Admin admin=adminManager.selectByPrimaryKey(questionnaire.getPublishPeopleId());
					if (admin!=null) {
						model.setPublishPeopleName(admin.getEmployeeName());
					}
				}
				list.add(model);
			}
		}
		
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			RoomQuestionnaireAnswerExample example=new RoomQuestionnaireAnswerExample();
			example.or().andRoomQuestionnaireIdEqualTo(id);
			roomQuestionnaireAnswerManager.deleteByExample(example);
			roomQuestionnaireManager.deleteByPrimaryKey(id);
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
