package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.DepotGetComeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CarParkRecordMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.CarParkRecord;
import com.bola.nwcl.dal.mybatis.model.CarParkRecordExample;
import com.bola.nwcl.dal.mybatis.model.CarParkRecordExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.web.model.DepotGetComeModel;

@Service
public class DepotGetComeManagerImpl extends ManagerImpl<CarParkRecord, CarParkRecordExample, Long>
		implements DepotGetComeManager {

	@SuppressWarnings("unused")
	private CarParkRecordMapper carParkRecordMapper;

	@Autowired
	private ServiceUserMapper serviceUserMapper;

	@Autowired
	public DepotGetComeManagerImpl(CarParkRecordMapper carParkRecordMapper) {
		this.mapper = carParkRecordMapper;
		this.carParkRecordMapper = carParkRecordMapper;
	}

	@Override
	public DataGrid dataGrid(CarParkRecord carParkRecord, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		CarParkRecordExample carParkRecordExample = new CarParkRecordExample();
		Criteria or = carParkRecordExample.or();
		if (carParkRecord.getCardNo() != null) {
			or.andCardNoEqualTo(carParkRecord.getCardNo());
		}
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		int total = carParkRecordMapper.countByExample(carParkRecordExample);
		carParkRecordExample.setLimit(ph.getRows());
		carParkRecordExample.setOffset((ph.getPage() - 1) * ph.getRows());
		carParkRecordExample.setOrderByClause("row_add_time DESC");
		List<CarParkRecord> lists = carParkRecordMapper.selectByExample(carParkRecordExample);
		List<DepotGetComeModel> list = new ArrayList<DepotGetComeModel>(lists.size());
		for (CarParkRecord su : lists) {
			DepotGetComeModel model = new DepotGetComeModel();
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
