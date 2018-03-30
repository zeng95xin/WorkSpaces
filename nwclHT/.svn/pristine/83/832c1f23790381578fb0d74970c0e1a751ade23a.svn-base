package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ArticleReleaseImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.ArticleReleaseImgMapper;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImg;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImgExample;

@Service
public class ArticleReleaseImgManagerImpl  extends ManagerImpl<ArticleReleaseImg, ArticleReleaseImgExample, Long> implements ArticleReleaseImgManager{
	
	private ArticleReleaseImgMapper articleReleaseImgMapper;
	
	@Autowired
	public ArticleReleaseImgManagerImpl(ArticleReleaseImgMapper articleReleaseImgMapper) {
		this.mapper = articleReleaseImgMapper;
		this.articleReleaseImgMapper = articleReleaseImgMapper;
	}

	@Override
	public void insertAndGetId(ArticleReleaseImg articleReleaseImg) {
		articleReleaseImgMapper.insertAndGetId(articleReleaseImg);
	}

}
