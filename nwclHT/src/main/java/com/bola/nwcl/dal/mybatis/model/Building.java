package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Building extends Model {

	/**
	 * 自增Id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 小区Id
	 * @mbggenerated
	 */
	private Long communityId;
	/**
	 * 楼栋名称
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 楼栋地址
	 * @mbggenerated
	 */
	private String address;
	/**
	 * 设备管理
	 * @mbggenerated
	 */
	private String device;
	/**
	 * 二级小区ID
	 * @mbggenerated
	 */
	private Long secondCommunityId;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.id
	 * @return  the value of n_building.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增Id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.id
	 * @param id  the value for n_building.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.community_id
	 * @return  the value of n_building.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区Id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.community_id
	 * @param communityId  the value for n_building.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.name
	 * @return  the value of n_building.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "楼栋名称")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.name
	 * @param name  the value for n_building.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.address
	 * @return  the value of n_building.address
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "楼栋地址")
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.address
	 * @param address  the value for n_building.address
	 * @mbggenerated
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.device
	 * @return  the value of n_building.device
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "设备管理")
	public String getDevice() {
		return device;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.device
	 * @param device  the value for n_building.device
	 * @mbggenerated
	 */
	public void setDevice(String device) {
		this.device = device == null ? null : device.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.second_community_id
	 * @return  the value of n_building.second_community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "二级小区ID")
	public Long getSecondCommunityId() {
		return secondCommunityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.second_community_id
	 * @param secondCommunityId  the value for n_building.second_community_id
	 * @mbggenerated
	 */
	public void setSecondCommunityId(Long secondCommunityId) {
		this.secondCommunityId = secondCommunityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.note
	 * @return  the value of n_building.note
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "备注")
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.note
	 * @param note  the value for n_building.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.row_add_time
	 * @return  the value of n_building.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.row_add_time
	 * @param rowAddTime  the value for n_building.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_building.row_update_time
	 * @return  the value of n_building.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_building.row_update_time
	 * @param rowUpdateTime  the value for n_building.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}