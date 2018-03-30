package com.bola.nwcl.web.model;

import java.util.Date;

public class VisitorsWebModel {
	/**
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 放行房间id
	 * @mbggenerated
	 */
	private Long roomId;
	/**
	 * 编号
	 * @mbggenerated
	 */
	private String serial;
	/**
	 * 生成人,放行人 id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 预计到访时间
	 * @mbggenerated
	 */
	private Date expectArriveTime;
	/**
	 * 预计离开时间
	 * @mbggenerated
	 */
	private Date expectLeaveTime;
	/**
	 * 有效时间起,默认在到访时间之前3小时
	 * @mbggenerated
	 */
	private Date effectiveArriveTime;
	/**
	 * 有效时间止，默认在离去时间后3小时
	 * @mbggenerated
	 */
	private Date effectiveLeaveTime;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 选项
	 * @mbggenerated
	 */
	private String option ;
	/**
	 * 选项值
	 * @mbggenerated
	 */
	private String key ;
	/**
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;
	/**
	 * 业主姓名
	 * @mbggenerated
	 */
	private String userName;
	/**
	 * 业主电话
	 * @mbggenerated
	 */
	private String phone;
	/**
	 * 房间号
	 * @mbggenerated
	 */
	private String roomNumber;
	/**
	 * 来访人姓名
	 * @mbggenerated
	 */
	private String visitorsName;
	/**
	 * 昵称
	 * @mbggenerated
	 */
	private String nickname;
	
	/**
	 * 物业系统的房间编号
	 * @mbggenerated
	 */
	private String unitNo;
	
	/**
	 * 预计来访人
	 * @mbggenerated
	 */
	private String vName;
	
	public String getvName() {
		return vName;
	}
	public void setvName(String vName) {
		this.vName = vName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getVisitorsName() {
		return visitorsName;
	}
	public void setVisitorsName(String visitorsName) {
		this.visitorsName = visitorsName;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public Long getBuserId() {
		return buserId;
	}
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}
	public Date getExpectArriveTime() {
		return expectArriveTime;
	}
	public void setExpectArriveTime(Date expectArriveTime) {
		this.expectArriveTime = expectArriveTime;
	}
	public Date getExpectLeaveTime() {
		return expectLeaveTime;
	}
	public void setExpectLeaveTime(Date expectLeaveTime) {
		this.expectLeaveTime = expectLeaveTime;
	}
	public Date getEffectiveArriveTime() {
		return effectiveArriveTime;
	}
	public void setEffectiveArriveTime(Date effectiveArriveTime) {
		this.effectiveArriveTime = effectiveArriveTime;
	}
	public Date getEffectiveLeaveTime() {
		return effectiveLeaveTime;
	}
	public void setEffectiveLeaveTime(Date effectiveLeaveTime) {
		this.effectiveLeaveTime = effectiveLeaveTime;
	}
	public Date getRowAddTime() {
		return rowAddTime;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	

}
