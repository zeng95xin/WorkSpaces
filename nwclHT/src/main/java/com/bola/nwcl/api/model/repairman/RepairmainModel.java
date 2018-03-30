package com.bola.nwcl.api.model.repairman;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RepairmainModel {
	private int id;
	private String headPortrait;
	private String nickname;
	private String detail;
	private String phone;
	private int status;
	private int complete;
	private int waitrepair;
	private int ratingCount;
	private int repairing;
	
	@ApiModelProperty(value = "已完成维修单数量")
	public int getComplete() {
		return complete;
	}
	@ApiModelProperty(value = "维修人员详情介绍")
	public String getDetail() {
		return detail;
	}
	@ApiModelProperty(value = "维修人员头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "维修人员id")
	public int getId() {
		return id;
	}
	@ApiModelProperty(value = "维修人员昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "维修人员电话")
	public String getPhone() {
		return phone;
	}
	@ApiModelProperty(value = "评价数量")
	public int getRatingCount() {
		return ratingCount;
	}
	@ApiModelProperty(value = "维修中的数量")
	public int getRepairing() {
		return repairing;
	}
	@ApiModelProperty(value = "状态，0:空闲，1:忙碌中,2:非常忙碌")
	public int getStatus() {
		return status;
	}
	@ApiModelProperty(value = "待维修单数量")
	public int getWaitrepair() {
		return waitrepair;
	}
	public void setComplete(int complete) {
		this.complete = complete;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public void setRepairing(int repairing) {
		this.repairing = repairing;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setWaitrepair(int waitrepair) {
		this.waitrepair = waitrepair;
	}
}
