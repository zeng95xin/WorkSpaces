package com.bola.nwcl.dal.model.page;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wordnik.swagger.annotations.ApiModelProperty;


@SuppressWarnings("rawtypes")
public class CommodityPage extends Pagination {
	/**
	 * 产品分类ID
	 */
	private Long commodityTypeId;
	/**
	 * 卖家的名字、关键字、产品名、内容等
	 */
	private String searchContent;
	
	@ApiModelProperty(value = "产品分类ID")
	public Long getCommodityTypeId() {
		return commodityTypeId;
	}
	public void setCommodityTypeId(Long commodityTypeId) {
		this.commodityTypeId = commodityTypeId;
	}
	
	@ApiModelProperty(value = "卖家的名字、关键字、产品名、内容等")
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
