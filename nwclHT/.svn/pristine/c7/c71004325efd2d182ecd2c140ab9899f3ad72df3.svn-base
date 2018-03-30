package com.bola.nwcl.api.model.market;

import com.bola.nwcl.dal.mybatis.model.OwnersMarketRating;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class OwnersMarketRatingModel extends OwnersMarketRating{
	
	private String nickname;
	private String headPortrait;
	private String replyName;
	private String replyContent;
	private int isBelongToCurrent;
	
	@ApiModelProperty(value = "是否当前用户发表,0：不是,1：是")
	public int getIsBelongToCurrent() {
		return isBelongToCurrent;
	}
	public void setIsBelongToCurrent(int isBelongToCurrent) {
		this.isBelongToCurrent = isBelongToCurrent;
	}
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
