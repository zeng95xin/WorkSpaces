package com.bola.nwcl.api.model.market;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.OwnersMarket;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class OwnersMarketModel extends OwnersMarket{
	private String name;
	private String nickname;
	private String headPortrait;
	
	private int ratingCount;
	private int isBelongToCurrent;
	
	private int isZan;
	private int zanCount;
	
	private List<OwnersMarketImg> imgs;
	private List<OwnersMarketRatingModel> ratings;
	
	@ApiModelProperty(value = "当前用户是否赞过,0:没有，1：赞过")
	public int getIsZan() {
		return isZan;
	}
	@ApiModelProperty(value = "点赞数量")
	public int getZanCount() {
		return zanCount;
	}
	@ApiModelProperty(value = "发布人头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "图片")
	public List<OwnersMarketImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "是否是当前用户发表")
	public int getIsBelongToCurrent() {
		return isBelongToCurrent;
	}
	@ApiModelProperty(value = "发布人姓名")
	public String getName() {
		return name;
	}
	@ApiModelProperty(value = "发布人昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "评论数量")
	public int getRatingCount() {
		return ratingCount;
	}
	@ApiModelProperty(value = "评论")
	public List<OwnersMarketRatingModel> getRatings() {
		return ratings;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setImgs(List<OwnersMarketImg> imgs) {
		this.imgs = imgs;
	}
	public void setIsBelongToCurrent(int isBelongToCurrent) {
		this.isBelongToCurrent = isBelongToCurrent;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public void setRatings(List<OwnersMarketRatingModel> ratings) {
		this.ratings = ratings;
	}
	public void setIsZan(int isZan) {
		this.isZan = isZan;
	}
	public void setZanCount(int zanCount) {
		this.zanCount = zanCount;
	}
}
