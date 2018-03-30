package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ArticleReleaseManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.ArticleReleaseMapper;
import com.bola.nwcl.dal.mybatis.model.ArticleRelease;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseExample;


@Service
public class ArticleReleaseManagerImpl  extends ManagerImpl<ArticleRelease, ArticleReleaseExample, Long> implements ArticleReleaseManager{
	
	private ArticleReleaseMapper articleReleaseMapper;
	
	@Autowired
	public ArticleReleaseManagerImpl(ArticleReleaseMapper articleReleaseMapper) {
		this.mapper = articleReleaseMapper;
		this.articleReleaseMapper = articleReleaseMapper;
	}

	@Override
	public void insertAndGetId(ArticleRelease articleRelease) {
		articleReleaseMapper.insertAndGetId(articleRelease);
	}

	

}
