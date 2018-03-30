package com.bola.nwcl.web.controller.honorEnjoy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.HonourEnjoyImgManager;
import com.bola.nwcl.biz.HonourEnjoyManager;
import com.bola.nwcl.biz.HonourEnjoyRelationManager;
import com.bola.nwcl.biz.HonourEnjoyUserManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseListResult;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.FileuploadHelper;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoy;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyImg;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRelation;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRelationExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUser;
import com.bola.nwcl.web.model.HonourEnjoyInfoModel;
/**
 * 业主账号管理 
 */
@Controller
@RequestMapping(value = "web/honor")
public class HonorEnjoyController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BizUserManager bizUserManager;
	@Autowired
	private HonourEnjoyManager heManager;
	@Autowired
	private HonourEnjoyUserManager huserManager;
	@Autowired
	private HonourEnjoyRelationManager relationManager;
	@Autowired
	private HonourEnjoyImgManager imgManager;
	
	/**
	 * 跳转到酒店公寓管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/hotelManager")
	public String manager() {
		return "/honor/hotel";
	}
	/**
	 * 跳转到温泉养生管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/springManager")
	public String manager2() {
		return "/honor/hotspring";
	}
	/**
	 * 跳转到温泉养生管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/plazaManager")
	public String manager3() {
		return "/honor/plaza";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(HonourEnjoyInfoModel m, PageHelper ph,HttpServletRequest request){	    
		return heManager.dataGrid(m, ph, request); 	      		
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
	
	/**
	 * 添加温泉页面
	 * @return
	 */
	@RequestMapping("/springAddPage")
	public String  addhspage() {
		return "/honor/springAdd";
	}
	/**
	 * 
	 * 
	 * 修改温泉页面
	 * @return
	 */
	@RequestMapping("/springEditPage")
	public String  editspage(HttpServletRequest request, Long id) {
		HonourEnjoyInfoModel model = heManager.getInfo(id);
		request.setAttribute("model", model);
		return "/honor/springEdit";
	}
	
	/**
	 * 添加商圈页面
	 * @return
	 */
	@RequestMapping("/plazaAddPage")
	public String  addphspage() {
		return "/honor/plazaAdd";
	}
	/**
	 * 修改商圈页面
	 * @return
	 */
	@RequestMapping("/plazaEditPage")
	public String  editpspage(HttpServletRequest request, Long id) {
		HonourEnjoyInfoModel model = heManager.getInfo(id);
		request.setAttribute("model", model);
		return "/honor/plazaEdit";
	}
	
	@RequestMapping("/addHotel")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, HonourEnjoyInfoModel im, String pictures,
			@RequestParam("Pic") MultipartFile Pic) {
		BaseResult baseResult = new BaseResult("SUCCESS","添加成功");
		String imgDir = "/upload/files/HonourEnjoy/";
		try {
			String picPath = null;
			// 头像上传
			if (!Pic.isEmpty()) {
				String imageMaxSize = "20";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName2 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(Pic, imageType, imageMaxSize);
				FileName2 = FileUploadDeleteUtil.upload(Pic, request, imgDir, FileName2);
				picPath = FileName2;
			}
			Long userId = (Long) request.getSession().getAttribute("userId");
			// 添加 HonourEnjoy 并得到ID
			HonourEnjoy honourEnjoy = new HonourEnjoy();
			honourEnjoy.setTitle(im.getTitle());
			honourEnjoy.setAddress(im.getAddress());
			honourEnjoy.setContent(im.getContent());
			honourEnjoy.setSummary(im.getSummary());
			honourEnjoy.setType(1);
			honourEnjoy.setPublishId(userId);
			honourEnjoy.setRowAddTime(new Date());
			heManager.insertAndGetId(honourEnjoy);
			Long zxId = honourEnjoy.getId();
			// 添加酒店联系人 并得到ID
			HonourEnjoyUser user = new HonourEnjoyUser();
			user.setName(im.getUserName());
			user.setPosition(im.getUserPosition());
			user.setPhone(im.getUserPhone());
			user.setHeadPortrait(picPath);
			user.setRowAddTime(new Date());
			huserManager.insertAndGetId(user);
			Long useid = user.getId();
			// 关联表（酒店尊享，酒店联系人）
			HonourEnjoyRelation relation = new HonourEnjoyRelation();
			relation.setHonourEnjoyId(zxId);
			relation.setUserId(useid);
			relation.setRowAddTime(new Date());
			relationManager.insert(relation);
			
			// 添加酒店图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						HonourEnjoyImg enjoyImg = new HonourEnjoyImg();
						enjoyImg.setHonourEnjoyId(zxId);
						enjoyImg.setImgPath(path);
						enjoyImg.setRowAddTime(new Date());
						imgManager.insert(enjoyImg);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/editHotel")
	@ResponseBody
	public BaseResult edithotel(HttpServletRequest request, HonourEnjoyInfoModel im, String pictures,
			@RequestParam("Pic") MultipartFile Pic) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改成功");
		String imgDir = "/upload/files/HonourEnjoy/";
		try {
			
			HonourEnjoy model = heManager.selectByPrimaryKey(im.getId());
			if(model == null){
				throw new BusinessValidateException("该信息不存在");
			}
			Long huserid = null;
			HonourEnjoyRelationExample relationExample = new HonourEnjoyRelationExample();
			relationExample.or().andHonourEnjoyIdEqualTo(model.getId());
			List<HonourEnjoyRelation> relatio = relationManager.selectByExample(relationExample);
			if (relatio != null && relatio.size() > 0) {
				//HonourEnjoyUser user = huserManager.selectByPrimaryKey(relation.get(0).getUserId());
				huserid = relatio.get(0).getUserId();
			}
			
			Long userId = (Long) request.getSession().getAttribute("userId");
			// 修改 HonourEnjoy 
			model.setTitle(im.getTitle());
			model.setAddress(im.getAddress());
			model.setContent(im.getContent());
			model.setSummary(im.getSummary());
			model.setType(1);
			model.setPublishId(userId);
			model.setRowAddTime(new Date());
			heManager.updateByPrimaryKeySelective(model);
			Long zxId = model.getId();
			
			//List<String> imgsPath = new ArrayList<String>();
			String picPath = null;
			
			// 头像上传
			if (!Pic.isEmpty()) {
				String imageMaxSize = "20";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName2 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(Pic, imageType, imageMaxSize);
				FileName2 = FileUploadDeleteUtil.upload(Pic, request, imgDir, FileName2);
				picPath = FileName2;
			}
			
			// 修改酒店联系人 并得到ID
			HonourEnjoyUser user = huserManager.selectByPrimaryKey(huserid);
			if (user != null) {
				user.setName(im.getUserName());
				user.setPosition(im.getUserPosition());
				user.setPhone(im.getUserPhone());
				user.setHeadPortrait(picPath);
				huserManager.updateByPrimaryKeySelective(user);
			}
			
			// 添加酒店图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						HonourEnjoyImg enjoyImg = new HonourEnjoyImg();
						enjoyImg.setHonourEnjoyId(zxId);
						enjoyImg.setImgPath(path);
						enjoyImg.setRowAddTime(new Date());
						imgManager.insert(enjoyImg);
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/addSpring")
	@ResponseBody
	public BaseResult adds(HttpServletRequest request, HonourEnjoyInfoModel im, String pictures,
			@RequestParam("Pic") MultipartFile Pic) {
		BaseResult baseResult = new BaseResult("SUCCESS","添加成功");
		String imgDir = "/upload/files/HonourEnjoy/";
		try {
			String picPath = null;
			// 头像上传
			if (!Pic.isEmpty()) {
				String imageMaxSize = "20";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName2 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(Pic, imageType, imageMaxSize);
				FileName2 = FileUploadDeleteUtil.upload(Pic, request, imgDir, FileName2);
				picPath =  FileName2;
			}
			Long userId = (Long) request.getSession().getAttribute("userId");
			// 添加 HonourEnjoy 并得到ID
			HonourEnjoy honourEnjoy = new HonourEnjoy();
			honourEnjoy.setTitle(im.getTitle());
			honourEnjoy.setAddress(im.getAddress());
			honourEnjoy.setContent(im.getContent());
			honourEnjoy.setContent(im.getSummary());
			honourEnjoy.setType(2); // 温泉
			honourEnjoy.setPublishId(userId);
			honourEnjoy.setRowAddTime(new Date());
			heManager.insertAndGetId(honourEnjoy);
			Long zxId = honourEnjoy.getId();
			// 添加酒店联系人 并得到ID
			HonourEnjoyUser user = new HonourEnjoyUser();
			user.setName(im.getUserName());
			user.setPosition(im.getUserPosition());
			user.setPhone(im.getUserPhone());
			user.setHeadPortrait(picPath);
			user.setRowAddTime(new Date());
			huserManager.insertAndGetId(user);
			Long useid = user.getId();
			// 关联表（酒店尊享，酒店联系人）
			HonourEnjoyRelation relation = new HonourEnjoyRelation();
			relation.setHonourEnjoyId(zxId);
			relation.setUserId(useid);
			relation.setRowAddTime(new Date());
			relationManager.insert(relation);
			
			// 添加酒店图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						HonourEnjoyImg enjoyImg = new HonourEnjoyImg();
						enjoyImg.setHonourEnjoyId(zxId);
						enjoyImg.setImgPath(path);
						enjoyImg.setRowAddTime(new Date());
						imgManager.insert(enjoyImg);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/editSpring")
	@ResponseBody
	public BaseResult editspring(HttpServletRequest request, HonourEnjoyInfoModel im, String pictures,
			@RequestParam("Pic") MultipartFile Pic) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改成功");
		String imgDir = "/upload/files/HonourEnjoy/";
		try {
			
			HonourEnjoy model = heManager.selectByPrimaryKey(im.getId());
			if(model == null){
				throw new BusinessValidateException("该信息不存在");
			}
			Long huserid = null;
			HonourEnjoyRelationExample relationExample = new HonourEnjoyRelationExample();
			relationExample.or().andHonourEnjoyIdEqualTo(model.getId());
			List<HonourEnjoyRelation> relatio = relationManager.selectByExample(relationExample);
			if (relatio != null && relatio.size() > 0) {
				//HonourEnjoyUser user = huserManager.selectByPrimaryKey(relation.get(0).getUserId());
				huserid = relatio.get(0).getUserId();
			}
			
			Long userId = (Long) request.getSession().getAttribute("userId");
			// 修改 HonourEnjoy 
			model.setTitle(im.getTitle());
			model.setAddress(im.getAddress());
			model.setContent(im.getContent());
			model.setSummary(im.getSummary());
			model.setType(2); // 温泉
			model.setPublishId(userId);
			model.setRowAddTime(new Date());
			heManager.updateByPrimaryKeySelective(model);
			Long zxId = model.getId();
			
			//List<String> imgsPath = new ArrayList<String>();
			String picPath = null;
			
			// 头像上传
			if (!Pic.isEmpty()) {
				String imageMaxSize = "20";// 允许文件的最大 单位M
				String imageType = "jpeg|jpg|png";// 允许的后缀格式
				String FileName2 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
				FileuploadHelper.validate(Pic, imageType, imageMaxSize);
				FileName2 = FileUploadDeleteUtil.upload(Pic, request, imgDir, FileName2);
				picPath =  FileName2;
			}
			
			// 修改酒店联系人 并得到ID
			HonourEnjoyUser user = huserManager.selectByPrimaryKey(huserid);
			if (user != null) {
				user.setName(im.getUserName());
				user.setPosition(im.getUserPosition());
				user.setPhone(im.getUserPhone());
				user.setHeadPortrait(picPath);
				huserManager.updateByPrimaryKeySelective(user);
			}
			
			// 添加酒店图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						HonourEnjoyImg enjoyImg = new HonourEnjoyImg();
						enjoyImg.setHonourEnjoyId(zxId);
						enjoyImg.setImgPath(path);
						enjoyImg.setRowAddTime(new Date());
						imgManager.insert(enjoyImg);
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/addPlaza")
	@ResponseBody
	public BaseResult addps(HttpServletRequest request, HonourEnjoyInfoModel im, String pictures ) {
		BaseResult baseResult = new BaseResult("SUCCESS","添加成功");
		try {
			Long userId = (Long) request.getSession().getAttribute("userId");
			// 添加 HonourEnjoy 并得到ID
			HonourEnjoy honourEnjoy = new HonourEnjoy();
			honourEnjoy.setTitle(im.getTitle());
			honourEnjoy.setContent(im.getContent());
			honourEnjoy.setSummary(im.getSummary());
			honourEnjoy.setType(3); // 商圈
			honourEnjoy.setPublishId(userId);
			honourEnjoy.setRowAddTime(new Date());
			heManager.insertAndGetId(honourEnjoy);
			Long zxId = honourEnjoy.getId();
			
			// 添加酒店图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						HonourEnjoyImg enjoyImg = new HonourEnjoyImg();
						enjoyImg.setHonourEnjoyId(zxId);
						enjoyImg.setImgPath(path);
						enjoyImg.setRowAddTime(new Date());
						imgManager.insert(enjoyImg);
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
		logger.info("添加返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/editPlaza")
	@ResponseBody
	public BaseResult editsplaza(HttpServletRequest request, HonourEnjoyInfoModel im, String pictures ) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改成功");
		try {
			
			HonourEnjoy model = heManager.selectByPrimaryKey(im.getId());
			if(model == null){
				throw new BusinessValidateException("该信息不存在");
			}
			Long userId = (Long) request.getSession().getAttribute("userId");
			// 修改 HonourEnjoy 
			model.setTitle(im.getTitle());
			model.setContent(im.getContent());
			model.setSummary(im.getSummary());
			model.setType(3); // 商圈
			model.setPublishId(userId);
			model.setRowAddTime(new Date());
			heManager.updateByPrimaryKeySelective(model);
			Long zxId = model.getId();
			
			// 添加酒店图片
			if (StringUtils.isNotBlank(pictures)) {
				String [] sPath =	pictures.split(",");
				if (sPath != null) {
					for(String path : sPath){
						HonourEnjoyImg enjoyImg = new HonourEnjoyImg();
						enjoyImg.setHonourEnjoyId(zxId);
						enjoyImg.setImgPath(path);
						enjoyImg.setRowAddTime(new Date());
						imgManager.insert(enjoyImg);
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS","删除成功");
		try {
			HonourEnjoy img = heManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			heManager.deleteHonorEnjoy(id);
			
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
		logger.info("删除图片返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/deleteImg")
	@ResponseBody
	public BaseResult deleteImg(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS","图片删除成功");
		try {
			HonourEnjoyImg img = imgManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			imgManager.deleteByPrimaryKey(id);
			
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
		logger.info("删除图片返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping(value = "/fileUpload", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BaseListResult<String> fileuploadf(HttpServletRequest request, @RequestParam("imgs") MultipartFile[] imgs) {
		BaseListResult<String> baseResult = new BaseListResult<String>("SUCCESS","图片上传成功");
		String imgDir = "/upload/files/HonourEnjoy/";
		try {
			List<String> imgsPath = new ArrayList<String>();
			// 酒店图片上传
			if (null != imgs) {
				for (MultipartFile img1 : imgs) {
					String imageMaxSize = "20";// 允许文件的最大 单位M
					String imageType = "jpeg|jpg|png";// 允许的后缀格式
					String FileName1 = System.currentTimeMillis() + "";// 无后缀的文件名  可按照时间戳生成一个
					FileuploadHelper.validate(img1, imageType, imageMaxSize);
					FileName1 = FileUploadDeleteUtil.upload(img1, request, imgDir, FileName1);
					String path = null;
					path =  FileName1;
					imgsPath.add(path);
				}
				baseResult.setResult(imgsPath);
			}					
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<String>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<String>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<String>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseListResult<String>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("上传图片返回值：{}", baseResult.toString());
		return baseResult;
	}
	
}
