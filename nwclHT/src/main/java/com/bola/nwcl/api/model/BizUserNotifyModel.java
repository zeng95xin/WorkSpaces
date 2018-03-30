package com.bola.nwcl.api.model;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BizUserNotifyModel {
	private Long id;
	private String imgPath;
	private String title;
	private String detail;
	private int type;
	private Date rowAddTime;
	private int status;
	@ApiModelProperty(value = "消息通知内容")
	public String getDetail() {
		return detail;
	}
	@ApiModelProperty(value = "消息通知id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "消息通知图片")
	public String getImgPath() {
		return imgPath;
	}
	@ApiModelProperty(value = "消息通知发送时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "是否已读,0未读1已读")
	public int getStatus() {
		return status;
	}
	@ApiModelProperty(value = "消息通知标题")
	public String getTitle() {
		return title;
	}
	@ApiModelProperty(value = "消息通知类型,1:管家消息")
	public int getType() {
		return type;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
