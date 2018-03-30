package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Coupon;
import com.bola.nwcl.dal.mybatis.model.CouponExample;

public interface CouponManager extends Manager<Coupon, CouponExample, Long>{
	DataGrid dataGrid(HttpServletRequest request, Coupon cn, PageHelper ph);
	void insertAndGetId(Coupon cn);
}
