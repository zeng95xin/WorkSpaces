package com.bola.nwcl.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.market.OwnersMarketRatingModel;
import com.bola.nwcl.biz.OwnersMarketRatingManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketMapper;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketRatingMapper;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRating;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRatingExample;

@Service
public class OwnersMarketRatingManagerImpl  extends ManagerImpl<OwnersMarketRating, OwnersMarketRatingExample, Long> implements OwnersMarketRatingManager{
	
	private OwnersMarketRatingMapper ownersMarketRatingMapper;
	
	@Autowired
	private OwnersMarketMapper ownersMarketMapper;
	
	@Autowired
	public OwnersMarketRatingManagerImpl(OwnersMarketRatingMapper ownersMarketRatingMapper) {
		this.mapper = ownersMarketRatingMapper;
		this.ownersMarketRatingMapper = ownersMarketRatingMapper;
	}

	@Override
	public void insertAndGetId(OwnersMarketRating ownersMarketRating) {
		ownersMarketRatingMapper.insertAndGetId(ownersMarketRating);
	}

	@Override
	public OwnersMarketRatingModel getOneRatingModel(long ratingId, long buserId) {
		Map<String, Object> condition = new HashMap<String, Object>(2);
		condition.put("buserId", buserId);
		condition.put("ratingId", ratingId);
		List<OwnersMarketRatingModel> ratings = ownersMarketRatingMapper.getOwnersMarketRating(condition);
		if(ratings == null || ratings.size() < 1){
			return null;
		}
		return ratings.get(0);
	}

	@Override
	public Page<OwnersMarketRatingModel> getPageRatingModel(long id, long buserId, int page, int rows) {
		if(ownersMarketMapper.selectByPrimaryKey(id) == null){
			throw new BusinessValidateException("该信息不存在");
		}
		Map<String, Object> condition = new HashMap<String, Object>(5);
		condition.put("buserId", buserId);
		condition.put("id", id);
		condition.put("orderByClause", " row_add_time desc ");
		PageRequest pager = new PageRequest(page - 1, rows);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		List<OwnersMarketRatingModel> ratings = ownersMarketRatingMapper.getOwnersMarketRating(condition);
		OwnersMarketRatingExample example_rating = new OwnersMarketRatingExample();
		example_rating.or().andOwnersMarketIdEqualTo(id);
		int total = ownersMarketRatingMapper.countByExample(example_rating);
		Page<OwnersMarketRatingModel> pageData = new PageImpl<OwnersMarketRatingModel>(ratings, pager, total);
		return pageData;
	}

}
