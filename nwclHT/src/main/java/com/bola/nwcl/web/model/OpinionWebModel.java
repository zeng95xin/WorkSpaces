package com.bola.nwcl.web.model;

import java.util.Date;

public class OpinionWebModel {
	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 发送人id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 后台回复人员id
	 * @mbggenerated
	 */
	private Long backId;
	/**
	 * 发送类型,0:后台发送给业主,1:业主发送给后台
	 * @mbggenerated
	 */
	private Integer sendType;
	/**
	 * 消息类型,0:文字,1:图片,2:声音,如果是文件类型,内容里面就是相应的路径
	 * @mbggenerated
	 */
	private Integer messageType;
	/**
	 * 消息内容
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 是否已读,0:未读,1:已读
	 * @mbggenerated
	 */
	private Integer status;
	/**
	 * 发送时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	
	/**
	 * 业主姓名
	 * @mbggenerated
	 */
	private String userName ;

	/**
	 * 房间号
	 * @mbggenerated
	 */
	private String roomNumber ;
	/**
	 * 电话
	 * @mbggenerated
	 */
	private String phone ;
	/**
	 * 图片
	 * @mbggenerated
	 */
	private String imgPath;
	
	/**
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;
	
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
	 * 楼栋
	 * @mbggenerated
	 */
	private Long building;
	
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
	
	private String replayContent;
	
	private int replay;
	
	public String getReplayContent() {
		return replayContent;
	}
	public void setReplayContent(String replayContent) {
		this.replayContent = replayContent;
	}
	public int getReplay() {
		return replay;
	}
	public void setReplay(int replay) {
		this.replay = replay;
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
	public Long getBuilding() {
		return building;
	}
	public void setBuilding(Long building) {
		this.building = building;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBuserId() {
		return buserId;
	}
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}
	public Long getBackId() {
		return backId;
	}
	public void setBackId(Long backId) {
		this.backId = backId;
	}
	public Integer getSendType() {
		return sendType;
	}
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getRowAddTime() {
		return rowAddTime;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
