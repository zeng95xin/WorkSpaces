package com.bola.nwcl.web.model;

import com.bola.nwcl.dal.mybatis.model.ServiceClockAttendance;

public class ServiceClockAttendanceModel extends ServiceClockAttendance{
	
	private static final long serialVersionUID = 1L;
	
	private String building;
	private String community;
	
	 //自定义字段（部门名称）
    private String deptName;
    //自定义字段（人员名称）
    private String uaerName;
    
    public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}

	public String getUaerName() {
		return uaerName;
	}

	public void setUaerName(String uaerName) {
		this.uaerName = uaerName;
	}
	
}
