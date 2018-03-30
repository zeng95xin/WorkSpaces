package com.bola.nwcl.api.model;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.Idlething;
import com.bola.nwcl.dal.mybatis.model.IdlethingImg;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class IdlethingModel extends Idlething{
	private String typeTitle;
	private int ratingCount;
	private int zanCount;
	private int isBelongToCurrent;
	private String headPortrait;
	private List<IdlethingImg> imgs;
	private int isZan;
	@ApiModelProperty(value = "当前用户已经点过赞,0:没有电,1:点了")
	public int getIsZan() {
		return isZan;
	}
	public void setIsZan(int isZan) {
		this.isZan = isZan;
	}
	@ApiModelProperty(value = "是否属于当前用户,0:不属于,1:属于")
	public int getIsBelongToCurrent() {
		return isBelongToCurrent;
	}
	@ApiModelProperty(value = "发布人头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "分类名")
	public String getTypeTitle() {
		return typeTitle;
	}
	@ApiModelProperty(value = "评论数量")
	public int getRatingCount() {
		return ratingCount;
	}
	@ApiModelProperty(value = "点赞数量")
	public int getZanCount() {
		return zanCount;
	}
	@ApiModelProperty(value = "图片")
	public List<IdlethingImg> getImgs() {
		return imgs;
	}
	public void setIsBelongToCurrent(int isBelongToCurrent) {
		this.isBelongToCurrent = isBelongToCurrent;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public void setZanCount(int zanCount) {
		this.zanCount = zanCount;
	}
	public void setImgs(List<IdlethingImg> imgs) {
		this.imgs = imgs;
	}
}
