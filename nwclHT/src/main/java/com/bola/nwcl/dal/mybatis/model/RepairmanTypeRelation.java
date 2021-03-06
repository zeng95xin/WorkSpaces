package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class RepairmanTypeRelation extends Model {

	/**
	 * 自增Id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 用户Id
	 * @mbggenerated
	 */
	private Long suserId;
	/**
	 * 类型Id
	 * @mbggenerated
	 */
	private Long typeId;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_type_relation.id
	 * @return  the value of n_repairman_type_relation.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增Id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_type_relation.id
	 * @param id  the value for n_repairman_type_relation.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_type_relation.suser_id
	 * @return  the value of n_repairman_type_relation.suser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "用户Id")
	public Long getSuserId() {
		return suserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_type_relation.suser_id
	 * @param suserId  the value for n_repairman_type_relation.suser_id
	 * @mbggenerated
	 */
	public void setSuserId(Long suserId) {
		this.suserId = suserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_type_relation.type_id
	 * @return  the value of n_repairman_type_relation.type_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "类型Id")
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_type_relation.type_id
	 * @param typeId  the value for n_repairman_type_relation.type_id
	 * @mbggenerated
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_type_relation.row_add_time
	 * @return  the value of n_repairman_type_relation.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_type_relation.row_add_time
	 * @param rowAddTime  the value for n_repairman_type_relation.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_repairman_type_relation.row_update_time
	 * @return  the value of n_repairman_type_relation.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_repairman_type_relation.row_update_time
	 * @param rowUpdateTime  the value for n_repairman_type_relation.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}