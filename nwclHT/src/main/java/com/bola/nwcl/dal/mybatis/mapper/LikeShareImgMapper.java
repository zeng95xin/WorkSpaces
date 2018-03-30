package com.bola.nwcl.dal.mybatis.mapper;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.bola.nwcl.dal.mybatis.model.LikeShareImgExample;

public interface LikeShareImgMapper extends Mapper<LikeShareImg, LikeShareImgExample, Long> {
	void insertAndGetId(LikeShareImg likeShareImg);
}