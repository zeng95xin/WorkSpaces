package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.indexes.FridendsIndexModel;
import com.bola.nwcl.api.model.likeshare.LikeShareSimpleModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.LikeShare;
import com.bola.nwcl.dal.mybatis.model.LikeShareExample;

public interface LikeShareMapper extends Mapper<LikeShare, LikeShareExample, Long> {
	void insertAndGetId(LikeShare likeShare);
	List<FridendsIndexModel> getFriendsIndexInfo(Map<String, Object> condition);
	
	
	List<LikeShareSimpleModel> getAllLikeShareByUser(Map<String, Object> condition);
}