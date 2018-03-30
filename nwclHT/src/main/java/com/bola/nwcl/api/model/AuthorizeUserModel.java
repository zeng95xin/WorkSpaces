package com.bola.nwcl.api.model;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AuthorizeUserModel {
	
	private Long id;
	private Long roomId;
	private String type;
	private String nickname;
	private String headPortrait;
	private String phone;
	private String address;
	private Date addTime;
	private int status;
	
	@ApiModelProperty(value = "是否被业主注销,0：不是,1:是")
	public int getStatus() {
		return status;
	}
	@ApiModelProperty(value = "用户id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "房间id")
	public Long getRoomId() {
		return roomId;
	}
	@ApiModelProperty(value = "与业主关系")
	public String getType() {
		return type;
	}
	@ApiModelProperty(value = "使用人")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "手机")
	public String getPhone() {
		return phone;
	}
	@ApiModelProperty(value = "管理单元")
	public String getAddress() {
		return address;
	}
	@ApiModelProperty(value = "注册时间")
	public Date getAddTime() {
		return addTime;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
