package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Notify extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 显示图片
	 * @mbggenerated
	 */
	private String imgPath;
	/**
	 * 标题
	 * @mbggenerated
	 */
	private String title;
	/**
	 * 详情
	 * @mbggenerated
	 */
	private String detail;
	/**
	 * 发布人id,-1,代表系统推送
	 * @mbggenerated
	 */
	private Long publishPeopleId;
	/**
	 * 状态,0:待审核,1:已发送,用户不能看见状态为待审核的消息通知
	 * @mbggenerated
	 */
	private Integer status;
	/**
	 * 送达人数
	 * @mbggenerated
	 */
	private Integer sendCount;
	/**
	 * 备注
	 * @mbggenerated
	 */
	private String remark;
	/**
	 * 消息通知类型,1:管家消息,2:借用单消息,3:挂画单消息,4:维修单消息,5:访客消息,6:放行条消息，7:授权消息,9:邻里帮消息,统一格式,类型:id:状态,如果没有是-1
	 * @mbggenerated
	 */
	private String type;
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
	 * @mbggenerated
	 */
	private Long communityId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.id
	 * @return  the value of n_notify.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.id
	 * @param id  the value for n_notify.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.img_path
	 * @return  the value of n_notify.img_path
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "显示图片")
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.img_path
	 * @param imgPath  the value for n_notify.img_path
	 * @mbggenerated
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath == null ? null : imgPath.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.title
	 * @return  the value of n_notify.title
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.title
	 * @param title  the value for n_notify.title
	 * @mbggenerated
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.detail
	 * @return  the value of n_notify.detail
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "详情")
	public String getDetail() {
		return detail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.detail
	 * @param detail  the value for n_notify.detail
	 * @mbggenerated
	 */
	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.publish_people_id
	 * @return  the value of n_notify.publish_people_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发布人id,-1,代表系统推送")
	public Long getPublishPeopleId() {
		return publishPeopleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.publish_people_id
	 * @param publishPeopleId  the value for n_notify.publish_people_id
	 * @mbggenerated
	 */
	public void setPublishPeopleId(Long publishPeopleId) {
		this.publishPeopleId = publishPeopleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.status
	 * @return  the value of n_notify.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "状态,0:待审核,1:已发送,用户不能看见状态为待审核的消息通知")
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.status
	 * @param status  the value for n_notify.status
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.send_count
	 * @return  the value of n_notify.send_count
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "送达人数")
	public Integer getSendCount() {
		return sendCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.send_count
	 * @param sendCount  the value for n_notify.send_count
	 * @mbggenerated
	 */
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.remark
	 * @return  the value of n_notify.remark
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "备注")
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.remark
	 * @param remark  the value for n_notify.remark
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.type
	 * @return  the value of n_notify.type
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "消息通知类型,1:管家消息,2:借用单消息,3:挂画单消息,4:维修单消息,5:访客消息,6:放行条消息，7:授权消息,9:邻里帮消息,统一格式,类型:id:状态,如果没有是-1")
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.type
	 * @param type  the value for n_notify.type
	 * @mbggenerated
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.row_add_time
	 * @return  the value of n_notify.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发布时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.row_add_time
	 * @param rowAddTime  the value for n_notify.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.row_update_time
	 * @return  the value of n_notify.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.row_update_time
	 * @param rowUpdateTime  the value for n_notify.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_notify.community_id
	 * @return  the value of n_notify.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_notify.community_id
	 * @param communityId  the value for n_notify.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	// 自定义查询字段
	private String sname;
	private String svalue;
	private	List<NotifyImg> images;
	
	public List<NotifyImg> getImages() {
		return images;
	}

	public void setImages(List<NotifyImg> images) {
		this.images = images;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSvalue() {
		return svalue;
	}

	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}
}