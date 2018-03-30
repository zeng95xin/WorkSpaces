package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.NotifyImg;
import com.bola.nwcl.dal.mybatis.model.NotifyImgExample;

public interface NotifyImgMapper extends Mapper<NotifyImg, NotifyImgExample, Long> {
	void insertAndGetId(NotifyImg notifyImg); 
}