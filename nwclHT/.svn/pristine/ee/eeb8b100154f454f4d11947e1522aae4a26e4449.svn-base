package com.bola.nwcl.web.model;

import java.util.Date;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class CommunityWebModel {
	/**
	 * 自增Id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 物业Id
	 * @mbggenerated
	 */
	private Long propertyId;
	/**
	 * 编号
	 * @mbggenerated
	 */
	@NotBlank(message="编号不能为空！")
	@Length(max = 40,message = "编号不能超过40个字符")
	private String serial;
	/**
	 * 小区名称
	 * @mbggenerated
	 */
	@NotBlank(message="小区不能为空！")
	private String name;
	/**
	 * 小区地址
	 * @mbggenerated
	 */
	@NotBlank(message="小区地址不能为空！")
	private String address;
	/**
	 * 小区联系人
	 * @mbggenerated
	 */
	private String contactPeople;
	/**
	 * 小区联系人电话
	 * @mbggenerated
	 */
	@NotBlank(message="小区联系人电话不能为空！")
	private String contactPhone;
	/**
	 * 备注
	 * @mbggenerated
	 */
	private String note;
	/**
	 * 门禁设备
	 * @mbggenerated
	 */
	private String device;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Long doorTypeId;
	
	
	public Long getDoorTypeId() {
		return doorTypeId;
	}
	public void setDoorTypeId(Long doorTypeId) {
		this.doorTypeId = doorTypeId;
	}
	private Date rowUpdateTime;
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPeople() {
		return contactPeople;
	}
	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	
}
