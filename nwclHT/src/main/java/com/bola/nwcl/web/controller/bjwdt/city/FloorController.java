package com.bola.nwcl.web.controller.bjwdt.city;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "web/bjwdt/floor")
public class FloorController extends BaseController{
	
	@Autowired private CommunityManager communityManager;
	
	@Autowired private AdminManager adminManager;
	
	/**
	 * 跳转图片管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/bjwdt/floor/floor";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph, HttpServletRequest request, Integer flagSale) {
		
		JSONObject bizData = new JSONObject();
		bizData.put("type", 6);
		bizData.put("num", ph.getPage());
		bizData.put("pageSize", ph.getRows());
		
		
		JSONObject data = new JSONObject();
		if(flagSale != null){
			data.put("flagSale", flagSale);
			bizData.put("data", data);
		}
		
		
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
			String name, 
			String descsale, 
			String flagsale, 
			String floorprice, 
			String label, 
			String descopentime, 
			String estatedid, 
			String flagApartment, 
			String flagSample, 
			String flagReal, 
			String flagSupporting, 
			String flagBuy, 
			String tel, 
			String descbuilding, 
			String descpropertyyear, 
			String descvolume, 
			String descgreening, 
			String descdoor, 
			String descfloor, 
			String descpropertymoney, 
			String descpropertycompany, 
			String desccar, 
			String descsubmittime, 
			String descschool, 
			String descbus, 
			String descSpeed, 
			String flooraddress, 
			String descbuy, 
			String descdesc
			,@RequestParam("img") MultipartFile file
			) throws Exception {
		
		BaseResult baseResult = new BaseResult("SUCCESS","成功");
		JSONObject bizData = new JSONObject();
		
		String floorimg = "";
		if (file != null && !file.isEmpty()) {
			String uploadPath = "/upload/files/bjwdt";
			String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String fileName = UUID.randomUUID().toString().substring(0, 12);
			fileName = todayStr + fileName;
			floorimg = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
		}
		
		bizData.put("type", 10);
		JSONObject data = new JSONObject();
		
		data.put("name", name);
		data.put("descsale", descsale);
		data.put("flagsale", flagsale);
		data.put("floorprice", floorprice);
		data.put("label", label);
		data.put("descopentime", descopentime);
		data.put("estatedid", UUID.randomUUID().toString());
		data.put("flagApartment", flagApartment);
		data.put("flagSample", flagSample);
		data.put("flagReal", flagReal);
		data.put("flagSupporting", flagSupporting);
		data.put("flagBuy", flagBuy);
		data.put("tel", tel);
		data.put("descbuilding", descbuilding);
		data.put("descpropertyyear", descpropertyyear);
		data.put("descvolume", descvolume);
		data.put("descgreening", descgreening);
		data.put("descdoor", descdoor);
		data.put("descfloor", descfloor);
		data.put("descpropertymoney", descpropertymoney);
		data.put("descpropertycompany", descpropertycompany);
		data.put("desccar", desccar);
		data.put("descsubmittime", descsubmittime);
		data.put("descschool", descschool);
		data.put("descbus", descbus);
		data.put("descSpeed", descSpeed);
		data.put("flooraddress", flooraddress);
		data.put("descbuy", descbuy);
		data.put("descdesc", descdesc);
		
		data.put("floorimg", floorimg);

		bizData.put("data", data);
		JSONObject result = BjwdtUtil.doPost(bizData);
		baseResult.setMessage(result.getString("result"));
		return baseResult;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, String code,
			String name, 
			String descsale, 
			String flagsale, 
			String floorprice, 
			String label, 
			String descopentime, 
			String estatedid, 
			String flagApartment, 
			String flagSample, 
			String flagReal, 
			String flagSupporting, 
			String flagBuy, 
			String tel, 
			String descbuilding, 
			String descpropertyyear, 
			String descvolume, 
			String descgreening, 
			String descdoor, 
			String descfloor, 
			String descpropertymoney, 
			String descpropertycompany, 
			String desccar, 
			String descsubmittime, 
			String descschool, 
			String descbus, 
			String descSpeed, 
			String flooraddress, 
			String descbuy, 
			String descdesc
			,String reginCode 
			,String descDecoration 
			,@RequestParam("img") MultipartFile file, String oldImgPath
			) throws Exception {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		
		JSONObject bizData = new JSONObject();
		bizData.put("type", 11);
		JSONObject data = new JSONObject();
		data.put("code", code);
		
		String floorimg = "";
		if (file != null && !file.isEmpty()) {
			String uploadPath = "/upload/files/bjwdt";
			String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String fileName = UUID.randomUUID().toString().substring(0, 12);
			fileName = todayStr + fileName;
			floorimg = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
		}else{
			if(StringUtils.isNotBlank(oldImgPath)){
				floorimg = oldImgPath;
			}
		}
		
		data.put("reginCode", reginCode);
		data.put("descDecoration", descDecoration);
		data.put("name", name);
		data.put("descsale", descsale);
		data.put("flagsale", flagsale);
		data.put("floorprice", floorprice);
		data.put("label", label);
		data.put("descopentime", descopentime);
		data.put("flagApartment", flagApartment);
		data.put("flagSample", flagSample);
		data.put("flagReal", flagReal);
		data.put("flagSupporting", flagSupporting);
		data.put("flagBuy", flagBuy);
		data.put("tel", tel);
		data.put("descbuilding", descbuilding);
		data.put("descpropertyyear", descpropertyyear);
		data.put("descvolume", descvolume);
		data.put("descgreening", descgreening);
		data.put("descdoor", descdoor);
		data.put("descfloor", descfloor);
		data.put("descpropertymoney", descpropertymoney);
		data.put("descpropertycompany", descpropertycompany);
		data.put("desccar", desccar);
		data.put("descsubmittime", descsubmittime);
		data.put("descschool", descschool);
		data.put("descbus", descbus);
		data.put("descSpeed", descSpeed);
		data.put("flooraddress", flooraddress);
		data.put("descbuy", descbuy);
		data.put("descdesc", descdesc);
		
		data.put("floorimg", floorimg);
		
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
		bizData.put("type", 12);
		bizData.put("floorCode", code);
		JSONObject result = BjwdtUtil.doPost(bizData);
		baseResult.setMessage(result.getString("result"));
		
		return baseResult;
	}
	
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		JSONObject bizData = new JSONObject();
		bizData.put("type", 30);
		bizData.put("num", 1);
		bizData.put("pageSize", 10000);
		JSONObject result = BjwdtUtil.doPost(bizData);
		JSONArray rows = result.getJSONObject("list").getJSONArray("elements");
		request.setAttribute("citys", rows);
		return "/bjwdt/floor/floorAdd";
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		JSONObject bizData = new JSONObject();
		bizData.put("type", 30);
		bizData.put("num", 1);
		bizData.put("pageSize", 10000);
		JSONObject result = BjwdtUtil.doPost(bizData);
		JSONArray rows = result.getJSONObject("list").getJSONArray("elements");
		request.setAttribute("citys", rows);
		return "/bjwdt/floor/floorEdit";
	}
	
}
