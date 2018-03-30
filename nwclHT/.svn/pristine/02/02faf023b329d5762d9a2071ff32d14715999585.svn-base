package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.MaintenanceUserAllModel;
import com.bola.nwcl.api.model.repair.RepairMaintenanceModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.MaintenanceExample;

public interface MaintenanceMapper extends Mapper<Maintenance, MaintenanceExample, Long> {
	void insertAndGetId(Maintenance maintenance);
	
	List<MaintenanceUserAllModel> getUserAllMaintenance(Map<String, Object> condition);
	
	List<RepairMaintenanceModel> getRepairMaintenanceModel(Map<String, Object> condition);
	int getRepairMaintenanceModelCount(Map<String, Object> condition);
}