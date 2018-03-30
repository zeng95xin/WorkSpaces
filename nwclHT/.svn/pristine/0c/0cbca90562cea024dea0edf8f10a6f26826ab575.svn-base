package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.VisitorsStatisticsModel;
import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.api.model.security.ScanResultModel;
import com.bola.nwcl.api.model.security.SercurityArticleReleaseModel;
import com.bola.nwcl.api.model.security.SercurityVisitorsModel;
import com.bola.nwcl.biz.SecurityStaffManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.util.JPushUtil;
import com.bola.nwcl.dal.mybatis.mapper.ArticleReleaseImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.ArticleReleaseMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsMapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsStatisticsDetailMapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsStatisticsMapper;
import com.bola.nwcl.dal.mybatis.model.ArticleRelease;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseExample;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImg;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseImgExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.Visitors;
import com.bola.nwcl.dal.mybatis.model.VisitorsExample;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatistics;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetail;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetailExample;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsExample;

@Service
public class SecurityStaffManagerImpl implements SecurityStaffManager {
	
	@Autowired
	private VisitorsMapper visitorsMapper;
	
	@Autowired
	private VisitorsStatisticsMapper visitorsStatisticsMapper;
	
	@Autowired
	private VisitorsStatisticsDetailMapper visitorsStatisticsDetailMapper;
	
	@Autowired
	private ArticleReleaseMapper articleReleaseMapper;
	
	@Autowired
	private ArticleReleaseImgMapper articleReleaseImgMapper;
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	@Autowired
	private NotifySendMapper notifySendMapper;
	
	@Override
	public ScanResultModel updateScan(String type, String serial) {
		ScanResultModel model = new ScanResultModel();
		
		if(type.equals("FB")){
			VisitorsExample example_v = new VisitorsExample();
			example_v.or().andSerialEqualTo(serial);
			List<Visitors> vs = visitorsMapper.selectByExample(example_v);
			if(vs == null || vs.size()<1){
				throw new BusinessValidateException("没有该放行记录");
			}
			Visitors v = vs.get(0);
			Date now = new Date();
			if(v.getEffectiveArriveTime().getTime() > now.getTime()){
				throw new BusinessValidateException("有效时间还没有到");
			}
			if(v.getEffectiveLeaveTime().getTime() < now.getTime()){
				throw new BusinessValidateException("已过期");
			}
			
			VisitorsStatisticsExample example_vs = new VisitorsStatisticsExample();
			example_vs.or().andVisitorsIdEqualTo(v.getId());
			example_vs.setOrderByClause(" row_add_time DESC ");
			List<VisitorsStatistics> list_vs = visitorsStatisticsMapper.selectByExample(example_vs);
			
			List<VisitorsStatisticsModel> list_vs_model = new ArrayList<VisitorsStatisticsModel>();
			
			for(VisitorsStatistics temp : list_vs){
				VisitorsStatisticsModel temp_model = new VisitorsStatisticsModel();
				BeanUtils.copyProperties(temp, temp_model);
				VisitorsStatisticsDetailExample example_detail = new VisitorsStatisticsDetailExample();
				example_detail.or().andVisitorsStatisticsEqualTo(temp.getId());
				List<VisitorsStatisticsDetail> list_details = visitorsStatisticsDetailMapper.selectByExample(example_detail);
				temp_model.setDetails(list_details);
				list_vs_model.add(temp_model);
			}
			
			
			
			Map<String, Object> condition = new HashMap<String, Object>(1);
			condition.put("id", v.getId());
			SercurityVisitorsModel svm = visitorsMapper.getSercurityVisitorsModel(condition).get(0);
			
			svm.setStatistics(list_vs_model);
			
			model.setSercurityVisitorsModel(svm);
			
		}else if(type.equals("FXT")){
			ArticleRelease ar = null;
			ArticleReleaseExample example = new ArticleReleaseExample();
			example.or().andSerialEqualTo(serial);
			List<ArticleRelease> ars = articleReleaseMapper.selectByExample(example);
			if(ars == null || ars.size()<1){
				throw new BusinessValidateException("没有该放行条记录");
			}
			ar = ars.get(0);
			
			if(ar.getValidTime().getTime() < System.currentTimeMillis()){
				throw new BusinessValidateException("该放行条已经过期");
			}
			
			if(ar.getStatus() == 1){
				
			}
			
			articleReleaseMapper.updateByPrimaryKeySelective(ar);
			
			ArticleReleaseImgExample example_img = new ArticleReleaseImgExample();
			example_img.or().andArticleReleaseIdEqualTo(ar.getId());
			List<ArticleReleaseImg> imgs = articleReleaseImgMapper.selectByExample(example_img);
			
			Map<String, Object> condition = new HashMap<String, Object>(1);
			condition.put("id", ar.getId());
			SercurityArticleReleaseModel sarm = articleReleaseMapper.getSercurityArticleReleaseModel(condition).get(0);
			
			sarm.setImgs(imgs);
			
			model.setSercurityArticleReleaseModel(sarm);
		}else{
			throw new BusinessValidateException("放行二维码不存在");
		}
		
		return model;
	}

	@Override
	public Page<SercurityArticleReleaseModel> getAllArticleRelease(int page, int rows, int type) {
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = articleReleaseMapper.countByExample(null);
		Map<String, Object> condition = new HashMap<String, Object>(3);
		if(type == 1){
			condition.put("where", type);
		}
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		List<SercurityArticleReleaseModel> list = articleReleaseMapper.getSercurityArticleReleaseModel(condition);
		Page<SercurityArticleReleaseModel> pageData = new PageImpl<SercurityArticleReleaseModel>(list, pager, count);
		return pageData;
	}
	
	@Override
	public Page<SercurityVisitorsModel> getAllVisitors(int page, int rows, int type) {
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = visitorsMapper.countByExample(null);
		Map<String, Object> condition = new HashMap<String, Object>(3);
		if(type == 1){
			condition.put("where", type);
		}
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		List<SercurityVisitorsModel> list = visitorsMapper.getSercurityVisitorsModel(condition);
		Page<SercurityVisitorsModel> pageData = new PageImpl<SercurityVisitorsModel>(list, pager, count);
		return pageData;
	}

	@Override
	public SercurityVisitorsModel getVisitorsDetail(long id) {
		
		Visitors v = visitorsMapper.selectByPrimaryKey(id);
		if(v == null){
			throw new BusinessValidateException("该信息不存在");
		}
		
		Map<String, Object> condition = new HashMap<String, Object>(1);
		condition.put("id", id);
		
		VisitorsStatisticsExample example_vs = new VisitorsStatisticsExample();
		example_vs.or().andVisitorsIdEqualTo(id);
		example_vs.setOrderByClause(" row_add_time DESC ");
		
		List<VisitorsStatistics> list_vs = visitorsStatisticsMapper.selectByExample(example_vs);
		
		List<VisitorsStatisticsModel> list_vs_model = new ArrayList<VisitorsStatisticsModel>();
		
		for(VisitorsStatistics temp : list_vs){
			VisitorsStatisticsModel temp_model = new VisitorsStatisticsModel();
			BeanUtils.copyProperties(temp, temp_model);
			VisitorsStatisticsDetailExample example_detail = new VisitorsStatisticsDetailExample();
			example_detail.setOrderByClause(" row_add_time DESC ");
			example_detail.or().andVisitorsStatisticsEqualTo(temp.getId());
			List<VisitorsStatisticsDetail> list_details = visitorsStatisticsDetailMapper.selectByExample(example_detail);
			temp_model.setDetails(list_details);
			list_vs_model.add(temp_model);
		}
		
		SercurityVisitorsModel svm = visitorsMapper.getSercurityVisitorsModel(condition).get(0);
		svm.setStatistics(list_vs_model);
		return svm;
	}

	@Override
	public SercurityArticleReleaseModel getArticleReleaseDetail(long id) {
		
		ArticleRelease ar = articleReleaseMapper.selectByPrimaryKey(id);
		if(ar == null){
			throw new BusinessValidateException("该信息不存在");
		}
		
		ArticleReleaseImgExample example_img = new ArticleReleaseImgExample();
		example_img.or().andArticleReleaseIdEqualTo(id);
		List<ArticleReleaseImg> imgs = articleReleaseImgMapper.selectByExample(example_img);
		
		Map<String, Object> condition = new HashMap<String, Object>(1);
		condition.put("id", id);
		
		SercurityArticleReleaseModel sarm = articleReleaseMapper.getSercurityArticleReleaseModel(condition).get(0);
		
		sarm.setImgs(imgs);
		
		return sarm;
	}

	@Override
	public void updateRelease(String ids, String type, ServiceUser suser) {
		if(type.equals("FB")){
			
			String[] id_array = ids.split(",");
			String people = "";
			long notify_buserId = -1l;
			long notify_typeId = -1l;
			for (String idstr : id_array) {
				long id = Long.valueOf(idstr);
				VisitorsStatistics vs = visitorsStatisticsMapper
						.selectByPrimaryKey(id);
				if (vs == null) {
					throw new BusinessValidateException("没有该访客记录");
				}
				notify_typeId = vs.getVisitorsId();
				vs.setStatus(1);
				visitorsStatisticsMapper.updateByPrimaryKeySelective(vs);
				Visitors v = visitorsMapper.selectByPrimaryKey(vs
						.getVisitorsId());
				notify_buserId = v.getBuserId();
				VisitorsStatisticsDetail vsd = new VisitorsStatisticsDetail();
				vsd.setArriveTime(new Date());
				vsd.setLeaveTime(v.getExpectLeaveTime());
				vsd.setName(vs.getName());
				people += vs.getName();
				people += ",";
				vsd.setPhone(vs.getPhone());
				vsd.setVisitorsId(v.getId());
				vsd.setVisitorsStatistics(vs.getId());
				visitorsStatisticsDetailMapper.insert(vsd);
			}
			String notify_title = "尊敬的业主，您邀请的贵宾已成功进入小区。";
			String notify_detail = "尊敬的业主，您邀请的贵宾,"+people+",已成功进入小区。感谢您的支持，祝您生活愉快。";
			String notify_type = "5:"+notify_typeId+":-1";
			Notify notify = new Notify();
			notify.setTitle(notify_title);
			notify.setDetail(notify_detail);
			notify.setPublishPeopleId(suser.getId());
			notify.setStatus(1);
			notify.setSendCount(1);
			notify.setType(notify_type);
			notifyMapper.insertAndGetId(notify);
			NotifySend notify_send = new NotifySend();
			notify_send.setBuserId(notify_buserId);
			notify_send.setNotifyId(notify.getId());
			notify_send.setStatus(0);
			notifySendMapper.insert(notify_send);
			List<String> alias=new ArrayList<String>(1);
			alias.add(notify_buserId+"buser");
			JPushChatModel jpModel=new JPushChatModel();
			jpModel.setType(notify_type);
			jpModel.setTitle(notify_title);
			jpModel.setContent(notify_detail);
			JPushUtil.jPushAssign(alias, jpModel);
		}else if(type.equals("FXT")){
			long id = Long.valueOf(ids);
			ArticleRelease ar = articleReleaseMapper.selectByPrimaryKey(id);
			if(ar == null){
				throw new BusinessValidateException("没有该放行记录");
			}
			if(ar.getStatus().intValue() == 1){
				throw new BusinessValidateException("该放行条已经使用过");
			}
			ar.setReleaseTime(new Date());
			ar.setStatus(1);
			articleReleaseMapper.updateByPrimaryKeySelective(ar);
			
			long notify_buserId = ar.getBuserId();
			String notify_title = "尊敬的业主，物品已成功放行。";
			String notify_detail = "尊敬的业主，放行编号"+ar.getSerial()+"的物品已成功放行。感谢您的支持，祝您生活愉快。";
			String notify_type = "6:"+ar.getId()+":"+ar.getStatus();
			Notify notify = new Notify();
			notify.setTitle(notify_title);
			notify.setDetail(notify_detail);
			notify.setPublishPeopleId(suser.getId());
			notify.setStatus(1);
			notify.setSendCount(1);
			notify.setType(notify_type);
			notifyMapper.insertAndGetId(notify);
			NotifySend notify_send = new NotifySend();
			notify_send.setBuserId(notify_buserId);
			notify_send.setNotifyId(notify.getId());
			notify_send.setStatus(0);
			notifySendMapper.insert(notify_send);
			List<String> alias=new ArrayList<String>(1);
			alias.add(notify_buserId+"buser");
			JPushChatModel jpModel=new JPushChatModel();
			jpModel.setType(notify_type);
			jpModel.setTitle(notify_title);
			jpModel.setContent(notify_detail);
			JPushUtil.jPushAssign(alias, jpModel);
			
		}else{
			throw new PatternException("格式错误");
		}
	}
	
}
