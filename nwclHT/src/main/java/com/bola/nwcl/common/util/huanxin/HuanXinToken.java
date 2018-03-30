package com.bola.nwcl.common.util.huanxin;

import java.util.Date;

public class HuanXinToken {

	private String token = "";
	private Date expireDate = new Date();

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
