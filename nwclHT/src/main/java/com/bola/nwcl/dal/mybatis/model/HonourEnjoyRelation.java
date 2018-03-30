package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class HonourEnjoyRelation extends Model {

	/**
	 * 自增ID
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 尊享id
	 * @mbggenerated
	 */
	private Long honourEnjoyId;
	/**
	 * 负责人id
	 * @mbggenerated
	 */
	private Long userId;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_user_relation.id
	 * @return  the value of n_honour_enjoy_user_relation.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增ID")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_user_relation.id
	 * @param id  the value for n_honour_enjoy_user_relation.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_user_relation.honour_enjoy_id
	 * @return  the value of n_honour_enjoy_user_relation.honour_enjoy_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "尊享id")
	public Long getHonourEnjoyId() {
		return honourEnjoyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_user_relation.honour_enjoy_id
	 * @param honourEnjoyId  the value for n_honour_enjoy_user_relation.honour_enjoy_id
	 * @mbggenerated
	 */
	public void setHonourEnjoyId(Long honourEnjoyId) {
		this.honourEnjoyId = honourEnjoyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_user_relation.user_id
	 * @return  the value of n_honour_enjoy_user_relation.user_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "负责人id")
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_user_relation.user_id
	 * @param userId  the value for n_honour_enjoy_user_relation.user_id
	 * @mbggenerated
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_user_relation.row_add_time
	 * @return  the value of n_honour_enjoy_user_relation.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_user_relation.row_add_time
	 * @param rowAddTime  the value for n_honour_enjoy_user_relation.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_user_relation.row_update_time
	 * @return  the value of n_honour_enjoy_user_relation.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_user_relation.row_update_time
	 * @param rowUpdateTime  the value for n_honour_enjoy_user_relation.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}