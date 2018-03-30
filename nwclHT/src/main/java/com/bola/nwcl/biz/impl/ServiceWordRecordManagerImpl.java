package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.CarPayRecordManager;
import com.bola.nwcl.biz.ServiceWordRecordManager;
import com.bola.nwcl.biz.ServiceWordRecordManagerImg;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CarPayRecordMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceWordRecordMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.CarPayRecord;
import com.bola.nwcl.dal.mybatis.model.CarPayRecordExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecord;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordExample;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordImg;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordImgExample;
import com.bola.nwcl.web.model.CarPayRecordModel;
import com.bola.nwcl.web.model.ServiceWordRecordImgModel;
import com.bola.nwcl.web.model.ServiceWordRecordModel;

@Service
public class ServiceWordRecordManagerImpl extends ManagerImpl<ServiceWordRecord, ServiceWordRecordExample, Long>
		implements ServiceWordRecordManager {

	@SuppressWarnings("unused")
	private ServiceWordRecordMapper serviceWordRecordMapper;

	@Autowired
	private ServiceUserMapper serviceUserMapper;
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired
	private ServiceWordRecordManagerImg managerImg;
	
	@Autowired
	public ServiceWordRecordManagerImpl(ServiceWordRecordMapper serviceWordRecordMapper) {
		this.mapper = serviceWordRecordMapper;
		this.serviceWordRecordMapper = serviceWordRecordMapper;
	}

	@Override
	public DataGrid dataGrid(ServiceWordRecord serviceWordRecord, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
	 	ServiceWordRecordExample serviceWordRecordExample = new ServiceWordRecordExample();
	 	Criteria c = serviceWordRecordExample.or();
	 	Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		// 小区查询
		if (serviceWordRecord.getCommunityId() != null && serviceWordRecord.getCommunityId() != -1) {
			c.andCommunityIdEqualTo(serviceWordRecord.getCommunityId());
		}
		int total = serviceWordRecordMapper.countByExample(serviceWordRecordExample);
		serviceWordRecordExample.setLimit(ph.getRows());
		serviceWordRecordExample.setOffset((ph.getPage() - 1) * ph.getRows());
		serviceWordRecordExample.setOrderByClause("row_add_time DESC");
		List<ServiceWordRecord> lists = serviceWordRecordMapper.selectByExample(serviceWordRecordExample);
		List<ServiceWordRecordModel> list = new ArrayList<ServiceWordRecordModel>(lists.size());
		for (ServiceWordRecord su : lists) {
			ServiceWordRecordModel model = new ServiceWordRecordModel();
			BeanUtils.copyProperties(su, model);
			if (su.getSuserId() != null) {
				ServiceUser dt = serviceUserMapper.selectByPrimaryKey(su.getSuserId());
				if (dt != null) {
					model.setUaerName(dt.getName());
				}
			}
			if (su.getCommunityId()!=null) {
				Community selectByPrimaryKey = communityMapper.selectByPrimaryKey(su.getCommunityId());
				model.setPayChannel(selectByPrimaryKey.getName());
			}
			if (su.getId()!=null) {
				ServiceWordRecordImgExample serviceWordRecordImgExample = new ServiceWordRecordImgExample();
				serviceWordRecordImgExample.or().andServiceWordRecordIdEqualTo(su.getId());
				List<ServiceWordRecordImg> selectByExample = managerImg.selectByExample(serviceWordRecordImgExample);
				model.setImgPahta(selectByExample);
			}
			list.add(model);
		}
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

}
;