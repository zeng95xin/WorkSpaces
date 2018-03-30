package com.bola.nwcl.web.controller.video;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.VideoManageManager;
import com.bola.nwcl.biz.VideoResourceManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.VideoManagement;
import com.bola.nwcl.dal.mybatis.model.VideoResource;
import com.bola.nwcl.dal.mybatis.model.VideoResourceExample;

@Controller
@RequestMapping(value = "web/VideoManagement")
public class VideoManagementController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VideoManageManager manageManager;
	@Autowired
	private VideoResourceManager videoResourceManager;
	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/video_management/video";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(VideoManagement management, PageHelper ph, HttpServletRequest request) {
		return manageManager.dataGrid(management, ph, request);
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/video_management/videoAdd";
	}

	@RequestMapping("/grantPage")
	public String grantPage(Long id, HttpServletRequest request) {
		request.setAttribute("id", id);
		VideoManagement model = manageManager.selectRoleByPrimaryKey(id.longValue());
		request.setAttribute("role", model);
		return "/video_management/videoGrant";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request, Long id,PageHelper ph) {
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try {
			VideoResourceExample example =new VideoResourceExample();
			VideoResource resource = new VideoResource();
			resource.setRoleId(id);
			DataGrid dataGrid = videoResourceManager.dataGrid(resource,ph,request);
			List<VideoResource> list = dataGrid.getRows();
			for (VideoResource rid : list) {
				videoResourceManager.deleteByPrimaryKey(rid.getId());
			}
			manageManager.deleteByPrimaryKey(id);
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.PATTERN_ERROR.getCode(), e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("删除返回值：{}", baseResult.toString());
		return baseResult;
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, String name,String secret,String url,String appKey) {
		BaseResult baseResult = new BaseResult("SUCCESS", "成功");
		try {
			VideoManagement model = new VideoManagement();
			if (name.length()<0) {
				throw new BusinessValidateException("视频名称不能为空!");
			}
			if (url.trim().toString()=="") {
				throw new BusinessValidateException("视频地址不能为空!");
			}
			if (appKey==null) {
				throw new BusinessValidateException("设备码不能为空!");
			}
			model.setSecret(secret);
			model.setUrl(url);
			model.setRowaddtime(new Date());
			model.setName(name);
			model.setAppkey(appKey);
			manageManager.insert(model);
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.PATTERN_ERROR.getCode(), e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		VideoManagement model = manageManager.selectByPrimaryKey(id);
		request.setAttribute("model", model);
		request.setAttribute("appKey", model.getAppkey());
		request.setAttribute("secret",model.getSecret());
		return "/video_management/videoEdit";
	}

	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request,Long id,String name,String secret,String url,String appKey) {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		try {
			VideoManagement model = manageManager.selectByPrimaryKey(id);
			model.setSecret(secret);
			model.setUrl(url);
			model.setRowupdatetime(new Date());
			model.setName(name);
			model.setAppkey(appKey);
			manageManager.updateByPrimaryKeySelective(model);		
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
	
	@RequestMapping("/grant")
	@ResponseBody
	public BaseResult grant(HttpServletRequest request, VideoManagement videoResource) {
		BaseResult baseResult = new BaseResult("SUCCESS","授权成功");	
		VideoManagement resource = manageManager.selectByPrimaryKey(videoResource.getId());
		try {
			if (resource == null) {
				throw new PatternException("视频不存在");
			}
			if (videoResource.getResourceIds() != null && !videoResource.getResourceIds().equalsIgnoreCase("")) {
				String[] ids = videoResource.getResourceIds().split(","); 
				List<Long> rids = new ArrayList<Long>();
				for(String rid : ids){
					if (rid != null && !rid.equalsIgnoreCase("")) {
						Long ridd=Long.parseLong(rid);
						rids.add(ridd);
					}
				}
				// 角色关联表中当前授权信息时不变，没有便添加
				for (Long id : rids) {
					VideoResourceExample rrExample1 = new VideoResourceExample();
					rrExample1.or().andVideoIdEqualTo(videoResource.getId()).andRoleIdEqualTo(id);
					VideoResource R = videoResourceManager.selectOneByExample(rrExample1);
					if (R == null) {
						VideoResource model = new VideoResource();
						model.setRoleId(id);
						model.setVideoId(videoResource.getId());
						model.setRawAddTime(new Date());
						videoResourceManager.insert(model);
					}
				}
				// 角色关联表中授权信息多余或被取消了授权，删除
				VideoResourceExample rrExample2 = new VideoResourceExample();
				rrExample2.or().andVideoIdEqualTo(videoResource.getId()).andRoleIdNotIn(rids);
				videoResourceManager.deleteByExample(rrExample2);
			}else{
				// 被取消所有授权
				VideoResourceExample rrExample3 = new VideoResourceExample();
				rrExample3.or().andVideoIdEqualTo(videoResource.getId());
				videoResourceManager.deleteByExample(rrExample3);
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
		logger.info("授权返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	
}
