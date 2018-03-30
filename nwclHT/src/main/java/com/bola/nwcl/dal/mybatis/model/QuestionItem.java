package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class QuestionItem extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 问题id
	 * @mbggenerated
	 */
	private Long questionId;
	/**
	 * 问题选项类容,如果该问题是填空题，则为默认内容
	 * @mbggenerated
	 */
	private String content;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_question_item.id
	 * @return  the value of n_question_item.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_question_item.id
	 * @param id  the value for n_question_item.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_question_item.question_id
	 * @return  the value of n_question_item.question_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "问题id")
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_question_item.question_id
	 * @param questionId  the value for n_question_item.question_id
	 * @mbggenerated
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_question_item.content
	 * @return  the value of n_question_item.content
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "问题选项类容,如果该问题是填空题，则为默认内容")
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_question_item.content
	 * @param content  the value for n_question_item.content
	 * @mbggenerated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_question_item.row_add_time
	 * @return  the value of n_question_item.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_question_item.row_add_time
	 * @param rowAddTime  the value for n_question_item.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_question_item.row_update_time
	 * @return  the value of n_question_item.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_question_item.row_update_time
	 * @param rowUpdateTime  the value for n_question_item.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}