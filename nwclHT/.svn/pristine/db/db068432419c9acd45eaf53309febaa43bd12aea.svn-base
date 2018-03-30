package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.keeper.KeeperHangModel;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;


public interface KeeperHangManager {
	Page<KeeperHangModel> getAllHang(int page, int rows, int type);
	KeeperHangModel getHangDetail(long id);
	void updateAssignRepairmain(long hangId, long repairmainId, ServiceUser suser);
	void deleteHang(long id);
	
	
	void updateCancelHang(long id, String reason, ServiceUser suser);
}
