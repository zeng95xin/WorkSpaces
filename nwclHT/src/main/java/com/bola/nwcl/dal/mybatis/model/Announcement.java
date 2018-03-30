package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Announcement extends Model {

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
	 * 发布人id
	 * @mbggenerated
	 */
	private Long publishPeopleId;
	/**
	 * 状态,0:待审核,1:已发送
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
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;
	/**
	 * 是否置顶，0：不是，1：是
	 * @mbggenerated
	 */
	private Integer isTop;
	/**
	 * 公告简介
	 * @mbggenerated
	 */
	private String summary;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.id
	 * @return  the value of n_announcement.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.id
	 * @param id  the value for n_announcement.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.img_path
	 * @return  the value of n_announcement.img_path
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "显示图片")
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.img_path
	 * @param imgPath  the value for n_announcement.img_path
	 * @mbggenerated
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath == null ? null : imgPath.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.title
	 * @return  the value of n_announcement.title
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.title
	 * @param title  the value for n_announcement.title
	 * @mbggenerated
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.detail
	 * @return  the value of n_announcement.detail
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "详情")
	public String getDetail() {
		return detail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.detail
	 * @param detail  the value for n_announcement.detail
	 * @mbggenerated
	 */
	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.publish_people_id
	 * @return  the value of n_announcement.publish_people_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发布人id")
	public Long getPublishPeopleId() {
		return publishPeopleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.publish_people_id
	 * @param publishPeopleId  the value for n_announcement.publish_people_id
	 * @mbggenerated
	 */
	public void setPublishPeopleId(Long publishPeopleId) {
		this.publishPeopleId = publishPeopleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.status
	 * @return  the value of n_announcement.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "状态,0:待审核,1:已发送")
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.status
	 * @param status  the value for n_announcement.status
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.send_count
	 * @return  the value of n_announcement.send_count
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "送达人数")
	public Integer getSendCount() {
		return sendCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.send_count
	 * @param sendCount  the value for n_announcement.send_count
	 * @mbggenerated
	 */
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.remark
	 * @return  the value of n_announcement.remark
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "备注")
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.remark
	 * @param remark  the value for n_announcement.remark
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.community_id
	 * @return  the value of n_announcement.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.community_id
	 * @param communityId  the value for n_announcement.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.is_top
	 * @return  the value of n_announcement.is_top
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "是否置顶，0：不是，1：是")
	public Integer getIsTop() {
		return isTop;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.is_top
	 * @param isTop  the value for n_announcement.is_top
	 * @mbggenerated
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.summary
	 * @return  the value of n_announcement.summary
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "公告简介")
	public String getSummary() {
		return summary;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.summary
	 * @param summary  the value for n_announcement.summary
	 * @mbggenerated
	 */
	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.row_add_time
	 * @return  the value of n_announcement.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发布时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.row_add_time
	 * @param rowAddTime  the value for n_announcement.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_announcement.row_update_time
	 * @return  the value of n_announcement.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_announcement.row_update_time
	 * @param rowUpdateTime  the value for n_announcement.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	private List<AnnouncementImg> images;
	

	public List<AnnouncementImg> getImages() {
		return images;
	}

	public void setImages(List<AnnouncementImg> images) {
		this.images = images;
	}

	// 自定义的查询字段名
	private String sname;
	//自定义查询字段名属性
	private String svalue;
	//发布人姓名（ID 转换过来）
	private String publishPeopleName;
	
	
	
	public String getPublishPeopleName() {
		return publishPeopleName;
	}

	public void setPublishPeopleName(String publishPeopleName) {
		this.publishPeopleName = publishPeopleName;
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