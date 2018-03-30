package com.bola.nwcl.web.model;

import java.util.Date;

public class NeighborHelpWebModel {
	
	private Integer isUserDelete;
	
	
	public Integer getIsUserDelete() {
		return isUserDelete;
	}
	public void setIsUserDelete(Integer isUserDelete) {
		this.isUserDelete = isUserDelete;
	}
	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 业主id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 分类标签
	 */
	private String label ;
	
	/**
	 * 标题
	 * @mbggenerated
	 */
	private String title;
	/**
	 * 内容,任务需求
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 悬赏
	 * @mbggenerated
	 */
	private String reward;
	/**
	 * '是否是包含敏感内容,0:不是,1:是',
	 * @mbggenerated
	 */
	private Integer isSensitive;
	/**
	 * 是否解决,0:不是,1:是
	 * @mbggenerated
	 */
	private Integer status;
	/**
	 * 阅读数量
	 * @mbggenerated
	 */
	private Integer readCount;
	/**
	 * 发布时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 更新时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;
	/**
	 * 楼栋
	 * @mbggenerated
	 */
	private Long building;
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
	 * 业主身份
	 * @mbggenerated
	 */
	private String owner ;
	/**
	 * 发布人
	 * @mbggenerated
	 */
	private String userName;
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
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId ;
	/**
	 * 图片路径
	 * @mbggenerated
	 */
	private String imgPath ;
	
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public Integer getIsSensitive() {
		return isSensitive;
	}
	public void setIsSensitive(Integer isSensitive) {
		this.isSensitive = isSensitive;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	public Date getRowAddTime() {
		return rowAddTime;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
	public Long getBuilding() {
		return building;
	}
	public void setBuilding(Long building) {
		this.building = building;
	}
	
	
	
}
