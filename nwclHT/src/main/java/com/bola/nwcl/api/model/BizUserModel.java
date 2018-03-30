package com.bola.nwcl.api.model;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BizUserModel {

	private RoomModel currentRoom;
	private String headPortrait;
	private Long id;
	private String name;
	private String nickname;
	private String phone;
	private Date rowAddTime;
	
	private String sex;
	private Short status;
	
	private String type;
	
	private int notifyNotReadCount;
	private int point;
	
	@ApiModelProperty(value = "当前绑定房产")
	public RoomModel getCurrentRoom() {
		return currentRoom;
	}
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "姓名")
	public String getName() {
		return name;
	}
	@ApiModelProperty(value = "昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "未读消息通知数量")
	public int getNotifyNotReadCount() {
		return notifyNotReadCount;
	}
	@ApiModelProperty(value = "手机")
	public String getPhone() {
		return phone;
	}
	@ApiModelProperty(value = "积分")
	public int getPoint() {
		return point;
	}
	@ApiModelProperty(value = "注册时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "性别")
	public String getSex() {
		return sex;
	}
	@ApiModelProperty(value = "状态,1启用,0禁用")
	public Short getStatus() {
		return status;
	}
	@ApiModelProperty(value = "用户在当前房间的身份")
	public String getType() {
		return type;
	}
	public void setCurrentRoom(RoomModel currentRoom) {
		this.currentRoom = currentRoom;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setNotifyNotReadCount(int notifyNotReadCount) {
		this.notifyNotReadCount = notifyNotReadCount;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public void setType(String type) {
		this.type = type;
	}
}
