package com.bola.nwcl.biz;

import java.util.List;

import com.bola.nwcl.api.model.BorrowThingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.BorrowThing;
import com.bola.nwcl.dal.mybatis.model.BorrowThingExample;

public interface BorrowThingManager extends Manager<BorrowThing, BorrowThingExample, Long>{
	List<BorrowThingModel> getBorrowThingDetail(Long id);
}
