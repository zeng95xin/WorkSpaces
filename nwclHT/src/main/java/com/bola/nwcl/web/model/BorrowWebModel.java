package com.bola.nwcl.web.model;

import java.util.Date;

public class BorrowWebModel {
	/**
	 * 房间号
	 */
	private Long roomNo;
	/**
	 * 物品名
	 */
	private String thingName;
	/**
	 * 借用人名
	 */
	private String userName;
	/**
	 * 借用人身份
	 */
	private String userType;
	/**
	 * 借用说明
	 */
	private String borrowReason;
	/**
	 * 借用时间
	 */
	private Date borrowTime;
	/**
	 * 状态
	 */
	private int state;
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Long roomNo) {
		this.roomNo = roomNo;
	}
	public String getThingName() {
		return thingName;
	}
	public void setThingName(String thingName) {
		this.thingName = thingName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getBorrowReason() {
		return borrowReason;
	}
	public void setBorrowReason(String borrowReason) {
		this.borrowReason = borrowReason;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	

}
