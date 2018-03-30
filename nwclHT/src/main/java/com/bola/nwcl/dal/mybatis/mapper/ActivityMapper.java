package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.indexes.ServiceAddIndexModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Activity;
import com.bola.nwcl.dal.mybatis.model.ActivityExample;

public interface ActivityMapper extends Mapper<Activity, ActivityExample, Long> {
	List<ServiceAddIndexModel> getServiceAddInfo(Map<String, Object> condition);
	void insertGenId(Activity activity) throws Exception;
}