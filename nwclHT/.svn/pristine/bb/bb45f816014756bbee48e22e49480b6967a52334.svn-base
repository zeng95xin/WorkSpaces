package com.bola.nwcl.api.model.security;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class SercurityArticleReleaseModel {
	private long id;
	private String serial;
	private String thing;
	private String name;
	private String nickname;
	private String roomStr;
	private String phone;
	private String peopleCount;
	private Date validTime;
	private Date releaseTime;
	private Date rowAddTime;
	
	private int status;
	
	private List<ArticleReleaseImg> imgs;
	
	@ApiModelProperty(value = "id")
	public long getId() {
		return id;
	}
	@ApiModelProperty(value = "放行条图片")
	public List<ArticleReleaseImg> getImgs() {
		return imgs;
	}
	
	@ApiModelProperty(value = "业主姓名")
	public String getName() {
		return name;
	}
	@ApiModelProperty(value = "业主昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "放行人数")
	public String getPeopleCount() {
		return peopleCount;
	}
	@ApiModelProperty(value = "电话")
	public String getPhone() {
		return phone;
	}
	@ApiModelProperty(value = "放行时间")
	public Date getReleaseTime() {
		return releaseTime;
	}
	@ApiModelProperty(value = "房号")
	public String getRoomStr() {
		return roomStr;
	}
	@ApiModelProperty(value = "生成时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "编号")
	public String getSerial() {
		return serial;
	}
	@ApiModelProperty(value = "状态1:已放行,2：已过期,3：可以使用")
	public int getStatus() {
		return status;
	}
	@ApiModelProperty(value = "放行物件描述")
	public String getThing() {
		return thing;
	}
	@ApiModelProperty(value = "有效时间，默认为生成时间之后3小时内")
	public Date getValidTime() {
		return validTime;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setImgs(List<ArticleReleaseImg> imgs) {
		this.imgs = imgs;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPeopleCount(String peopleCount) {
		this.peopleCount = peopleCount;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public void setRoomStr(String roomStr) {
		this.roomStr = roomStr;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setThing(String thing) {
		this.thing = thing;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
}
