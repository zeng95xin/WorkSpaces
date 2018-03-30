package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HangPictureManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.EmployeeMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureCompleteImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.HangPictureCompleteImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureCompleteImgExample;
import com.bola.nwcl.dal.mybatis.model.HangPictureExample;
import com.bola.nwcl.dal.mybatis.model.HangPictureExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.HangPictureRating;
import com.bola.nwcl.dal.mybatis.model.HangPictureRatingExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;


@Service
public class HangPictureManagerImpl  extends ManagerImpl<HangPicture, HangPictureExample, Long> implements HangPictureManager{
	
	private HangPictureMapper hangPictureMapper;
	
	@Autowired
	private ServiceUserMapper serviceUserMapper;
	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private BizUserMapper bizUserMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private HangPictureRatingMapper ratingMapper;
	@Autowired
	private HangPictureCompleteImgMapper completeImgMapper;
	@Autowired BuildingMapper buildingMapper;
	
	@Autowired
	public HangPictureManagerImpl(HangPictureMapper hangPictureMapper) {
		this.mapper = hangPictureMapper;
		this.hangPictureMapper = hangPictureMapper;
	}

	@Override
	public void insertAndGetId(HangPicture hangPicture) {
		hangPictureMapper.insertAndGetId(hangPicture);
	}

	@Override
	public DataGrid dataGrid(Long communityId, String key, String value,PageHelper ph,HttpServletRequest request) {
		
			DataGrid dg = new DataGrid();
			HangPictureExample example=new HangPictureExample();
			
			Criteria c = example.or();
			//得到用户对象
			Admin user = (Admin)request.getSession().getAttribute("loginUser");
			if(user.getRoleId() != 1){
				@SuppressWarnings("unchecked")
				List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
				c.andCommunityIdIn(communityIds);
			}
			
			if(communityId != null && communityId != -1){
				c.andCommunityIdEqualTo(communityId);
			}
			
			if(StringUtils.isNotBlank(key) && key.equals("1")){
				c.andDescriptionLike("%"+value+"%");
			}
			if(StringUtils.isNotBlank(key) && key.equals("2")){
				ServiceUserExample serviceUserExample = new ServiceUserExample();
				serviceUserExample.or().andNameLike("%"+value+"%");
				List<ServiceUser> su = serviceUserMapper.selectByExample(serviceUserExample);
				List<Long> list = new ArrayList<Long>();
				if(su != null && su.size() > 0){
					for (ServiceUser serviceUser : su) {
						list.add(serviceUser.getId());
					}
				}
				if (list.size() > 0) {
					c.andHangIdIn(list);
				} else {
					c.andHangIdEqualTo(-5131L);
				}
				
			}
			if(StringUtils.isNotBlank(key) && key.equals("3")){
				RoomExample roomExample = new RoomExample();
				roomExample.or().andUnitNoLike("%"+value+"%");
				List<Room> room = roomMapper.selectByExample(roomExample);
				List<Long> list = new ArrayList<Long>();
				if(room !=null && room.size() > 0){
					for (Room room2 : room) {
						list.add(room2.getId());
					}
				}
				if (list.size() > 0) {
					c.andRoomIdIn(list);
				} else {
					c.andRoomIdEqualTo(-5131L);
				}
			}
			
			if(StringUtils.isNotBlank(key) && key.equals("4")){
				c.andSerialLike("%"+value+"%");
			}
			int total=hangPictureMapper.countByExample(example);
			example.setLimit(ph.getRows());
			example.setOffset((ph.getPage()-1)*ph.getRows());
			example.setOrderByClause("row_add_time DESC");
			List<HangPicture> list=hangPictureMapper.selectByExample(example);
			
			if (list != null && list.size() > 0) {
				for(HangPicture m : list){
					Room r = roomMapper.selectByPrimaryKey(m.getRoomId());
					if (r != null) {
						m.setRoom(r.getUnitNo());
					}
					ServiceUser su = serviceUserMapper.selectByPrimaryKey(m.getHangId());
					if (su != null) {
						m.setRepairman(su.getName());
						EmployeeExample employeeExample = new EmployeeExample();
						employeeExample.or().andSuserIdEqualTo(su.getId());
						List<Employee> employees = employeeMapper.selectByExample(employeeExample);
						if ( employees != null && employees.size() > 0) {
							Employee employee = employees.get(0);
							if (employee != null) {
								m.setRepairmanPhone(employee.getTelephoneNumber());
							}
						}
					}
					BizUser us = bizUserMapper.selectByPrimaryKey(m.getBuserId());
					if (us != null) {
						m.setUser(us.getNickname());
						m.setName(us.getName());
						m.setPhone(us.getPhone());
					}
					
					HangPictureCompleteImgExample imgExample = new HangPictureCompleteImgExample();
					imgExample.or().andHangPictureIdEqualTo(m.getId());
					List<HangPictureCompleteImg> ps = completeImgMapper.selectByExample(imgExample);
					if (ps != null && ps.size() > 0) {
						m.setPictures(ps.get(0).getImgPath());
					}
					
					HangPictureRatingExample ratingExample = new HangPictureRatingExample();
					ratingExample.or().andHangPictureIdEqualTo(m.getId());
					List<HangPictureRating> ratings = ratingMapper.selectByExample(ratingExample);
					if (ratings != null && ratings.size() > 0) {
						HangPictureRating rating = ratings.get(0);
						if (rating != null) {
						m.setComments(rating.getRatingLevel()+"星");
						}
					}
				}
			}
			
			
			
			dg.setRows(list);
			
			dg.setTotal(total);
			
			return dg;
		
	}

}
