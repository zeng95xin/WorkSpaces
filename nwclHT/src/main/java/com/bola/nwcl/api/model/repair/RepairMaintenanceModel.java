package com.bola.nwcl.api.model.repair;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.dal.mybatis.model.MaintenanceImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOffer;
import com.bola.nwcl.dal.mybatis.model.MaintenanceProspectingImg;
import com.wordnik.swagger.annotations.ApiModelProperty;


public class RepairMaintenanceModel {
	private long id;
	private String serial;
	private String roomStr;
	private String name;
	private String nickname;
	private String description;
	private String prospectingDescription;
	private String phone;
	private String reason;
	
	private String ratingContent;
	private Integer ratingLevel;
	
	private int status;
	
	private Date rowAddTime;
	
	private List<MaintenanceImg> imgs;
	private List<MaintenanceProspectingImg> prospectingImgs;
	private List<MaintenanceOffer> offers;
	
	
	@ApiModelProperty(value = "报修事项")
	public String getDescription() {
		return description;
	}

	@ApiModelProperty(value = "id")
	public long getId() {
		return id;
	}

	@ApiModelProperty(value = "报修图片")
	public List<MaintenanceImg> getImgs() {
		return imgs;
	}

	@ApiModelProperty(value = "报修人姓名")
	public String getName() {
		return name;
	}

	@ApiModelProperty(value = "报修人昵称")
	public String getNickname() {
		return nickname;
	}

	@ApiModelProperty(value = "报价")
	public List<MaintenanceOffer> getOffers() {
		return offers;
	}

	@ApiModelProperty(value = "报修人电话")
	public String getPhone() {
		return phone;
	}

	@ApiModelProperty(value = "勘探描述")
	public String getProspectingDescription() {
		return prospectingDescription;
	}

	@ApiModelProperty(value = "勘探照片")
	public List<MaintenanceProspectingImg> getProspectingImgs() {
		return prospectingImgs;
	}

	@ApiModelProperty(value = "评论内容")
	public String getRatingContent() {
		return ratingContent;
	}

	@ApiModelProperty(value = "评论等级")
	public Integer getRatingLevel() {
		return ratingLevel;
	}

	@ApiModelProperty(value = "取消原因")
	public String getReason() {
		return reason;
	}

	@ApiModelProperty(value = "报修人房间")
	public String getRoomStr() {
		return roomStr;
	}

	@ApiModelProperty(value = "报修时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	@ApiModelProperty(value = "编号")
	public String getSerial() {
		return serial;
	}

	@ApiModelProperty(value = "'状态，0,1:待处理,2345:处理中,-167:已完结,维修状态，0:待发布,1:人员指派,2师傅接单,3:现场勘探,4:工程报价,5:付款结算,6:师傅确认完成,7:验收评价,-1:订单取消'")
	public int getStatus() {
		return status;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setImgs(List<MaintenanceImg> imgs) {
		this.imgs = imgs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOffers(List<MaintenanceOffer> offers) {
		this.offers = offers;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setProspectingDescription(String prospectingDescription) {
		this.prospectingDescription = prospectingDescription;
	}

	public void setProspectingImgs(List<MaintenanceProspectingImg> prospectingImgs) {
		this.prospectingImgs = prospectingImgs;
	}

	public void setRatingContent(String ratingContent) {
		this.ratingContent = ratingContent;
	}

	public void setRatingLevel(Integer ratingLevel) {
		this.ratingLevel = ratingLevel;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setRoomStr(String roomStr) {
		this.roomStr = roomStr;
	}

	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
