package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ConvenienceTelephone extends Model {

	/**
	 * 自增Id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 电话名称
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 便民电话分类
	 * @mbggenerated
	 */
	private Integer type;
	/**
	 * 电话
	 * @mbggenerated
	 */
	private String telephone;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_convenience_telephone.id
	 * @return  the value of n_convenience_telephone.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增Id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_convenience_telephone.id
	 * @param id  the value for n_convenience_telephone.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_convenience_telephone.name
	 * @return  the value of n_convenience_telephone.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "电话名称")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_convenience_telephone.name
	 * @param name  the value for n_convenience_telephone.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_convenience_telephone.type
	 * @return  the value of n_convenience_telephone.type
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "便民电话分类")
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_convenience_telephone.type
	 * @param type  the value for n_convenience_telephone.type
	 * @mbggenerated
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_convenience_telephone.telephone
	 * @return  the value of n_convenience_telephone.telephone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "电话")
	public String getTelephone() {
		return telephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_convenience_telephone.telephone
	 * @param telephone  the value for n_convenience_telephone.telephone
	 * @mbggenerated
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_convenience_telephone.row_add_time
	 * @return  the value of n_convenience_telephone.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_convenience_telephone.row_add_time
	 * @param rowAddTime  the value for n_convenience_telephone.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_convenience_telephone.row_update_time
	 * @return  the value of n_convenience_telephone.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_convenience_telephone.row_update_time
	 * @param rowUpdateTime  the value for n_convenience_telephone.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_convenience_telephone.community_id
	 * @return  the value of n_convenience_telephone.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_convenience_telephone.community_id
	 * @param communityId  the value for n_convenience_telephone.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	private String typeName;
	
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}