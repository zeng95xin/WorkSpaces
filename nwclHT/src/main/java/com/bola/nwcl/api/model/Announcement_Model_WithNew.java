package com.bola.nwcl.api.model;

import com.bola.nwcl.dal.mybatis.model.Announcement;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class Announcement_Model_WithNew extends Announcement{
	private int isNew;

	@ApiModelProperty(value="是否最新,0：不是,1：是")
	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	
}
