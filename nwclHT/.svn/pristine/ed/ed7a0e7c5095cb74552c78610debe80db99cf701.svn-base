package com.bola.nwcl.api.model.activity;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.Activity;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ActivityModel extends Activity{
	
	private List<ActivityRatingModel> ratings;
	
	@ApiModelProperty(value = "评论")
	public List<ActivityRatingModel> getRatings() {
		return ratings;
	}

	public void setRatings(List<ActivityRatingModel> ratings) {
		this.ratings = ratings;
	}
	
}
