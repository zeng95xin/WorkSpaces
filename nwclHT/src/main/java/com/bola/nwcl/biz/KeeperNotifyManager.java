package com.bola.nwcl.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.keeper.KeeperNotifyModel;
import com.bola.nwcl.dal.mybatis.model.NotifyImg;

public interface KeeperNotifyManager {
	int addNotify(HttpServletRequest request, String title, String detail, String buildingId, String  floorIds, String roomId, MultipartFile[] imgs, String saveDir, Long publishPeopleId) throws Exception;
	
	Page<KeeperNotifyModel> getAllNotify(int page, int rows);
	
	void updateNotify(HttpServletRequest request, String detail, String buildingId, String  floorIds, String roomId, long id, String saveDir, Long publishPeopleId) throws Exception;
	void deleteNotify(long id);
	void deleteNotifyImg(String id);
	List<NotifyImg> addNotifyImg(HttpServletRequest request, MultipartFile[] imgs, String saveDir, long id) throws Exception;
}
