package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class MaintenanceServiceType extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 服务类名
	 * @mbggenerated
	 */
	private String serviceType;
	/**
	 * 备注
	 * @mbggenerated
	 */
	private String note;
	/**
	 * 生成时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;
	/**
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;
	/**
	 * 小区名称(自定义)
	 */
	private String communityName;
	

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_maintenance_service_type.id
	 * @return  the value of n_maintenance_service_type.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_maintenance_service_type.id
	 * @param id  the value for n_maintenance_service_type.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_maintenance_service_type.service_type
	 * @return  the value of n_maintenance_service_type.service_type
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "服务类名")
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_maintenance_service_type.service_type
	 * @param serviceType  the value for n_maintenance_service_type.service_type
	 * @mbggenerated
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType == null ? null : serviceType.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_maintenance_service_type.note
	 * @return  the value of n_maintenance_service_type.note
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "备注")
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_maintenance_service_type.note
	 * @param note  the value for n_maintenance_service_type.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_maintenance_service_type.row_add_time
	 * @return  the value of n_maintenance_service_type.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "生成时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_maintenance_service_type.row_add_time
	 * @param rowAddTime  the value for n_maintenance_service_type.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_maintenance_service_type.row_update_time
	 * @return  the value of n_maintenance_service_type.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_maintenance_service_type.row_update_time
	 * @param rowUpdateTime  the value for n_maintenance_service_type.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_maintenance_service_type.community_id
	 * @return  the value of n_maintenance_service_type.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_maintenance_service_type.community_id
	 * @param communityId  the value for n_maintenance_service_type.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
}