package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.api.model.keeper.KeeperHangModel;
import com.bola.nwcl.biz.KeeperHangManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.util.JPushUtil;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureMapper;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifyMapper;
import com.bola.nwcl.dal.mybatis.mapper.NotifySendMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.HangPictureImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureImgExample;
import com.bola.nwcl.dal.mybatis.model.HangPictureRatingExample;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.NotifySend;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;

@Service
public class KeeperHangManagerImpl implements KeeperHangManager {
	
	@Autowired
	private HangPictureMapper hangPictureMapper;
	
	@Autowired
	private HangPictureImgMapper hangPictureImgMapper;
	
	@Autowired
	private HangPictureRatingMapper hangPictureRatingMapper;
	
	@Autowired
	private ServiceUserMapper serviceUserMapper;
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	@Autowired
	private NotifySendMapper notifySendMapper;

	@Override
	public Page<KeeperHangModel> getAllHang(int page, int rows, int type) {
		PageRequest pager = new PageRequest(page - 1, rows);
		Map<String, Object> condition = new HashMap<String, Object>(3);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("type", type);
		int count = hangPictureMapper.getKeeperHangModelCount(condition);
		List<KeeperHangModel> list = hangPictureMapper.getKeeperHangModel(condition);
		for(KeeperHangModel khm : list){
			HangPictureImgExample example_img = new HangPictureImgExample();
			example_img.or().andHangPictureIdEqualTo(khm.getId());
			List<HangPictureImg> imgs = hangPictureImgMapper.selectByExample(example_img);
			khm.setImgs(imgs);
		}
		Page<KeeperHangModel> pageData = new PageImpl<KeeperHangModel>(list, pager, count);
		return pageData;
	}

	@Override
	public KeeperHangModel getHangDetail(long id) {
		Map<String, Object> condition = new HashMap<String, Object>(1);
		condition.put("id", id);
		KeeperHangModel khm = hangPictureMapper.getKeeperHangModel(condition).get(0);
		if(khm==null){
			throw new BusinessValidateException("改挂画单不存在");
		}
		HangPictureImgExample example_img = new HangPictureImgExample();
		example_img.or().andHangPictureIdEqualTo(khm.getId());
		List<HangPictureImg> imgs = hangPictureImgMapper.selectByExample(example_img);
		khm.setImgs(imgs);
		return khm;
	}

	@Override
	public void updateAssignRepairmain(long hangId, long repairmainId, ServiceUser suser) {
		if(serviceUserMapper.selectByPrimaryKey(repairmainId)==null){
			throw new BusinessValidateException("挂画师傅不存在");
		}
		HangPicture hp = hangPictureMapper.selectByPrimaryKey(hangId);
		if(hp == null){
			throw new BusinessValidateException("挂画单不存在");
		}
		int s = hp.getStatus().intValue();
		if(s <= -1){
			throw new BusinessValidateException("挂画单已经取消");
		}
		if(s >= 1){
			throw new BusinessValidateException("挂画单已经指派");
		}
		hp.setAssignTime(new Date());
		hp.setHangId(repairmainId);
		hp.setStatus(1);
		hangPictureMapper.updateByPrimaryKeySelective(hp);
		
		
		String notify_title = "尊敬的业主，您的挂画申请已受理";
		String notify_detail = "订单号"+hp.getSerial()+"，维修人员将尽快上门为您服务，感谢您的支持，祝您生活愉快。";
		long notify_buserId = hp.getBuserId();
		String notify_type = "3:"+hangId+":"+hp.getStatus();
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
	public void deleteHang(long id) {
		HangPicture hp = hangPictureMapper.selectByPrimaryKey(id);
		if(hp == null){
			throw new BusinessValidateException("挂画单不存在");
		}
		HangPictureImgExample example_img = new HangPictureImgExample();
		example_img.or().andHangPictureIdEqualTo(id);
		List<HangPictureImg> imgs = hangPictureImgMapper.selectByExample(example_img);
		for(HangPictureImg img : imgs){
			FileUploadDeleteUtil.delete(img.getImgPath());
			FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
		}
		hangPictureImgMapper.deleteByExample(example_img);
		HangPictureRatingExample example_rating = new HangPictureRatingExample();
		example_rating.or().andHangPictureIdEqualTo(id);
		hangPictureRatingMapper.deleteByExample(example_rating);
		hangPictureMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateCancelHang(long id, String reason, ServiceUser suser) {
		HangPicture hp = hangPictureMapper.selectByPrimaryKey(id);
		if(hp == null){
			throw new BusinessValidateException("挂画单不存在");
		}
		if(hp.getStatus().intValue() <= -1){
			throw new BusinessValidateException("挂画单已经取消");
		}
		if(hp.getStatus().intValue() > 1){
			throw new BusinessValidateException("挂画单已经指派,请联系挂画师傅取消");
		}
		hp.setReason(reason);
		hp.setStatus(-1);
		hangPictureMapper.updateByPrimaryKeySelective(hp);
		
		
		String notify_title = "尊敬的业主，您的挂画申请被取消";
		String notify_detail = "取消原因:"+reason;
		long notify_buserId = hp.getBuserId();
		String notify_type = "3:"+id+":"+hp.getStatus();
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
	
}
