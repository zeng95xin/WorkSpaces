package com.bola.nwcl.biz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.AnnouncementRatingModel;
import com.bola.nwcl.api.model.Announcement_Model;
import com.bola.nwcl.api.model.keeper.FloorModel;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.Room;

public interface KeeperSendMessageManager {
	
	Page<Announcement_Model> getAllAnnouncement(Integer page, Integer rows);
	Page<Announcement_Model> getSensitiveAnnouncement(Integer page, Integer rows);
	Page<AnnouncementRatingModel> getAnnouncementSensitiveRating(Integer page, Integer rows, Long id);
	
	List<Building> getAllBuilding();
	List<FloorModel> getAllFloor(Long id);
	List<Room> getAllRoom(Long id, String floor);
	
	
	void deleteAnnouncement(long id);
}
