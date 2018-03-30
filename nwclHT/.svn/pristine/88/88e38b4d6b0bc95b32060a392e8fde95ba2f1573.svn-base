package com.bola.nwcl.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BizUserLingLingModel {
	private long id;
	private String regcode;
	private String encryptStr;
	private int interval = 60;
	
	@ApiModelProperty(value = "key")
	public String getEncryptStr() {
		return encryptStr;
	}
	@ApiModelProperty(value = "业主id")
	public long getId() {
		return id;
	}
	@ApiModelProperty(value = "过期时间,秒")
	public int getInterval() {
		return interval;
	}
	@ApiModelProperty(value = "业主注册码,account")
	public String getRegcode() {
		return regcode;
	}
	public void setEncryptStr(String encryptStr) {
		this.encryptStr = encryptStr;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}
	
}
