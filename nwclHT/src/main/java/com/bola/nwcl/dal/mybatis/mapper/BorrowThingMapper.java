package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;

import com.bola.nwcl.api.model.BorrowThingModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.BorrowThing;
import com.bola.nwcl.dal.mybatis.model.BorrowThingExample;

public interface BorrowThingMapper extends Mapper<BorrowThing, BorrowThingExample, Long> {
	List<BorrowThingModel> getBorrowThingDetail(Long id);
}