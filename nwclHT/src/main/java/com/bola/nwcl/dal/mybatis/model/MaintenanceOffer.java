package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class MaintenanceOffer extends Model {
    /**
     * 自增id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 维修单id
     *
     * @mbggenerated
     */
    private Long maintenanceId;

    /**
     * 维修项目名称
     *
     * @mbggenerated
     */
    private String projectName;

    /**
     * 维修项目价格
     *
     * @mbggenerated
     */
    private Integer projectPrice;

    /**
     * 是否付款,0：没有，1：付了
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 生成时间
     *
     * @mbggenerated
     */
    private Date rowAddTime;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date rowUpdateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_maintenance_offer.id
     *
     * @return the value of n_maintenance_offer.id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "自增id")
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_maintenance_offer.id
     *
     * @param id the value for n_maintenance_offer.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_maintenance_offer.maintenance_id
     *
     * @return the value of n_maintenance_offer.maintenance_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "维修单id")
    public Long getMaintenanceId() {
        return maintenanceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_maintenance_offer.maintenance_id
     *
     * @param maintenanceId the value for n_maintenance_offer.maintenance_id
     *
     * @mbggenerated
     */
    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_maintenance_offer.project_name
     *
     * @return the value of n_maintenance_offer.project_name
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "维修项目名称")
    public String getProjectName() {
        return projectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_maintenance_offer.project_name
     *
     * @param projectName the value for n_maintenance_offer.project_name
     *
     * @mbggenerated
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_maintenance_offer.project_price
     *
     * @return the value of n_maintenance_offer.project_price
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "维修项目价格")
    public Integer getProjectPrice() {
        return projectPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_maintenance_offer.project_price
     *
     * @param projectPrice the value for n_maintenance_offer.project_price
     *
     * @mbggenerated
     */
    public void setProjectPrice(Integer projectPrice) {
        this.projectPrice = projectPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_maintenance_offer.status
     *
     * @return the value of n_maintenance_offer.status
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "是否付款,0：没有，1：付了")
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_maintenance_offer.status
     *
     * @param status the value for n_maintenance_offer.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_maintenance_offer.row_add_time
     *
     * @return the value of n_maintenance_offer.row_add_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "生成时间")
    public Date getRowAddTime() {
        return rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_maintenance_offer.row_add_time
     *
     * @param rowAddTime the value for n_maintenance_offer.row_add_time
     *
     * @mbggenerated
     */
    public void setRowAddTime(Date rowAddTime) {
        this.rowAddTime = rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_maintenance_offer.row_update_time
     *
     * @return the value of n_maintenance_offer.row_update_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "修改时间")
    public Date getRowUpdateTime() {
        return rowUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_maintenance_offer.row_update_time
     *
     * @param rowUpdateTime the value for n_maintenance_offer.row_update_time
     *
     * @mbggenerated
     */
    public void setRowUpdateTime(Date rowUpdateTime) {
        this.rowUpdateTime = rowUpdateTime;
    }
}