package com.bola.nwcl.web.controller.bjwdt.city;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.api.controller.BaseController;
import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;


/**
 * 城市管理
 * 
 */
@Controller
@RequestMapping(value = "web/bjwdt/city")
public class CityController extends BaseController{
	
	@Autowired private CommunityManager communityManager;
	
	@Autowired private AdminManager adminManager;
	
	/**
	 * 跳转图片管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/bjwdt/city/city";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(PageHelper ph,HttpServletRequest request) {
		
		JSONObject bizData = new JSONObject();
		bizData.put("type", 30);
		bizData.put("num", ph.getPage());
		bizData.put("pageSize", ph.getRows());
		
		JSONObject result = BjwdtUtil.doPost(bizData);
		
		int total = result.getIntValue("size");
		JSONArray rows = result.getJSONObject("list").getJSONArray("elements");
		DataGrid dg = new DataGrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/bjwdt/city/cityAdd";
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		return "/bjwdt/city/cityEdit";
	}

	@RequestMapping("/add")
	@ResponseBody
	public BaseResult add(HttpServletRequest request, String name) {
		BaseResult baseResult = new BaseResult("SUCCESS","成功");
		JSONObject bizData = new JSONObject();
		bizData.put("type", 31);
		JSONObject data = new JSONObject();
		data.put("name", name);
		bizData.put("data", data);
		JSONObject result = BjwdtUtil.doPost(bizData);
		baseResult.setMessage(result.getString("result"));
		return baseResult;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public BaseResult edit(HttpServletRequest request, String code, String name) {
		BaseResult baseResult = new BaseResult("SUCCESS","编辑成功");
		
		JSONObject bizData = new JSONObject();
		bizData.put("type", 32);
		JSONObject data = new JSONObject();
		data.put("name", name);
		data.put("code", code);
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
		bizData.put("type", 33);
		JSONObject data = new JSONObject();
		data.put("code", code);
		bizData.put("data", data);
		JSONObject result = BjwdtUtil.doPost(bizData);
		baseResult.setMessage(result.getString("result"));
		
		return baseResult;
	}
	
}
