package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.api.model.BizUserCouponModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.BuserCoupon;
import com.bola.nwcl.dal.mybatis.model.BuserCouponExample;

public interface BuserCouponMapper extends Mapper<BuserCoupon, BuserCouponExample, Long> {
	void insertAndGetId(BuserCoupon buserCoupon);
	int getCouponUnusedCount(long id);
	BizUserCouponModel getUserCouponDetail(long id);
	
}