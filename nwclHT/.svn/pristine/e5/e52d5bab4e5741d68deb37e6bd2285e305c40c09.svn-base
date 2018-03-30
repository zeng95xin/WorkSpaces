package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.likeshare.LikeShareSimpleModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.LikeShare;
import com.bola.nwcl.dal.mybatis.model.LikeShareExample;
import com.bola.nwcl.web.model.LikeShareWebModel;

public interface LikeShareManager extends Manager<LikeShare, LikeShareExample, Long>{
	void insertAndGetId(LikeShare likeShare);
	void deleteByKeeper(HttpServletRequest request, String saveDir, Long id);
	
	
	void addLikeShare(String saveDir, long buserId, HttpServletRequest request, String title, String content, String shareType, MultipartFile[] imgs) throws Exception;
	Page<LikeShareSimpleModel> getAllLikeShare(long buserId, int page, int rows, String isGetCurrentUser);
	LikeShareSimpleModel updateGetLikeShareDetail(long id, long buserId, int ratingCount);
	void deleteLikeShare(long id, long buserId);
	void updateZanLikeShare(long id, long buserId);
	void updateCancelZanLikeShare(long id, long buserId);
	void updateReportLikeShare(long id, long buserId);
	void edit(HttpServletRequest request,LikeShareWebModel likeShareWebModel,@RequestParam("file") MultipartFile[] file) throws Exception;
	void delete(Long id) throws Exception;
}
