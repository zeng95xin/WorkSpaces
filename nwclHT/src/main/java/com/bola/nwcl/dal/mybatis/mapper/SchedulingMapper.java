package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Scheduling;
import com.bola.nwcl.dal.mybatis.model.SchedulingExample;

public interface SchedulingMapper extends Mapper<Scheduling, SchedulingExample, Long> {
	int selectCountByExample(SchedulingExample example); 
}