package com.bola.nwcl.api.model.likeshare;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.LikeShare;
import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LikeShareModel extends LikeShare{
	private BizUser buser;
	private List<LikeShareImg> imgs;
	private Integer ratingCount;
	private List<LikeShareRatingModel> ratings;
	private boolean isCurrent;
	
	@ApiModelProperty(value = "发表人信息")
	public BizUser getBuser() {
		return buser;
	}
	@ApiModelProperty(value = "分享图片")
	public List<LikeShareImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "评论数量")
	public Integer getRatingCount() {
		return ratingCount;
	}
	@ApiModelProperty(value = "评论")
	public List<LikeShareRatingModel> getRatings() {
		return ratings;
	}
	@ApiModelProperty(value = "是否当前用户发表")
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public void setBuser(BizUser buser) {
		this.buser = buser;
	}
	public void setImgs(List<LikeShareImg> imgs) {
		this.imgs = imgs;
	}
	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}
	public void setRatings(List<LikeShareRatingModel> ratings) {
		this.ratings = ratings;
	}
	
}
