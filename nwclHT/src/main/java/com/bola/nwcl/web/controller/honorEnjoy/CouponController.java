package com.bola.nwcl.web.controller.honorEnjoy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.BuserCouponManager;
import com.bola.nwcl.biz.CouponManager;
import com.bola.nwcl.biz.HonourEnjoyManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseListResult;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.FileuploadHelper;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CouponImgMapper;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImg;
import com.bola.nwcl.dal.mybatis.model.Coupon;
import com.bola.nwcl.dal.mybatis.model.CouponImg;
import com.bola.nwcl.dal.mybatis.model.CouponImgExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoy;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyImg;
import com.bola.nwcl.web.model.HonourEnjoyInfoModel;
/**
 * 业主账号管理 
 */
@Controller
@RequestMapping(value = "web/coupon")
public class CouponController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BizUserManager bizUserManager;
	@Autowired
	private HonourEnjoyManager heManager;
	@Autowired
	private CouponManager couponManager;
	@Autowired
	private BuserCouponManager buserCouponManager;
	@Autowired
	private CouponImgMapper couponImgManager;
	
	
	/**
	 * 跳转管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/coupon/coupon";
	}

	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Coupon cn, PageHelper ph,HttpServletRequest request){	   
		
		return couponManager.dataGrid(request, cn, ph);
	}
	
	/**
	 * 添加酒店公寓页面
	 * @return
	 */
	@RequestMapping("/addPage")
	public String  addhpage() {
		return "/coupon/couponAdd";
	}
	/**
	 * 修改酒店公寓页面
	 * @return
	 */
	@RequestMapping("/editPage")
	public String  editpage(HttpServletRequest request, Long id) {
		Coupon model = couponManager.selectByPrimaryKey(id);
		if (model.getSendCount() != null) {
			if (model.getSendCount() > 0) {
			}else{
				model.setSendCount(0L);
			}
		}else{
			model.setSendCount(0L);
		}
		model.setStartTime(DateUtils.DateToStr(DateUtils.YYYY_MM_DD, model.getStart()));
		model.setEndTime(DateUtils.DateToStr(DateUtils.YYYY_MM_DD, model.getEnd()));
		request.setAttribute("model", model);
		HonourEnjoy hy = heManager.selectByPrimaryKey(model.getHonourEnjoyId());
		if (hy != null) {
			HonourEnjoyExample hEnjoyExample = new HonourEnjoyExample();
			hEnjoyExample.or().andTypeEqualTo(hy.getType());
			List<HonourEnjoy> hs = heManager.selectByExample(hEnjoyExample);
			request.setAttribute("honors", hs);
		}
		CouponImgExample imgExample = new CouponImgExample();
		imgExample.or().andCouponIdEqualTo(id);
		List<CouponImg> imgs = couponImgManager.selectByExample(imgExample);
		if (imgs != null && imgs.size() > 0) {
			model.setImages(imgs);
		}
		return "/coupon/couponEdit";
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, Coupon cn, String pictures) {
		BaseResult baseResult = new BaseResult("SUCCESS","添加成功");
		try {
			Coupon model = new Coupon();
			BeanUtils.copyProperties(cn, model);
			if (cn.getSendCount() != null) {
				if (cn.getSendCount() > 0) {
					model.setSendCount(cn.getSendCount());
				}else{
					model.setSendCount(-1L);
				}
			}else{
				model.setSendCount(-1L);
			}
			if (cn.getStartTime() != null) {
				Date d = DateUtils.strToDate(cn.getStartTime(),DateUtils.YYYY_MM_DD);
				model.setStart(d);
			}
			if (cn.getEndTime() != null) {
				Date d = DateUtils.strToDate(cn.getEndTime(),DateUtils.YYYY_MM_DD);
				model.setEnd(d);
			}
			model.setTime(0L);
			model.setSendCount(0L);
			model.setRowAddTime(new Date());
			couponManager.insertAndGetId(model);
			Long aid = model.getId();
			// 添加图片
			if (StringUtils.isNotBlank(pictures)) {
				String[] sPath = pictures.split(",");
				if (sPath != null) {
					for (String path : sPath) {
						if (StringUtils.isNotBlank(path)) {
							CouponImg ai = new CouponImg();
							ai.setCouponId(aid);
							ai.setImgPath(path);
							ai.setRowAddTime(new Date());
							couponImgManager.insert(ai);
						}
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
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edithotel(HttpServletRequest request, Coupon cn, String pictures) {
		BaseResult baseResult = new BaseResult("SUCCESS","修改成功");
		try {
			Coupon model = couponManager.selectByPrimaryKey(cn.getId());
			if (model == null) {
				throw new PatternException("数据错误");
			}
			BeanUtils.copyProperties(cn, model);
			if (cn.getSendCount() != null) {
				if (cn.getSendCount() > 0) {
					model.setSendCount(cn.getSendCount());
				}else{
					model.setSendCount(-1L);
				}
			}else{
				model.setSendCount(-1L);
			}
			if (cn.getStartTime() != null) {
				Date d = DateUtils.strToDate(cn.getStartTime(),DateUtils.YYYY_MM_DD);
				model.setStart(d);
			}
			if (cn.getEndTime() != null) {
				Date d = DateUtils.strToDate(cn.getEndTime(),DateUtils.YYYY_MM_DD);
				model.setEnd(d);
			}
			
			Long aid = model.getId();
			// 添加图片
			if (StringUtils.isNotBlank(pictures)) {
				String[] sPath = pictures.split(",");
				if (sPath != null) {
					for (String path : sPath) {
						if (StringUtils.isNotBlank(path)) {
							CouponImg ai = new CouponImg();
							ai.setCouponId(aid);
							ai.setImgPath(path);
							ai.setRowAddTime(new Date());
							couponImgManager.insert(ai);
						}
					}
				}
			}
			couponManager.updateByPrimaryKeySelective(model);
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
			couponManager.deleteByPrimaryKey(id);
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
	
	@RequestMapping("/getHonor")
	@ResponseBody
	public BaseListResult<HonourEnjoy> getHe(HttpServletRequest request, Integer type) {
		BaseListResult<HonourEnjoy> baseResult = new BaseListResult<HonourEnjoy>("SUCCESS","成功");
		try {
			HonourEnjoyExample hEnjoyExample = new HonourEnjoyExample();
			hEnjoyExample.or().andTypeEqualTo(type);
			List<HonourEnjoy> hs = heManager.selectByExample(hEnjoyExample);
			baseResult.setResult(hs);
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<HonourEnjoy>(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<HonourEnjoy>(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseListResult<HonourEnjoy>(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseListResult<HonourEnjoy>(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	/**
	 * 跳转优惠券使用管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager2")
	public String manager2() {
		return "/coupon/detail";
	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid2")
	@ResponseBody
	public DataGrid dataGrid2(Coupon cn, PageHelper ph,HttpServletRequest request){	   
		cn.setId(-10l);
		return couponManager.dataGrid(request, cn, ph);
	}
	
	/**
	 * 跳转优惠券使用管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager3")
	public String manager3(HttpServletRequest request, Long cid) {
		Coupon coupon = couponManager.selectByPrimaryKey(cid);
		if (coupon.getEnd() != null) {
			if (coupon.getEnd().getTime() > new Date().getTime()) {
				coupon.setStatus("生效中");
			}
			if (coupon.getEnd().getTime() < new Date().getTime()) {
				coupon.setStatus("已失效");
			}
		}
		request.setAttribute("model", coupon);
		request.setAttribute("cid", cid);
		return "/coupon/useDetail";
	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid3")
	@ResponseBody
	public DataGrid dataGrid3(Long cid, PageHelper ph,HttpServletRequest request){	   
		
		return buserCouponManager.dataGrid(cid, ph, request);
	}
	
	@RequestMapping(value = "/fileUpload", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public BaseListResult<String> fileuploadf(HttpServletRequest request,
			@RequestParam("imgs") MultipartFile[] imgs) {
		BaseListResult<String> baseResult = new BaseListResult<String>(
				"SUCCESS", "图片上传成功");
		String imgDir = "/upload/files/coupon/";
		try {
			List<String> imgsPath = new ArrayList<String>();
			// 图片上传
			if (null != imgs) {
				for (MultipartFile img1 : imgs) {
					
					String fileName = System.currentTimeMillis() + "";
					String path = FileUploadDeleteUtil.upload(img1, request,imgDir, fileName);
					imgsPath.add(path);
				}
				baseResult.setResult(imgsPath);
			}
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseListResult<String>(
					CodeEnum.PATTERN_ERROR.getCode(), e.getMessage());
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
			baseResult = new BaseListResult<String>(
					CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("上传图片返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping("/deleteImg")
	@ResponseBody
	public BaseResult deleteImg(HttpServletRequest request, Long id) {
		BaseResult baseResult = new BaseResult("SUCCESS","图片删除成功");
		try {
			CouponImg img = couponImgManager.selectByPrimaryKey(id);
			if (null == img) {
				throw new PatternException("信息不存在！");
			}
			couponImgManager.deleteByPrimaryKey(id);
			
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
}
