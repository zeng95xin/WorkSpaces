package com.bola.nwcl.dal.mybatis.model;

import java.util.Date;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class PropertyPayMent extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 客户姓名
	 * @mbggenerated
	 */
	private String customerName;
	/**
	 * 物业单元编号
	 * @mbggenerated
	 */
	private String unitNo;
	/**
	 * 收费单id
	 * @mbggenerated
	 */
	private Long receivableId;
	/**
	 * 收款项目名称
	 * @mbggenerated
	 */
	private String receivableDispname;
	/**
	 * 应收日期
	 * @mbggenerated
	 */
	private Date receivableDate;
	/**
	 * 应收金额
	 * @mbggenerated
	 */
	private Integer receivableAmount;
	/**
	 * 实收金额
	 * @mbggenerated
	 */
	private Integer currAmount;
	/**
	 * 缴费状态：是否已缴清（0-未缴清;1-缴清）
	 * @mbggenerated
	 */
	private Integer receivableStatus;
	/**
	 * 房间id
	 * @mbggenerated
	 */
	private Long roomId;
	/**
	 * @mbggenerated
	 */
	private Date propertyDate;
	/**
	 * 生成时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.id
	 * @return  the value of n_property_pay_ment.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.id
	 * @param id  the value for n_property_pay_ment.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.customer_name
	 * @return  the value of n_property_pay_ment.customer_name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "客户姓名")
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.customer_name
	 * @param customerName  the value for n_property_pay_ment.customer_name
	 * @mbggenerated
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.unit_no
	 * @return  the value of n_property_pay_ment.unit_no
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "物业单元编号")
	public String getUnitNo() {
		return unitNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.unit_no
	 * @param unitNo  the value for n_property_pay_ment.unit_no
	 * @mbggenerated
	 */
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo == null ? null : unitNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.receivable_id
	 * @return  the value of n_property_pay_ment.receivable_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "收费单id")
	public Long getReceivableId() {
		return receivableId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.receivable_id
	 * @param receivableId  the value for n_property_pay_ment.receivable_id
	 * @mbggenerated
	 */
	public void setReceivableId(Long receivableId) {
		this.receivableId = receivableId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.receivable_dispName
	 * @return  the value of n_property_pay_ment.receivable_dispName
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "收款项目名称")
	public String getReceivableDispname() {
		return receivableDispname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.receivable_dispName
	 * @param receivableDispname  the value for n_property_pay_ment.receivable_dispName
	 * @mbggenerated
	 */
	public void setReceivableDispname(String receivableDispname) {
		this.receivableDispname = receivableDispname == null ? null
				: receivableDispname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.receivable_date
	 * @return  the value of n_property_pay_ment.receivable_date
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "应收日期")
	public Date getReceivableDate() {
		return receivableDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.receivable_date
	 * @param receivableDate  the value for n_property_pay_ment.receivable_date
	 * @mbggenerated
	 */
	public void setReceivableDate(Date receivableDate) {
		this.receivableDate = receivableDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.receivable_amount
	 * @return  the value of n_property_pay_ment.receivable_amount
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "应收金额")
	public Integer getReceivableAmount() {
		return receivableAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.receivable_amount
	 * @param receivableAmount  the value for n_property_pay_ment.receivable_amount
	 * @mbggenerated
	 */
	public void setReceivableAmount(Integer receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.curr_amount
	 * @return  the value of n_property_pay_ment.curr_amount
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "实收金额")
	public Integer getCurrAmount() {
		return currAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.curr_amount
	 * @param currAmount  the value for n_property_pay_ment.curr_amount
	 * @mbggenerated
	 */
	public void setCurrAmount(Integer currAmount) {
		this.currAmount = currAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.receivable_status
	 * @return  the value of n_property_pay_ment.receivable_status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "缴费状态：是否已缴清（0-未缴清;1-缴清）")
	public Integer getReceivableStatus() {
		return receivableStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.receivable_status
	 * @param receivableStatus  the value for n_property_pay_ment.receivable_status
	 * @mbggenerated
	 */
	public void setReceivableStatus(Integer receivableStatus) {
		this.receivableStatus = receivableStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.room_id
	 * @return  the value of n_property_pay_ment.room_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "房间id")
	public Long getRoomId() {
		return roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.room_id
	 * @param roomId  the value for n_property_pay_ment.room_id
	 * @mbggenerated
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.property_date
	 * @return  the value of n_property_pay_ment.property_date
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "")
	public Date getPropertyDate() {
		return propertyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.property_date
	 * @param propertyDate  the value for n_property_pay_ment.property_date
	 * @mbggenerated
	 */
	public void setPropertyDate(Date propertyDate) {
		this.propertyDate = propertyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.row_add_time
	 * @return  the value of n_property_pay_ment.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "生成时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.row_add_time
	 * @param rowAddTime  the value for n_property_pay_ment.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_property_pay_ment.row_update_time
	 * @return  the value of n_property_pay_ment.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_property_pay_ment.row_update_time
	 * @param rowUpdateTime  the value for n_property_pay_ment.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}