package com.bola.nwcl.web.controller.depot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.DepotGetComeManager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.CarParkRecord;

@Controller
@RequestMapping(value = "web/depotCome")
public class DepotGetComeController {

	@Autowired
	private DepotGetComeManager depotGetComeManager;

	@RequestMapping("/getCome")
	public String manager(HttpServletRequest request) {
		return "/depot/DepotGetCome";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(CarParkRecord carParkRecord, PageHelper ph, HttpServletRequest request) {
		return depotGetComeManager.dataGrid(carParkRecord, ph, request);
	}
}
