package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class LectureHallMessage extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 标题
	 * @mbggenerated
	 */
	private String title;
	/**
	 * 内容
	 * @mbggenerated
	 */
	private String content;
	/**
	 * 创建时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_message.id
	 * @return  the value of n_lecture_hall_message.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_message.id
	 * @param id  the value for n_lecture_hall_message.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_message.title
	 * @return  the value of n_lecture_hall_message.title
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_message.title
	 * @param title  the value for n_lecture_hall_message.title
	 * @mbggenerated
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_message.content
	 * @return  the value of n_lecture_hall_message.content
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "内容")
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_message.content
	 * @param content  the value for n_lecture_hall_message.content
	 * @mbggenerated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_message.row_add_time
	 * @return  the value of n_lecture_hall_message.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "创建时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_message.row_add_time
	 * @param rowAddTime  the value for n_lecture_hall_message.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_message.row_update_time
	 * @return  the value of n_lecture_hall_message.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_message.row_update_time
	 * @param rowUpdateTime  the value for n_lecture_hall_message.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}