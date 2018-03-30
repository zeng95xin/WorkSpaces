package com.bola.nwcl.api.model.indexes;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ServiceAddIndexModel {
	private Long id;
	private String title;
	private Integer type;
	private String img_path;
	private Date row_add_time;
	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}
	@ApiModelProperty(value = "类型,1：文娱活动,2：义工活动,3：业主大会,-1:业主讲堂")
	public Integer getType() {
		return type;
	}
	@ApiModelProperty(value = "图片")
	public String getImg_path() {
		return img_path;
	}
	@ApiModelProperty(value = "添加时间")
	public Date getRow_add_time() {
		return row_add_time;
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
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public void setRow_add_time(Date row_add_time) {
		this.row_add_time = row_add_time;
	}
}
