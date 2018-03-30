package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.BuserCoupon;
import com.bola.nwcl.dal.mybatis.model.BuserCouponExample;

public interface BuserCouponManager extends Manager<BuserCoupon, BuserCouponExample, Long>{
	DataGrid dataGrid(Long cid, PageHelper ph, HttpServletRequest request);
}
