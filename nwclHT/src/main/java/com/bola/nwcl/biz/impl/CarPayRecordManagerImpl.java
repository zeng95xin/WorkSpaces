package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.CarPayRecordManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CarPayRecordMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.CarPayRecord;
import com.bola.nwcl.dal.mybatis.model.CarPayRecordExample;
import com.bola.nwcl.dal.mybatis.model.CarPayRecordExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.web.model.CarPayRecordModel;

@Service
public class CarPayRecordManagerImpl extends ManagerImpl<CarPayRecord, CarPayRecordExample, Long>
		implements CarPayRecordManager {

	@SuppressWarnings("unused")
	private CarPayRecordMapper carPayRecordMapper;

	@Autowired
	private ServiceUserMapper serviceUserMapper;

	@Autowired
	public CarPayRecordManagerImpl(CarPayRecordMapper carPayRecordMapper) {
		this.mapper = carPayRecordMapper;
		this.carPayRecordMapper = carPayRecordMapper;
	}

	@Override
	public DataGrid dataGrid(CarPayRecord carPayRecord, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		CarPayRecordExample carPayRecordExample = new CarPayRecordExample();
		Criteria or = carPayRecordExample.or();
		if (carPayRecord.getCardNo() != null) {
			or.andCardNoEqualTo(carPayRecord.getCardNo());
		}
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		int total = carPayRecordMapper.countByExample(carPayRecordExample);
		carPayRecordExample.setLimit(ph.getRows());
		carPayRecordExample.setOffset((ph.getPage() - 1) * ph.getRows());
		carPayRecordExample.setOrderByClause("row_add_time DESC");
		List<CarPayRecord> lists = carPayRecordMapper.selectByExample(carPayRecordExample);
		List<CarPayRecordModel> list = new ArrayList<CarPayRecordModel>(lists.size());
		for (CarPayRecord su : lists) {
			CarPayRecordModel model = new CarPayRecordModel();
			BeanUtils.copyProperties(su, model);
			if (su.getBuserId() != null) {
				ServiceUser dt = serviceUserMapper.selectByPrimaryKey(su.getBuserId());
				if (dt != null) {
					model.setUaerName(dt.getName());
				}
			}
			list.add(model);
		}
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

}
