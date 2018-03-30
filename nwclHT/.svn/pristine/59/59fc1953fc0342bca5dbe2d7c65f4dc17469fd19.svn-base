package com.bola.nwcl.web.controller.borrow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.ThingManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Thing;


/**
 * 物品借用管理 
 */
@Controller
@RequestMapping(value = "web/thing")
public class ThingWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String saveDir="/upload/files/thing";
	
	@Autowired
	private  ThingManager thingManager;
	@Autowired
	private  CommunityManager communityManager;
	
	/**
	 * 跳转到维修单据页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/borrow/thing";
	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Thing thing, PageHelper ph,HttpServletRequest request){	    
		return thingManager.dataGrid(thing, ph, request); 
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		List<Community> coms = communityManager.selectByExample(null);
		request.setAttribute("coms", coms);
		return "/borrow/thingAdd";
	}
	
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(Thing t,HttpServletRequest request,@RequestParam("file") MultipartFile file) {

		BaseResult baseResult = new BaseResult("SUCCESS", "添加成功");
		try {
			
				if(!file.isEmpty()){
					String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
					String fileName=UUID.randomUUID().toString().substring(0,12);
					fileName = todayStr + fileName;
					String[] saveFileName=FileUploadDeleteUtil.upload2(file, request, saveDir, fileName);
					t.setImgPath(saveFileName[0]);
					t.setRemainingNum(t.getNum());
					thingManager.insert(t);														
				}else{
					throw new PatternException("请上传一张图片");
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
	 * 跳转到标签修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		List<Community> coms = communityManager.selectByExample(null);
		request.setAttribute("coms", coms);
		Thing t = thingManager.selectByPrimaryKey(id);
		request.setAttribute("t", t);
		return "/borrow/thingEdit";
	}

	/**
	 * 修改标签
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(Thing t,HttpServletRequest request,@RequestParam("file") MultipartFile file) {
		BaseResult baseResult = new BaseResult("SUCCESS", "修改成功");
		try {
				Thing thing = new Thing();
				BeanUtils.copyProperties(t, thing);;
				if(!file.isEmpty()){
					String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
					String fileName=UUID.randomUUID().toString().substring(0,12);
					fileName = todayStr + fileName;
					String[] saveFileName=FileUploadDeleteUtil.upload2(file, request, saveDir, fileName);
					thing.setImgPath(saveFileName[0]);
				}
				thing.setRemainingNum(t.getNum());
				thingManager.updateByPrimaryKeySelective(thing);												
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
		logger.info("修改返回值：{}", baseResult.toString());
		return baseResult;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(Long id, HttpSession session) {
		/*SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil
				.getSessionInfoName());*/
		BaseResult baseResult = new BaseResult("SUCCESS", "删除成功");
		try{
		if (id != null) {// 不能删除自己
			thingManager.deleteByPrimaryKey(id);
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
}
