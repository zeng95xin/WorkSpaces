package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HonorCommentManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyRatingMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRating;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRatingExample;

@Service
public class HonorCommentManagerImpl  extends ManagerImpl<HonourEnjoyRating, HonourEnjoyRatingExample, Long> implements HonorCommentManager{
	
	@SuppressWarnings("unused")
	private HonourEnjoyRatingMapper productMapper;
	
	@Autowired
	private BizUserMapper bizUserMapper;
	
	@Autowired
	public HonorCommentManagerImpl(HonourEnjoyRatingMapper productMapper) {
		this.mapper = productMapper;
		this.productMapper = productMapper;
	}

	@Override
	public DataGrid dataGrid(Long hid, PageHelper ph) {
		DataGrid dg = new DataGrid();
		HonourEnjoyRatingExample example = new HonourEnjoyRatingExample();
		Long pid = hid;
		if (pid != null) {
			example.or().andHonourEnjoyIdEqualTo(pid);
		}else{
			example.or().andHonourEnjoyIdEqualTo(-1L);
		}
		dg.setTotal(productMapper.countByExample(example));
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		
		List<HonourEnjoyRating> imgs = productMapper.selectByExample(example);
		if (imgs != null && imgs.size() > 0) {
			for(HonourEnjoyRating r : imgs){
				BizUser user = bizUserMapper.selectByPrimaryKey(r.getBuserId());
				if (user != null) {
					r.setUserNickname(user.getNickname());
					r.setUserAccount(user.getPhone());
				}
			}
		}
		dg.setRows(imgs);
		
		return dg;
	}

}
