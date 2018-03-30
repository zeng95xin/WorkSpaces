package com.bola.nwcl.api.model.keeper;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class FriendsReportImgModel {
	private long id;
	private String path;
	private String pathThumbnail;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@ApiModelProperty(value = "图片")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@ApiModelProperty(value = "缩略图片")
	public String getPathThumbnail() {
		return pathThumbnail;
	}
	public void setPathThumbnail(String pathThumbnail) {
		this.pathThumbnail = pathThumbnail;
	}
	
}
