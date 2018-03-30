package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.IdlethingDetailModel;
import com.bola.nwcl.api.model.IdlethingModel;
import com.bola.nwcl.api.model.IdlethingRatingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.Idlething;
import com.bola.nwcl.dal.mybatis.model.IdlethingExample;
import com.bola.nwcl.dal.mybatis.model.IdlethingRating;

public interface IdlethingManager extends Manager<Idlething, IdlethingExample, Long>{
	void insertIdlething(String saveDir,Long buserId, String title, String description,Long typeId, MultipartFile[] imgs) throws Exception;
	void deleteIdlething(Long buserId, Long id) throws Exception;
	Page<IdlethingModel> getAllIdlething(long buserId, int page, int rows, Long type);
	IdlethingDetailModel getIdlethingDetail(long buserId, long id, int ratingCount);
	Page<IdlethingRatingModel> getIdlethingRating(long buserId, long id, int page, int rows);
	
	IdlethingRatingModel getOneRatingModel(long id, long buserId);
	void insertAndGetIdRagting(IdlethingRating idlethingRating);
}
