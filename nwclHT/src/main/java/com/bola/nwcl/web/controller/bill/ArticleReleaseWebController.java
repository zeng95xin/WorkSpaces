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

import com.bola.nwcl.biz.ArticleReleaseImgManager;
import com.bola.nwcl.biz.ArticleReleaseManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BizUserRoomManager;
import com.bola.nwcl.biz.BuildingManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.ArticleRelease;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseExample;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImg;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImgExample;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.web.model.ArticleReleaseWebModel;

/**
 * 放行单据管理 
 */
@Controller
@RequestMapping(value = "web/articleRelease")
public class ArticleReleaseWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleReleaseManager articleReleaseManager;
	@Autowired
	private ArticleReleaseImgManager articleReleaseImgManager;
	@Autowired RoomManager roomManager;
	@Autowired CommunityManager communityManager;
	@Autowired BizUserManager bizUserManager;
	@Autowired BizUserRoomManager bizUserRoomManager;
	@Autowired BuildingManager buildingManager;

	/**
	 * 跳转到放行单据页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		//提前查询出所有的小区
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		return "/bill/articleRelease";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(ArticleReleaseWebModel articleReleaseWebModel, PageHelper ph,HttpServletRequest request){	    
		DataGrid dg = new DataGrid();
		ArticleReleaseExample example=new ArticleReleaseExample();
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
		if(articleReleaseWebModel.getCommunityId()!=null&&articleReleaseWebModel.getCommunityId()!=-1){
			c.andCommunityIdEqualTo(articleReleaseWebModel.getCommunityId());
		}

		//放行物品
		if(StringUtils.isNotBlank(articleReleaseWebModel.getOption())&&articleReleaseWebModel.getOption().equals("1")){
			c.andThingLike("%"+articleReleaseWebModel.getKey()+"%");
		}
		//放行时间
		if(StringUtils.isNotBlank(articleReleaseWebModel.getOption())&&articleReleaseWebModel.getOption().equals("2")){
			Date addTime=DateUtils.strToDate(articleReleaseWebModel.getKey(), "yyyy-MM-dd");
			c.andReleaseTimeBetween(addTime, new Date(addTime.getTime()+1000*60*60*24));
		}
		//业主
		if(StringUtils.isNoneBlank(articleReleaseWebModel.getOption())&&articleReleaseWebModel.getOption().equals("3")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andNameLike("%"+articleReleaseWebModel.getKey()+"%");
			List<BizUser> bizUser=bizUserManager.selectByExample(bizUserExample);
			for (BizUser bizUser2 : bizUser) {
				list.add(bizUser2.getId());
			}
			if (list.size()>0) {
				c.andBuserIdIn(list);
			} else {
				c.andBuserIdEqualTo(-1L);
			}
		}
		//电话
		if(StringUtils.isNotBlank(articleReleaseWebModel.getOption())&&articleReleaseWebModel.getOption().equals("4")){
			List<Long> list=null;
			list=new ArrayList<Long>();
			BizUserExample bizUserExample=new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+articleReleaseWebModel.getKey()+"%");
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
		List<ArticleReleaseWebModel> list=null;
		list=new ArrayList<ArticleReleaseWebModel>();
		List<ArticleRelease> articleRelease=articleReleaseManager.selectByExample(example);
		if (articleRelease.size()>0) {
			for (ArticleRelease articleRelease2 : articleRelease) {
				ArticleReleaseWebModel model=new ArticleReleaseWebModel();
				ArticleRelease ar=articleReleaseManager.selectByPrimaryKey(articleRelease2.getId());
				if (ar!=null) {
					model.setId(ar.getId());
					model.setSerial(ar.getSerial());
					model.setThing(ar.getThing());
					model.setPeopelCount(ar.getPeopelCount());
					model.setReleaseTime(ar.getReleaseTime());
				}

				BizUser bizUser=bizUserManager.selectByPrimaryKey(articleRelease2.getBuserId());
				if(bizUser!=null){
					model.setUserName(bizUser.getName());
					model.setPhone(bizUser.getPhone());
					model.setNickname(bizUser.getNickname());
					if(bizUser.getCurrentRoomId() != null){
						Room room = roomManager.selectByPrimaryKey(bizUser.getCurrentRoomId());
						if(room != null){
							model.setUnitNo(room.getUnitNo());
						}
					}
					
				}
				ArticleReleaseImgExample articleReleaseImgExample=new ArticleReleaseImgExample();
				articleReleaseImgExample.or().andArticleReleaseIdEqualTo(articleRelease2.getId());
				ArticleReleaseImg articleReleaseImg=articleReleaseImgManager.selectOneByExample(articleReleaseImgExample);
				if (articleReleaseImg!=null) {
					model.setImgPath(articleReleaseImg.getImgPathThumbnail());
				}

				list.add(model);
			}
		}
		dg.setRows(list);
		int total=articleReleaseManager.countByExample(null);
		dg.setTotal(total);

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
				ArticleReleaseImgExample articleReleaseImgExample=new ArticleReleaseImgExample();
				articleReleaseImgExample.or().andArticleReleaseIdEqualTo(id);
				List<ArticleReleaseImg> list=articleReleaseImgManager.selectByExample(articleReleaseImgExample);
				for (ArticleReleaseImg articleReleaseImg : list) {
					FileManager.deleteFile(articleReleaseImg.getImgPath());
					FileManager.deleteFile(articleReleaseImg.getImgPathThumbnail());
					articleReleaseImgManager.deleteByPrimaryKey(articleReleaseImg.getId());
				}
				articleReleaseManager.deleteByPrimaryKey(id);
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

	@RequestMapping("/imgPage")
	public String imgPage(HttpServletRequest request,Long id){
		ArticleReleaseImgExample articleReleaseImgExample=new ArticleReleaseImgExample();
		articleReleaseImgExample.or().andArticleReleaseIdEqualTo(id);
		List<ArticleReleaseImg> list=articleReleaseImgManager.selectByExample(articleReleaseImgExample);
		request.setAttribute("img", list);
		return "/bill/articleReleaseImg";

	}
}
