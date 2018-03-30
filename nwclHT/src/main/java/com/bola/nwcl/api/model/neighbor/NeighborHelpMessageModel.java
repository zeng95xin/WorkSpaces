package com.bola.nwcl.api.model.neighbor;

import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessage;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class NeighborHelpMessageModel extends NeighborHelpMessage{
	private String nickname;
	private String headPortrait;
	private String replyName;
	private String replyContent;
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
