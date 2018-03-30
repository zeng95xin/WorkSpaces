package com.bola.nwcl.api.model;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.PropertyPayMent;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class PropertyPayMentModel {

	private String dateStr;
	
	private List<PropertyPayMent> propertys;
	
	private int status;

	@ApiModelProperty(value = "月份")
	public String getDateStr() {
		return dateStr;
	}

	@ApiModelProperty(value = "详情")
	public List<PropertyPayMent> getPropertys() {
		return propertys;
	}

	@ApiModelProperty(value = "是否缴清,0：未结清,1：已缴费")
	public int getStatus() {
		return status;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public void setPropertys(List<PropertyPayMent> propertys) {
		this.propertys = propertys;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
