package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProduct;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProductExample;

public interface HonorProductManager extends Manager<HonourEnjoyProduct, HonourEnjoyProductExample, Long>{
	DataGrid dataGrid(Long hid, PageHelper pageHelper);
}
