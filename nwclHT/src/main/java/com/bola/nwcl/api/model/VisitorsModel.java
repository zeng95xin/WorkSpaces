package com.bola.nwcl.api.model;

import java.util.Date;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class VisitorsModel {
	private Long id;
	private Long roomId;
	private String serial;
	private String namePhone;
	private Date expectArriveTime;
	private Date expectLeaveTime;
	private Date effectiveArriveTime;
	private Date effectiveLeaveTime;
	private Date rowAddTime;
	private int visitorsCount;
	private List<VisitorsStatisticsModel> details;
	
	@ApiModelProperty(value = "到访详情")
	public List<VisitorsStatisticsModel> getDetails() {
		return details;
	}
	@ApiModelProperty(value = "有效到达时间")
	public Date getEffectiveArriveTime() {
		return effectiveArriveTime;
	}
	@ApiModelProperty(value = "有效离开时间")
	public Date getEffectiveLeaveTime() {
		return effectiveLeaveTime;
	}
	@ApiModelProperty(value = "预计到达时间")
	public Date getExpectArriveTime() {
		return expectArriveTime;
	}
	@ApiModelProperty(value = "预计离开时间")
	public Date getExpectLeaveTime() {
		return expectLeaveTime;
	}
	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "来访人手机:姓名多个直接用英文逗号,隔开")
	public String getNamePhone() {
		return namePhone;
	}
	@ApiModelProperty(value = "到访房间id")
	public Long getRoomId() {
		return roomId;
	}
	@ApiModelProperty(value = "住户生成到访信息时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "编号")
	public String getSerial() {
		return serial;
	}
	@ApiModelProperty(value = "到访记录数")
	public int getVisitorsCount() {
		return visitorsCount;
	}
	public void setDetails(List<VisitorsStatisticsModel> details) {
		this.details = details;
	}
	public void setEffectiveArriveTime(Date effectiveArriveTime) {
		this.effectiveArriveTime = effectiveArriveTime;
	}
	public void setEffectiveLeaveTime(Date effectiveLeaveTime) {
		this.effectiveLeaveTime = effectiveLeaveTime;
	}
	public void setExpectArriveTime(Date expectArriveTime) {
		this.expectArriveTime = expectArriveTime;
	}
	public void setExpectLeaveTime(Date expectLeaveTime) {
		this.expectLeaveTime = expectLeaveTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNamePhone(String namePhone) {
		this.namePhone = namePhone;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public void setVisitorsCount(int visitorsCount) {
		this.visitorsCount = visitorsCount;
	}
	
}
