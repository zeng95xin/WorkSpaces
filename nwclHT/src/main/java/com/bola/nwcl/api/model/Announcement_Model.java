package com.bola.nwcl.api.model;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.dal.mybatis.model.AnnouncementImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class Announcement_Model {
	private Long id;
	private String imgPath;
	private String title;
	private String detail;
	private Date rowAddTime;
	private int status;
	private int sendCount;
	private List<AnnouncementRatingModel> ratings;
	private List<AnnouncementImg> imgs;
	@ApiModelProperty(value = "详情")
	public String getDetail() {
		return detail;
	}
	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "图片路径")
	public String getImgPath() {
		return imgPath;
	}
	@ApiModelProperty(value = "所有公告图片")
	public List<AnnouncementImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "评论,默认加载最近10条")
	public List<AnnouncementRatingModel> getRatings() {
		return ratings;
	}
	@ApiModelProperty(value = "发布时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "送达人数")
	public int getSendCount() {
		return sendCount;
	}
	@ApiModelProperty(value = "状态,0:待审核,1:已发送")
	public int getStatus() {
		return status;
	}
	
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
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
	public void setImgs(List<AnnouncementImg> imgs) {
		this.imgs = imgs;
	}
	public void setRatings(List<AnnouncementRatingModel> ratings) {
		this.ratings = ratings;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
