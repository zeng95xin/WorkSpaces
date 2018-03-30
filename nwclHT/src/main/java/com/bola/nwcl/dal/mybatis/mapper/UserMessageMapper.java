package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.UserMessageModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.UserMessage;
import com.bola.nwcl.dal.mybatis.model.UserMessageExample;

public interface UserMessageMapper extends Mapper<UserMessage, UserMessageExample, Long> {
	List<UserMessageModel> getUserAllMsg(Map<String, Object> condition);
}