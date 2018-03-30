package com.bola.nwcl.api.model;

import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class MaintenanceUserAllModel extends Maintenance{
	private String repairManName;
	private String repairManHeadPortrait;
	private String repairManPhone;
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
	public void setRepairManHeadPortrait(String repairManHeadPortrait) {
		this.repairManHeadPortrait = repairManHeadPortrait;
	}
	public void setRepairManName(String repairManName) {
		this.repairManName = repairManName;
	}
	public void setRepairManPhone(String repairManPhone) {
		this.repairManPhone = repairManPhone;
	}
	
}
