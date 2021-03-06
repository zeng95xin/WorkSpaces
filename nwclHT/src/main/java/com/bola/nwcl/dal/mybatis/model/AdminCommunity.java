package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class AdminCommunity extends Model {
    /**
     * 自增id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 小区id
     *
     * @mbggenerated
     */
    private Long communityId;

    /**
     * 用户id
     *
     * @mbggenerated
     */
    private Long adminUserId;

    /**
     * 添加时间
     *
     * @mbggenerated
     */
    private Date rowAddTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_admin_community.id
     *
     * @return the value of n_admin_community.id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "自增id")
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_admin_community.id
     *
     * @param id the value for n_admin_community.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_admin_community.community_id
     *
     * @return the value of n_admin_community.community_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "小区id")
    public Long getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_admin_community.community_id
     *
     * @param communityId the value for n_admin_community.community_id
     *
     * @mbggenerated
     */
    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_admin_community.admin_user_id
     *
     * @return the value of n_admin_community.admin_user_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "用户id")
    public Long getAdminUserId() {
        return adminUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_admin_community.admin_user_id
     *
     * @param adminUserId the value for n_admin_community.admin_user_id
     *
     * @mbggenerated
     */
    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_admin_community.row_add_time
     *
     * @return the value of n_admin_community.row_add_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "添加时间")
    public Date getRowAddTime() {
        return rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_admin_community.row_add_time
     *
     * @param rowAddTime the value for n_admin_community.row_add_time
     *
     * @mbggenerated
     */
    public void setRowAddTime(Date rowAddTime) {
        this.rowAddTime = rowAddTime;
    }
}