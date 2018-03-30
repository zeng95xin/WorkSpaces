package com.bola.nwcl.api.model;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.UserMessage;
import com.bola.nwcl.dal.mybatis.model.UserOpinionImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserOpinionModel extends UserMessage{
	private String username;
	private String userHeadPortrait;
	private String keepername;
	private String keeperHeadPortrait;
	private List<UserOpinionImg> imgs;
	@ApiModelProperty(value = "用户昵称")
	public String getUsername() {
		return username;
	}
	@ApiModelProperty(value = "用户头像")
	public String getUserHeadPortrait() {
		return userHeadPortrait;
	}
	@ApiModelProperty(value = "后台昵称")
	public String getKeepername() {
		return keepername;
	}
	@ApiModelProperty(value = "后台头像")
	public String getKeeperHeadPortrait() {
		return keeperHeadPortrait;
	}
	@ApiModelProperty(value = "意见图片文件")
	public List<UserOpinionImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<UserOpinionImg> imgs) {
		this.imgs = imgs;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserHeadPortrait(String userHeadPortrait) {
		this.userHeadPortrait = userHeadPortrait;
	}
	public void setKeepername(String keepername) {
		this.keepername = keepername;
	}
	public void setKeeperHeadPortrait(String keeperHeadPortrait) {
		this.keeperHeadPortrait = keeperHeadPortrait;
	}
}
