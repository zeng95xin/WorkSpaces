package com.bola.nwcl.api.model.keeper;

import java.util.Date;
import java.util.List;

import com.bola.nwcl.dal.mybatis.model.HangPictureImg;
import com.wordnik.swagger.annotations.ApiModelProperty;


public class KeeperHangModel {
	private long id;
	private String serial;
	private String roomStr;
	private String name;
	private String nickname;
	private String description;
	private String phone;
	
	private String repairManName;
	private String repairManNickName;
	private String repairManHeadPortrait;
	private String repairManPhone;
	
	private String ratingContent;
	private Integer ratingLevel;
	
	private int status;
	
	private Date assignTime;
	
	private List<HangPictureImg> imgs;
	
	@ApiModelProperty(value = "状态，0,1:待处理,234:处理中,56:已完结,维修状态，0:待发布,1:人员指派,2师傅接单,3:现场勘探,4:工程报价,5:付款结算,6:验收评价,-1:订单取消")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@ApiModelProperty(value = "派单时间")
	public Date getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}

	@ApiModelProperty(value = "id")
	public long getId() {
		return id;
	}

	@ApiModelProperty(value = "编号")
	public String getSerial() {
		return serial;
	}

	@ApiModelProperty(value = "申请人房间")
	public String getRoomStr() {
		return roomStr;
	}

	@ApiModelProperty(value = "申请人姓名")
	public String getName() {
		return name;
	}

	@ApiModelProperty(value = "申请人昵称")
	public String getNickname() {
		return nickname;
	}

	@ApiModelProperty(value = "挂画描述")
	public String getDescription() {
		return description;
	}

	@ApiModelProperty(value = "申请人电话")
	public String getPhone() {
		return phone;
	}

	@ApiModelProperty(value = "挂画人姓名")
	public String getRepairManName() {
		return repairManName;
	}

	@ApiModelProperty(value = "挂画人昵称")
	public String getRepairManNickName() {
		return repairManNickName;
	}

	@ApiModelProperty(value = "挂画人头像")
	public String getRepairManHeadPortrait() {
		return repairManHeadPortrait;
	}

	@ApiModelProperty(value = "挂画人电话")
	public String getRepairManPhone() {
		return repairManPhone;
	}

	@ApiModelProperty(value = "评论内容")
	public String getRatingContent() {
		return ratingContent;
	}

	@ApiModelProperty(value = "评论等级")
	public Integer getRatingLevel() {
		return ratingLevel;
	}

	@ApiModelProperty(value = "挂画图片")
	public List<HangPictureImg> getImgs() {
		return imgs;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setRoomStr(String roomStr) {
		this.roomStr = roomStr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRepairManName(String repairManName) {
		this.repairManName = repairManName;
	}

	public void setRepairManNickName(String repairManNickName) {
		this.repairManNickName = repairManNickName;
	}

	public void setRepairManHeadPortrait(String repairManHeadPortrait) {
		this.repairManHeadPortrait = repairManHeadPortrait;
	}

	public void setRepairManPhone(String repairManPhone) {
		this.repairManPhone = repairManPhone;
	}

	public void setRatingContent(String ratingContent) {
		this.ratingContent = ratingContent;
	}

	public void setRatingLevel(Integer ratingLevel) {
		this.ratingLevel = ratingLevel;
	}

	public void setImgs(List<HangPictureImg> imgs) {
		this.imgs = imgs;
	}
	
}
