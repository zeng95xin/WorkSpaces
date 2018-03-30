package com.bola.nwcl.api.model;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.HangPictureCompleteImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureRating;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class HangPictureModel extends HangPicture{
	
	private List<HangPictureImg> imgs;
	private List<HangPictureCompleteImg> completeImgs;
	private HangPictureRating rating;
	private String repairManHeadPortrait;
	private String repairManName;
	private String repairManPhone;
	
	
	@ApiModelProperty(value = "挂画单完成后拍照图片")
	public List<HangPictureCompleteImg> getCompleteImgs() {
		return completeImgs;
	}
	@ApiModelProperty(value = "挂画单图片")
	public List<HangPictureImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "挂画单评论")
	public HangPictureRating getRating() {
		return rating;
	}
	@ApiModelProperty(value = "挂画人员头像")
	public String getRepairManHeadPortrait() {
		return repairManHeadPortrait;
	}
	@ApiModelProperty(value = "挂画人员姓名")
	public String getRepairManName() {
		return repairManName;
	}
	@ApiModelProperty(value = "挂画人员电话")
	public String getRepairManPhone() {
		return repairManPhone;
	}
	public void setCompleteImgs(List<HangPictureCompleteImg> completeImgs) {
		this.completeImgs = completeImgs;
	}
	public void setImgs(List<HangPictureImg> imgs) {
		this.imgs = imgs;
	}
	
	public void setRating(HangPictureRating rating) {
		this.rating = rating;
	}
	public void setRepairManHeadPortrait(String repairManHeadPortrait) {
		this.repairManHeadPortrait = repairManHeadPortrait;
	}
	public void setRepairManName(String repairManName) {
		this.repairManName = repairManName;
	}
	public void setRepairManPhone(String repairManPhone) {
		this.repairManPhone = repairManPhone;
	}
	
}
