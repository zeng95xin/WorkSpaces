package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Report extends Model {
    /**
     * 自增id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 举报人id
     *
     * @mbggenerated
     */
    private Long buserId;

    /**
     * 内容id
     *
     * @mbggenerated
     */
    private Long contentId;

    /**
     * 内容评论id
     *
     * @mbggenerated
     */
    private Long contentRatingId;

    /**
     * 举报类型,1:爱分享，2：邻里帮，3：业主市集
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 举报理由
     *
     * @mbggenerated
     */
    private String reason;

    /**
     * 举报时间
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
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.id
     *
     * @return the value of n_report.id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "自增id")
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.id
     *
     * @param id the value for n_report.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.buser_id
     *
     * @return the value of n_report.buser_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "举报人id")
    public Long getBuserId() {
        return buserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.buser_id
     *
     * @param buserId the value for n_report.buser_id
     *
     * @mbggenerated
     */
    public void setBuserId(Long buserId) {
        this.buserId = buserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.content_id
     *
     * @return the value of n_report.content_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "内容id")
    public Long getContentId() {
        return contentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.content_id
     *
     * @param contentId the value for n_report.content_id
     *
     * @mbggenerated
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.content_rating_id
     *
     * @return the value of n_report.content_rating_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "内容评论id")
    public Long getContentRatingId() {
        return contentRatingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.content_rating_id
     *
     * @param contentRatingId the value for n_report.content_rating_id
     *
     * @mbggenerated
     */
    public void setContentRatingId(Long contentRatingId) {
        this.contentRatingId = contentRatingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.type
     *
     * @return the value of n_report.type
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "举报类型,1:爱分享，2：邻里帮，3：业主市集")
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.type
     *
     * @param type the value for n_report.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.reason
     *
     * @return the value of n_report.reason
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "举报理由")
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.reason
     *
     * @param reason the value for n_report.reason
     *
     * @mbggenerated
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.row_add_time
     *
     * @return the value of n_report.row_add_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "举报时间")
    public Date getRowAddTime() {
        return rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.row_add_time
     *
     * @param rowAddTime the value for n_report.row_add_time
     *
     * @mbggenerated
     */
    public void setRowAddTime(Date rowAddTime) {
        this.rowAddTime = rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_report.row_update_time
     *
     * @return the value of n_report.row_update_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "更新时间")
    public Date getRowUpdateTime() {
        return rowUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_report.row_update_time
     *
     * @param rowUpdateTime the value for n_report.row_update_time
     *
     * @mbggenerated
     */
    public void setRowUpdateTime(Date rowUpdateTime) {
        this.rowUpdateTime = rowUpdateTime;
    }
}