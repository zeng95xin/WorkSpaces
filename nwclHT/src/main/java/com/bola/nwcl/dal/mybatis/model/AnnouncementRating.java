package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class AnnouncementRating extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 公告id
	 * @mbggenerated
	 */
	private Long announcementId;
	/**
	 * 留言内容
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 留言人id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 是否是敏感留言,0:不是,1:是
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

	// 自定义字段 评论人 帐号
	private String userName;
	private String userAccount;
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.id
	 * @return  the value of n_announcement_rating.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.id
	 * @param id  the value for n_announcement_rating.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.announcement_id
	 * @return  the value of n_announcement_rating.announcement_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "公告id")
	public Long getAnnouncementId() {
		return announcementId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.announcement_id
	 * @param announcementId  the value for n_announcement_rating.announcement_id
	 * @mbggenerated
	 */
	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.content
	 * @return  the value of n_announcement_rating.content
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "留言内容")
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.content
	 * @param content  the value for n_announcement_rating.content
	 * @mbggenerated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.buser_id
	 * @return  the value of n_announcement_rating.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "留言人id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.buser_id
	 * @param buserId  the value for n_announcement_rating.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.status
	 * @return  the value of n_announcement_rating.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "是否是敏感留言,0:不是,1:是")
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.status
	 * @param status  the value for n_announcement_rating.status
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.reply_id
	 * @return  the value of n_announcement_rating.reply_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "被回复评论id")
	public Long getReplyId() {
		return replyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.reply_id
	 * @param replyId  the value for n_announcement_rating.reply_id
	 * @mbggenerated
	 */
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.row_add_time
	 * @return  the value of n_announcement_rating.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.row_add_time
	 * @param rowAddTime  the value for n_announcement_rating.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement_rating.row_update_time
	 * @return  the value of n_announcement_rating.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement_rating.row_update_time
	 * @param rowUpdateTime  the value for n_announcement_rating.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}