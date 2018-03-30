package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HonorProductManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyProductMapper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProduct;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProductExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProductExample.Criteria;

@Service
public class HonorProductManagerImpl  extends ManagerImpl<HonourEnjoyProduct, HonourEnjoyProductExample, Long> implements HonorProductManager{
	
	@SuppressWarnings("unused")
	private HonourEnjoyProductMapper productMapper;
	
	@Autowired
	public HonorProductManagerImpl(HonourEnjoyProductMapper productMapper) {
		this.mapper = productMapper;
		this.productMapper = productMapper;
	}

	@Override
	public DataGrid dataGrid(Long hid, PageHelper ph) {
		DataGrid dg = new DataGrid();
		HonourEnjoyProductExample example = new HonourEnjoyProductExample();
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
		List<HonourEnjoyProduct> imgs = productMapper.selectByExample(example);
		dg.setRows(imgs);
		
		return dg;
	}

}
