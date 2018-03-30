package com.bola.nwcl.api.model.likeshare;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.LikeShare;
import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LikeShareSimpleModel extends LikeShare{
	
	private List<LikeShareImg> imgs;
	private List<LikeShareRatingSimpleModel> ratings;
	private int ratingCount;
	private int zanCount;
	private int isZan;
	private int isBelongToCurrent;
	private String name;
	private String nickname;
	private String headPortrait;
	
	private boolean isCurrent;
	private BizUser buser = new BizUser();
	@ApiModelProperty(value = "发表人信息,过时,用（name,nickname,headPortrait）替代")
	public BizUser getBuser() {
		return buser;
	}
	@ApiModelProperty(value = "发表人头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "分享图片")
	public List<LikeShareImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "是否当前用户发表,0:不是，1：是")
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
	@ApiModelProperty(value = "评论数量")
	public int getRatingCount() {
		return ratingCount;
	}
	
	@ApiModelProperty(value = "评论")
	public List<LikeShareRatingSimpleModel> getRatings() {
		return ratings;
	}
	@ApiModelProperty(value = "点赞数量")
	public int getZanCount() {
		return zanCount;
	}
	@ApiModelProperty(value = "是否当前用户发表,过时,用isBelongToCurrent替代")
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setBuser(BizUser buser) {
		this.buser = buser;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
		buser.setHeadPortrait(headPortrait);
	}
	public void setImgs(List<LikeShareImg> imgs) {
		this.imgs = imgs;
	}
	public void setIsBelongToCurrent(int isBelongToCurrent) {
		this.isBelongToCurrent = isBelongToCurrent;
		if(isBelongToCurrent == 1){
			isCurrent =true;
		}
	}
	public void setIsZan(int isZan) {
		this.isZan = isZan;
	}
	public void setName(String name) {
		this.name = name;
		buser.setName(name);
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
		buser.setNickname(nickname);
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public void setRatings(List<LikeShareRatingSimpleModel> ratings) {
		this.ratings = ratings;
	}
	public void setZanCount(int zanCount) {
		this.zanCount = zanCount;
	}
	
}
