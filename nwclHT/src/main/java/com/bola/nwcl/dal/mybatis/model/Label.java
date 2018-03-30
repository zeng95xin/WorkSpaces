package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Label extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 类型,1：爱分享,2：邻里帮,3：业主市集,4:用户意见
	 * @mbggenerated
	 */
	private Integer typeId;
	/**
	 * 标签内容
	 * @mbggenerated
	 */
	private String content;
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
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_label.id
	 * @return  the value of n_label.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_label.id
	 * @param id  the value for n_label.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_label.type_id
	 * @return  the value of n_label.type_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "类型,1：爱分享,2：邻里帮,3：业主市集,4:用户意见")
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_label.type_id
	 * @param typeId  the value for n_label.type_id
	 * @mbggenerated
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_label.content
	 * @return  the value of n_label.content
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标签内容")
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_label.content
	 * @param content  the value for n_label.content
	 * @mbggenerated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_label.row_add_time
	 * @return  the value of n_label.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_label.row_add_time
	 * @param rowAddTime  the value for n_label.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_label.row_update_time
	 * @return  the value of n_label.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_label.row_update_time
	 * @param rowUpdateTime  the value for n_label.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_label.community_id
	 * @return  the value of n_label.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_label.community_id
	 * @param communityId  the value for n_label.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
}