package com.bola.nwcl.api.model.coupon;

import com.bola.nwcl.dal.mybatis.model.Coupon;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class CouponModel extends Coupon{
	
	private int isGet;
	
	@ApiModelProperty(value = "当前用户是否领取过,0:未领取,1已经领取过")
	public int getIsGet() {
		return isGet;
	}

	public void setIsGet(int isGet) {
		this.isGet = isGet;
	}

}
