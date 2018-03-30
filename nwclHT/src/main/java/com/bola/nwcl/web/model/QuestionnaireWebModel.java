package com.bola.nwcl.web.model;

import java.util.Date;

public class QuestionnaireWebModel {
	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 标题
	 * @mbggenerated
	 */
	private String title;
	/**
	 * 发布人id
	 * @mbggenerated
	 */
	private Long publishPeopleId;
	/**
	 * 发布人name
	 * @mbggenerated
	 */
	private String publishPeopleName;
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
	 * 房间号
	 * @mbggenerated
	 */
	private String roomNumber ;
	
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
	 * 栏目
	 * @mbggenerated
	 */
	private String column ;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPublishPeopleId() {
		return publishPeopleId;
	}

	public void setPublishPeopleId(Long publishPeopleId) {
		this.publishPeopleId = publishPeopleId;
	}

	public String getPublishPeopleName() {
		return publishPeopleName;
	}

	public void setPublishPeopleName(String publishPeopleName) {
		this.publishPeopleName = publishPeopleName;
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

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	
}
