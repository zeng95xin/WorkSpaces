package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.coupon.CouponModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Coupon;
import com.bola.nwcl.dal.mybatis.model.CouponExample;

public interface CouponMapper extends Mapper<Coupon, CouponExample, Long> {
	List<CouponModel> getUserCoupon(Map<String,Object> condition);
	void insertAndGetId(Coupon cn);
}