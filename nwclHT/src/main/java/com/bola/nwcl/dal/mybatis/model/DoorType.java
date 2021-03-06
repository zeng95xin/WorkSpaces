package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class DoorType extends Model {
    /**
     * 自增Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 添加时间
     *
     * @mbggenerated
     */
    private Date rowAddTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_door_type.id
     *
     * @return the value of n_door_type.id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "自增Id")
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_door_type.id
     *
     * @param id the value for n_door_type.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_door_type.name
     *
     * @return the value of n_door_type.name
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "名称")
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_door_type.name
     *
     * @param name the value for n_door_type.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_door_type.row_add_time
     *
     * @return the value of n_door_type.row_add_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "添加时间")
    public Date getRowAddTime() {
        return rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_door_type.row_add_time
     *
     * @param rowAddTime the value for n_door_type.row_add_time
     *
     * @mbggenerated
     */
    public void setRowAddTime(Date rowAddTime) {
        this.rowAddTime = rowAddTime;
    }
}