package com.bola.nwcl.api.model.lecture;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.LectureHall;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LectureHallModel extends LectureHall{
	
	private static final long serialVersionUID = 1L;

	private List<LectureHallRatingModel> ratings;
	
	private int status;
	
	private int isRegister;
	
	@ApiModelProperty(value = "是否报名,0:没有,1:报名了")
	public int getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(int isRegister) {
		this.isRegister = isRegister;
	}

	@ApiModelProperty(value = "评论")
	public List<LectureHallRatingModel> getRatings() {
		return ratings;
	}
	
	@ApiModelProperty(value = "状态,1:最新,2:往期回顾")
	public int getStatus() {
		return status;
	}

	public void setRatings(List<LectureHallRatingModel> ratings) {
		this.ratings = ratings;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
