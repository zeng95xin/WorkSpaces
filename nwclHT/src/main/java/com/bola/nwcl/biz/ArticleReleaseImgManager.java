package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImg;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImgExample;

public interface ArticleReleaseImgManager extends Manager<ArticleReleaseImg, ArticleReleaseImgExample, Long>{
	void insertAndGetId(ArticleReleaseImg articleReleaseImg);
}
