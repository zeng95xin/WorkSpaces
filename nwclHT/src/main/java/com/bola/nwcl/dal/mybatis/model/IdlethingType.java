package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class IdlethingType extends Model {
    /**
     * 自增id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 分类名
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 添加时间
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
     * This method returns the value of the database column n_idlething_type.id
     *
     * @return the value of n_idlething_type.id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "自增id")
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_idlething_type.id
     *
     * @param id the value for n_idlething_type.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_idlething_type.title
     *
     * @return the value of n_idlething_type.title
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "分类名")
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_idlething_type.title
     *
     * @param title the value for n_idlething_type.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_idlething_type.row_add_time
     *
     * @return the value of n_idlething_type.row_add_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "添加时间")
    public Date getRowAddTime() {
        return rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_idlething_type.row_add_time
     *
     * @param rowAddTime the value for n_idlething_type.row_add_time
     *
     * @mbggenerated
     */
    public void setRowAddTime(Date rowAddTime) {
        this.rowAddTime = rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_idlething_type.row_update_time
     *
     * @return the value of n_idlething_type.row_update_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "更新时间")
    public Date getRowUpdateTime() {
        return rowUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_idlething_type.row_update_time
     *
     * @param rowUpdateTime the value for n_idlething_type.row_update_time
     *
     * @mbggenerated
     */
    public void setRowUpdateTime(Date rowUpdateTime) {
        this.rowUpdateTime = rowUpdateTime;
    }
}