package com.bola.nwcl.web.controller.bjwdt.city;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.api.controller.BaseController;
import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;


/**
 * 城市管理
 * 
 */
@Controller
@RequestMapping(value = "web/bjwdt/owner")
public class OwnerController extends BaseController{
	
	@Autowired private CommunityManager communityManager;
	
	@Autowired private AdminManager adminManager;
	
	/**
	 * 跳转图片管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/bjwdt/owner/owner";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, HttpServletRequest request, Integer finType) {
		
		JSONObject bizData = new JSONObject();
		bizData.put("type", 23);
		bizData.put("num", ph.getPage());
		bizData.put("pageSize", ph.getRows());
		
		
		JSONObject data = new JSONObject();
		if(finType == null){
			finType = 1;
		}
		data.put("finType", finType);
		bizData.put("data", data);
		
		
		JSONObject result = BjwdtUtil.doPost(bizData);
		
		int total = result.getIntValue("size");
		JSONArray rows = result.getJSONObject("list").getJSONArray("elements");
		DataGrid dg = new DataGrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request,
			String name
			,@RequestParam("img") MultipartFile file
			) throws Exception {
		BaseResult baseResult = new BaseResult("SUCCESS","成功");
		JSONObject bizData = new JSONObject();
		
		String ownerimg = "";
		if (file != null && !file.isEmpty()) {
			String uploadPath = "/upload/files/bjwdt";
			String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String fileName = UUID.randomUUID().toString().substring(0, 12);
			fileName = todayStr + fileName;
			ownerimg = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
		}
		
		bizData.put("type", 10);
		JSONObject data = new JSONObject();
		
		data.put("name", name);
		
		data.put("ownerimg", ownerimg);

		bizData.put("data", data);
		JSONObject result = BjwdtUtil.doPost(bizData);
		baseResult.setMessage(result.getString("result"));
		return baseResult;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, String code,
			String loginName, 
			String nickName
			,@RequestParam("img") MultipartFile file, String oldImgPath
			) throws Exception {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		
		JSONObject bizData = new JSONObject();
		bizData.put("type", 21);
		JSONObject data = new JSONObject();
		data.put("code", code);
		
		/*String ownerimg = "";
		if (file != null && !file.isEmpty()) {
			String uploadPath = "/upload/files/bjwdt";
			String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String fileName = UUID.randomUUID().toString().substring(0, 12);
			fileName = todayStr + fileName;
			ownerimg = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
		}else{
			if(StringUtils.isNotBlank(oldImgPath)){
				ownerimg = oldImgPath;
			}
		}*/
		
		data.put("loginName", loginName);
		data.put("nickName", nickName);
		
		
		bizData.put("data", data);
		JSONObject result = BjwdtUtil.doPost(bizData);
		baseResult.setMessage(result.getString("result"));
		
		return baseResult;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResult delete(HttpServletRequest request, String code) {
		BaseResult baseResult = new BaseResult("SUCCESS","删除成功");		
		
		JSONObject bizData = new JSONObject();
		bizData.put("type", 24);
		JSONObject data = new JSONObject();
		data.put("code", code);
		bizData.put("data", data);
		JSONObject result = BjwdtUtil.doPost(bizData);
		baseResult.setMessage(result.getString("result"));
		
		return baseResult;
	}
	
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/bjwdt/owner/ownerAdd";
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		return "/bjwdt/owner/ownerEdit";
	}
	
}
