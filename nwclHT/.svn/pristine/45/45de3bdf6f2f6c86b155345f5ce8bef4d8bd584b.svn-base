package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.keeper.KeeperBorrowModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Borrow;
import com.bola.nwcl.dal.mybatis.model.BorrowExample;

public interface BorrowMapper extends Mapper<Borrow, BorrowExample, Long> {
	void insertAndGetId(Borrow borrow);
	List<KeeperBorrowModel> getAllBorrowByKeeper(Map<String, Object> condition);
}