package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class NeighborHelpZan extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 邻里帮id
	 * @mbggenerated
	 */
	private Long neighborHelpId;
	/**
	 * 点赞人id
	 * @mbggenerated
	 */
	private Long buserId;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help_zan.id
	 * @return  the value of n_neighbor_help_zan.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help_zan.id
	 * @param id  the value for n_neighbor_help_zan.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help_zan.neighbor_help_id
	 * @return  the value of n_neighbor_help_zan.neighbor_help_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "邻里帮id")
	public Long getNeighborHelpId() {
		return neighborHelpId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help_zan.neighbor_help_id
	 * @param neighborHelpId  the value for n_neighbor_help_zan.neighbor_help_id
	 * @mbggenerated
	 */
	public void setNeighborHelpId(Long neighborHelpId) {
		this.neighborHelpId = neighborHelpId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help_zan.buser_id
	 * @return  the value of n_neighbor_help_zan.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "点赞人id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help_zan.buser_id
	 * @param buserId  the value for n_neighbor_help_zan.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help_zan.row_add_time
	 * @return  the value of n_neighbor_help_zan.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help_zan.row_add_time
	 * @param rowAddTime  the value for n_neighbor_help_zan.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help_zan.row_update_time
	 * @return  the value of n_neighbor_help_zan.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help_zan.row_update_time
	 * @param rowUpdateTime  the value for n_neighbor_help_zan.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}