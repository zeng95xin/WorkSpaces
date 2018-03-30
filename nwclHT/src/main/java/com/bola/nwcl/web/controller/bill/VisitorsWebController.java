package com.bola.nwcl.web.controller.bill;

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

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.biz.VisitorsManager;
import com.bola.nwcl.biz.VisitorsStatisticsDetailManager;
import com.bola.nwcl.biz.VisitorsStatisticsManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.Visitors;
import com.bola.nwcl.dal.mybatis.model.VisitorsExample;
import com.bola.nwcl.dal.mybatis.model.VisitorsExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatistics;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetail;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetailExample;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsExample;
import com.bola.nwcl.web.model.VisitorsWebModel;

/**
 * 来访单据管理 
 */
@Controller
@RequestMapping(value = "web/visitors")
public class VisitorsWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VisitorsManager visitorsManager;
	@Autowired
	private VisitorsStatisticsDetailManager visitorsStatisticsDetailManager;
	@Autowired
	private VisitorsStatisticsManager visitorsStatisticsManager;
	@Autowired RoomManager roomManager;
	@Autowired CommunityManager communityManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired BuildingManager buildingManager;
	/**
	 * 跳转到来访单据页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		//提前查询出所有的小区
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		return "/bill/visitors";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(VisitorsWebModel visitors, PageHelper ph,HttpServletRequest request){	
		DataGrid dg = new DataGrid();
		try{
			VisitorsExample example=new VisitorsExample();
			example.setLimit(ph.getRows());
			example.setOffset((ph.getPage()-1)*ph.getRows());
			Criteria  c=example.or();
			example.setOrderByClause("row_add_time DESC");

			//得到用户对象
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			//判断是普通管理员还是admin(普通管理员给出当前小区id)
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				c.andCommunityIdIn(communityIds);
			}
			//通过小区查询
			if(visitors.getCommunityId()!=null&&visitors.getCommunityId()!=-1){
				c.andCommunityIdEqualTo(visitors.getCommunityId());
			}
			//房间号
			if(StringUtils.isNotBlank(visitors.getOption())&&visitors.getOption().equals("1")){
				List<Long> list=null;
				list=new ArrayList<Long>();
				if(visitors.getCommunityId()!=null&&visitors.getCommunityId()!=-1){
					BuildingExample buildingExample=new BuildingExample();
					buildingExample.or().andCommunityIdEqualTo(visitors.getCommunityId());
					List<Building> building=buildingManager.selectByExample(buildingExample);
					for (Building building2 : building) {
						RoomExample roomExample=new RoomExample();
						roomExample.or().andRoomNoLike("%"+visitors.getKey()+"%").andBuildingIdEqualTo(building2.getId());
						List<Room> room=roomManager.selectByExample(roomExample);
						if(room.size()!=0){
							for (Room room2 : room) {
								BizUserExample bizUserExample=new BizUserExample();
								bizUserExample.or().andCurrentRoomIdEqualTo(room2.getId());
								List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
								if(bizUser.size()!=0){
									for (BizUser bizUser2 : bizUser) {
										list.add(bizUser2.getId());
									}
								}
							}
						}

					}
				}else{
					RoomExample roomExample=new RoomExample();
					roomExample.or().andRoomNoLike("%"+visitors.getKey()+"%");
					List<Room> room=roomManager.selectByExample(roomExample);
					if(room.size()!=0){
						for (Room room2 : room) {
							BizUserExample bizUserExample=new BizUserExample();
							bizUserExample.or().andCurrentRoomIdEqualTo(room2.getId());
							List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
							if(bizUser.size()!=0){
								for (BizUser bizUser2 : bizUser) {
									list.add(bizUser2.getId());
								}
							}
						}
					}
				}
				if (list.size()>0) {
					c.andBuserIdIn(list);
				} else {
					c.andBuserIdEqualTo(-1L);
				}
			}
			//预计来访时间
			if(StringUtils.isNotBlank(visitors.getOption())&&visitors.getOption().equals("2")){
				Date addTime=DateUtils.strToDate(visitors.getKey(), "yyyy-MM-dd");
				c.andExpectArriveTimeBetween(addTime, new Date(addTime.getTime()+1000*60*60*24));
			}
			//电话
			if(StringUtils.isNotBlank(visitors.getOption())&&visitors.getOption().equals("3")){
				List<Long> list=null;
				list=new ArrayList<Long>();
				BizUserExample bizUserExample=new BizUserExample();
				bizUserExample.or().andPhoneLike("%"+visitors.getKey()+"%");
				List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
				if (bizUser.size()>0) {
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
			List<VisitorsWebModel> list=null;
			list=new ArrayList<VisitorsWebModel>();
			List<Visitors> Vis=visitorsManager.selectByExample(example);
			if (Vis.size()>0) {
				for (Visitors visitors2 : Vis) {
					VisitorsWebModel model=new VisitorsWebModel();
					Visitors vi=visitorsManager.selectByPrimaryKey(visitors2.getId());
					if (vi!=null) {
						model.setId(vi.getId());
						model.setSerial(vi.getSerial());
						model.setExpectArriveTime(vi.getExpectArriveTime());
						model.setRowAddTime(vi.getRowUpdateTime());
					}
					VisitorsStatisticsDetailExample visitorsStatisticsDetailExample=new VisitorsStatisticsDetailExample();
					visitorsStatisticsDetailExample.or().andVisitorsIdEqualTo(visitors2.getId());
					List<VisitorsStatisticsDetail> visitorsStatisticsDetail=visitorsStatisticsDetailManager.selectByExample(visitorsStatisticsDetailExample);
					String visitorsName="";
					if (visitorsStatisticsDetail.size()>0) {
						for (VisitorsStatisticsDetail visitorsStatisticsDetail2 : visitorsStatisticsDetail) {
							visitorsName+=visitorsStatisticsDetail2.getName();
							visitorsName+=",";
						}
					}
					if(!"".equals(visitorsName) && visitorsName != null){
						visitorsName = visitorsName.substring(0,visitorsName.length()-1);
					}
					model.setVisitorsName(visitorsName);
					
					VisitorsStatisticsExample visitorsStatisticsExample = new VisitorsStatisticsExample();
					visitorsStatisticsExample.or().andVisitorsIdEqualTo(visitors2.getId());
					List<VisitorsStatistics> visitorsStatistics = visitorsStatisticsManager.selectByExample(visitorsStatisticsExample);
					String vName = "";
					if(visitorsStatistics.size() > 0){
						for (VisitorsStatistics visitorsStatistics2 : visitorsStatistics) {
							vName+=visitorsStatistics2.getName();
							vName+=",";
						}
					}
					if(!"".equals(vName) && vName != null){
						vName = vName.substring(0,vName.length()-1);
					}
					model.setvName(vName);
					
					BizUser bizUser=bizUserManager.selectByPrimaryKey(visitors2.getBuserId());
					if(bizUser!=null){
						model.setUserName(bizUser.getName());
						model.setPhone(bizUser.getPhone());
						model.setNickname(bizUser.getNickname());
						if(bizUser.getCurrentRoomId() != null){
						Room room=roomManager.selectByPrimaryKey(bizUser.getCurrentRoomId());
						if(room!=null){
							model.setRoomNumber(room.getRoomNo());
							model.setUnitNo(room.getUnitNo());
						}
					}
				}
					list.add(model);
				}
			}

			dg.setRows(list);
			int total=visitorsManager.countByExample(example);
			dg.setTotal(total);

		}catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
		}
		return dg;

	}


	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try{
			if (id != null) {// 不能删除自己
				VisitorsStatisticsDetailExample visitorsStatisticsDetailExample=new VisitorsStatisticsDetailExample();
				visitorsStatisticsDetailExample.or().andVisitorsIdEqualTo(id);
				visitorsStatisticsDetailManager.deleteByExample(visitorsStatisticsDetailExample);

				VisitorsStatisticsExample visitorsStatisticsExample=new VisitorsStatisticsExample();
				visitorsStatisticsExample.or().andVisitorsIdEqualTo(id);
				visitorsStatisticsManager.deleteByExample(visitorsStatisticsExample);

				visitorsManager.deleteByPrimaryKey(id);
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

	/**
	 * 跳转到来访单据详情页面
	 * 
	 * @return
	 */
	@RequestMapping("/detailManager")
	public String detailManager(HttpServletRequest request,Long id) {
		request.setAttribute("id", id);
		return "/bill/visitorsDetail";
	}

	@RequestMapping("/detailDataGrid")
	@ResponseBody
	public DataGrid detailDataGrid(PageHelper ph,Long id){	    
		DataGrid dg = new DataGrid();
		VisitorsStatisticsDetailExample example=new VisitorsStatisticsDetailExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.or().andVisitorsIdEqualTo(id);
		example.setOrderByClause("row_add_time DESC");
		List<VisitorsStatisticsDetail> list=visitorsStatisticsDetailManager.selectByExample(example);
		int total=visitorsStatisticsDetailManager.countByExample(example);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;

	}


	/**
	 * 删除详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/detailDelete")
	@ResponseBody
	public BaseResult detailDelete(Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try{
			if (id != null) {// 不能删除自己
				visitorsStatisticsDetailManager.deleteByPrimaryKey(id);
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

	@RequestMapping("/detailBatchDelete")
	@ResponseBody
	public BaseResult detailBatchDelete(String ids) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			if (ids != null && ids.length() > 0) {
				for (String id : ids.split(",")) {
					if (id != null) {
						this.detailDelete((long) Integer.parseInt(id));
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
