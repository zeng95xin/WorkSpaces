package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Community extends Model {

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
	private String serial;
	/**
	 * 小区名称
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 小区地址
	 * @mbggenerated
	 */
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
	private String contactPhone;
	/**
	 * 备注
	 * @mbggenerated
	 */
	private String note;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;
	/**
	 * 小区门禁设备
	 * @mbggenerated
	 */
	private String device;
	/**
	 * 小区门禁类型
	 * @mbggenerated
	 */
	private Long doorTypeId;


	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.id
	 * @return  the value of n_community.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增Id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.id
	 * @param id  the value for n_community.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.property_id
	 * @return  the value of n_community.property_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "物业Id")
	public Long getPropertyId() {
		return propertyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.property_id
	 * @param propertyId  the value for n_community.property_id
	 * @mbggenerated
	 */
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.serial
	 * @return  the value of n_community.serial
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "编号")
	public String getSerial() {
		return serial;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.serial
	 * @param serial  the value for n_community.serial
	 * @mbggenerated
	 */
	public void setSerial(String serial) {
		this.serial = serial == null ? null : serial.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.name
	 * @return  the value of n_community.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区名称")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.name
	 * @param name  the value for n_community.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.address
	 * @return  the value of n_community.address
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区地址")
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.address
	 * @param address  the value for n_community.address
	 * @mbggenerated
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.contact_people
	 * @return  the value of n_community.contact_people
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区联系人")
	public String getContactPeople() {
		return contactPeople;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.contact_people
	 * @param contactPeople  the value for n_community.contact_people
	 * @mbggenerated
	 */
	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople == null ? null : contactPeople
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.contact_phone
	 * @return  the value of n_community.contact_phone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区联系人电话")
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.contact_phone
	 * @param contactPhone  the value for n_community.contact_phone
	 * @mbggenerated
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.note
	 * @return  the value of n_community.note
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "备注")
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.note
	 * @param note  the value for n_community.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.row_add_time
	 * @return  the value of n_community.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.row_add_time
	 * @param rowAddTime  the value for n_community.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.row_update_time
	 * @return  the value of n_community.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.row_update_time
	 * @param rowUpdateTime  the value for n_community.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.device
	 * @return  the value of n_community.device
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区门禁设备")
	public String getDevice() {
		return device;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.device
	 * @param device  the value for n_community.device
	 * @mbggenerated
	 */
	public void setDevice(String device) {
		this.device = device == null ? null : device.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_community.door_type_id
	 * @return  the value of n_community.door_type_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区门禁类型")
	public Long getDoorTypeId() {
		return doorTypeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_community.door_type_id
	 * @param doorTypeId  the value for n_community.door_type_id
	 * @mbggenerated
	 */
	public void setDoorTypeId(Long doorTypeId) {
		this.doorTypeId = doorTypeId;
	}

	// 自定义字段 ： 物业公司名
	private String propertyName;
	// 自定义字段 ： 物业公司名
	private String doorTypeName;
	

	public String getDoorTypeName() {
		return doorTypeName;
	}

	public void setDoorTypeName(String doorTypeName) {
		this.doorTypeName = doorTypeName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}