package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BuserCoupon extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 业主id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 优惠卷id
	 * @mbggenerated
	 */
	private Long couponId;
	/**
	 * 使用次数
	 * @mbggenerated
	 */
	private Integer useTime;
	/**
	 * 优惠卷编号
	 * @mbggenerated
	 */
	private String serial;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_buser_coupon.id
	 * @return  the value of n_buser_coupon.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_buser_coupon.id
	 * @param id  the value for n_buser_coupon.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_buser_coupon.buser_id
	 * @return  the value of n_buser_coupon.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_buser_coupon.buser_id
	 * @param buserId  the value for n_buser_coupon.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_buser_coupon.coupon_id
	 * @return  the value of n_buser_coupon.coupon_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "优惠卷id")
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_buser_coupon.coupon_id
	 * @param couponId  the value for n_buser_coupon.coupon_id
	 * @mbggenerated
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_buser_coupon.use_time
	 * @return  the value of n_buser_coupon.use_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "使用次数")
	public Integer getUseTime() {
		return useTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_buser_coupon.use_time
	 * @param useTime  the value for n_buser_coupon.use_time
	 * @mbggenerated
	 */
	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_buser_coupon.serial
	 * @return  the value of n_buser_coupon.serial
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "优惠卷编号")
	public String getSerial() {
		return serial;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_buser_coupon.serial
	 * @param serial  the value for n_buser_coupon.serial
	 * @mbggenerated
	 */
	public void setSerial(String serial) {
		this.serial = serial == null ? null : serial.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_buser_coupon.row_add_time
	 * @return  the value of n_buser_coupon.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_buser_coupon.row_add_time
	 * @param rowAddTime  the value for n_buser_coupon.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_buser_coupon.row_update_time
	 * @return  the value of n_buser_coupon.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_buser_coupon.row_update_time
	 * @param rowUpdateTime  the value for n_buser_coupon.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
	
	/**
	 * 自定义字段 （昵称）
	 */
	private String userNickName;
	/**
	 * 自定义字段 （帐号）
	 */
	private String userAccount;
	/**
	 * 自定义字段 （用户名）
	 */
	private String userName;

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}