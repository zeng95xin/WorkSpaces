package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.Announcement;
import com.bola.nwcl.dal.mybatis.model.AnnouncementExample;

public interface AnnouncementMapper extends Mapper<Announcement, AnnouncementExample, Long> {
	void insertAndGetId(Announcement announcement);
	List<Announcement> getSensitiveAnnouncement(Map<String, Object> condition);
	int countSensitiveAnnouncement();
}