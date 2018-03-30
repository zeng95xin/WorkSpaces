package com.bola.nwcl.api.controller.pay;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CreateOrder {

	private String orderNo;//订单id
	private String orderType;//订单类型
	private String userId;//用户id
	private String payStatus;//支付状态(ALREADY_PAY--已支付，NOT_PAY---未支付)
	private Long number;//充值积分数量
	private Date rawAddTime;//订单创建时间
	
	@ApiModelProperty(value = "数量")
	public Long getNumber() {
		return number;
	}
	@ApiModelProperty(value = "订单号")
	public String getOrderNo() {
		return orderNo;
	}
	
	@ApiModelProperty(value = "订单类型,BXD:保修单,WY:物业缴费")
	public String getOrderType() {
		return orderType;
	}
	@ApiModelProperty(value = "支付状态(ALREADY_PAY--已支付，NOT_PAY---未支付)")
	public String getPayStatus() {
		return payStatus;
	}
	@ApiModelProperty(value = "订单创建时间")
	public Date getRawAddTime() {
		return rawAddTime;
	}
	@ApiModelProperty(value = "用户id")
	public String getUserId() {
		return userId;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public void setRawAddTime(Date rawAddTime) {
		this.rawAddTime = rawAddTime;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
