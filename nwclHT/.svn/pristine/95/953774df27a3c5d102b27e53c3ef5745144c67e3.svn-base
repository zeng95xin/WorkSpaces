package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.keeper.FridendsReportModel;
import com.bola.nwcl.api.model.keeper.FriendsReportImgModel;
import com.bola.nwcl.biz.KeeperFriendsCircleManager;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpMapper;

@Service
public class KeeperFriendsCircleManagerImpl implements KeeperFriendsCircleManager{
	
	@Autowired
	private NeighborHelpMapper neighborMapper;
	
	@Override
	public Page<FridendsReportModel> getAllReport(int page, int rows) {
		// TODO 获取所有举报信息
		
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<FridendsReportModel> list = new ArrayList<FridendsReportModel>();
		
		Page<FridendsReportModel> pageData = new PageImpl<FridendsReportModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public Page<FridendsReportModel> getAllSensitive(int page, int rows) {
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<FridendsReportModel> list = null;
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", pager.getOffset());
		condition.put("limit", pager.getPageSize());
		condition.put("orderByClause", " row_add_time desc");
		list = neighborMapper.getAllSensitive(condition);
		
		for(FridendsReportModel m : list){
			if(m.getType() == 1){
				List<FriendsReportImgModel> list_img = neighborMapper.getFriendsReportImgModelByLike(m.getId());
				m.setImgs(list_img);
			}else if(m.getType() == 2){
				List<FriendsReportImgModel> list_img = neighborMapper.getFriendsReportImgModelByNeighbor(m.getId());
				m.setImgs(list_img);
			}
		}
		
		count = neighborMapper.getAllSensitiveCount();
		
		Page<FridendsReportModel> pageData = new PageImpl<FridendsReportModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public Page<FridendsReportModel> getAllContent(int page, int rows) {
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<FridendsReportModel> list = null;
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", pager.getOffset());
		condition.put("limit", pager.getPageSize());
		condition.put("orderByClause", " row_add_time desc");
		list = neighborMapper.getAllContent(condition);
		
		for(FridendsReportModel m : list){
			if(m.getType() == 1){
				List<FriendsReportImgModel> list_img = neighborMapper.getFriendsReportImgModelByLike(m.getId());
				m.setImgs(list_img);
			}else if(m.getType() == 2){
				List<FriendsReportImgModel> list_img = neighborMapper.getFriendsReportImgModelByNeighbor(m.getId());
				m.setImgs(list_img);
			}
		}
		
		count = neighborMapper.getAllContentCount();
		
		Page<FridendsReportModel> pageData = new PageImpl<FridendsReportModel>(list, pager, count);
		
		return pageData;
	}
	
}
