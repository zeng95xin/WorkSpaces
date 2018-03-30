package com.bola.nwcl.api.model;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;


public class UserWebmasterMessageModel {
	private long id;
	private String name;
	private String nickname;
	private String headPortrait;
	private String content;
	private int messageType;
	private int sendType;
	private Integer status;
	private Date rowAddTime;
	@ApiModelProperty(value = "id")
	public long getId() {
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
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "内容")
	public String getContent() {
		return content;
	}
	@ApiModelProperty(value = "消息类型")
	public int getMessageType() {
		return messageType;
	}
	@ApiModelProperty(value = "是否已读，0：未读，1：已读")
	public Integer getStatus() {
		return status;
	}
	@ApiModelProperty(value = "发送时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "发送类型，1:当前用户发送给其他人，2:其他人发送给当前用户")
	public int getSendType() {
		return sendType;
	}
	public void setSendType(int sendType) {
		this.sendType = sendType;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
}
