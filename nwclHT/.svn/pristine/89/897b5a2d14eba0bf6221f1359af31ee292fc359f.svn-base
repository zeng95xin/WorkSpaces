package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.neighbor.NeighborHelpMessageModel;
import com.bola.nwcl.biz.NeighborHelpMessageManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpMessageMapper;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessage;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessageExample;

@Service
public class NeighborHelpMessageManagerImpl  extends ManagerImpl<NeighborHelpMessage, NeighborHelpMessageExample, Long> implements NeighborHelpMessageManager{
	
	private NeighborHelpMessageMapper neighborHelpMessageMapper;
	
	@Autowired
	public NeighborHelpMessageManagerImpl(NeighborHelpMessageMapper neighborHelpMessageMapper) {
		this.mapper = neighborHelpMessageMapper;
		this.neighborHelpMessageMapper = neighborHelpMessageMapper;
	}

	@Override
	public List<NeighborHelpMessageModel> getLimitMessageModel(long id, Long buserId, int messageCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", messageCount);
		List<NeighborHelpMessageModel> list = neighborHelpMessageMapper.getPageMessageModel(map);
		return list;
	}

	@Override
	public Page<NeighborHelpMessageModel> getPageMessageModel(long id, Long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		NeighborHelpMessageExample example_msg = new NeighborHelpMessageExample();
		if(buserId==null){
			example_msg.or().andNeighborHelpIdEqualTo(id);
		}else{
			example_msg.or().andNeighborHelpIdEqualTo(id).andBuserIdEqualTo(buserId);
			example_msg.or().andNeighborHelpIdEqualTo(id).andGetIdEqualTo(buserId);
		}
		int count = neighborHelpMessageMapper.countByExample(example_msg);
		
		List<NeighborHelpMessageModel> list = neighborHelpMessageMapper.getPageMessageModel(map);
		Page<NeighborHelpMessageModel> pageData = new PageImpl<NeighborHelpMessageModel>(list, pager, count);
		return pageData;
	}
	
	@Override
	public void insertAndGetId(NeighborHelpMessage neighborHelpMessage){
		neighborHelpMessageMapper.insertAndGetId(neighborHelpMessage);
	}

	@Override
	public NeighborHelpMessageModel getOneMessageModel(long id, long buserId) {
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		condition.put("buserId", buserId);
		return neighborHelpMessageMapper.getOneMessageModel(condition);
	}

}
