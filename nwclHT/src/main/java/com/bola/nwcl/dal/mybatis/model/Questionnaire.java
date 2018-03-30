package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Questionnaire extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 问卷分类id
	 * @mbggenerated
	 */
	private Long questionnaireClassifyId;
	/**
	 * 标题
	 * @mbggenerated
	 */
	private String title;
	/**
	 * 发布人id
	 * @mbggenerated
	 */
	private Long publishPeopleId;
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
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_questionnaire.id
	 * @return  the value of n_questionnaire.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_questionnaire.id
	 * @param id  the value for n_questionnaire.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_questionnaire.questionnaire_classify_id
	 * @return  the value of n_questionnaire.questionnaire_classify_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "问卷分类id")
	public Long getQuestionnaireClassifyId() {
		return questionnaireClassifyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_questionnaire.questionnaire_classify_id
	 * @param questionnaireClassifyId  the value for n_questionnaire.questionnaire_classify_id
	 * @mbggenerated
	 */
	public void setQuestionnaireClassifyId(Long questionnaireClassifyId) {
		this.questionnaireClassifyId = questionnaireClassifyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_questionnaire.title
	 * @return  the value of n_questionnaire.title
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "标题")
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_questionnaire.title
	 * @param title  the value for n_questionnaire.title
	 * @mbggenerated
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_questionnaire.publish_people_id
	 * @return  the value of n_questionnaire.publish_people_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "发布人id")
	public Long getPublishPeopleId() {
		return publishPeopleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_questionnaire.publish_people_id
	 * @param publishPeopleId  the value for n_questionnaire.publish_people_id
	 * @mbggenerated
	 */
	public void setPublishPeopleId(Long publishPeopleId) {
		this.publishPeopleId = publishPeopleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_questionnaire.row_add_time
	 * @return  the value of n_questionnaire.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_questionnaire.row_add_time
	 * @param rowAddTime  the value for n_questionnaire.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_questionnaire.row_update_time
	 * @return  the value of n_questionnaire.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_questionnaire.row_update_time
	 * @param rowUpdateTime  the value for n_questionnaire.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}