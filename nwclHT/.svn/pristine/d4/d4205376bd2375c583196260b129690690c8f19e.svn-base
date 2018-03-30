package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.security.ScanResultModel;
import com.bola.nwcl.api.model.security.SercurityArticleReleaseModel;
import com.bola.nwcl.api.model.security.SercurityVisitorsModel;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;

public interface SecurityStaffManager {
	ScanResultModel updateScan(String type, String serial);
	Page<SercurityArticleReleaseModel> getAllArticleRelease(int page, int rows, int type);
	Page<SercurityVisitorsModel> getAllVisitors(int page, int rows, int type);
	SercurityVisitorsModel getVisitorsDetail(long id);
	SercurityArticleReleaseModel getArticleReleaseDetail(long id);
	
	void updateRelease(String id, String type, ServiceUser suser);
	
}
