package com.bola.nwcl.api.model.keeper;

import java.util.Date;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class FridendsReportModel {
	private Long id;
	private String title;
	private String content;
	private String nickname;
	private String headPortrait;
	private int type;
	private Date rowAddTime;
	private Date reportTime;
	
	private List<FriendsReportImgModel> imgs;
	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}
	@ApiModelProperty(value = "类型,1:爱分享,2:邻里帮")
	public Integer getType() {
		return type;
	}
	@ApiModelProperty(value = "发表时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "举报时间")
	public Date getReportTime() {
		return reportTime;
	}
	
	@ApiModelProperty(value = "内容")
	public String getContent() {
		return content;
	}
	@ApiModelProperty(value = "昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	
	@ApiModelProperty(value = "图片")
	public List<FriendsReportImgModel> getImgs() {
		return imgs;
	}
	public void setImgs(List<FriendsReportImgModel> imgs) {
		this.imgs = imgs;
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
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
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
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	
}
