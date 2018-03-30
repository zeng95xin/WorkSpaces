package com.bola.nwcl.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.keeper.KeeperUserModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;
import com.bola.nwcl.web.model.EmployeeModel;

public interface ServiceUserManager extends Manager<ServiceUser, ServiceUserExample, Long>{
	
	List<KeeperUserModel> getKeeperModel(List<Long> ids);
	
	
	
	DataGrid dataGrid(ServiceUser suser, PageHelper ph,HttpServletRequest request);
	
	
	void addServiceUserByWeb(HttpServletRequest request, String saveDir, EmployeeModel em, MultipartFile img, String types) throws Exception;
	
	void updateServiceUserByWeb(HttpServletRequest request, String saveDir, EmployeeModel em, MultipartFile img, String types) throws Exception;
	
	void deleteServiceUserByWeb(Long id);
	
}
