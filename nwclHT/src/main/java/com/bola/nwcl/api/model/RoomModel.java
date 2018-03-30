package com.bola.nwcl.api.model;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String community_name;
	private String building_name;
	private String floor;
	private String unit;
	private String roomNo;
	
	private String authorizeUserName;
	private String authorizeUserNickname;
	private String authorizeUserPhone;
	
	private int isCurrent;
	
	@ApiModelProperty(value = "业主姓名")
	public String getAuthorizeUserName() {
		return authorizeUserName;
	}
	@ApiModelProperty(value = "业主昵称")
	public String getAuthorizeUserNickname() {
		return authorizeUserNickname;
	}
	@ApiModelProperty(value = "业主电话")
	public String getAuthorizeUserPhone() {
		return authorizeUserPhone;
	}
	@ApiModelProperty(value = "楼栋名称")
	public String getBuilding_name() {
		return building_name;
	}
	@ApiModelProperty(value = "小区名称")
	public String getCommunity_name() {
		return community_name;
	}
	@ApiModelProperty(value = "楼层")
	public String getFloor() {
		return floor;
	}
	@ApiModelProperty(value = "房间id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "是否是用户当前房间,0:不是,1:是")
	public int getIsCurrent() {
		return isCurrent;
	}
	@ApiModelProperty(value = "房间号")
	public String getRoomNo() {
		return roomNo;
	}
	@ApiModelProperty(value = "单元")
	public String getUnit() {
		return unit;
	}
	public void setAuthorizeUserName(String authorizeUserName) {
		this.authorizeUserName = authorizeUserName;
	}
	public void setAuthorizeUserNickname(String authorizeUserNickname) {
		this.authorizeUserNickname = authorizeUserNickname;
	}
	public void setAuthorizeUserPhone(String authorizeUserPhone) {
		this.authorizeUserPhone = authorizeUserPhone;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
