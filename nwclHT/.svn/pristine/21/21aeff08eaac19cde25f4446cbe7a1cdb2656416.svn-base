package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Announcement;
import com.bola.nwcl.dal.mybatis.model.AnnouncementExample;


public interface AnnouncementManager extends Manager<Announcement, AnnouncementExample, Long>{
	
	DataGrid dataGrid(Announcement announcement, PageHelper ph,HttpServletRequest rquest);
	void insertAndGetId(Announcement announcement);
	void deleteAnnouncement(Long id);
}
