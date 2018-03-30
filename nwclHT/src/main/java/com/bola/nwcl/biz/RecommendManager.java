package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.RecommendDetailModel;
import com.bola.nwcl.api.model.RecommendModel;
import com.bola.nwcl.api.model.RecommendRatingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.Recommend;
import com.bola.nwcl.dal.mybatis.model.RecommendExample;
import com.bola.nwcl.dal.mybatis.model.RecommendRating;

public interface RecommendManager extends Manager<Recommend, RecommendExample, Long>{
	void insertRecommend(String saveDir,Long buserId, String title, String description,Long typeId, MultipartFile[] imgs) throws Exception;
	void deleteRecommend(Long buserId, Long id) throws Exception;
	Page<RecommendModel> getAllRecommend(long buserId, int page, int rows, Long type);
	RecommendDetailModel getRecommendDetail(long buserId, long id, int ratingCount);
	Page<RecommendRatingModel> getRecommendRating(long buserId, long id, int page, int rows);
	
	RecommendRatingModel getOneRatingModel(long id, long buserId);
	void insertAndGetIdRagting(RecommendRating recommendRating);
}
