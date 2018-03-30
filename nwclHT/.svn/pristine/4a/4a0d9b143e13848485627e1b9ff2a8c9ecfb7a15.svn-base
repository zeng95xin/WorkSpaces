package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.CarPayItemManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CarPayItemMapper;
import com.bola.nwcl.dal.mybatis.mapper.CarPayRecordMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.CarPayItem;
import com.bola.nwcl.dal.mybatis.model.CarPayItemExample;
import com.bola.nwcl.dal.mybatis.model.CarPayItemExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.CarPayRecord;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.web.model.CarPayItemModel;

@Service
public class CarPayItemManagerImpl extends ManagerImpl<CarPayItem, CarPayItemExample, Long>
		implements CarPayItemManager {

	@SuppressWarnings("unused")
	private CarPayItemMapper carPayItemMapper;

	@Autowired
	private ServiceUserMapper serviceUserMapper;
	@Autowired
	private CarPayRecordMapper carPayRecordMapper;
	@Autowired
	public CarPayItemManagerImpl(CarPayItemMapper carPayItemMapper) {
		this.mapper = carPayItemMapper;
		this.carPayItemMapper = carPayItemMapper;
	}

	@Override
	public DataGrid dataGrid(CarPayItem carPayItem, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		CarPayItemExample carPayItemExample = new CarPayItemExample();
		Criteria or = carPayItemExample.or();
		if (carPayItem.getCardNo() != null) {
			or.andCardNoEqualTo(carPayItem.getCardNo());
		}
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		int total = carPayItemMapper.countByExample(carPayItemExample);
		carPayItemExample.setLimit(ph.getRows());
		carPayItemExample.setOffset((ph.getPage() - 1) * ph.getRows());
		carPayItemExample.setOrderByClause("row_add_time DESC");
		List<CarPayItem> lists = carPayItemMapper.selectByExample(carPayItemExample);
		List<CarPayItemModel> list = new ArrayList<CarPayItemModel>(lists.size());
		for (CarPayItem su : lists) {
			CarPayItemModel model = new CarPayItemModel();
			BeanUtils.copyProperties(su, model);
			if (su.getBuserId() != null) {
				ServiceUser dt = serviceUserMapper.selectByPrimaryKey(su.getBuserId());
				if (dt != null) {
					model.setUaerName(dt.getName());
				}
			}
			if (su.getCarPayId()!=null) {
				CarPayRecord selectByPrimaryKey = carPayRecordMapper.selectByPrimaryKey(su.getCarPayId());
				model.setPayChannel(selectByPrimaryKey.getPayChannel());
			}
			list.add(model);
		}
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

}
