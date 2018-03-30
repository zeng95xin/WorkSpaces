package com.bola.nwcl.api.model.indexes;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class FridendsIndexModel {
	private Long id;
	private String title;
	private String content;
	private Integer type;
	private String headPortrait;
	private Integer readCount;
	private Integer ratingCount;
	private Date rowAddTime;
	private List<LikeShareImg> imgs;
	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}
	@ApiModelProperty(value = "内容")
	public String getContent() {
		return content;
	}
	@ApiModelProperty(value = "类型,1：爱分享,2：邻里帮")
	public Integer getType() {
		return type;
	}
	@ApiModelProperty(value = "阅读数量")
	public Integer getReadCount() {
		return readCount;
	}
	@ApiModelProperty(value = "评论数量")
	public Integer getRatingCount() {
		return ratingCount;
	}
	@ApiModelProperty(value = "图片数组")
	public List<LikeShareImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}
	public void setImgs(List<LikeShareImg> imgs) {
		this.imgs = imgs;
	}
	
}
