package com.bola.nwcl.web.controller.depot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.CarPayItemManager;
import com.bola.nwcl.biz.CarPayRecordManager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.CarPayItem;
import com.bola.nwcl.dal.mybatis.model.CarPayRecord;

@Controller
@RequestMapping(value = "web/depotPayment")
public class CarPayRecordController {

	@Autowired
	private CarPayRecordManager carPayRecordManager;

	@RequestMapping("/getPayment")
	public String manager(HttpServletRequest request) {
		return "/depot/CarPayRecord";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(CarPayRecord carPayRecord, PageHelper ph, HttpServletRequest request) {
		return carPayRecordManager.dataGrid(carPayRecord, ph, request);
	}
}
