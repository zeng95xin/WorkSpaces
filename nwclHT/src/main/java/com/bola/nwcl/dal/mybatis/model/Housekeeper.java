package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Housekeeper extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 姓名
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 昵称
	 * @mbggenerated
	 */
	private String nickname;
	/**
	 * 头像
	 * @mbggenerated
	 */
	private String headPortrait;
	/**
	 * 性别
	 * @mbggenerated
	 */
	private String sex;
	/**
	 * 手机
	 * @mbggenerated
	 */
	private String phone;
	/**
	 * 密码
	 * @mbggenerated
	 */
	private String password;
	/**
	 * 状态，1值班中，0下班了，-1离职
	 * @mbggenerated
	 */
	private Short status;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.id
	 * @return  the value of n_housekeeper.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.id
	 * @param id  the value for n_housekeeper.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.name
	 * @return  the value of n_housekeeper.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "姓名")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.name
	 * @param name  the value for n_housekeeper.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.nickname
	 * @return  the value of n_housekeeper.nickname
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "昵称")
	public String getNickname() {
		return nickname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.nickname
	 * @param nickname  the value for n_housekeeper.nickname
	 * @mbggenerated
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.head_portrait
	 * @return  the value of n_housekeeper.head_portrait
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.head_portrait
	 * @param headPortrait  the value for n_housekeeper.head_portrait
	 * @mbggenerated
	 */
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait == null ? null : headPortrait.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.sex
	 * @return  the value of n_housekeeper.sex
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "性别")
	public String getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.sex
	 * @param sex  the value for n_housekeeper.sex
	 * @mbggenerated
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.phone
	 * @return  the value of n_housekeeper.phone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "手机")
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.phone
	 * @param phone  the value for n_housekeeper.phone
	 * @mbggenerated
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.password
	 * @return  the value of n_housekeeper.password
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "密码")
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.password
	 * @param password  the value for n_housekeeper.password
	 * @mbggenerated
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.status
	 * @return  the value of n_housekeeper.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "状态，1值班中，0下班了，-1离职")
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.status
	 * @param status  the value for n_housekeeper.status
	 * @mbggenerated
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.row_add_time
	 * @return  the value of n_housekeeper.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.row_add_time
	 * @param rowAddTime  the value for n_housekeeper.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_housekeeper.row_update_time
	 * @return  the value of n_housekeeper.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_housekeeper.row_update_time
	 * @param rowUpdateTime  the value for n_housekeeper.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}