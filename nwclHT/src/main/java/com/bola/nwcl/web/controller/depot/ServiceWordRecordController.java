package com.bola.nwcl.web.controller.depot;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.DepartmentManager;
import com.bola.nwcl.biz.ServiceWordRecordManager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecord;

@Controller
@RequestMapping(value = "web/ServiceWordRecord")
public class ServiceWordRecordController {

	@Autowired
	private ServiceWordRecordManager serviceWordRecordManager;

	@Autowired
	private DepartmentManager departmentManager;

	@Autowired
	private CommunityManager communityManager;
	@RequestMapping("/getServiceWordRecord")
	public String manager(HttpServletRequest request) {
		List<Community> coms = communityManager.selectByExample(null);
		List<Department> departments = departmentManager.selectByExample(null);
		request.setAttribute("comus", coms);
		request.setAttribute("departments", departments);
		return "/clock/ServiceWordRecord";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(ServiceWordRecord serviceWordRecord, PageHelper ph, HttpServletRequest request) {
		return serviceWordRecordManager.dataGrid(serviceWordRecord, ph, request);
	}
}
