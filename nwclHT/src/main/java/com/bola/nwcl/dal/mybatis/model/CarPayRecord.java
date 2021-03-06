package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class CarPayRecord extends Model {
    /**
     * 自增id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 车辆id
     *
     * @mbggenerated
     */
    private Long carId;

    /**
     * 用户id
     *
     * @mbggenerated
     */
    private Long buserId;

    /**
     * 车牌号
     *
     * @mbggenerated
     */
    private String cardNo;

    /**
     * 订单号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 停车场卡号
     *
     * @mbggenerated
     */
    private String parkCardNo;

    /**
     * 金额
     *
     * @mbggenerated
     */
    private Double money;

    /**
     * 付费时间
     *
     * @mbggenerated
     */
    private Date payTime;

    /**
     * 支付渠道（alipay--支付宝，wx--微信支付）
     *
     * @mbggenerated
     */
    private String payChannel;

    /**
     * 到期时间
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String note;

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
     * This method returns the value of the database column n_car_pay_record.id
     *
     * @return the value of n_car_pay_record.id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "自增id")
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.id
     *
     * @param id the value for n_car_pay_record.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.car_id
     *
     * @return the value of n_car_pay_record.car_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "车辆id")
    public Long getCarId() {
        return carId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.car_id
     *
     * @param carId the value for n_car_pay_record.car_id
     *
     * @mbggenerated
     */
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.buser_id
     *
     * @return the value of n_car_pay_record.buser_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "用户id")
    public Long getBuserId() {
        return buserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.buser_id
     *
     * @param buserId the value for n_car_pay_record.buser_id
     *
     * @mbggenerated
     */
    public void setBuserId(Long buserId) {
        this.buserId = buserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.card_no
     *
     * @return the value of n_car_pay_record.card_no
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "车牌号")
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.card_no
     *
     * @param cardNo the value for n_car_pay_record.card_no
     *
     * @mbggenerated
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.order_no
     *
     * @return the value of n_car_pay_record.order_no
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "订单号")
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.order_no
     *
     * @param orderNo the value for n_car_pay_record.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.park_card_no
     *
     * @return the value of n_car_pay_record.park_card_no
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "停车场卡号")
    public String getParkCardNo() {
        return parkCardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.park_card_no
     *
     * @param parkCardNo the value for n_car_pay_record.park_card_no
     *
     * @mbggenerated
     */
    public void setParkCardNo(String parkCardNo) {
        this.parkCardNo = parkCardNo == null ? null : parkCardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.money
     *
     * @return the value of n_car_pay_record.money
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "金额")
    public Double getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.money
     *
     * @param money the value for n_car_pay_record.money
     *
     * @mbggenerated
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.pay_time
     *
     * @return the value of n_car_pay_record.pay_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "付费时间")
    public Date getPayTime() {
        return payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.pay_time
     *
     * @param payTime the value for n_car_pay_record.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.pay_channel
     *
     * @return the value of n_car_pay_record.pay_channel
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "支付渠道（alipay--支付宝，wx--微信支付）")
    public String getPayChannel() {
        return payChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.pay_channel
     *
     * @param payChannel the value for n_car_pay_record.pay_channel
     *
     * @mbggenerated
     */
    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.end_time
     *
     * @return the value of n_car_pay_record.end_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "到期时间")
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.end_time
     *
     * @param endTime the value for n_car_pay_record.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.note
     *
     * @return the value of n_car_pay_record.note
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "备注")
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.note
     *
     * @param note the value for n_car_pay_record.note
     *
     * @mbggenerated
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.row_add_time
     *
     * @return the value of n_car_pay_record.row_add_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "添加时间")
    public Date getRowAddTime() {
        return rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.row_add_time
     *
     * @param rowAddTime the value for n_car_pay_record.row_add_time
     *
     * @mbggenerated
     */
    public void setRowAddTime(Date rowAddTime) {
        this.rowAddTime = rowAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column n_car_pay_record.row_update_time
     *
     * @return the value of n_car_pay_record.row_update_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "更新时间")
    public Date getRowUpdateTime() {
        return rowUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column n_car_pay_record.row_update_time
     *
     * @param rowUpdateTime the value for n_car_pay_record.row_update_time
     *
     * @mbggenerated
     */
    public void setRowUpdateTime(Date rowUpdateTime) {
        this.rowUpdateTime = rowUpdateTime;
    }
}