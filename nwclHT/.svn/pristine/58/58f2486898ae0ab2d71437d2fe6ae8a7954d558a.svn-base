package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.market.OwnersMarketModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.OwnersMarket;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketExample;

public interface OwnersMarketMapper extends Mapper<OwnersMarket, OwnersMarketExample, Long> {
	void insertAndGetId(OwnersMarket ownersMarket);
	
	List<OwnersMarketModel> getAllOwnersMarket(Map<String, Object> condition);
}