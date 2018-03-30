package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.AnnouncementImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementImgMapper;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImg;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImgExample;

@Service
public class AnnouncementImgManagerImpl  extends ManagerImpl<AnnouncementImg, AnnouncementImgExample, Long> implements AnnouncementImgManager{
	
	@SuppressWarnings("unused")
	private AnnouncementImgMapper announcementImgMapper;
	
	@Autowired
	public AnnouncementImgManagerImpl(AnnouncementImgMapper announcementImgMapper) {
		this.mapper = announcementImgMapper;
		this.announcementImgMapper = announcementImgMapper;
	}

}
