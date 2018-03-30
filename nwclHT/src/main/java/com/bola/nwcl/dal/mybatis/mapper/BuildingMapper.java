package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;

public interface BuildingMapper extends Mapper<Building, BuildingExample, Long> {
	void insertAndGetId(Building building);
}