package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class NeighborHelp extends Model {

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
	 * 房间id
	 * @mbggenerated
	 */
	private Long roomId;
	/**
	 * 标题
	 * @mbggenerated
	 */
	private String title;
	/**
	 * 内容,任务需求
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 悬赏
	 * @mbggenerated
	 */
	private String reward;
	/**
	 * '是否是包含敏感内容,0:不是,1:是',
	 * @mbggenerated
	 */
	private Integer isSensitive;
	/**
	 * 是否解决,0:不是,1:是
	 * @mbggenerated
	 */
	private Integer status;
	/**
	 * 阅读数量
	 * @mbggenerated
	 */
	private Integer readCount;
	/**
	 * 发布时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 更新时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;
	/**
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;
	/**
	 * 标签
	 * @mbggenerated
	 */
	private String label;
	/**
	 * 采纳id
	 * @mbggenerated
	 */
	private Long endId;
	/**
	 * 用户是否删除,0:没有,1:删除
	 * @mbggenerated
	 */
	private Integer isUserDelete;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.id
	 * @return  the value of n_neighbor_help.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.id
	 * @param id  the value for n_neighbor_help.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.buser_id
	 * @return  the value of n_neighbor_help.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.buser_id
	 * @param buserId  the value for n_neighbor_help.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.room_id
	 * @return  the value of n_neighbor_help.room_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "房间id")
	public Long getRoomId() {
		return roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.room_id
	 * @param roomId  the value for n_neighbor_help.room_id
	 * @mbggenerated
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.title
	 * @return  the value of n_neighbor_help.title
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.title
	 * @param title  the value for n_neighbor_help.title
	 * @mbggenerated
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.content
	 * @return  the value of n_neighbor_help.content
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "内容,任务需求")
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.content
	 * @param content  the value for n_neighbor_help.content
	 * @mbggenerated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.reward
	 * @return  the value of n_neighbor_help.reward
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "悬赏")
	public String getReward() {
		return reward;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.reward
	 * @param reward  the value for n_neighbor_help.reward
	 * @mbggenerated
	 */
	public void setReward(String reward) {
		this.reward = reward == null ? null : reward.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.is_sensitive
	 * @return  the value of n_neighbor_help.is_sensitive
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "'是否是包含敏感内容,0:不是,1:是',")
	public Integer getIsSensitive() {
		return isSensitive;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.is_sensitive
	 * @param isSensitive  the value for n_neighbor_help.is_sensitive
	 * @mbggenerated
	 */
	public void setIsSensitive(Integer isSensitive) {
		this.isSensitive = isSensitive;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.status
	 * @return  the value of n_neighbor_help.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "是否解决,0:不是,1:是")
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.status
	 * @param status  the value for n_neighbor_help.status
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.read_count
	 * @return  the value of n_neighbor_help.read_count
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "阅读数量")
	public Integer getReadCount() {
		return readCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.read_count
	 * @param readCount  the value for n_neighbor_help.read_count
	 * @mbggenerated
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.row_add_time
	 * @return  the value of n_neighbor_help.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发布时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.row_add_time
	 * @param rowAddTime  the value for n_neighbor_help.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.row_update_time
	 * @return  the value of n_neighbor_help.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.row_update_time
	 * @param rowUpdateTime  the value for n_neighbor_help.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.community_id
	 * @return  the value of n_neighbor_help.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.community_id
	 * @param communityId  the value for n_neighbor_help.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.label
	 * @return  the value of n_neighbor_help.label
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标签")
	public String getLabel() {
		return label;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.label
	 * @param label  the value for n_neighbor_help.label
	 * @mbggenerated
	 */
	public void setLabel(String label) {
		this.label = label == null ? null : label.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.end_id
	 * @return  the value of n_neighbor_help.end_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "采纳id")
	public Long getEndId() {
		return endId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.end_id
	 * @param endId  the value for n_neighbor_help.end_id
	 * @mbggenerated
	 */
	public void setEndId(Long endId) {
		this.endId = endId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_neighbor_help.is_user_delete
	 * @return  the value of n_neighbor_help.is_user_delete
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "用户是否删除,0:没有,1:删除")
	public Integer getIsUserDelete() {
		return isUserDelete;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_neighbor_help.is_user_delete
	 * @param isUserDelete  the value for n_neighbor_help.is_user_delete
	 * @mbggenerated
	 */
	public void setIsUserDelete(Integer isUserDelete) {
		this.isUserDelete = isUserDelete;
	}
}