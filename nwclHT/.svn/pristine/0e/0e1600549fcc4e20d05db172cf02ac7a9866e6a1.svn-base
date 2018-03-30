package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.MaintenanceModel;
import com.bola.nwcl.api.model.MaintenanceUserAllModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.MaintenanceExample;

public interface MaintenanceManager extends Manager<Maintenance, MaintenanceExample, Long>{
	void insertAndGetId(Maintenance maintenance);
	
	MaintenanceModel addMaintenanceByBuser(String saveDir, BizUser buser, HttpServletRequest request, Long repairMainId, String description, MultipartFile[] maintenanceImgs) throws Exception;
	void updatePayMaintenance(BizUser buser, long id);
	
	Page<MaintenanceUserAllModel> getUserAllMaintenance(int page, int rows, long buserId);

	DataGrid dataGrid(Maintenance maintenance, PageHelper ph,HttpServletRequest request);
	
	void updatePaySuccess(Maintenance m);
}
