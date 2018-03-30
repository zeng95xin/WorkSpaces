package com.bola.nwcl.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class MaintenanceImgModel {
	
	private Long id;
	private String imgPath;
	
	@ApiModelProperty(value = "报修单图片id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ApiModelProperty(value = "报修单图片路径")
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
