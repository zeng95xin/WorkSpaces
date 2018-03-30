package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.biz.ActivityManager;
import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.common.util.ValidateUtil;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.notify.JPushUtil_Buser;
import com.bola.nwcl.common.util.notify.NotifyUtil;
import com.bola.nwcl.dal.mybatis.mapper.ActivityMapper;
import com.bola.nwcl.dal.mybatis.mapper.IndexesImgMapper;
import com.bola.nwcl.dal.mybatis.model.Activity;
import com.bola.nwcl.dal.mybatis.model.ActivityExample;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.IndexesImg;
import com.bola.nwcl.dal.mybatis.model.IndexesImgExample;
import com.bola.nwcl.web.model.Activitys;

@Service
public class ActivityManagerImpl extends
		ManagerImpl<Activity, ActivityExample, Long> implements ActivityManager {

	@SuppressWarnings("unused")
	private ActivityMapper activityMapper;

	@Autowired
	NotifyUtil notifyUtil;
	@Autowired
	BizUserManager bizUserManager;
	@Autowired private IndexesImgMapper indexesImgMapper;

	@Autowired
	public ActivityManagerImpl(ActivityMapper activityMapper) {
		this.mapper = activityMapper;
		this.activityMapper = activityMapper;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertGenId(HttpServletRequest request, Activitys activity,
			MultipartFile file, String push, String pushContent, String recommended)
			throws Exception {
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");

		String vu = ValidateUtil.checkExcept(activity);
		if (StringUtils.isNotEmpty(vu)) {
			throw new PatternException(vu);
		}
		// 定义一个 图片相对项目放的位置
		Activity model = new Activity();
		
		String savePath;
		if (file != null && !file.isEmpty()) {
			String uploadPath = "/upload" + "/files/activity";
			String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String fileName = UUID.randomUUID().toString().substring(0, 12);
			fileName = todayStr + fileName;
			savePath = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
			model.setImgPath(savePath);
		}

		BeanUtils.copyProperties(activity, model);
		model.setCommuntiyId(activity.getCommunity());
		model.setPublishId(user.getId());
		activityMapper.insertGenId(model);

		if ("1".equals(push)) {

			Long connunityId;
			if (user.getRoleId() == 1) {
				connunityId = activity.getCommunity();
			} else {
				connunityId = user.getCommunityId();
			}

			push(connunityId, pushContent, model.getId(), user.getId(), model.getType());

		}
		
		if("1".equals(recommended)){
			IndexesImg img = new IndexesImg();
			img.setCommunityId(activity.getCommunity());
			img.setAppTitle(model.getTitle());
			img.setUrl("app:2:"+model.getId());
			img.setType(5);
			img.setImgPath(model.getImgPath());
			img.setImgPathThumbnail(model.getImgPath());
			indexesImgMapper.insert(img);
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void edit(HttpServletRequest request, Activitys activity,
			MultipartFile file, String push, String recommended, String pushContent)
			throws Exception {
		//得到用户对象
		Admin user = (Admin)request.getSession().getAttribute("loginUser");

		String vu=ValidateUtil.checkExcept(activity);
		if (StringUtils.isNotEmpty(vu)) {
			throw new PatternException(vu);
		}
		Activity model=new Activity();
		BeanUtils.copyProperties(activity, model);
		if(file != null&&!file.isEmpty()){
			//定义一个 图片相对项目放的位置
			String uploadPath =  "/upload" + "/files/activity";
			String todayStr = new SimpleDateFormat("yyyyMMdd")
			.format(new Date());
			String fileName = UUID.randomUUID().toString()
					.substring(0, 12);
			fileName = todayStr + fileName;
			String savePath = FileUploadDeleteUtil.upload(file, request, uploadPath, fileName);
			//删除原来的图片
			Activity ac=activityMapper.selectByPrimaryKey(activity.getId());
			if(ac.getImgPath() != null){
				FileManager.deleteFile(ac.getImgPath());
			}
			model.setImgPath(savePath);
		}else{
			model.setImgPath(activity.getImgPath());
		}
		model.setCommuntiyId(activity.getCommunity());
		model.setPublishId(user.getId());
		activityMapper.updateByPrimaryKeySelective(model);
		
		if ("1".equals(push)) {
			Long connunityId;
			if (user.getRoleId() == 1) {
				connunityId = activity.getCommunity();
			} else {
				connunityId = user.getCommunityId();
			}
			push(connunityId, pushContent, activity.getId(), user.getId(), activity.getType());
		}
		
		if("1".equals(recommended)){
			IndexesImg img = new IndexesImg();
			img.setCommunityId(activity.getCommunity());
			String url = "app:2:"+model.getId();
			IndexesImgExample example_index_img = new IndexesImgExample();
			example_index_img.or().andUrlEqualTo(url);
			if(indexesImgMapper.countByExample(example_index_img) < 1){
				img.setAppTitle(model.getTitle());
				img.setUrl("app:2:"+model.getId());
				img.setType(5);
				img.setImgPath(model.getImgPath());
				img.setImgPathThumbnail(model.getImgPath());
				indexesImgMapper.insert(img);
			}
		}
		
	}
	
	private void push(Long connunityId,String pushContent,Long id,Long userId, Integer type) {
		BizUserExample bizUserExample = new BizUserExample();
		bizUserExample.or().andCommunityIdEqualTo(connunityId);
		List<BizUser> bizUser = bizUserManager.selectByExample(bizUserExample);
		// 消息
		if(type == null){
			type = -1;
		}
		notifyUtil.send("社区文化提示", pushContent, "8:" + id+ ":" + type, userId, bizUser, null);
		
		// 推送
		JPushChatModel jPushChatModel = new JPushChatModel();
		jPushChatModel.setType("8:" + id + ":" + type);
		jPushChatModel.setTitle("社区文化提示");
		jPushChatModel.setContent(pushContent);
		JPushUtil_Buser.sendToBuser(jPushChatModel, bizUser);
		
	}

}
