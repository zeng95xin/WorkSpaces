package com.bola.nwcl.api.model;

import java.util.Date;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ArticleReleaseModel {
	private Long id;
	private String serial;
	private String thing;
	private String peopelCount;
	private Date validTime;
	private Date rowAddTime;
	private Integer status;
	private List<ArticleReleaseImgModel> imgs;
	
	@ApiModelProperty(value = "状态1:已放行,2：已过期,3：可以使用")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@ApiModelProperty(value = "放行条id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "放行条编号")
	public String getSerial() {
		return serial;
	}
	@ApiModelProperty(value = "放行条物件描述")
	public String getThing() {
		return thing;
	}
	@ApiModelProperty(value = "放行人数")
	public String getPeopelCount() {
		return peopelCount;
	}
	@ApiModelProperty(value = "放行条有效截止时间")
	public Date getValidTime() {
		return validTime;
	}
	@ApiModelProperty(value = "放行条生成时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "放行条图片")
	public List<ArticleReleaseImgModel> getImgs() {
		return imgs;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public void setThing(String thing) {
		this.thing = thing;
	}
	public void setPeopelCount(String peopelCount) {
		this.peopelCount = peopelCount;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setImgs(List<ArticleReleaseImgModel> imgs) {
		this.imgs = imgs;
	}
	
}
