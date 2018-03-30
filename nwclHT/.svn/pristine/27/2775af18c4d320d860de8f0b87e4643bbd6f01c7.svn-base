package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.UserWebmasterMessageModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.UserWebmasterMessage;
import com.bola.nwcl.dal.mybatis.model.UserWebmasterMessageExample;

public interface UserWebmasterMessageMapper extends Mapper<UserWebmasterMessage, UserWebmasterMessageExample, Long> {
	List<UserWebmasterMessageModel> getUserAllMsgWithNeitherUser(Map<String, Object> condition);
}