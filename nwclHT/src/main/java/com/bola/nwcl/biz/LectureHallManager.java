package com.bola.nwcl.biz;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.LectureHall;
import com.bola.nwcl.dal.mybatis.model.LectureHallExample;

public interface LectureHallManager extends Manager<LectureHall, LectureHallExample, Long>{
	void insertGenId(HttpServletRequest request, LectureHall hall,
			String push, String recommended, String pushContent, String endTime1,
			Date activityEndTime1, MultipartFile img1) throws Exception;
	
	void edit(HttpServletRequest request, LectureHall hall,
			String push, String recommended, String pushContent, String endTime1,
			Date activityEndTime1, MultipartFile img1) throws Exception;
}
