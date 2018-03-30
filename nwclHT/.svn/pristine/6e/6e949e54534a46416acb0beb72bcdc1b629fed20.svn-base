package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.security.SercurityArticleReleaseModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.ArticleRelease;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseExample;

public interface ArticleReleaseMapper extends Mapper<ArticleRelease, ArticleReleaseExample, Long> {
	void insertAndGetId(ArticleRelease articleRelease);
	
	List<SercurityArticleReleaseModel> getSercurityArticleReleaseModel(Map<String,Object> condition);
	
}