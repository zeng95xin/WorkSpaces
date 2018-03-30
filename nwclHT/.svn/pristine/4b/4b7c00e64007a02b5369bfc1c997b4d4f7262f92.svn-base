package com.bola.nwcl.api.model.keeper;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class KeeperUserMessageIndexModel {
	private int count;
	private int status;
	private Long buserId;
	private Date rowAddTime;
	private String content;
	private String nickname;
	private String headPortrait;
	private int messageType;
	@ApiModelProperty(value = "未读消息数量")
	public int getCount() {
		return count;
	}
	@ApiModelProperty(value = "该用户消息是否全部已经读完,0:不是,1：是")
	public int getStatus() {
		return status;
	}
	@ApiModelProperty(value = "用户id")
	public Long getBuserId() {
		return buserId;
	}
	@ApiModelProperty(value = "最后一条消息发送时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "最后一条消息内容")
	public String getContent() {
		return content;
	}
	@ApiModelProperty(value = "用户昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "用户头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "消息类型,0:文字,1:图片,2:声音,如果是文件类型,内容里面就是相应的路径")
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	
}
