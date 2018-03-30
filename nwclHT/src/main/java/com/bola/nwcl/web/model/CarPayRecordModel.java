package com.bola.nwcl.web.model;

import com.bola.nwcl.dal.mybatis.model.CarPayRecord;

public class CarPayRecordModel extends CarPayRecord {

	private static final long serialVersionUID = 1L;
	// 自定义字段（人员名称）
	private String uaerName;

	public String getUaerName() {
		return uaerName;
	}

	public void setUaerName(String uaerName) {
		this.uaerName = uaerName;
	}

}
