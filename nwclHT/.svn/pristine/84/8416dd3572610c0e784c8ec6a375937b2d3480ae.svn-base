package com.bola.nwcl.biz.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bola.nwcl.biz.AnnouncementManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementMapper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementSendMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Announcement;
import com.bola.nwcl.dal.mybatis.model.AnnouncementExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.AnnouncementImgExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementRatingExample;
import com.bola.nwcl.dal.mybatis.model.AnnouncementSendExample;

@Service
public class AnnouncementManagerImpl  extends ManagerImpl<Announcement, AnnouncementExample, Long> implements AnnouncementManager{


	private AnnouncementMapper announcementMapper;
	@Autowired
	private AnnouncementImgMapper announcementImgMapper;
	@Autowired
	private AnnouncementSendMapper announcementSendMapper;
	@Autowired
	private AnnouncementRatingMapper announcementRatingMapper;

	@Autowired
	public AnnouncementManagerImpl(AnnouncementMapper announcementMapper) {
		this.mapper = announcementMapper;
		this.announcementMapper = announcementMapper;
	}

	@Override
	public DataGrid dataGrid(Announcement announcement, PageHelper ph,HttpServletRequest request) {

		DataGrid dg = new DataGrid();
		AnnouncementExample example=new AnnouncementExample();
		
		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		Criteria c=example.or();
		//判断是普通管理员还是admin
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		if(announcement!=null){
			
			//通过小区查询
			if(announcement.getCommunityId()!=null){
				c.andCommunityIdEqualTo(announcement.getCommunityId());
			}

			if(StringUtils.isNotBlank(announcement.getSname())&&
					StringUtils.isNotBlank(announcement.getSvalue())){
				if(announcement.getSname().equals("title")){					
					c.andTitleLike("%"+announcement.getSvalue()+"%");					
				}	
				if(announcement.getSname().equals("detail")){					
					c.andDetailLike("%"+announcement.getSvalue()+"%");
				}	
				if(announcement.getSname().equals("remark")){					
					c.andRemarkLike("%"+announcement.getRemark()+"%");
				}	
				/*if(announcement.getSname().equals("publishPeopleId")){					
					c.andRemarkLike(announcement.getRemark());
				}	*/
				if(announcement.getSname().equals("rowAddTime")){
					Date date = DateUtils.strToDate(announcement.getSvalue(), DateUtils.YYYY_MM_DD);
					Calendar ca = Calendar.getInstance();
					ca.setTime(date);
					ca.add(Calendar.DATE, 1);
					Date date2 = ca.getTime();
					c.andRowAddTimeBetween(date,date2);
				}	
			}

		}
		if(StringUtils.isNoneBlank(ph.getSort())){
			example.setOrderByClause(ph.getSort() + " " + ph.getOrder());
		}else{
			example.setOrderByClause("row_add_time DESC");
		}
		int total=announcementMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		List<Announcement> list=announcementMapper.selectByExample(example);
		dg.setRows(list);
		dg.setTotal(total);

		return dg;
	}

	@Override
	public void insertAndGetId(Announcement announcement) {
		announcementMapper.insertAndGetId(announcement);
	}

	@Override
	@Transactional
	public void deleteAnnouncement(Long id){
		AnnouncementImgExample imgExample = new AnnouncementImgExample();
		imgExample.or().andAnnouncementIdEqualTo(id);
		announcementImgMapper.deleteByExample(imgExample);

		AnnouncementSendExample sendExample = new AnnouncementSendExample();
		sendExample.or().andAnnouncementIdEqualTo(id);
		announcementSendMapper.deleteByExample(sendExample);

		AnnouncementRatingExample ratingExample = new AnnouncementRatingExample();
		ratingExample.or().andAnnouncementIdEqualTo(id);
		announcementRatingMapper.deleteByExample(ratingExample);

		announcementMapper.deleteByPrimaryKey(id);
	}
}
