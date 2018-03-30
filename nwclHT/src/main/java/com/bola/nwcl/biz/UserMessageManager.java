package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.UserMessageModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.UserMessage;
import com.bola.nwcl.dal.mybatis.model.UserMessageExample;

public interface UserMessageManager extends Manager<UserMessage, UserMessageExample, Long>{
	Page<UserMessageModel> getUserAllMsg(long buserId, int page, int rows);
}
