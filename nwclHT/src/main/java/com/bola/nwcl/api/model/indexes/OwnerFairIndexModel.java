package com.bola.nwcl.api.model.indexes;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class OwnerFairIndexModel {
	private Long id;
	private String title;
	private Integer type;
	private String imgPath;
	private Date rowAddTime;
	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}
	@ApiModelProperty(value = "分类,1:特色推荐,2:闲置物品")
	public Integer getType() {
		return type;
	}
	@ApiModelProperty(value = "图片")
	public String getImgPath() {
		return imgPath;
	}
	@ApiModelProperty(value = "发布时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	
}
