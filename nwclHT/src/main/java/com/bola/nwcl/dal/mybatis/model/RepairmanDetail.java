package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class RepairmanDetail extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 维修人员id
	 * @mbggenerated
	 */
	private Long repairmainId;
	/**
	 * 详情介绍
	 * @mbggenerated
	 */
	private String detail;
	/**
	 * 状态，0:空闲，1:忙碌中,2:非常忙碌
	 * @mbggenerated
	 */
	private Integer status;
	/**
	 * 工号
	 * @mbggenerated
	 */
	private String serial;
	/**
	 * 电话
	 * @mbggenerated
	 */
	private String phone;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.id
	 * @return  the value of n_repairman_detail.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.id
	 * @param id  the value for n_repairman_detail.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.repairmain_id
	 * @return  the value of n_repairman_detail.repairmain_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "维修人员id")
	public Long getRepairmainId() {
		return repairmainId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.repairmain_id
	 * @param repairmainId  the value for n_repairman_detail.repairmain_id
	 * @mbggenerated
	 */
	public void setRepairmainId(Long repairmainId) {
		this.repairmainId = repairmainId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.detail
	 * @return  the value of n_repairman_detail.detail
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "详情介绍")
	public String getDetail() {
		return detail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.detail
	 * @param detail  the value for n_repairman_detail.detail
	 * @mbggenerated
	 */
	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.status
	 * @return  the value of n_repairman_detail.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "状态，0:空闲，1:忙碌中,2:非常忙碌")
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.status
	 * @param status  the value for n_repairman_detail.status
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.serial
	 * @return  the value of n_repairman_detail.serial
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "工号")
	public String getSerial() {
		return serial;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.serial
	 * @param serial  the value for n_repairman_detail.serial
	 * @mbggenerated
	 */
	public void setSerial(String serial) {
		this.serial = serial == null ? null : serial.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.phone
	 * @return  the value of n_repairman_detail.phone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "电话")
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.phone
	 * @param phone  the value for n_repairman_detail.phone
	 * @mbggenerated
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.row_add_time
	 * @return  the value of n_repairman_detail.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.row_add_time
	 * @param rowAddTime  the value for n_repairman_detail.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_detail.row_update_time
	 * @return  the value of n_repairman_detail.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_detail.row_update_time
	 * @param rowUpdateTime  the value for n_repairman_detail.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}