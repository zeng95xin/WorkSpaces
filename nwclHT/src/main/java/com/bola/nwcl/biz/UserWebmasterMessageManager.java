package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.UserWebmasterMessageModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.UserWebmasterMessage;
import com.bola.nwcl.dal.mybatis.model.UserWebmasterMessageExample;

public interface UserWebmasterMessageManager extends Manager<UserWebmasterMessage, UserWebmasterMessageExample, Long>{
	void updateSendMessage(String  saveDir, long buserSendId, long buserReceiveId, HttpServletRequest request, String type, String content, MultipartFile[] files) throws Exception;
	Page<UserWebmasterMessageModel> updateGetUserAllMsgWithNeitherUser(long buserId, long neitherId, int page, int rows);
}
