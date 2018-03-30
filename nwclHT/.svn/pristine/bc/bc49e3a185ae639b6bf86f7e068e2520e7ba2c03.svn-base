package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.api.model.repair.RepairHangModel;
import com.bola.nwcl.api.model.repair.RepairMaintenanceModel;
import com.bola.nwcl.biz.RepairStaffManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.util.JPushUtil;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureCompleteImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceOfferMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceProspectingImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.HangPictureCompleteImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureCompleteImgExample;
import com.bola.nwcl.dal.mybatis.model.HangPictureImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureImgExample;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImgExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOffer;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOfferExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceProspectingImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceProspectingImgExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;

@Service
public class RepairStaffManagerImpl implements RepairStaffManager {

	@Autowired
	private HangPictureMapper hangPictureMapper;
	
	@Autowired
	private HangPictureImgMapper hangPictureImgMapper;
	
	@Autowired
	private HangPictureCompleteImgMapper hangPictureCompleteImgMapper;
	
	@Autowired
	private HangPictureRatingMapper hangPictureRatingMapper;
	
	
	@Autowired
	private MaintenanceMapper maintenanceMapper;
	
	@Autowired
	private MaintenanceImgMapper maintenanceImgMapper;
	
	@Autowired
	private MaintenanceProspectingImgMapper maintenanceProspectingImgMapper;
	
	@Autowired
	private MaintenanceOfferMapper maintenanceOfferMapper;
	
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	@Autowired
	private NotifySendMapper notifySendMapper;
	
	@Override
	public Page<RepairHangModel> getAllHang(int page, int rows, int type, long hangId) {
		PageRequest pager = new PageRequest(page - 1, rows);
		Map<String, Object> condition = new HashMap<String, Object>(4);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("hangId", hangId);
		condition.put("type", type);
		int count = hangPictureMapper.getRepairHangModelCount(condition);
		List<RepairHangModel> list = hangPictureMapper.getRepairHangModel(condition);
		for(RepairHangModel khm : list){
			HangPictureImgExample example_img = new HangPictureImgExample();
			example_img.or().andHangPictureIdEqualTo(khm.getId());
			List<HangPictureImg> imgs = hangPictureImgMapper.selectByExample(example_img);
			khm.setImgs(imgs);
		}
		Page<RepairHangModel> pageData = new PageImpl<RepairHangModel>(list, pager, count);
		return pageData;
	}

	@Override
	public RepairHangModel getHangDetail(long id) {
		Map<String, Object> condition = new HashMap<String, Object>(1);
		condition.put("id", id);
		RepairHangModel khm = hangPictureMapper.getRepairHangModel(condition).get(0);
		if(khm==null){
			throw new BusinessValidateException("改挂画单不存在");
		}
		HangPictureImgExample example_img = new HangPictureImgExample();
		example_img.or().andHangPictureIdEqualTo(khm.getId());
		List<HangPictureImg> imgs = hangPictureImgMapper.selectByExample(example_img);
		khm.setImgs(imgs);
		
		HangPictureCompleteImgExample example_img_complete = new HangPictureCompleteImgExample();
		example_img_complete.or().andHangPictureIdEqualTo(id);
		List<HangPictureCompleteImg> completeImgs = hangPictureCompleteImgMapper.selectByExample(example_img_complete);
		khm.setCompleteImgs(completeImgs);
		
		return khm;
	}

	@Override
	public void updateConfirmOrder(long hangId, long suserId, ServiceUser suser) {
		HangPicture hp = hangPictureMapper.selectByPrimaryKey(hangId);
		if(hp==null){
			throw new BusinessValidateException("该挂画单不存在");
		}
		if(hp.getHangId().longValue() != suserId){
			throw new BusinessValidateException("只能接自己的单子");
		}
		int s = hp.getStatus().intValue();
		if(s <= -1){
			throw new BusinessValidateException("挂画单已经取消");
		}
		if(s == 0){
			throw new BusinessValidateException("挂画单还未指派");
		}
		if(s >= 2){
			throw new BusinessValidateException("挂画单已经指派");
		}
		hp.setStatus(2);
		hangPictureMapper.updateByPrimaryKeySelective(hp);
		
		
		String notify_title = "尊敬的业主，您的挂画申请，挂画人员已经接单";
		String notify_detail = "订单号"+hp.getSerial()+"，维修人员将尽快上门为您服务，感谢您的支持，祝您生活愉快。";
		String notify_type = "3:"+hp.getId()+":"+hp.getStatus();
		long notify_buserId = hp.getBuserId();
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
	}
	
	@Override
	public void updatecancelHang(long hangId, long suserId, String reason, ServiceUser suser) {
		HangPicture hp = hangPictureMapper.selectByPrimaryKey(hangId);
		if(hp==null){
			throw new BusinessValidateException("该挂画单不存在");
		}
		if(hp.getHangId().longValue() != suserId){
			throw new BusinessValidateException("只能取消自己的单子");
		}
		int s = hp.getStatus().intValue();
		if(s >= 5){
			throw new BusinessValidateException("挂画单已完成");
		}
		hp.setStatus(-1);
		hp.setReason(reason);
		hangPictureMapper.updateByPrimaryKeySelective(hp);
		
		String notify_title = "尊敬的业主，您的挂画申请被取消";
		String notify_detail = "取消原因："+reason;
		String notify_type = "3:"+hp.getId()+":"+hp.getStatus();
		long notify_buserId = hp.getBuserId();
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
		
	}

	@Override
	public void updateCompleteHang(HttpServletRequest request, long hangId, 
			long suserId, String completeDescription, MultipartFile[] imgs, String saveDir, ServiceUser suser) throws Exception {
		HangPicture hp = hangPictureMapper.selectByPrimaryKey(hangId);
		if(hp==null){
			throw new BusinessValidateException("改挂画单不存在");
		}
		if(hp.getHangId().longValue() != suserId){
			throw new BusinessValidateException("只能完成自己的单子");
		}
		int s = hp.getStatus().intValue();
		if(s <= -1){
			throw new BusinessValidateException("挂画单已经取消");
		}
		if(s >= 6){
			throw new BusinessValidateException("挂画单已经完成");
		}
		if(s == 0){
			throw new BusinessValidateException("挂画单还未指派");
		}
		if(s < 2){
			throw new BusinessValidateException("还未接单");
		}
		hp.setStatus(5);
		hp.setCompleteDescription(completeDescription);
		hangPictureMapper.updateByPrimaryKeySelective(hp);
		
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				HangPictureCompleteImg himg = new HangPictureCompleteImg();
				himg.setHangPictureId(hangId);
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] saveFileName=FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);
				himg.setImgPath(saveFileName[0]);
				himg.setImgPathThumbnail(saveFileName[1]);
				hangPictureCompleteImgMapper.insert(himg);
			}
		}
		
		String notify_title = "尊敬的业主，您的挂画申请已经完成";
		String notify_detail = "挂画人员发表评价："+completeDescription;
		String notify_type = "3:"+hp.getId()+":"+hp.getStatus();
		long notify_buserId = hp.getBuserId();
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
		jpModel.setTitle(notify_title);
		jpModel.setContent(notify_detail);
		jpModel.setType(notify_type);
		JPushUtil.jPushAssign(alias, jpModel);
		
	}

	@Override
	public Page<RepairMaintenanceModel> getAllMaintenance(int page, int rows, int type, long repair_main_id) {
		PageRequest pager = new PageRequest(page - 1, rows);
		Map<String, Object> condition = new HashMap<String, Object>(4);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("repair_main_id", repair_main_id);
		condition.put("type", type);
		int count = maintenanceMapper.getRepairMaintenanceModelCount(condition);
		List<RepairMaintenanceModel> list = maintenanceMapper.getRepairMaintenanceModel(condition);
		for(RepairMaintenanceModel khm : list){
			MaintenanceImgExample example_img = new MaintenanceImgExample();
			example_img.or().andMaintenanceIdEqualTo(khm.getId());
			List<MaintenanceImg> imgs = maintenanceImgMapper.selectByExample(example_img);
			khm.setImgs(imgs);
		}
		Page<RepairMaintenanceModel> pageData = new PageImpl<RepairMaintenanceModel>(list, pager, count);
		return pageData;
	}

	@Override
	public RepairMaintenanceModel getMaintenanceDetail(long id) {
		Map<String, Object> condition = new HashMap<String, Object>(1);
		condition.put("id", id);
		RepairMaintenanceModel khm = maintenanceMapper.getRepairMaintenanceModel(condition).get(0);
		if(khm==null){
			throw new BusinessValidateException("该报修单不存在");
		}
		MaintenanceImgExample example_img = new MaintenanceImgExample();
		example_img.or().andMaintenanceIdEqualTo(khm.getId());
		List<MaintenanceImg> imgs = maintenanceImgMapper.selectByExample(example_img);
		khm.setImgs(imgs);
		
		MaintenanceProspectingImgExample example_img_prospecting = new MaintenanceProspectingImgExample();
		example_img_prospecting.or().andMaintenanceIdEqualTo(id);
		List<MaintenanceProspectingImg> imgs_prospecting = maintenanceProspectingImgMapper.selectByExample(example_img_prospecting);
		khm.setProspectingImgs(imgs_prospecting);
		
		MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
		example_offer.or().andMaintenanceIdEqualTo(id);
		List<MaintenanceOffer> offers = maintenanceOfferMapper.selectByExample(example_offer);
		khm.setOffers(offers);
		
		return khm;
	}

	@Override
	public void updateConfirmMaintenance(long maintenanceId, long suserId) {
		Maintenance m = maintenanceMapper.selectByPrimaryKey(maintenanceId);
		if(m==null){
			throw new BusinessValidateException("该维修单不存在");
		}
		if(m.getRepairMainId().longValue() != suserId){
			throw new BusinessValidateException("只能接自己的单子");
		}
		if(m.getStatus().intValue() >= 2){
			throw new BusinessValidateException("已经接过该维修单");
		}
		if(m.getStatus().intValue() <= -1){
			throw new BusinessValidateException("该维修单已经被取消");
		}
		if(m.getStatus().intValue() == 0){
			throw new BusinessValidateException("该维修单未指派");
		}
		m.setStatus(2);
		maintenanceMapper.updateByPrimaryKeySelective(m);
		
		String notify_title = "尊敬的业主，您的维修申请，维修人员已经受理";
		String notify_detail = "订单号"+m.getSerial()+"，维修人员将尽快上门为您服务，感谢您的支持，祝您生活愉快。";
		String notify_type = "4:"+m.getId()+":"+m.getStatus();
		long notify_buserId = m.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suserId);
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
		
		
	}

	@Override
	public void updateProspectingMaintenance(HttpServletRequest request,
			long maintenanceId, long suserId, String prospectingDescription,
			MultipartFile[] imgs, String saveDir) throws Exception {
		Maintenance m = maintenanceMapper.selectByPrimaryKey(maintenanceId);
		if(m==null){
			throw new BusinessValidateException("该报修单不存在");
		}
		if(m.getRepairMainId().longValue() != suserId){
			throw new BusinessValidateException("只能提交自己的单子");
		}
		int s = m.getStatus().intValue();
		if(s == 2){
			
		}else{
			if(s > 2){
				throw new BusinessValidateException("该报修单已经勘探过");
			}
			if(s <= -1){
				throw new BusinessValidateException("该维修单已经被取消");
			}
			if(s == 1){
				throw new BusinessValidateException("该维修单还未接单");
			}
		}
		m.setStatus(3);
		m.setProspectingDescription(prospectingDescription);
		maintenanceMapper.updateByPrimaryKeySelective(m);
		
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				MaintenanceProspectingImg himg = new MaintenanceProspectingImg();
				himg.setMaintenanceId(m.getId());
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] saveFileName=FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);
				himg.setImgPath(saveFileName[0]);
				himg.setImgPathThumbnail(saveFileName[1]);
				maintenanceProspectingImgMapper.insert(himg);
			}
		}
		
		String notify_title = "尊敬的业主，您的维修申请，维修人员已经勘察";
		String notify_detail = "订单号"+m.getSerial()+"，维修人员将尽快为您维修，感谢您的支持，祝您生活愉快。";
		String notify_type = "4:"+m.getId()+":"+m.getStatus();
		long notify_buserId = m.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suserId);
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
	}

	@Override
	public void updateOfferMaintenance(long maintenanceId, long suserId,
			String project) {
		Maintenance m = maintenanceMapper.selectByPrimaryKey(maintenanceId);
		if(m==null){
			throw new BusinessValidateException("该报修单不存在");
		}
		if(m.getRepairMainId().longValue() != suserId){
			throw new BusinessValidateException("只能提交自己的单子");
		}
		int s = m.getStatus().intValue();
		if(s == 3){
			
		}else{
			if(s > 3){
				throw new BusinessValidateException("该报修单已经报价");
			}
			if(s <= -1){
				throw new BusinessValidateException("该维修单已经被取消");
			}
			if(s == 1){
				throw new BusinessValidateException("该维修单还未接单");
			}
			if(s == 2){
				throw new BusinessValidateException("该维修单还未勘探");
			}
		}
		String[] pros = project.split(",");
		int total = 0;
		for(String str : pros){
			String[] temp = str.split(":");
			MaintenanceOffer offer = new MaintenanceOffer();
			offer.setMaintenanceId(maintenanceId);
			String project_name = temp[0];
			if(!StringUtils.hasText(project_name)){
				throw new BusinessValidateException("项目名字不能为空");
			}
			offer.setProjectName(temp[0]);
			int price = Integer.valueOf(temp[1]);
			if(price < 0 ){
				throw new BusinessValidateException("价格不能小于0");
			}
			total += price;
			offer.setProjectPrice(price);
			offer.setStatus(0);
			maintenanceOfferMapper.insert(offer);
		}
		if(total <= 0){
			m.setStatus(6);
		}else{
			m.setStatus(4);
		}
		maintenanceMapper.updateByPrimaryKeySelective(m);
		
		
		String notify_title = "尊敬的业主，您的维修申请，维修人员已经报价";
		String notify_detail = "订单号"+m.getSerial()+"，维修人员将尽快为您维修，感谢您的支持，祝您生活愉快。";
		String notify_type = "4:"+m.getId()+":"+m.getStatus();
		long notify_buserId = m.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suserId);
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
		
	}

	@Override
	public void updateCancelMaintenance(long maintenanceId, long suserId,
			String reason) {
		Maintenance m = maintenanceMapper.selectByPrimaryKey(maintenanceId);
		if(m==null){
			throw new BusinessValidateException("该报修单不存在");
		}
		if(m.getRepairMainId().longValue() != suserId){
			throw new BusinessValidateException("只能取消自己的单子");
		}
		if(m.getStatus().intValue() > 4){
			throw new BusinessValidateException("用户已经付款,必须维修");
		}
		m.setStatus(-1);
		m.setReason(reason);
		maintenanceMapper.updateByPrimaryKeySelective(m);
		
		String notify_title = "尊敬的业主，您的维修申请，维修人员已取消";
		String notify_detail = "订单号"+m.getSerial()+"，取消原因。"+reason;
		String notify_type = "4:"+m.getId()+":"+m.getStatus();
		long notify_buserId = m.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suserId);
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
	}

	@Override
	public void updateOffer(long suserId, long id, String projectName, String projectPrice) {
		MaintenanceOffer offer = maintenanceOfferMapper.selectByPrimaryKey(id);
		int price = 0; 
		if (StringUtils.hasText(projectPrice)) {
			try {
				price = Integer.valueOf(projectPrice);
				if(price < 0){
					throw new PatternException("维修单价格小于0");
				}
			} catch (Exception e) {
				throw new PatternException("维修单价格错误");
			}
		}
		if(offer == null){
			throw new BusinessValidateException("该维修项目不存在");
		}
		Maintenance m = maintenanceMapper.selectByPrimaryKey(offer.getMaintenanceId());
		if(m.getRepairMainId().longValue() != suserId){
			throw new BusinessValidateException("只能修改自己的维修单");
		}
		int s = m.getStatus().intValue();
		if(s == 4){
			
		}else{
			if(s > 4){
				throw new BusinessValidateException("该报修单已经支付");
			}
			if(s <= -1){
				throw new BusinessValidateException("该维修单已经被取消");
			}
			if(s == 1){
				throw new BusinessValidateException("该维修单还未接单");
			}
			if(s == 2){
				throw new BusinessValidateException("该维修单还未勘探");
			}
			if(s == 3){
				throw new BusinessValidateException("该维修单还未报价");
			}
		}
		if(StringUtils.hasText(projectPrice)){
			offer.setProjectPrice(price);
		}
		if(StringUtils.hasText(projectName)){
			offer.setProjectName(projectName);
		}
		maintenanceOfferMapper.updateByPrimaryKeySelective(offer);
		
		MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
		example_offer.or().andMaintenanceIdEqualTo(m.getId());
		List<MaintenanceOffer> offers = maintenanceOfferMapper.selectByExample(example_offer);
		int total = 0;
		for(MaintenanceOffer o : offers){
			total += o.getProjectPrice();
		}
		if(total <= 0){
			m.setStatus(6);
		}
	}

	@Override
	public void updateCompleteMaintenance(long maintenanceId, long suserId) {
		Maintenance m = maintenanceMapper.selectByPrimaryKey(maintenanceId);
		if(m==null){
			throw new BusinessValidateException("该报修单不存在");
		}
		if(m.getRepairMainId().longValue() != suserId){
			throw new BusinessValidateException("只能取消自己的单子");
		}
		int s = m.getStatus().intValue();
		if(s == 5){
			
		}else{
			if(s > 5){
				throw new BusinessValidateException("该报修单已经确认支付了");
			}
			if(s <= -1){
				throw new BusinessValidateException("该维修单已经被取消");
			}
			if(s == 1){
				throw new BusinessValidateException("该维修单还未接单");
			}
			if(s == 2){
				throw new BusinessValidateException("该维修单还未勘探");
			}
			if(s == 3){
				throw new BusinessValidateException("该维修单还未报价");
			}
			if(s == 4){
				throw new BusinessValidateException("该维修单还未支付");
			}
		}
		m.setStatus(6);
		maintenanceMapper.updateByPrimaryKeySelective(m);
		
		String notify_title = "尊敬的业主，您的维修申请，已经完成";
		String notify_detail = "订单号"+m.getSerial()+"，可以进行评价了。";
		String notify_type = "4:"+m.getId()+":"+m.getStatus();
		long notify_buserId = m.getBuserId();
		Notify notify = new Notify();
		notify.setTitle(notify_title);
		notify.setDetail(notify_detail);
		notify.setPublishPeopleId(suserId);
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
	}

}
