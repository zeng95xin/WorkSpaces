package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.keeper.KeeperUserMessageIndexModel;
import com.bola.nwcl.api.model.keeper.KeeperUserMessageModel;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;


public interface KeeperConsultationAndCommunicationManager {
	Page<KeeperUserMessageIndexModel> getIndexMessage(int page, int rows, Long id);
	Page<KeeperUserMessageModel> updateGetBizUserMessage(Long id, int page, int rows, ServiceUser suer);
	void updateMarkHasRead(long id, long suerId);
	void updateMarkHasNotRead(long id, long suerId);
	void deleteMsg(long id, long suserId);
	
	void addMsg(long id, long suserId, String content, ServiceUser suer);
}
