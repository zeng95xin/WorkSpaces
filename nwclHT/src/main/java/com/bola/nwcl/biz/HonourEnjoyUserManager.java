package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUser;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUserExample;

public interface HonourEnjoyUserManager extends Manager<HonourEnjoyUser, HonourEnjoyUserExample, Long>{
	void insertAndGetId(HonourEnjoyUser honourEnjoy);
}
