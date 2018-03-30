package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.keeper.KeeperBorrowModel;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;

public interface KeeperBorrowManager {
	Page<KeeperBorrowModel> getAllBorrow(int orderType, int type, int page, int rows);
	KeeperBorrowModel getBorrowDetail(long id);
	
	void updateAllowBorrow(long borrowId, ServiceUser suser);
	void updateTakeBorrow(long borrowId, String thing_id, ServiceUser suser);
	void updateCancelBorrow(long borrowId, String cancelReason, ServiceUser suser);
	void updateReturnBorrow(long borrowId, String thing_id, ServiceUser suser);
}
