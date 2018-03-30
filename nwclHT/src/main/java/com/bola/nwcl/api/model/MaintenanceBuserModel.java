package com.bola.nwcl.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;



public class MaintenanceBuserModel {
	
	private boolean canMaintenance;
	private long maintenanceId;
	private int status;
	
	@ApiModelProperty(value = "订单状态")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@ApiModelProperty(value = "是否可以添加报修单,true是，false,不是")
	public boolean isCanMaintenance() {
		return canMaintenance;
	}
	public void setCanMaintenance(boolean canMaintenance) {
		this.canMaintenance = canMaintenance;
	}
	@ApiModelProperty(value = "当前进行中的报修单id")
	public long getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	
}
