package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.AnnouncementRatingModel;
import com.bola.nwcl.api.model.Announcement_Model;
import com.bola.nwcl.api.model.keeper.FloorModel;
import com.bola.nwcl.biz.KeeperSendMessageManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementMapper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementSendMapper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserRoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.model.Announcement;
import com.bola.nwcl.dal.mybatis.model.AnnouncementExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImg;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImgExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRatingExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementSendExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

@Service
public class KeeperSendMessageManagerImpl implements KeeperSendMessageManager{
	
	@Autowired
	private AnnouncementMapper announcementMapper;
	
	@Autowired
	private AnnouncementImgMapper announcementImgMapper;
	
	@Autowired
	private AnnouncementRatingMapper announcementRatingMapper;
	
	@Autowired
	private AnnouncementSendMapper announcementSendMapper;
	
	@Autowired
	private BizUserRoomMapper bizUserRoomMapper;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Override
	public Page<Announcement_Model> getAllAnnouncement(Integer page, Integer rows) {

		AnnouncementExample example_a = new AnnouncementExample();
		example_a.setOrderByClause(" row_add_time DESC ");
		example_a.setOffset((page-1)*rows);
		example_a.setLimit(rows);
		int count = announcementMapper.countByExample(example_a);
		List<Announcement> anns = announcementMapper.selectByExample(example_a);
		List<Announcement_Model> ms = new ArrayList<Announcement_Model>(anns.size());
		for(Announcement a : anns){
			Announcement_Model m = new Announcement_Model();
			BeanUtils.copyProperties(a, m);
			
			AnnouncementImgExample example_img = new AnnouncementImgExample();
			example_img.or().andAnnouncementIdEqualTo(a.getId());
			List<AnnouncementImg> imgs = announcementImgMapper.selectByExample(example_img);
			
			m.setImgs(imgs);
			ms.add(m);
		}
		PageRequest pager = new PageRequest(page - 1, rows);
		Page<Announcement_Model> pageData = new PageImpl<Announcement_Model>(ms, pager, count);
		return pageData;
	}

	@Override
	public Page<Announcement_Model> getSensitiveAnnouncement(Integer page, Integer rows) {
		AnnouncementRatingExample example_rating = new AnnouncementRatingExample();
		example_rating.or().andStatusEqualTo(1);
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("orderByClause", " row_add_time DESC ");
		condition.put("limit", rows);
		condition.put("offset", (page-1)*rows);
		int count = announcementMapper.countSensitiveAnnouncement();
		List<Announcement> anns = announcementMapper.getSensitiveAnnouncement(condition);
		List<Announcement_Model> ms = new ArrayList<Announcement_Model>(anns.size());
		for(Announcement a : anns){
			Announcement_Model m = new Announcement_Model();
			BeanUtils.copyProperties(a, m);
			
			AnnouncementImgExample example_img = new AnnouncementImgExample();
			example_img.or().andAnnouncementIdEqualTo(a.getId());
			List<AnnouncementImg> imgs = announcementImgMapper.selectByExample(example_img);
			
			m.setImgs(imgs);
			ms.add(m);
		}
		PageRequest pager = new PageRequest(page - 1, rows);
		Page<Announcement_Model> pageData = new PageImpl<Announcement_Model>(ms, pager, count);
		return pageData;
	}

	@Override
	public Page<AnnouncementRatingModel> getAnnouncementSensitiveRating(Integer page, Integer rows, Long id) {
		Announcement a = announcementMapper.selectByPrimaryKey(id);
		if(a == null){
			throw new BusinessValidateException("该信息不存在");
		}
		
		AnnouncementRatingExample example_rating = new AnnouncementRatingExample();
		example_rating.or().andAnnouncementIdEqualTo(id).andStatusEqualTo(1);
		
		PageRequest pager = new PageRequest(page - 1, rows);
		example_rating.setLimit(pager.getPageSize());
		example_rating.setOffset(pager.getOffset());
		Map<String, Object> condition = new HashMap<String, Object>(3);
		condition.put("id", id);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		List<AnnouncementRatingModel> content = announcementRatingMapper.getAnnouncementSensitiveRating(condition);
		int total = announcementRatingMapper.countByExample(example_rating);
		Page<AnnouncementRatingModel> pageData = new PageImpl<AnnouncementRatingModel>(content, pager, total);
		return pageData;
	}

	@Override
	public List<Building> getAllBuilding() {
		BuildingExample example = new BuildingExample();
		List<Building> list = buildingMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<FloorModel> getAllFloor(Long id) {
		List<FloorModel> list = roomMapper.getAllFloor(id);
		return list;
	}

	@Override
	public List<Room> getAllRoom(Long id, String floor) {
		RoomExample example = new RoomExample();
		example.or().andBuildingIdEqualTo(id).andFloorEqualTo(floor);
		List<Room> list = roomMapper.selectByExample(example);
		return list;
	}

	@Override
	public void deleteAnnouncement(long id) {
		Announcement a = announcementMapper.selectByPrimaryKey(id);
		if(a== null){
			throw new BusinessValidateException("该公告不存在");
		}
		if(a.getStatus().intValue() == 1){
			throw new BusinessValidateException("该公告已经发送,不能在删除");
		}
		AnnouncementImgExample example_img = new AnnouncementImgExample();
		example_img.or().andAnnouncementIdEqualTo(id);
		List<AnnouncementImg> imgs = announcementImgMapper.selectByExample(example_img);
		for(AnnouncementImg img : imgs){
			FileUploadDeleteUtil.delete(img.getImgPath());
		}
		announcementImgMapper.deleteByExample(example_img);
		AnnouncementRatingExample example_rating = new AnnouncementRatingExample();
		example_rating.or().andAnnouncementIdEqualTo(id);
		announcementRatingMapper.deleteByExample(example_rating);
		AnnouncementSendExample example_send = new AnnouncementSendExample();
		example_send.or().andAnnouncementIdEqualTo(id);
		announcementSendMapper.deleteByPrimaryKey(id);
		announcementMapper.deleteByPrimaryKey(id);
	}
	
}
