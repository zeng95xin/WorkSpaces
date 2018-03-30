package com.bola.nwcl.biz;

import java.util.Date;
import java.util.Map;

import com.bola.nwcl.api.model.VisitorsModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Visitors;
import com.bola.nwcl.dal.mybatis.model.VisitorsExample;

public interface VisitorsManager extends Manager<Visitors, VisitorsExample, Long>{
	
	VisitorsModel addVisitorsByBuser(BizUser buser, String[] namePhones, Date expectArriveTime, Date expectLeaveTime) throws Exception;
	VisitorsModel updateVisitorsByBuser(Visitors visitors, BizUser buser, String[] namePhones, Date expectArriveTime, Date expectLeaveTime) throws Exception;
	
	void deleteByBuser(Visitors v);
	
	
	void insertAndGetId(Visitors visitors);
	
	
	DataGrid dataGridVisitorPassportRecord(Map<String,Object> params);
	int dataGridVisitorPassportRecordCount(Map<String,Object> params);
	
	void deleteVisitorPassportRecord(Long id);
	void deleteBatchVisitorPassportRecord(String ids);
}
