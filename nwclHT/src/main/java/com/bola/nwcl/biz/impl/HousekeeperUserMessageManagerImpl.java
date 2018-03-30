package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.HousekeeperUserMessageModel;
import com.bola.nwcl.biz.HousekeeperUserMessageManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HousekeeperUserMessageMapper;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessage;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample;

@Service
public class HousekeeperUserMessageManagerImpl  extends ManagerImpl<HousekeeperUserMessage, HousekeeperUserMessageExample, Long> implements HousekeeperUserMessageManager{
	
	private HousekeeperUserMessageMapper housekeeperUserMessageMapper;
	
	@Autowired
	public HousekeeperUserMessageManagerImpl(HousekeeperUserMessageMapper housekeeperUserMessageMapper) {
		this.mapper = housekeeperUserMessageMapper;
		this.housekeeperUserMessageMapper = housekeeperUserMessageMapper;
	}

	@Override
	public Page<HousekeeperUserMessageModel> getUserAllHousekeeperMsg(long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		HousekeeperUserMessageExample example = new HousekeeperUserMessageExample();
		example.or().andBuserIdEqualTo(buserId);
		int count = housekeeperUserMessageMapper.countByExample(example);
		List<HousekeeperUserMessageModel> list = housekeeperUserMessageMapper.getUserAllHousekeeperMsg(map);
		Page<HousekeeperUserMessageModel> pageData = new PageImpl<HousekeeperUserMessageModel>(list, pager, count);
		return pageData;
	}
	
	
	@Override
	public int selectCountByExample(HousekeeperUserMessageExample example){
		return housekeeperUserMessageMapper.selectCountByExample(example);
	}

}
