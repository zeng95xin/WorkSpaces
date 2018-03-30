package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.BizUserNotifyModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Announcement;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifyExample;

public interface NotifyManager extends Manager<Notify, NotifyExample, Long>{
	
	void insertAndGetId(Notify notify);
	
	
	Page<BizUserNotifyModel> getUserAllNotify(long buserId, int page, int rows);
	int getUserAllNotifyNotReadCount(long buserId);
	void deleteUserNotify(long buserId, String ids);
	void updateReadNotify(long buserId, String ids);
	
	DataGrid dataGrid(Notify notify, PageHelper ph,HttpServletRequest request);
	void deleteNotify(Long id);
}
