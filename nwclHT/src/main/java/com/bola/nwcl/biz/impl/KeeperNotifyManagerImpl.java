package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.keeper.KeeperNotifyModel;
import com.bola.nwcl.biz.KeeperNotifyManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.BizUserRoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.model.BizUserRoom;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifyExample;
import com.bola.nwcl.dal.mybatis.model.NotifyImg;
import com.bola.nwcl.dal.mybatis.model.NotifyImgExample;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.NotifySendExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

@Service
public class KeeperNotifyManagerImpl implements KeeperNotifyManager {
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	@Autowired
	private NotifySendMapper notifySendMapper;
	
	@Autowired
	private NotifyImgMapper notifyImgMapper;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	private BizUserRoomMapper bizUserRoomMapper;
	
	@Override
	public int addNotify(HttpServletRequest request, String title,
			String detail, String buildingId, String floorIds, String roomId,
			MultipartFile[] imgs, String saveDir, Long publishPeopleId)
			throws Exception {
		Notify a = new Notify();
		a.setDetail(detail);
		a.setPublishPeopleId(publishPeopleId);
		a.setStatus(0);
		a.setTitle(title);
		a.setType("1:-1:-1");

		notifyMapper.insertAndGetId(a);

		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		boolean savePath = false;
		for (int i = 0; i < imgs.length; i++) {
			MultipartFile img = imgs[i];
			if (!img.isEmpty()) {
				String fileName = UUID.randomUUID().toString().substring(0, 12);
				fileName = todayStr + fileName;
				String[] saveFileName = FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);

				if (!savePath) {
					a.setImgPath(saveFileName[0]);
					savePath = true;
				}

				NotifyImg aimg = new NotifyImg();
				aimg.setImgPath(saveFileName[0]);
				aimg.setImgPathThumbnail(saveFileName[1]);
				aimg.setNotifyId(a.getId());
				notifyImgMapper.insert(aimg);
			}
		}

		List<Long> roomids = new LinkedList<Long>();

		String[] buildingIdStrs = null;

		if (buildingId != null) {
			buildingIdStrs = buildingId.split(",");
		} else {
			buildingIdStrs = new String[0];
		}
		for (String str : buildingIdStrs) {
			RoomExample example_room = new RoomExample();
			try {
				Long bid = Long.valueOf(str);
				example_room.or().andBuildingIdEqualTo(bid);
			} catch (NumberFormatException e) {
				throw new PatternException("楼栋id格式错误");
			}
			List<Room> rooms = roomMapper.selectByExample(example_room);
			for (Room r : rooms) {
				roomids.add(r.getId());
			}
		}

		String[] floorIdStrs = null;

		if (floorIds != null) {
			floorIdStrs = floorIds.split(",");
		} else {
			floorIdStrs = new String[0];
		}
		for (String str : floorIdStrs) {
			RoomExample example_room = new RoomExample();
			String[] ss = str.split("-");
			try {
				Long bid = Long.valueOf(ss[0]);
				example_room.or().andBuildingIdEqualTo(bid)
						.andFloorEqualTo(ss[1]);
			} catch (Exception e) {
				throw new PatternException("楼层id格式错误");
			}
			List<Room> rooms = roomMapper.selectByExample(example_room);
			for (Room r : rooms) {
				roomids.add(r.getId());
			}
		}

		String[] roomIdStrs = null;
		if (roomId != null) {
			roomIdStrs = roomId.split(",");
		} else {
			roomIdStrs = new String[0];
		}
		for (String str : roomIdStrs) {
			try {
				Long roomid = Long.valueOf(str);
				roomids.add(roomid);
			} catch (NumberFormatException e) {
				throw new PatternException("房间id格式错误");
			}
		}

		BizUserRoomExample example_bur = new BizUserRoomExample();
		example_bur.or().andRoomIdIn(roomids);
		List<BizUserRoom> busers = bizUserRoomMapper.selectByExample(example_bur);

		List<Long> bids = new ArrayList<Long>(busers.size());
		for (BizUserRoom b : busers) {
			if (!bids.contains(b.getBizUserId())) {
				bids.add(b.getBizUserId());
			}
		}

		for (Long id : bids) {
			NotifySend s = new NotifySend();
			s.setNotifyId(a.getId());
			s.setBuserId(id);
			s.setStatus(0);
			notifySendMapper.insert(s);
		}
		a.setSendCount(bids.size());
		notifyMapper.updateByPrimaryKeySelective(a);
		return a.getSendCount();
	}
	
	@Override
	public void updateNotify(HttpServletRequest request, String detail, String buildingId, String floorIds, String roomId, long aid, String saveDir, Long publishPeopleId) throws Exception {
		Notify a = notifyMapper.selectByPrimaryKey(aid);
		if(a == null){
			throw new BusinessValidateException("该消息不存在");
		}
		if(StringUtils.isNotBlank(roomId)||StringUtils.isNotBlank(buildingId)||StringUtils.isNotBlank(floorIds)){
			
			NotifySendExample example_send = new NotifySendExample();
			example_send.or().andNotifyIdEqualTo(aid);
			notifySendMapper.deleteByExample(example_send);
		
			List<Long> roomids = new LinkedList<Long>();
			
			String[] buildingIdStrs = buildingId.split(",");
			
			for(String str : buildingIdStrs){
				RoomExample example_room = new RoomExample();
				try {
					Long bid = Long.valueOf(str);
					example_room.or().andBuildingIdEqualTo(bid);
				} catch (NumberFormatException e) {
					throw new PatternException("楼栋id格式错误");
				}
				List<Room> rooms = roomMapper.selectByExample(example_room);
				for(Room r : rooms){
					roomids.add(r.getId());
				}
			}
			
			String[] floorIdStrs = floorIds.split(",");
			for(String str : floorIdStrs){
				RoomExample example_room = new RoomExample();
				String [] ss = str.split("-");
				try {
					Long bid = Long.valueOf(ss[0]);
					example_room.or().andBuildingIdEqualTo(bid).andFloorEqualTo(ss[1]);
				} catch (Exception e) {
					throw new PatternException("楼层id格式错误");
				}
				List<Room> rooms = roomMapper.selectByExample(example_room);
				for(Room r : rooms){
					roomids.add(r.getId());
				}
			}
			
			String[] roomIdStrs = roomId.split(",");
			for(String str : roomIdStrs){
				try {
					Long roomid = Long.valueOf(str);
					roomids.add(roomid);
				} catch (NumberFormatException e) {
					throw new PatternException("房间id格式错误");
				}
			}
			
			BizUserRoomExample example_bur = new BizUserRoomExample();
			example_bur.or().andRoomIdIn(roomids);
			List<BizUserRoom> busers = bizUserRoomMapper.selectByExample(example_bur);
			
			List<Long> bids = new ArrayList<Long>(busers.size());
			for(BizUserRoom b : busers){
				if(!bids.contains(b.getBizUserId())){
					bids.add(b.getBizUserId());
				}
			}
		
			for(Long id : bids){
				NotifySend s = new NotifySend();
				s.setNotifyId(a.getId());
				s.setBuserId(id);
				notifySendMapper.insert(s);
			}
			a.setSendCount(bids.size());
		}
		if(detail != null){
			a.setDetail(detail);
		}
		notifyMapper.updateByPrimaryKeySelective(a);
	}

	@Override
	public void deleteNotify(long id) {
		Notify n = notifyMapper.selectByPrimaryKey(id);
		if(n== null){
			throw new BusinessValidateException("该消息不存在");
		}
		if(n.getStatus()==1){
			throw new BusinessValidateException("该消息已经发送给用户,只能后台删除");
		}
		NotifyImgExample example_img = new NotifyImgExample();
		example_img.or().andNotifyIdEqualTo(id);
		List<NotifyImg> imgs = notifyImgMapper.selectByExample(example_img);
		for(NotifyImg img : imgs){
			FileUploadDeleteUtil.delete(img.getImgPath());
			FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
		}
		notifyImgMapper.deleteByExample(example_img);
		NotifySendExample example_send = new NotifySendExample();
		example_send.or().andNotifyIdEqualTo(id);
		notifySendMapper.deleteByExample(example_send);
		notifyMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteNotifyImg(String id) {
		String[] ids = id.split(",");
		List<Long> ids_long = new ArrayList<Long>(ids.length);
		for(String str : ids){
			Long imgid = Long.valueOf(str);
			ids_long.add(imgid);
		}
		NotifyImgExample example_img = new NotifyImgExample();
		example_img.or().andIdIn(ids_long);
		List<NotifyImg> imgs = notifyImgMapper.selectByExample(example_img);
		for(NotifyImg img : imgs){
			FileUploadDeleteUtil.delete(img.getImgPath());
			FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
		}
		notifyImgMapper.deleteByExample(example_img);
	}

	@Override
	public List<NotifyImg> addNotifyImg(HttpServletRequest request, MultipartFile[] imgs, String saveDir, long id) throws Exception {
		Notify a = notifyMapper.selectByPrimaryKey(id);
		if(a == null){
			throw new BusinessValidateException("该消息不存在");
		}
		List<NotifyImg> list_img = new ArrayList<NotifyImg>(imgs.length);
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		boolean savePath = false;
		if(StringUtils.isNotBlank(a.getImgPath())){
			savePath = true;
		}
		for (int i = 0; i < imgs.length; i++) {
			MultipartFile img = imgs[i];
			if (!img.isEmpty()) {
				String fileName = UUID.randomUUID().toString().substring(0, 12);
				fileName = todayStr + fileName;
				String[] saveFileName = FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);

				if (!savePath) {
					a.setImgPath(saveFileName[0]);
					savePath = true;
				}

				NotifyImg aimg = new NotifyImg();
				aimg.setImgPath(saveFileName[0]);
				aimg.setImgPathThumbnail(saveFileName[1]);
				aimg.setNotifyId(a.getId());
				notifyImgMapper.insertAndGetId(aimg);
				list_img.add(aimg);
			}
		}
		return list_img;
	}

	@Override
	public Page<KeeperNotifyModel> getAllNotify(int page, int rows) {
		PageRequest pager = new PageRequest(page - 1, rows);
		NotifyExample example = new NotifyExample();
		example.setLimit(pager.getPageSize());
		example.setOffset(pager.getOffset());
		example.setOrderByClause(" row_add_time desc");
		List<Notify> list = notifyMapper.selectByExample(example);
		List<KeeperNotifyModel> list_model = new ArrayList<KeeperNotifyModel>(list.size());
		for(Notify n : list){
			KeeperNotifyModel model = new KeeperNotifyModel();
			BeanUtils.copyProperties(n, model);
			NotifyImgExample example_img = new NotifyImgExample();
			example_img.or().andNotifyIdEqualTo(n.getId());
			List<NotifyImg> imgs = notifyImgMapper.selectByExample(example_img);
			model.setImgs(imgs);
			list_model.add(model);
		}
		int count =notifyMapper.countByExample(null);
		Page<KeeperNotifyModel> pageData = new PageImpl<KeeperNotifyModel>(list_model, pager, count);
		return pageData;
	}

}
