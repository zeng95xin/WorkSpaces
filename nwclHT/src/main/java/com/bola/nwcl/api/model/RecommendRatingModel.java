package com.bola.nwcl.api.model;

import com.bola.nwcl.dal.mybatis.model.RecommendRating;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RecommendRatingModel extends RecommendRating{
	private String nickname;
	private String headPortrait;
	private String replyName;
	private String replyContent;
	private int isBelongToCurrent;
	@ApiModelProperty(value = "被回复人名称")
	public String getReplyName() {
		return replyName;
	}
	@ApiModelProperty(value = "被回复人发表内容")
	public String getReplyContent() {
		return replyContent;
	}
	@ApiModelProperty(value = "发表人昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "发表人头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "是否属于当前用户,0:不属于,1:属于")
	public int getIsBelongToCurrent() {
		return isBelongToCurrent;
	}
	public void setIsBelongToCurrent(int isBelongToCurrent) {
		this.isBelongToCurrent = isBelongToCurrent;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
}
