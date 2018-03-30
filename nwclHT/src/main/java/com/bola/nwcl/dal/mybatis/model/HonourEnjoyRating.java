package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class HonourEnjoyRating extends Model {

	/**
	 * 自增ID，评论ID
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 尊享id
	 * @mbggenerated
	 */
	private Long honourEnjoyId;
	/**
	 * 评论内容
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 评论人id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 是否是敏感评价,0:不是,1:是
	 * @mbggenerated
	 */
	private Integer status;
	/**
	 * 被回复评论id
	 * @mbggenerated
	 */
	private Long replyId;
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

	//自定义字段
	private String userNickname;
	private String userAccount;
	
	
	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.id
	 * @return  the value of n_honour_enjoy_rating.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增ID，评论ID")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.id
	 * @param id  the value for n_honour_enjoy_rating.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.honour_enjoy_id
	 * @return  the value of n_honour_enjoy_rating.honour_enjoy_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "尊享id")
	public Long getHonourEnjoyId() {
		return honourEnjoyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.honour_enjoy_id
	 * @param honourEnjoyId  the value for n_honour_enjoy_rating.honour_enjoy_id
	 * @mbggenerated
	 */
	public void setHonourEnjoyId(Long honourEnjoyId) {
		this.honourEnjoyId = honourEnjoyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.content
	 * @return  the value of n_honour_enjoy_rating.content
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "评论内容")
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.content
	 * @param content  the value for n_honour_enjoy_rating.content
	 * @mbggenerated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.buser_id
	 * @return  the value of n_honour_enjoy_rating.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "评论人id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.buser_id
	 * @param buserId  the value for n_honour_enjoy_rating.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.status
	 * @return  the value of n_honour_enjoy_rating.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "是否是敏感评价,0:不是,1:是")
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.status
	 * @param status  the value for n_honour_enjoy_rating.status
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.reply_id
	 * @return  the value of n_honour_enjoy_rating.reply_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "被回复评论id")
	public Long getReplyId() {
		return replyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.reply_id
	 * @param replyId  the value for n_honour_enjoy_rating.reply_id
	 * @mbggenerated
	 */
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.row_add_time
	 * @return  the value of n_honour_enjoy_rating.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.row_add_time
	 * @param rowAddTime  the value for n_honour_enjoy_rating.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_honour_enjoy_rating.row_update_time
	 * @return  the value of n_honour_enjoy_rating.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_honour_enjoy_rating.row_update_time
	 * @param rowUpdateTime  the value for n_honour_enjoy_rating.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}