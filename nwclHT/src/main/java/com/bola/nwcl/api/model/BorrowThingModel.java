package com.bola.nwcl.api.model;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BorrowThingModel{
    private Long id;
    
    private Long borrowId;

    private Long thingId;

    private Date expectReturnTime;

    private Date returnTime;

    private Integer status;

    private Date rowAddTime;
    
    private String name;

    private Float compensationPrice;

    private String imgPath;
    
    private int num;
    
    @ApiModelProperty(value = "借用数量")
    public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@ApiModelProperty(value = "id")
	public Long getId() {
		return id;
	}

    @ApiModelProperty(value = "物品id")
	public Long getThingId() {
		return thingId;
	}
    
    @ApiModelProperty(value = "预计归还时间")
	public Date getExpectReturnTime() {
		return expectReturnTime;
	}

    @ApiModelProperty(value = "归还时间")
	public Date getReturnTime() {
		return returnTime;
	}

    @ApiModelProperty(value = "状态，0:未同意,1:同意未领取,2已领取,3:已归还,-1:取消")
	public Integer getStatus() {
		return status;
	}
    
    @ApiModelProperty(value = "借用时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

    @ApiModelProperty(value = "物品名称")
	public String getName() {
		return name;
	}

    @ApiModelProperty(value = "物品赔偿价格")
	public Float getCompensationPrice() {
		return compensationPrice;
	}

    @ApiModelProperty(value = "物品图片路径")
	public String getImgPath() {
		return imgPath;
	}
    
    @ApiModelProperty(value = "借用单id")
	public Long getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setThingId(Long thingId) {
		this.thingId = thingId;
	}

	public void setExpectReturnTime(Date expectReturnTime) {
		this.expectReturnTime = expectReturnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompensationPrice(Float compensationPrice) {
		this.compensationPrice = compensationPrice;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
    
}
