package com.bola.nwcl.web.model;

import java.util.Date;

public class BizUserWebModel {
	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 姓名
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 昵称
	 * @mbggenerated
	 */
	private String nickname;
	/**
	 * 头像
	 * @mbggenerated
	 */
	private String headPortrait;
	/**
	 * 性别
	 * @mbggenerated
	 */
	private String sex;
	/**
	 * 手机
	 * @mbggenerated
	 */
	private String phone;
	/**
	 * 状态，1启用，0禁用
	 * @mbggenerated
	 */
	private Short status;
	/**
	 * 当前绑定房产
	 * @mbggenerated
	 */
	private Long currentRoomId;
	/**
	 * 积分
	 * @mbggenerated
	 */
	private Integer point;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	

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
	 * 楼栋ID
	 */
	private Long buildingId;
	
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	/**
	 * 房间号
	 * @mbggenerated
	 */
	private String roomNumber ;
	/**
	 * 房间编号
	 * @mbggenerated
	 */
	private String unitNo ;
	/**
	 * 业主身份
	 * @mbggenerated
	 */
	private String owner ;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Long getCurrentRoomId() {
		return currentRoomId;
	}
	public void setCurrentRoomId(Long currentRoomId) {
		this.currentRoomId = currentRoomId;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Date getRowAddTime() {
		return rowAddTime;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
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
	public Long getBuilding() {
		return building;
	}
	public void setBuilding(Long building) {
		this.building = building;
	}
	
}
