package com.bola.nwcl.biz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.bola.nwcl.biz.IndexesImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.IndexesImgMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.IndexesImg;
import com.bola.nwcl.dal.mybatis.model.IndexesImgExample;
import com.bola.nwcl.dal.mybatis.model.IndexesImgExample.Criteria;

@Service
public class IndexesImgManagerImpl  extends ManagerImpl<IndexesImg, IndexesImgExample, Long> implements IndexesImgManager{
	
	private IndexesImgMapper apper;

	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	public IndexesImgManagerImpl(IndexesImgMapper apper) {
		this.mapper = apper;
		this.apper = apper;
	}

	@Override
	public DataGrid dataGrid(IndexesImg indexesImg, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		IndexesImgExample example = new IndexesImgExample();
		
		Criteria c= example.or();
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		if (indexesImg != null) {
			if (indexesImg.getId() != null) {
				c.andIdEqualTo(indexesImg.getId());
			}
			if (indexesImg.getType() != null) {
				if (indexesImg.getType() == 1) {
					c.andTypeEqualTo(1);
				}
				else if (indexesImg.getType() == 2) {
					c.andTypeEqualTo(2);
				}
				else if (indexesImg.getType() == 3) {
					c.andTypeEqualTo(3);
				}
				else if (indexesImg.getType() == 4) {
					c.andTypeEqualTo(4);
				}
				else if (indexesImg.getType() == 5) {
					c.andTypeEqualTo(5);
				}
			}
		}
		dg.setTotal(apper.countByExample(example));
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<IndexesImg> imgs = apper.selectByExample(example);
		if (imgs != null && imgs.size() > 0) {
			for(IndexesImg  in : imgs){
				Community com = communityMapper.selectByPrimaryKey(in.getCommunityId());
				if (com != null) {
					in.setCommunityName(com.getName());
				}
			}
		}
		dg.setRows(imgs);
		
		return dg;
	}
	
	
}
