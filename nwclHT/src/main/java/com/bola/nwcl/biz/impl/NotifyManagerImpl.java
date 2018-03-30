package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bola.nwcl.api.model.BizUserNotifyModel;
import com.bola.nwcl.biz.NotifyManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.NotifyImgExample;
import com.bola.nwcl.dal.mybatis.model.NotifySendExample;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifyExample;
import com.bola.nwcl.dal.mybatis.model.NotifyExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.NotifyImgExample;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.NotifySendExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;

@Service
public class NotifyManagerImpl  extends ManagerImpl<Notify, NotifyExample, Long> implements NotifyManager{

	private NotifyMapper notifyMapper;

	@Autowired
	private NotifySendMapper notifySendMapper;
	@Autowired
	private NotifyImgMapper notifyImgMapper;
	@Autowired BuildingMapper buildingMapper;
	@Autowired RoomMapper roomMapper;
	@Autowired BizUserMapper bizUserMapper;

	@Autowired
	public NotifyManagerImpl(NotifyMapper notifyMapper) {
		this.mapper = notifyMapper;
		this.notifyMapper = notifyMapper;
	}

	@Override
	public Page<BizUserNotifyModel> getUserAllNotify(long buserId, int page, int rows) {
		PageRequest pager = new PageRequest(page - 1, rows);
		Map<String, Object> condition = new HashMap<String, Object>(3);
		condition.put("buserId", buserId);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		int count = notifyMapper.getUserAllNotifyCount(buserId);
		List<BizUserNotifyModel> list = notifyMapper.getUserAllNotify(condition);
		Page<BizUserNotifyModel> pageData = new PageImpl<BizUserNotifyModel>(list, pager, count);
		return pageData;
	}

	@Override
	public int getUserAllNotifyNotReadCount(long buserId) {
		int count = notifyMapper.getUserAllNotifyNotReadCount(buserId);
		return count;
	}

	@Override
	public void deleteUserNotify(long buserId, String ids) {
		String[] idstrs = ids.split(",");
		List<Long> list_ids = new ArrayList<Long>(idstrs.length);
		for(String idstr : idstrs){
			Long id;
			try {
				id = Long.valueOf(idstr);
			} catch (Exception e) {
				throw new BusinessValidateException("格式错误");
			}
			list_ids.add(id);
		}
		NotifySendExample example_send = new NotifySendExample();
		example_send.or().andBuserIdEqualTo(buserId).andNotifyIdIn(list_ids);
		notifySendMapper.deleteByExample(example_send);
	}

	@Override
	public void updateReadNotify(long buserId, String ids) {
		String[] idstrs = ids.split(",");
		List<Long> list_ids = new ArrayList<Long>(idstrs.length);
		for(String idstr : idstrs){
			Long id;
			try {
				id = Long.valueOf(idstr);
			} catch (Exception e) {
				throw new BusinessValidateException("格式错误");
			}
			list_ids.add(id);
		}
		NotifySendExample example_send = new NotifySendExample();
		example_send.or().andBuserIdEqualTo(buserId).andNotifyIdIn(list_ids);
		NotifySend ns = new NotifySend();
		ns.setStatus(1);
		notifySendMapper.updateByExampleSelective(ns, example_send);
	}

	@Override
	public void insertAndGetId(Notify notify) {
		notifyMapper.insertAndGetId(notify);
	}

	@Override
	public DataGrid dataGrid(Notify notify, PageHelper ph,HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		NotifyExample example=new NotifyExample();

		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		Criteria c=example.or();
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		if(notify!=null){
			//通过小区查询
			if(notify.getCommunityId()!=null){
				List<Long> sss = this.getNotifyIds(notify.getCommunityId());
				if (sss != null && sss.size() > 0) {
					c.andIdIn(sss);
				}else{
					c.andIdEqualTo(-1L);
				}
			}
			if (notify.getStatus() != null) {
				c.andStatusEqualTo(notify.getStatus());
			}
			if(StringUtils.isNotBlank(notify.getSname())&&
					StringUtils.isNotBlank(notify.getSvalue())){
				if(notify.getSname().equals("title")){					
					c.andTitleLike("%"+notify.getSvalue()+"%");					
				}	
				if(notify.getSname().equals("detail")){					
					c.andDetailLike("%"+notify.getSvalue()+"%");
				}	
				if(notify.getSname().equals("remark")){					
					c.andRemarkLike("%"+notify.getRemark()+"%");
				}

				/*if(notify.getSname().equals("publishPeopleId")){					
					c.andRemarkLike(notify.getRemark());
				}	*/
				if(notify.getSname().equals("rowAddTime")){
					Date date = DateUtils.strToDate(notify.getSvalue(), DateUtils.YYYY_MM_DD);
					Calendar ca = Calendar.getInstance();
					ca.setTime(date);
					ca.add(Calendar.DATE, 1);
					Date date2 = ca.getTime();
					c.andRowAddTimeBetween(date,date2);
				}	
			}

		}
		int total=notifyMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<Notify> list=notifyMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for (Notify n : list) {
				String[] ss = n.getType().split(":");
				if (StringUtils.isNotBlank(ss[0])) {
					n.setType(ss[0]);
				}
			}
		}
		dg.setRows(list);
		dg.setTotal(total);

		return dg;
	}

	@Override
	@Transactional
	public void deleteNotify(Long id) {
		NotifyImgExample imgExample = new NotifyImgExample();
		imgExample.or().andNotifyIdEqualTo(id);
		notifyImgMapper.deleteByExample(imgExample);

		NotifySendExample sendExample = new NotifySendExample();
		sendExample.or().andNotifyIdEqualTo(id);
		notifySendMapper.deleteByExample(sendExample);

		notifyMapper.deleteByPrimaryKey(id);
	}
	
	private List<Long> getNotifyIds(Long communityId){
		List<Long> list = new ArrayList<Long>();
		BuildingExample buildingExample=new BuildingExample();
		buildingExample.or().andCommunityIdEqualTo(communityId);
		List<Building> building=buildingMapper.selectByExample(buildingExample);
		if(building.size()!=0){
			for (Building building2 : building) {
				RoomExample roomExample=new RoomExample();
				roomExample.or().andBuildingIdEqualTo(building2.getId());
				List<Room> room=roomMapper.selectByExample(roomExample);
				if(room.size()!=0){
					for (Room room2 : room) {
						BizUserExample bizUserExample=new BizUserExample();
						bizUserExample.or().andCurrentRoomIdEqualTo(room2.getId());
						List<BizUser> bizUser=bizUserMapper.selectByExample(bizUserExample);
						if(bizUser.size()!=0){
							for (BizUser bizUser2 : bizUser) {
								NotifySendExample notifySendExample = new NotifySendExample();
								notifySendExample.or().andBuserIdEqualTo(bizUser2.getId());
								List<NotifySend> notifySend=notifySendMapper.selectByExample(notifySendExample);
								for (NotifySend notifySend2 : notifySend) {
									list.add(notifySend2.getNotifyId());
								}
							}
						}
					}
				}
			}
		}	
		return list;
	}

}
