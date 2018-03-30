package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.ConvenienceTelephoneManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.ConvenienceTelephoneMapper;
import com.bola.nwcl.dal.mybatis.mapper.ConvenienceTelephoneTypeMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephone;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneExample;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneType;
import com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneTypeExample;


@Service
public class ConvenienceTelephoneManagerImpl  extends ManagerImpl<ConvenienceTelephone, ConvenienceTelephoneExample, Long> implements ConvenienceTelephoneManager{

	private ConvenienceTelephoneMapper convenienceTelephoneMapper;
	@Autowired
	private CommunityManager communityManager;
	
	
	@Autowired
	private ConvenienceTelephoneTypeMapper typeMapper;
	@Autowired
	public ConvenienceTelephoneManagerImpl(ConvenienceTelephoneMapper convenienceTelephoneMapper) {
		this.mapper = convenienceTelephoneMapper;
		this.convenienceTelephoneMapper = convenienceTelephoneMapper;
	}

	@Override
	public DataGrid dataGrid(HttpServletRequest request, ConvenienceTelephone convenienceTelephone,
			PageHelper ph) {
		DataGrid dg = new DataGrid();
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		ConvenienceTelephoneExample example=new ConvenienceTelephoneExample();
		com.bola.nwcl.dal.mybatis.model.ConvenienceTelephoneExample.Criteria c = example.or();
		if (admin != null) {
			if (admin.getRoleId() != 1) {
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				c.andCommunityIdIn(communityIds);
			}
		}
		
		if (convenienceTelephone.getCommunityId() != null) {
			c.andCommunityIdEqualTo(convenienceTelephone.getCommunityId());
		}
		int total=convenienceTelephoneMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<ConvenienceTelephone> list=convenienceTelephoneMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(ConvenienceTelephone cc : list){
				ConvenienceTelephoneType type = typeMapper.selectByPrimaryKey(cc.getType());
				if (type != null) {
					cc.setTypeName(type.getName());
				}
				if (cc.getCommunityId() != null) {
					Community community = communityManager.selectByPrimaryKey(cc.getCommunityId());
					if (community != null) {
						cc.setCommunityName(community.getName());
					}
				}
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);
		
		return dg;
	}

	
	

}
