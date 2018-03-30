package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.BizUserNotifyModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifyExample;

public interface NotifyMapper extends Mapper<Notify, NotifyExample, Long> {
	void insertAndGetId(Notify notify);
	List<BizUserNotifyModel> getUserAllNotify(Map<String, Object> condition);
	int getUserAllNotifyCount(long buserId);
	int getUserAllNotifyNotReadCount(long buserId);
}