package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class HousekeeperDetail extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 管家自增id
	 * @mbggenerated
	 */
	private Long keeperId;
	/**
	 * 头像
	 * @mbggenerated
	 */
	private String detail;
	/**
	 * 手机
	 * @mbggenerated
	 */
	private String phone;
	/**
	 * 状态，0下班了，1值班中，2离职
	 * @mbggenerated
	 */
	private Short status;
	/**
	 * 工号
	 * @mbggenerated
	 */
	private String serial;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 更新时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.id
	 * @return  the value of n_housekeeper_detail.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.id
	 * @param id  the value for n_housekeeper_detail.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.keeper_id
	 * @return  the value of n_housekeeper_detail.keeper_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "管家自增id")
	public Long getKeeperId() {
		return keeperId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.keeper_id
	 * @param keeperId  the value for n_housekeeper_detail.keeper_id
	 * @mbggenerated
	 */
	public void setKeeperId(Long keeperId) {
		this.keeperId = keeperId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.detail
	 * @return  the value of n_housekeeper_detail.detail
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "头像")
	public String getDetail() {
		return detail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.detail
	 * @param detail  the value for n_housekeeper_detail.detail
	 * @mbggenerated
	 */
	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.phone
	 * @return  the value of n_housekeeper_detail.phone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "手机")
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.phone
	 * @param phone  the value for n_housekeeper_detail.phone
	 * @mbggenerated
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.status
	 * @return  the value of n_housekeeper_detail.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "状态，0下班了，1值班中，2离职")
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.status
	 * @param status  the value for n_housekeeper_detail.status
	 * @mbggenerated
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.serial
	 * @return  the value of n_housekeeper_detail.serial
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "工号")
	public String getSerial() {
		return serial;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.serial
	 * @param serial  the value for n_housekeeper_detail.serial
	 * @mbggenerated
	 */
	public void setSerial(String serial) {
		this.serial = serial == null ? null : serial.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.row_add_time
	 * @return  the value of n_housekeeper_detail.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.row_add_time
	 * @param rowAddTime  the value for n_housekeeper_detail.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper_detail.row_update_time
	 * @return  the value of n_housekeeper_detail.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper_detail.row_update_time
	 * @param rowUpdateTime  the value for n_housekeeper_detail.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}