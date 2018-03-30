package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.repair.RepairHangModel;
import com.bola.nwcl.api.model.repair.RepairMaintenanceModel;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;

public interface RepairStaffManager {
	Page<RepairHangModel> getAllHang(int page, int rows, int type, long hangId);
	RepairHangModel getHangDetail(long id);
	
	void updateConfirmOrder(long hangId, long suserId, ServiceUser suser);
	void updatecancelHang(long hangId, long suserId, String reason, ServiceUser suser);
	void updateCompleteHang(HttpServletRequest request, long hangId, long suserId, String completeDescription, MultipartFile[] imgs, String saveDir, ServiceUser suser) throws Exception;
	
	
	
	Page<RepairMaintenanceModel> getAllMaintenance(int page, int rows, int type, long repair_main_id);
	RepairMaintenanceModel getMaintenanceDetail(long id);
	
	void updateConfirmMaintenance(long maintenanceId, long suserId);
	void updateProspectingMaintenance(HttpServletRequest request, long maintenanceId, long suserId, String prospectingDescription, MultipartFile[] imgs, String saveDir) throws Exception;
	
	void updateOfferMaintenance(long maintenanceId, long suserId, String project);
	void updateCancelMaintenance(long maintenanceId, long suserId, String reason);
	
	void updateCompleteMaintenance(long maintenanceId, long suserId);
	
	void updateOffer(long suserId, long id, String projectName, String projectPrice);
	
	
}
