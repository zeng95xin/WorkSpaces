package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class RoomPhone extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 房间id
	 * @mbggenerated
	 */
	private Long roomId;
	/**
	 * 预留手机
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_room_phone.id
	 * @return  the value of n_room_phone.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_room_phone.id
	 * @param id  the value for n_room_phone.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_room_phone.room_id
	 * @return  the value of n_room_phone.room_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "房间id")
	public Long getRoomId() {
		return roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_room_phone.room_id
	 * @param roomId  the value for n_room_phone.room_id
	 * @mbggenerated
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_room_phone.phone
	 * @return  the value of n_room_phone.phone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "预留手机")
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_room_phone.phone
	 * @param phone  the value for n_room_phone.phone
	 * @mbggenerated
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_room_phone.row_add_time
	 * @return  the value of n_room_phone.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_room_phone.row_add_time
	 * @param rowAddTime  the value for n_room_phone.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_room_phone.row_update_time
	 * @return  the value of n_room_phone.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_room_phone.row_update_time
	 * @param rowUpdateTime  the value for n_room_phone.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}