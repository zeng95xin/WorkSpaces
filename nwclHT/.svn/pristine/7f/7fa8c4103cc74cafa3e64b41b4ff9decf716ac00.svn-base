package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.neighbor.NeighborHelpModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.NeighborHelp;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpExample;
import com.bola.nwcl.web.model.NeighborHelpWebModel;

public interface NeighborHelpManager extends Manager<NeighborHelp, NeighborHelpExample, Long>{
	void insertAndGetId(NeighborHelp neighborHelp);
	void deleteByKeeper(HttpServletRequest request, String saveDir, Long id);
	
	void addNeighborHelp(String saveDir, long buserId, HttpServletRequest request, String title,
			String content, String reward, MultipartFile[] imgs) throws Exception;
	
	Page<NeighborHelpModel> getAllNeighborHelp(long buserId, int page, int rows, String isGetCurrentUser);
	NeighborHelpModel updateGetNeighborDetail(long id, long buserId, int messageCount);
	void deleteNeighborHelp(long id, long buserId);
	void updateZanNeighborHelp(long id, long buserId);
	void updateCancelZanNeighborHelp(long id, long buserId);
	
	void updateReportNeighborHelp(long id, long buserId);
	
	void edit(HttpServletRequest request,NeighborHelpWebModel neighborHelpWebModel,@RequestParam("file") MultipartFile[] file) throws Exception;
	void delete(Long id) throws Exception;
}
