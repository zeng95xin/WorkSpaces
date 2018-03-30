package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.UserOpinionModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.UserOpinion;
import com.bola.nwcl.dal.mybatis.model.UserOpinionExample;

public interface UserOpinionMapper extends Mapper<UserOpinion, UserOpinionExample, Long> {
	List<UserOpinionModel> getUserAllMsg(Map<String, Object> condition);
	void insertAndGetId(UserOpinion userOpinion);
}