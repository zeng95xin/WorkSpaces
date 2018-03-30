package com.bola.nwcl.api.model;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.dal.mybatis.model.MaintenanceOffer;
import com.bola.nwcl.dal.mybatis.model.MaintenanceProspectingImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceRating;
import com.wordnik.swagger.annotations.ApiModelProperty;


public class MaintenanceModel {
	private Long id;
	private String serial;
	private String description;
	private Long buserId;
	private Long repairMainId;
	private Long roomId;
	private Long manHourCost;
	private Long materialCost;
	private String repairManName;
	private String repairManHeadPortrait;
	private String repairManPhone;
	private String prospectingDescription;
	private List<MaintenanceImgModel> imgs;
	private MaintenanceRating maintenanceRating;
	private Integer status;
	private Date rowAddTime;
	
	private List<MaintenanceProspectingImg> prospectingImgs;
	private List<MaintenanceOffer> offers;
	
	@ApiModelProperty(value = "报修人id")
	public Long getBuserId() {
		return buserId;
	}
	@ApiModelProperty(value = "报修单情况描述")
	public String getDescription() {
		return description;
	}
	@ApiModelProperty(value = "报修单id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "报修单图片")
	public List<MaintenanceImgModel> getImgs() {
		return imgs;
	}
	
	@ApiModelProperty(value = "报修单评论")
	public MaintenanceRating getMaintenanceRating() {
		return maintenanceRating;
	}
	@ApiModelProperty(value = "报修单工时费")
	public Long getManHourCost() {
		return manHourCost;
	}
	@ApiModelProperty(value = "报修单材料费")
	public Long getMaterialCost() {
		return materialCost;
	}
	@ApiModelProperty(value = "报价")
	public List<MaintenanceOffer> getOffers() {
		return offers;
	}
	@ApiModelProperty(value = "勘探照片")
	public List<MaintenanceProspectingImg> getProspectingImgs() {
		return prospectingImgs;
	}
	@ApiModelProperty(value = "维修人id")
	public Long getRepairMainId() {
		return repairMainId;
	}
	@ApiModelProperty(value = "维修人员头像")
	public String getRepairManHeadPortrait() {
		return repairManHeadPortrait;
	}
	@ApiModelProperty(value = "维修人员姓名")
	public String getRepairManName() {
		return repairManName;
	}
	@ApiModelProperty(value = "维修人员电话")
	public String getRepairManPhone() {
		return repairManPhone;
	}
	@ApiModelProperty(value = "报修房间id")
	public Long getRoomId() {
		return roomId;
	}
	@ApiModelProperty(value = "报修单生成时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}
	@ApiModelProperty(value = "报修单编号")
	public String getSerial() {
		return serial;
	}
	@ApiModelProperty(value = "'状态，0,1:待处理,2345:处理中,-167:已完结,维修状态，0:待发布,1:人员指派,2师傅接单,3:现场勘探,4:工程报价,5:付款结算,6:师傅确认完成,7:验收评价,-1:订单取消'")
	public Integer getStatus() {
		return status;
	}
	@ApiModelProperty(value = "勘探描述")
	public String getProspectingDescription() {
		return prospectingDescription;
	}
	public void setProspectingDescription(String prospectingDescription) {
		this.prospectingDescription = prospectingDescription;
	}
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setImgs(List<MaintenanceImgModel> imgs) {
		this.imgs = imgs;
	}
	public void setMaintenanceRating(MaintenanceRating maintenanceRating) {
		this.maintenanceRating = maintenanceRating;
	}
	public void setManHourCost(Long manHourCost) {
		this.manHourCost = manHourCost;
	}
	public void setMaterialCost(Long materialCost) {
		this.materialCost = materialCost;
	}
	public void setOffers(List<MaintenanceOffer> offers) {
		this.offers = offers;
	}
	public void setProspectingImgs(List<MaintenanceProspectingImg> prospectingImgs) {
		this.prospectingImgs = prospectingImgs;
	}
	public void setRepairMainId(Long repairMainId) {
		this.repairMainId = repairMainId;
	}
	public void setRepairManHeadPortrait(String repairManHeadPortrait) {
		this.repairManHeadPortrait = repairManHeadPortrait;
	}
	public void setRepairManName(String repairManName) {
		this.repairManName = repairManName;
	}
	public void setRepairManPhone(String repairManPhone) {
		this.repairManPhone = repairManPhone;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
