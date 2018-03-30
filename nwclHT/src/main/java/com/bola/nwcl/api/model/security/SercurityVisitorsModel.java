package com.bola.nwcl.api.model.security;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.api.model.VisitorsStatisticsModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class SercurityVisitorsModel {
	
	private long id;
	private String serial;
	private String nickname;
	private String name;
	private String roomStr;
	private String identity;
	private String phone;
	private long keeperid;
	private String keepername;
	private String keeperno;
	
	private int visitorsCount;
	
	private Date expectArriveTime;
	private Date expectLeaveTime;
	private Date effectiveArriveTime;
	private Date effectiveLeaveTime;
	
	private List<VisitorsStatisticsModel> statistics;
	
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
	public long getId() {
		return id;
	}

	@ApiModelProperty(value = "邀请人所在单元的身份")
	public String getIdentity() {
		return identity;
	}

	@ApiModelProperty(value = "邀请人所在楼栋管家id")
	public long getKeeperid() {
		return keeperid;
	}

	@ApiModelProperty(value = "邀请人所在楼栋管家名")
	public String getKeepername() {
		return keepername;
	}

	@ApiModelProperty(value = "邀请人所在楼栋管家工号")
	public String getKeeperno() {
		return keeperno;
	}

	@ApiModelProperty(value = "邀请人姓名")
	public String getName() {
		return name;
	}

	@ApiModelProperty(value = "邀请人昵称")
	public String getNickname() {
		return nickname;
	}

	@ApiModelProperty(value = "邀请人电话")
	public String getPhone() {
		return phone;
	}

	@ApiModelProperty(value = "邀请人所在单元")
	public String getRoomStr() {
		return roomStr;
	}

	@ApiModelProperty(value = "编号")
	public String getSerial() {
		return serial;
	}

	@ApiModelProperty(value = "到访人统计")
	public List<VisitorsStatisticsModel> getStatistics() {
		return statistics;
	}

	@ApiModelProperty(value = "到访人通过记录数")
	public int getVisitorsCount() {
		return visitorsCount;
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

	public void setId(long id) {
		this.id = id;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public void setKeeperid(long keeperid) {
		this.keeperid = keeperid;
	}

	public void setKeepername(String keepername) {
		this.keepername = keepername;
	}

	public void setKeeperno(String keeperno) {
		this.keeperno = keeperno;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRoomStr(String roomStr) {
		this.roomStr = roomStr;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setStatistics(List<VisitorsStatisticsModel> statistics) {
		this.statistics = statistics;
	}

	public void setVisitorsCount(int visitorsCount) {
		this.visitorsCount = visitorsCount;
	}

}
