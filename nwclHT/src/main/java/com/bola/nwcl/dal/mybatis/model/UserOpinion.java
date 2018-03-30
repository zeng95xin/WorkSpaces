package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class UserOpinion extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 发送人id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 房间id
	 * @mbggenerated
	 */
	private Long roomId;
	/**
	 * 后台回复人员id
	 * @mbggenerated
	 */
	private Long backId;
	/**
	 * 发送类型,0:后台发送给业主,1:业主发送给后台
	 * @mbggenerated
	 */
	private Integer sendType;
	/**
	 * 消息类型,0:文字,1:图片,2:声音,如果是文件类型,内容里面就是相应的路径
	 * @mbggenerated
	 */
	private Integer messageType;
	/**
	 * 消息内容
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 是否已读,0:未读,1:已读
	 * @mbggenerated
	 */
	private Integer status;
	/**
	 * 发送时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 更新时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;
	/**
	 * 标签
	 * @mbggenerated
	 */
	private String label;
	/**
	 * 回复id
	 * @mbggenerated
	 */
	private Long replayId;
	/**
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.id
	 * @return  the value of n_user_opinion.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.id
	 * @param id  the value for n_user_opinion.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.buser_id
	 * @return  the value of n_user_opinion.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发送人id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.buser_id
	 * @param buserId  the value for n_user_opinion.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.room_id
	 * @return  the value of n_user_opinion.room_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "房间id")
	public Long getRoomId() {
		return roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.room_id
	 * @param roomId  the value for n_user_opinion.room_id
	 * @mbggenerated
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.back_id
	 * @return  the value of n_user_opinion.back_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "后台回复人员id")
	public Long getBackId() {
		return backId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.back_id
	 * @param backId  the value for n_user_opinion.back_id
	 * @mbggenerated
	 */
	public void setBackId(Long backId) {
		this.backId = backId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.send_type
	 * @return  the value of n_user_opinion.send_type
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发送类型,0:后台发送给业主,1:业主发送给后台")
	public Integer getSendType() {
		return sendType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.send_type
	 * @param sendType  the value for n_user_opinion.send_type
	 * @mbggenerated
	 */
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.message_type
	 * @return  the value of n_user_opinion.message_type
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "消息类型,0:文字,1:图片,2:声音,如果是文件类型,内容里面就是相应的路径")
	public Integer getMessageType() {
		return messageType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.message_type
	 * @param messageType  the value for n_user_opinion.message_type
	 * @mbggenerated
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.content
	 * @return  the value of n_user_opinion.content
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "消息内容")
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.content
	 * @param content  the value for n_user_opinion.content
	 * @mbggenerated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.status
	 * @return  the value of n_user_opinion.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "是否已读,0:未读,1:已读")
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.status
	 * @param status  the value for n_user_opinion.status
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.row_add_time
	 * @return  the value of n_user_opinion.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发送时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.row_add_time
	 * @param rowAddTime  the value for n_user_opinion.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.row_update_time
	 * @return  the value of n_user_opinion.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.row_update_time
	 * @param rowUpdateTime  the value for n_user_opinion.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.label
	 * @return  the value of n_user_opinion.label
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标签")
	public String getLabel() {
		return label;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.label
	 * @param label  the value for n_user_opinion.label
	 * @mbggenerated
	 */
	public void setLabel(String label) {
		this.label = label == null ? null : label.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.replay_id
	 * @return  the value of n_user_opinion.replay_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "回复id")
	public Long getReplayId() {
		return replayId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.replay_id
	 * @param replayId  the value for n_user_opinion.replay_id
	 * @mbggenerated
	 */
	public void setReplayId(Long replayId) {
		this.replayId = replayId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_user_opinion.community_id
	 * @return  the value of n_user_opinion.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_user_opinion.community_id
	 * @param communityId  the value for n_user_opinion.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
}