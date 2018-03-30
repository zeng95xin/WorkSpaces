package com.bola.nwcl.api.model.lecture;

import com.bola.nwcl.dal.mybatis.model.LectureHallRating;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LectureHallRatingModel extends LectureHallRating{
	private String nickname;
	private String headPortrait;
	private String replyName;
	private String replyContent;
	private int isBelongToCurrent;
	
	@ApiModelProperty(value = "是否当前用户发表,0：不是,1：是")
	public int getIsBelongToCurrent() {
		return isBelongToCurrent;
	}
	@ApiModelProperty(value = "被回复人名称")
	public String getReplyName() {
		return replyName;
	}
	@ApiModelProperty(value = "被回复人发表内容")
	public String getReplyContent() {
		return replyContent;
	}
	@ApiModelProperty(value = "评论人昵称")
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@ApiModelProperty(value = "评论人头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public void setIsBelongToCurrent(int isBelongToCurrent) {
		this.isBelongToCurrent = isBelongToCurrent;
	}
	
}
