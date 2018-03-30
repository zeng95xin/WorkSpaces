package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.UserOpinionModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.UserOpinion;
import com.bola.nwcl.dal.mybatis.model.UserOpinionExample;

public interface UserOpinionManager extends Manager<UserOpinion, UserOpinionExample, Long>{
	Page<UserOpinionModel> getUserAllOpinion(long buserId, int page, int rows);
	void addOpinion(String content,MultipartFile[] imgs, String saveDir, long buserId, HttpServletRequest request) throws Exception;
	void insertAndGetId(UserOpinion userOpinion);
}
