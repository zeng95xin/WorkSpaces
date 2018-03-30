package com.bola.nwcl.web.controller.depot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.CarPayItemManager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.CarPayItem;

@Controller
@RequestMapping(value = "web/depotPay")
public class CarPayItemController {

	@Autowired
	private CarPayItemManager carPayItemManager;

	@RequestMapping("/getPay")
	public String manager(HttpServletRequest request) {
		return "/depot/CarPayItem";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(CarPayItem carPayItem, PageHelper ph, HttpServletRequest request) {
		return carPayItemManager.dataGrid(carPayItem, ph, request);
	}
}
