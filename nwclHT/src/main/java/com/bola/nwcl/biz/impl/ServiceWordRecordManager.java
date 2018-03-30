package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ServiceWordRecordManagerImg;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.ServiceWordRecordImgMapper;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordImg;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordImgExample;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordImgExample.Criteria;
import com.bola.nwcl.web.model.ServiceWordRecordImgModel;

@Service
public class ServiceWordRecordManager extends ManagerImpl<ServiceWordRecordImg, ServiceWordRecordImgExample, Long>
		implements ServiceWordRecordManagerImg {

	@SuppressWarnings("unused")
	private ServiceWordRecordImgMapper serviceWordRecordMapper;

	@Autowired
	public ServiceWordRecordManager(ServiceWordRecordImgMapper serviceWordRecordMapper) {
		this.mapper = serviceWordRecordMapper;
		this.serviceWordRecordMapper = serviceWordRecordMapper;
	}

	@Override
	public List<ServiceWordRecordImgModel> dataGrid(Long id) {
		ServiceWordRecordImgExample imgExample = new ServiceWordRecordImgExample();
		Criteria or = imgExample.or();
		or.andServiceWordRecordIdEqualTo(id);
		List<ServiceWordRecordImg> selectByExample = serviceWordRecordMapper.selectByExample(imgExample);
		List<ServiceWordRecordImgModel> list = new ArrayList<ServiceWordRecordImgModel>();
		for (ServiceWordRecordImg serviceWordRecordImg : selectByExample) {
			ServiceWordRecordImgModel serviceWordRecordImgModel = new ServiceWordRecordImgModel();
			serviceWordRecordImgModel.setImgPahta(serviceWordRecordImg.getImgPath());
			list.add(serviceWordRecordImgModel);
		}
		return list;
	}

}
