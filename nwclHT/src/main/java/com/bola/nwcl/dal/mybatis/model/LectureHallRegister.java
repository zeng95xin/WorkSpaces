package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class LectureHallRegister extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 业主讲堂id
	 * @mbggenerated
	 */
	private Long lectureHallId;
	/**
	 * 报名人id
	 * @mbggenerated
	 */
	private Long buserId;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_register.id
	 * @return  the value of n_lecture_hall_register.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_register.id
	 * @param id  the value for n_lecture_hall_register.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_register.lecture_hall_id
	 * @return  the value of n_lecture_hall_register.lecture_hall_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主讲堂id")
	public Long getLectureHallId() {
		return lectureHallId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_register.lecture_hall_id
	 * @param lectureHallId  the value for n_lecture_hall_register.lecture_hall_id
	 * @mbggenerated
	 */
	public void setLectureHallId(Long lectureHallId) {
		this.lectureHallId = lectureHallId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_register.buser_id
	 * @return  the value of n_lecture_hall_register.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "报名人id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_register.buser_id
	 * @param buserId  the value for n_lecture_hall_register.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_register.row_add_time
	 * @return  the value of n_lecture_hall_register.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "报名时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_register.row_add_time
	 * @param rowAddTime  the value for n_lecture_hall_register.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_register.row_update_time
	 * @return  the value of n_lecture_hall_register.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_register.row_update_time
	 * @param rowUpdateTime  the value for n_lecture_hall_register.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}