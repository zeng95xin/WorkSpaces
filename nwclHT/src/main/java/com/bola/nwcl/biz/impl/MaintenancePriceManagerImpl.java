package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.MaintenancePriceManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenancePriceMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.MaintenancePrice;
import com.bola.nwcl.dal.mybatis.model.MaintenancePriceExample;
import com.bola.nwcl.dal.mybatis.model.MaintenancePriceExample.Criteria;


@Service
public class MaintenancePriceManagerImpl  extends ManagerImpl< MaintenancePrice,  MaintenancePriceExample, Long> implements  MaintenancePriceManager{
	
	private  MaintenancePriceMapper  maintenancePriceMapper;
	
	@Autowired
	public MaintenancePriceManagerImpl( MaintenancePriceMapper maintenancePriceMapper) {
		this.mapper = maintenancePriceMapper;
		this.maintenancePriceMapper = maintenancePriceMapper;
	}

	@Override
	public DataGrid dataGrid(MaintenancePrice maintenancePrice, PageHelper ph, Long type, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		MaintenancePriceExample example=new MaintenancePriceExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		Criteria c = example.or();
		c.andServiceTypeIdEqualTo(type);
		if(maintenancePrice.getServiceProject()!=null){
			c.andServiceProjectLike("%"+maintenancePrice.getServiceProject()+"%");
		}
		
		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		@SuppressWarnings("unchecked")
		List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
		if(user.getRoleId() != 1){
			c.andCommunityIdIn(communityIds);
		}

		List<MaintenancePrice> list=maintenancePriceMapper.selectByExample(example);
		dg.setRows(list);
		MaintenancePriceExample example1=new MaintenancePriceExample();
		if(user.getRoleId() != 1){
			example1.or().andServiceTypeIdEqualTo(type).andCommunityIdIn(communityIds);
		}else{
			example1.or().andServiceTypeIdEqualTo(type);
		}
		int total=maintenancePriceMapper.countByExample(example1);
		dg.setTotal(total);
		
		return dg;
	}

	

}
