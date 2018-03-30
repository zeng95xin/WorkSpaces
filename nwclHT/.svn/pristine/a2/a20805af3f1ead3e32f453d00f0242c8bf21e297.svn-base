package com.bola.nwcl.api.model.repair;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.dal.mybatis.model.HangPictureCompleteImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureImg;
import com.wordnik.swagger.annotations.ApiModelProperty;


public class RepairHangModel {
	private long id;
	private String serial;
	private String roomStr;
	private String name;
	private String nickname;
	private String description;
	private String completeDescription;
	private String phone;
	private String reason;
	
	private String ratingContent;
	private Integer ratingLevel;
	
	private int status;
	
	private Date rowAddTime;
	
	private List<HangPictureImg> imgs;
	private List<HangPictureCompleteImg> completeImgs;
	
	@ApiModelProperty(value = "完成后拍个照片的描述")
	public String getCompleteDescription() {
		return completeDescription;
	}

	@ApiModelProperty(value = "完成后拍的照片")
	public List<HangPictureCompleteImg> getCompleteImgs() {
		return completeImgs;
	}

	@ApiModelProperty(value = "挂画描述")
	public String getDescription() {
		return description;
	}

	@ApiModelProperty(value = "id")
	public long getId() {
		return id;
	}

	@ApiModelProperty(value = "挂画图片")
	public List<HangPictureImg> getImgs() {
		return imgs;
	}

	@ApiModelProperty(value = "申请人姓名")
	public String getName() {
		return name;
	}

	@ApiModelProperty(value = "申请人昵称")
	public String getNickname() {
		return nickname;
	}

	@ApiModelProperty(value = "申请人电话")
	public String getPhone() {
		return phone;
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

	@ApiModelProperty(value = "申请人房间")
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

	@ApiModelProperty(value = "状态，0,1:待处理,234:处理中,56:已完结,维修状态，0:待发布,1:人员指派,2师傅接单,3:现场勘探,4:工程报价,5:付款结算,6:验收评价,-1:订单取消")
	public int getStatus() {
		return status;
	}

	public void setCompleteDescription(String completeDescription) {
		this.completeDescription = completeDescription;
	}
	
	public void setCompleteImgs(List<HangPictureCompleteImg> completeImgs) {
		this.completeImgs = completeImgs;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setImgs(List<HangPictureImg> imgs) {
		this.imgs = imgs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
