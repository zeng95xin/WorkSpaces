package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Property extends Model {

	/**
	 * 自增Id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 物业名称
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 物业地址
	 * @mbggenerated
	 */
	private String address;
	/**
	 * 物业联系人
	 * @mbggenerated
	 */
	private String contactPeople;
	/**
	 * 物业联系电话
	 * @mbggenerated
	 */
	private String contactPhone;
	/**
	 * 邮箱
	 * @mbggenerated
	 */
	private String email;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.id
	 * @return  the value of n_property.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增Id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.id
	 * @param id  the value for n_property.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.name
	 * @return  the value of n_property.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "物业名称")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.name
	 * @param name  the value for n_property.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.address
	 * @return  the value of n_property.address
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "物业地址")
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.address
	 * @param address  the value for n_property.address
	 * @mbggenerated
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.contact_people
	 * @return  the value of n_property.contact_people
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "物业联系人")
	public String getContactPeople() {
		return contactPeople;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.contact_people
	 * @param contactPeople  the value for n_property.contact_people
	 * @mbggenerated
	 */
	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople == null ? null : contactPeople
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.contact_phone
	 * @return  the value of n_property.contact_phone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "物业联系电话")
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.contact_phone
	 * @param contactPhone  the value for n_property.contact_phone
	 * @mbggenerated
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.email
	 * @return  the value of n_property.email
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "邮箱")
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.email
	 * @param email  the value for n_property.email
	 * @mbggenerated
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.note
	 * @return  the value of n_property.note
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "备注")
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.note
	 * @param note  the value for n_property.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.row_add_time
	 * @return  the value of n_property.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.row_add_time
	 * @param rowAddTime  the value for n_property.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property.row_update_time
	 * @return  the value of n_property.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property.row_update_time
	 * @param rowUpdateTime  the value for n_property.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}