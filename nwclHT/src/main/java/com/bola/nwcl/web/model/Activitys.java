package com.bola.nwcl.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Activitys implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 自增id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 发布人id
     *
     * @mbggenerated
     */
    private Long publishId;

    /**
     * 活动图片
     *
     * @mbggenerated
     */
    private String imgPath;

    /**
     * 活动标题
     *
     * @mbggenerated
     */
    @NotBlank(message="标题不能为空！")
    @Length(max=200,message="标题不能超过200个字符")
    private String title;

    /**
     * 活动内容
     *
     * @mbggenerated
     */
    @NotBlank(message="活动内容不能为空！")
    private String content;

    /**
     * 1：文娱活动,2：义工活动,3：业主大会
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 报名截止时间
     *
     * @mbggenerated
     */
    @NotNull(message="报名截止时间不能为空！")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 发布时间
     *
     * @mbggenerated
     */
    private Date rowAddTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date rowUpdateTime;
    /**
     * 发布人
     *
     * @mbggenerated
     */
    private String  publish;
    /**
     * 小区id
     *
     * @mbggenerated
     */
    private Long  community;
    /**
     * 查询选项
     *
     * @mbggenerated
     */
    private String  selectOption;
    /**
     * 关键字
     *
     * @mbggenerated
     */
    private String key;
    /**
     * 状态  （报名截止，正在进行）  
     *
     * @mbggenerated
     */
    private String state;
    /**
     * 是否置顶
     *
     * @mbggenerated
     */
    private Integer isTop;
    
    public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
     * 状活动结束时间
     *
     * @mbggenerated
     */
    @NotNull(message="活动结束时间不能为空！")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activityEndTime; 
    
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPublishId() {
		return publishId;
	}
	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getRowAddTime() {
		return rowAddTime;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public Long getCommunity() {
		return community;
	}
	public void setCommunity(Long community) {
		this.community = community;
	}
	public String getSelectOption() {
		return selectOption;
	}
	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
    
}
