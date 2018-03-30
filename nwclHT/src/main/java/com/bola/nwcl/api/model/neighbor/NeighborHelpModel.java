package com.bola.nwcl.api.model.neighbor;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.NeighborHelp;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class NeighborHelpModel extends NeighborHelp{
	
	private List<NeighborHelpImg> imgs;
	private List<NeighborHelpMessageModel> msgs;
	
	private int helpCount;
	private int zanCount;
	private int isZan;
	private int isBelongToCurrent;
	private String name;
	private String nickname;
	private String headPortrait;
	
	private BizUser buser = new BizUser();
	private int isCurrent;
	
	@ApiModelProperty(value = "发表人头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "是否当前用户发表，0:不是，1：是")
	public int getIsBelongToCurrent() {
		return isBelongToCurrent;
	}
	@ApiModelProperty(value = "当前用户是否赞过,0:没有，1：赞过")
	public int getIsZan() {
		return isZan;
	}
	@ApiModelProperty(value = "发表人姓名")
	public String getName() {
		return name;
	}
	@ApiModelProperty(value = "发表人昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "点赞数量")
	public int getZanCount() {
		return zanCount;
	}
	
	@ApiModelProperty(value = "发布人信息,过时,用（name,nickname,headPortrait）替代")
	public BizUser getBuser() {
		return buser;
	}
	@ApiModelProperty(value = "帮助次数")
	public int getHelpCount() {
		return helpCount;
	}
	@ApiModelProperty(value = "邻里帮图片")
	public List<NeighborHelpImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "是否是当前用户发表,0：不是,1：是，过时，用isBelongToCurrent代替")
	public int getIsCurrent() {
		return isCurrent;
	}
	@ApiModelProperty(value = "邻里帮消息")
	public List<NeighborHelpMessageModel> getMsgs() {
		return msgs;
	}
	public void setBuser(BizUser buser) {
		this.buser = buser;
	}
	public void setHelpCount(int helpCount) {
		this.helpCount = helpCount;
	}
	public void setImgs(List<NeighborHelpImg> imgs) {
		this.imgs = imgs;
	}
	
	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}
	public void setMsgs(List<NeighborHelpMessageModel> msgs) {
		this.msgs = msgs;
	}
	public void setZanCount(int zanCount) {
		this.zanCount = zanCount;
	}
	public void setIsZan(int isZan) {
		this.isZan = isZan;
	}
	public void setIsBelongToCurrent(int isBelongToCurrent) {
		this.isBelongToCurrent = isBelongToCurrent;
		isCurrent = isBelongToCurrent;
	}
	public void setName(String name) {
		this.name = name;
		buser.setName(name);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
		buser.setNickname(nickname);
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
		buser.setHeadPortrait(headPortrait);
	}
	
}
