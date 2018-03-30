package com.bola.nwcl.api.model.security;

import com.wordnik.swagger.annotations.ApiModelProperty;


public class ScanResultModel {
	
	private SercurityVisitorsModel sercurityVisitorsModel;
	
	private SercurityArticleReleaseModel sercurityArticleReleaseModel;

	@ApiModelProperty(value = "访客到访详情")
	public SercurityVisitorsModel getSercurityVisitorsModel() {
		return sercurityVisitorsModel;
	}

	public void setSercurityVisitorsModel(
			SercurityVisitorsModel sercurityVisitorsModel) {
		this.sercurityVisitorsModel = sercurityVisitorsModel;
	}

	@ApiModelProperty(value = "放行条详情")
	public SercurityArticleReleaseModel getSercurityArticleReleaseModel() {
		return sercurityArticleReleaseModel;
	}

	public void setSercurityArticleReleaseModel(
			SercurityArticleReleaseModel sercurityArticleReleaseModel) {
		this.sercurityArticleReleaseModel = sercurityArticleReleaseModel;
	}
	
	
}
