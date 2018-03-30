package com.bola.nwcl.api.model;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.VisitorsStatistics;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetail;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class VisitorsStatisticsModel extends VisitorsStatistics{
	
	private List<VisitorsStatisticsDetail> details;

	@ApiModelProperty(value = "到访详情记录统计")
	public List<VisitorsStatisticsDetail> getDetails() {
		return details;
	}

	public void setDetails(List<VisitorsStatisticsDetail> details) {
		this.details = details;
	}
	
}
