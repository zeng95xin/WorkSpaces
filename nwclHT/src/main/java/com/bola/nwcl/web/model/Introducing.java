package com.bola.nwcl.web.model;

import java.io.Serializable;
import java.util.Date;

public class Introducing implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * 话题,标题
	 * @mbggenerated
	 */
	private String title;
	/**
	 * 内容大纲
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 姓名
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 电话
	 * @mbggenerated
	 */
	private String phone;
	/**
	 * 个人介绍
	 * @mbggenerated
	 */
	private String description;
	/**
	 * 报名时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 更新时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;
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
	 * 图片路径
	 * @mbggenerated
	 */
	private String imgPath ;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
}
