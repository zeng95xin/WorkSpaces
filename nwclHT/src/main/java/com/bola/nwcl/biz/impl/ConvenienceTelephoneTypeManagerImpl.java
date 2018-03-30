package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ConvenienceTelephoneTypeManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.ConvenienceTelephoneTypeMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneType;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneTypeExample;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneTypeExample.Criteria;


@Service
public class ConvenienceTelephoneTypeManagerImpl  extends ManagerImpl<ConvenienceTelephoneType, ConvenienceTelephoneTypeExample, Integer> implements ConvenienceTelephoneTypeManager{
	
	private ConvenienceTelephoneTypeMapper convenienceTelephoneTypeMapper;
	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	public ConvenienceTelephoneTypeManagerImpl(ConvenienceTelephoneTypeMapper convenienceTelephoneTypeMapper) {
		this.mapper = convenienceTelephoneTypeMapper;
		this.convenienceTelephoneTypeMapper = convenienceTelephoneTypeMapper;
	}

	@Override
	public DataGrid dataGrid(HttpServletRequest request, ConvenienceTelephoneType convenienceTelephoneType,
			PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		ConvenienceTelephoneTypeExample example=new ConvenienceTelephoneTypeExample();
		Criteria c = example.or();
		if (admin != null) {
			if (admin.getRoleId() != 1) {
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				c.andCommunityIdIn(communityIds);
			}
		}
		if (convenienceTelephoneType.getCommunityId() != null) {
			c.andCommunityIdEqualTo(convenienceTelephoneType.getCommunityId());
		}
		int total=convenienceTelephoneTypeMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<ConvenienceTelephoneType> list=convenienceTelephoneTypeMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(ConvenienceTelephoneType cc : list){
				Community community = communityMapper.selectByPrimaryKey(cc.getCommunityId());
				if (community != null) {
					cc.setCommunityName(community.getName());
				}
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);
		
		return dg;
	}
	
	
	@Override
	public List<ConvenienceTelephoneType> typeList() {
		List<ConvenienceTelephoneType> list=new ArrayList<ConvenienceTelephoneType>();
		list=convenienceTelephoneTypeMapper.selectByExample(null);
		return list;
	}
}
